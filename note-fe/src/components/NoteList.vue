<template>

<div class="container">
  <h2>Note List</h2>          
  <table class="table table-striped">
    <thead>
      <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Description</th>
        <th>Note Type</th>
        <th>Processing</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, index) in notes" :key="index">
        <td>{{item.id}}</td>
        <td>{{item.title}}</td>
        <td>{{item.description}}</td>
        <td>{{item.noteType}}</td>
        <td>
          <div v-if="item.completed" class="text-primary">
            Completed
          </div>
          <div v-else >
            Uncompleted
          </div>
        </td>
         <td>
          <router-link :to="'/notes/' + item.id" class="m-3 btn btn-sm btn-info">Edit</router-link>
          <button border:none
            @click="deleteNote(item.id)"
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
import NoteService from "../services/NoteService";

export default {
  name: "list-note",
  data() {
    return {
      notes: []
    };
  },
  mounted() {
      this.getAllNotes();
  },
  methods: {
    getAllNotes() {
      let data = {sortBy: { id: "desc" , name: 'title'}};
      NoteService.getNotes(data)
        .then(response => {
          let responseData = response.data;
          if (responseData !== null && responseData.data != null) {
            this.notes = responseData.data.data;
          }
          console.log(this.notes)
        })
        .catch(e => {
          console.error(e);
        });
    },

    deleteNote(id) {
      if(confirm("Do you really want to delete?")){
        NoteService.deleteNote(id)
          .then(response => {
            console.log(response.data);
            this.getAllNotes();
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
