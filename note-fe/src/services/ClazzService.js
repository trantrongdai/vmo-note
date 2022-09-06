import http from "../http-common";

class ClazzService {
    getAll() {
        return http.get("/clazz/list-all");
    }
}

export default new ClazzService();