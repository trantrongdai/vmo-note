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

         <li v-for="(item, index) in note.checkBoxes" :key="index">
          <input class="mx-1" type="checkbox" :value="item.value" v-model="item.selected"/>
          <label>{{item.name}}</label>
        </li>
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
        completed: false,
        checkBoxes: [
            {
                name: "checkbox1",
                value: "value",
                selected: false
            },
            {
                name: "checkbox2",
                value: "value",
                selected: false
            },
            {
                name: "checkbox3",
                value: "value",
                selected: false
            }
        ]
      },
      submitted: false
    };
  },
  mounted() {
    
    console.log("checkBoxes" + this.checkBoxes);
  },
  methods: {
    addCheckBox() {
      this.checkBoxes.push({
                "name": "checkbox3",
                "value": "value",
                "selected": true
            })
    },
    saveNote() {
      var data = {
        title: this.note.title,
        description: this.note.description,
        noteType: "CHECKBOX_NOTE",
        completed: this.note.completed,
        checkBoxes : this.note.checkBoxes
      };

      NoteService.create(data)
        .then(response => {
          console.log("request" + data);
          this.note.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
          this.$router.push('/list-note')
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