<template>
<form @submit.prevent="saveNote">
    <div class="submit-form">
      <div v-if="!submitted">
        <div class="form-group">
          <label for="title">Note name</label>
          <input
            type="text"
            class="form-control"
            id="title"
            required
            maxlength="100"
            v-model="note.title"
            placeholder="Note name"
            name="name"
          />
        </div>

        <div class="form-group Note-form">
          <label for="description">Description</label>
          <input
            class="form-control"
            id="description"
            required
            maxlength="100"
            v-model="note.description"
            placeholder="Note name"
            name="description"
          />
        </div>
        <br>

        <div class="form-group Note-form">
          <label for="description">Processing...</label><br>
          <input type="checkbox" :value="note.completed" v-model="note.completed"/>
          <label> Completed </label>
        </div>
        <br>

        <button type="submit" class="btn btn-success" style="margin-top:20px">Submit</button>
      </div>

      <div v-else>
        <h4>You submitted successfully!</h4>
        <button class="btn btn-success" style="margin-top:20px" @click="newNote">Add</button>
      </div>
    </div>
  </form>
</template>

<script>
import NoteService from "../services/NoteService";

export default {
  name: "AddBasicNote",
  data() {
    return {
      note: {
        title: "",
        description: "",
        noteType: "BASIC_NOTE",
        imageUrl: "",
        checkBoxes : [],
        completed: false
      },
      selectedClazzId: null,
      clazzes: {},
      submitted: false
    };
  },
  mounted() {
  },
  methods: {
    saveNote() {
      var data = {
        title: this.note.title,
        description: this.note.description,
        noteType: "BASIC_NOTE",
        completed: this.note.completed
      };

      NoteService.create(data)
        .then(response => {
          console.log("request" + data);
          this.note.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newNote() {
      this.submitted = false;
      this.note = {};
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