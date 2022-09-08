import http from "../http-common";
class NoteService {
  getNotes(data) {
    return http.post("/notes/list?page=0&pageSize=50", data);
  }
  
  getNoteById(id) {
    return http.get("/notes/" + id);
  }

  create(data) {
    return http.post("/notes", data);
  }

  update(id, data) {
    return http.put("/notes/" + id, data);
  }

  deleteNote(id) {
    return http.delete("/notes/" + id);
  }
}
export default new NoteService();
