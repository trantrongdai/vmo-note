<template>

<div class="container">
  <h2>Student List</h2>          
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Class</th>
        <th>Roll Number</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in students" :key="index">
        <td>{{item.id}}</td>
        <td>{{item.name}}</td>
        <td>{{item.clazz.name}}</td>
         <td>{{item.rollNumber}}</td>
         <td>
          <router-link :to="'/student/' + item.id" class="m-3 btn btn-sm btn-info">Edit</router-link>
          <button border:none
            @click="deleteStudent(item.id)"
            id="btn-delete"
            class="align-middle">
          >
          </button>
        
        </td>
      </tr>
    </tbody>
  </table>
</div>
</template>

<script>
import StudentService from "../services/StudentService";

export default {
  name: "add-student",
  data() {
    return {
      students: []
    };
  },
  mounted() {
      this.getAllStudents();
  },
  methods: {
    getAllStudents() {
      let data = {sortBy: { rollNumber: 'asc', name: 'asc', id: "asc" }};
      StudentService.getStudents(data)
        .then(response => {
          let responseData = response.data;
          if (responseData !== null && responseData.data != null) {
            this.students = responseData.data.data;
          }
          console.log(this.students)
        })
        .catch(e => {
          console.error(e);
        });
    },

    deleteStudent(id) {
      if(confirm("Do you really want to delete?")){
        StudentService.deleteStudent(id)
          .then(response => {
            console.log(response.data);
            this.getAllStudents();
          })
          .catch(e => {
            console.log(e);
          });
      }
    }
  }
};
</script>
<style scoped>
#btn-delete {
    width:30px;
    height:30px;
    border: none;
    background: transparent;
    background-image: url(https://img.icons8.com/pastel-glyph/30/fa314a/trash.png);
    background-repeat: no-repeat;
}
</style>
