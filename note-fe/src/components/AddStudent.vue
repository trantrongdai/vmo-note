<template>
<form @submit.prevent="saveStudent">
    <div class="submit-form">
      <div v-if="!submitted">
        <div class="form-group">
          <label for="title">Student name</label>
          <input
            type="text"
            class="form-control"
            id="name"
            required
            maxlength="100"
            v-model="student.name"
            placeholder="Student name"
            name="name"
          />
        </div>

        <div class="form-group">
          <label for="description">Class</label> <br>
          <select class="form-select" v-model="selectedClazzId" @change="changeStudent($event)" required>
            <option disabled value="">Please select one</option>
            <option v-for="clazz in clazzes"  v-bind:value="clazz.id" v-bind:key="clazz.name">{{ clazz.name }}</option> 
          </select>
        </div>
        <button type="submit" class="btn btn-success" style="margin-top:20px">Submit</button>
      </div>

      <div v-else>
        <h4>You submitted successfully!</h4>
        <button class="btn btn-success" style="margin-top:20px" @click="newStudent">Add</button>
      </div>
    </div>
  </form>
</template>

<script>
import StudentService from "../services/StudentService";
import ClazzService from "../services/ClazzService"

export default {
  name: "AddStudent",
  data() {
    return {
      student: {
        id: null,
        name: ""
      },
      selectedClazzId: null,
      clazzes: {},
      submitted: false
    };
  },
  mounted() {
    this.getAllClazzes();
  },
  methods: {
    getAllClazzes() {
      ClazzService.getAll()
        .then(response => {
          let responseData = response.data;
          if (responseData !== null && responseData.data != null) {
            this.clazzes = responseData.data;
          }
          console.log("clazzes" + this.clazzes);
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveStudent() {
      var data = {
        name: this.student.name,
        clazzId: this.selectedClazzId
      };

      StudentService.create(data)
        .then(response => {
          console.log("request" + data);
          this.student.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newStudent() {
      this.submitted = false;
      this.student = {};
    }

  }
};
</script>

<style>
.submit-form {
  max-width: 500px;
  margin: auto;
}
</style>