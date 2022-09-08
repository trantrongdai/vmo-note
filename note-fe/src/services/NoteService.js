import http from "../http-common";
class NoteService {
  getNotes(data) {
    return http.post("/note/list?page=0&pageSize=50", data);
  }
  
  getNoteById(id) {
    return http.get("/note/" + id);
  }

  create(data) {
    return http.post("/note", data);
  }

  update(id, data) {
    return http.put("/note/" + id, data);
  }

  deleteNote(id) {
    return http.delete("/note/" + id);
  }
}
export default new NoteService();
