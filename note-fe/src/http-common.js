import axios from "axios";
export default axios.create({
  baseURL: "http://localhost:8080/api/v1",
  headers: {
    "Content-type": "application/json",
    "Authorization": "Bearer " + "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjYyNjAzNjUwLCJleHAiOjE2NjMyMDg0NTB9.UlIxBM1jz75BUnuZRbK9E4WI_ql8QdjJrhxRaiRZZHpH-wtYmoKjGtOry-M9vICguk90XBufyF5B5iPKXh-OgA",
  },

  // token_user_2 
  // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIyIiwiaWF0IjoxNjYyNjAzNjUwLCJleHAiOjE2NjMyMDg0NTB9.UlIxBM1jz75BUnuZRbK9E4WI_ql8QdjJrhxRaiRZZHpH-wtYmoKjGtOry-M9vICguk90XBufyF5B5iPKXh-OgA
});
