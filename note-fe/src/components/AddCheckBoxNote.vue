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

        <div class="form-group Note-form">
         <label for="description">Note options</label>
          <button type="button" on-click="addCheckBox()" class="btn btn-success" style="margin-top:20px">Add options</button>
          <div id="checkboxes" v-for="(checkbox, index) in checkboxes" v-bind:key="index" class="m-2">
          <input class="mx-1" type="checkbox" :value="checkbox.value" v-model="checkbox.selected"/>
          <label>{{checkbox.name}}</label>
        </div>
      </div>

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
// import checkBoxData from '../assets/checkboxes.json';

export default {
  name: "AddCheckBoxNote",
  data() {
    return {
      note: {
        title: "",
        description: "",
        noteType: "CHECKBOX_NOTE",
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
    
    console.log(this.checkBoxes);
  },
  methods: {
    addCheckBox() {
this.checkboxes = [{
                name: "checkbox3",
                value: "value",
                selected: true
            }]
    },
    saveNote() {
      var data = {
        title: this.note.title,
        description: this.note.description,
        noteType: "CHECKBOX_NOTE",
        completed: this.note.completed,
        checkBoxes : this.checkboxes
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