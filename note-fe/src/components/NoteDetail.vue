<template>
  <div v-if="currentNote != null" class="edit-form">
    <h2 class="text-primary">{{ message }}</h2>
    <h4>Note</h4>
    <form id="edit-form" @submit.prevent="updateNote">
      <div class="form-group Note-form">
        <label for="description">Titlte</label>
        <input
          class="form-control"
          id="title"
          required
          maxlength="100"
          v-model="currentNote.title"
          placeholder="Note name"
          name="title"
        />
      </div>
      <br>

      <div class="form-group Note-form">
        <label for="description">Description</label>
        <input
          class="form-control"
          id="description"
          required
          maxlength="100"
          v-model="currentNote.description"
          placeholder="Note name"
          name="description"
        />
      </div>
      <br>

      <div class="form-group Note-form">
        <label for="description">Processing...</label><br>
        <input type="checkbox" :value="currentNote.completed" v-model="currentNote.completed"/>
        <label> Completed </label>
      </div>
      <br>

      <div v-if="currentNote.noteType == 'IMAGE_NOTE'" class="form-group Note-form">
        <label for="description">Image</label>
        <input
          class="form-control"
          id="imageUrl"
          required
          maxlength="100"
          v-model="currentNote.imageUrl"
          placeholder="Note name"
          name="imageUrl"
        />
      </div>

      <div v-if="currentNote.noteType == 'CHECKBOX_NOTE'" class="form-group Note-form">
         <label for="description">Note options</label>
          <div id="checkboxes" v-for="(checkbox, index) in checkboxes" v-bind:key="index" class="m-2">
          <input class="mx-1" type="checkbox" :value="checkbox.value" v-model="checkbox.selected"/>
          <label>{{checkbox.name}}</label>
        </div>
      </div>

      <div class="form-group Note-form edit-form-button" style="margin-top:20px">
        <button type="submit" class="badge btn-success custom-btn" style="margin-left:3px">
          Update
        </button>
      </div>
    </form>
  </div>
  <div v-else>
    <br />
    <p>Please click on a Note...</p>
  </div>
</template>

<script>
import NoteService from "../services/NoteService";

export default {
  name: "NoteDetail",
  data() {
    return {
      currentNote: null,
      checkboxes: [],
      message: '',
    };
  },
  mounted() {
    this.message = '';
    this.getNote(this.$route.params.id);
  },

  methods: {
    onCheck(val) {
      this.selected = val;
    },

    showDropdown() {
      this.show = !this.show
    },

    getNote(id) {
      NoteService.getNoteById(id)
        .then(response => {
          console.log(JSON.stringify(response));
          this.currentNote = response.data.data.noteDto;
          this.checkboxes = this.currentNote.checkBoxes;
          console.log("currentNote" + JSON.stringify(this.currentNote))
          console.log("checkboxes" + JSON.stringify(this.checkboxes))
        })
        .catch(e => {
          console.log(e);
        });
    },

    updateNote() {
      console.log('update')
      var data = {
        title: this.currentNote.title,
        description: this.currentNote.description,
        noteType: this.currentNote.noteType,
        imageUrl: this.currentNote.imageUrl,
        checkBoxes : this.checkboxes,
        completed: this.currentNote.completed
      };
      NoteService.update(this.currentNote.id, data)
        .then(response => {
          console.log(response.data);
          this.message = 'The Note was updated successfully!';
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

.Note-form {
  margin-bottom: 2px;
}

.custom-btn {
  font-size: 14px;
}

.edit-form-button {
  margin-top: 15px;
}

.col {
  flex: 0 0 50%;
  max-width: 50%;
  padding-left: 1rem;
  padding-right: 1rem;
}


/*****
- MultiSelect 
*****/

.dropdown { 
  position: relative; 
  cursor: pointer;
}

.multiselect {
  position: relative;
  
  ul {
    border: 1px solid #ddd;
    border-top: 0;
    border-radius: 0 0 3px 3px;
    left: 0px;
    padding: 8px 8px;
    position: absolute;
    top: -1rem;
    width: 100%;
    list-style: none;
    max-height: 150px;
    overflow: auto;
  }
}

.overselect {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
}
</style>
