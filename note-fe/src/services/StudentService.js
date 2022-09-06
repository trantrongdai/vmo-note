import http from "../http-common";
class StudentService {
  getStudents(data) {
    return http.post("/student/list?page=0&pageSize=50", data);
  }
  
  getStudentById(id) {
    return http.get("/student/" + id);
  }

  create(data) {
    return http.post("/student", data);
  }

  update(id, data) {
    return http.put("/student/" + id, data);
  }

  deleteStudent(id) {
    return http.delete("/student/" + id);
  }
}
export default new StudentService();
