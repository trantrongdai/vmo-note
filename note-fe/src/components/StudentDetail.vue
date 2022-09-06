<template>
  <div v-if="currentStudent != null" class="edit-form">
    <h4>Student</h4>
    <form id="edit-form" @submit.prevent="updateStudent">
      <div class="form-group Student-form">
        <label for="description">Name</label>
        <input
          class="form-control"
          id="name"
          required
          maxlength="100"
          v-model="currentStudent.name"
          placeholder="Student name"
          name="name"
        />
      </div>

      <div class="form-group Student-form" style="margin-top:10px">
        <label for="description">Class</label> <br>
        <select class="form-select" v-model="selectedClazzId" @change="changeClazz($event)" required>
          <option disabled value="">Please select one</option>
          <option v-for="clazz in clazzes"  v-bind:value="clazz.id" v-bind:key="clazz.name">{{ clazz.name }}</option> 
        </select>
      </div>

      <div class="form-group Student-form edit-form-button" style="margin-top:20px">
        <button type="submit" class="badge btn-success custom-btn" style="margin-left:3px">
          Update
        </button>
      </div>
    </form>
    <p>{{ message }}</p>
  </div>
  <div v-else>
    <br />
    <p>Please click on a Student...</p>
  </div>
</template>

<script>
import StudentService from "../services/StudentService";
import ClazzService from "../services/ClazzService";

export default {
  name: "StudentDetail",
  data() {
    return {
      currentStudent: null,
      message: '',
      selectedClazzId: null,
      clazzes: null,
    };
  },
  mounted() {
    this.message = '';
    this.getAllClass();
    this.getStudent(this.$route.params.id);
  },

  methods: {
    getStudent(id) {
      StudentService.getStudentById(id)
        .then(response => {
          this.currentStudent = response.data.data.studentDto;
          console.log("currentStudent" + JSON.stringify(this.currentStudent))
          this.selectedClazzId = this.currentStudent.clazz.id;
          console.log("selectedClazzId" + this.selectedClazzId);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getAllClass() {
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

    changeClazz(event) {
      this.selectedClazzId = event.target.value;
    },

    updateStudent() {
      console.log('update')
      var data = {
        name: this.currentStudent.name,
        clazzId: this.selectedClazzId
      };
      StudentService.update(this.currentStudent.id, data)
        .then(response => {
          console.log(response.data);
          this.message = 'The Student was updated successfully!';
        })
        .catch(e => {
          console.log(e);
          this.message = null;
        });
    }
  }
};
</script>

<style>
.edit-form {
  max-width: 500px;
  margin: auto;
}

#edit-form {
  margin-bottom: 15px;
}

.Student-form {
  margin-bottom: 2px;
}

.custom-btn {
  font-size: 14px;
}

.edit-form-button {
  margin-top: 15px;
}
</style>
