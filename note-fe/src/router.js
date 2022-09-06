import { createWebHistory, createRouter } from "vue-router";
const routes = [
  {
    path: "/list",
    alias: "/list",
    name: "list student",
    component: () => import("./components/StudentList"),
  },
  {
    path: "/student/:id",
    name: "student-details",
    component: () => import("./components/StudentDetail")
  },
  {
    path: "/add",
    alias: "/add",
    name: "add student",
    component: () => import("./components/AddStudent"),
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});
export default router;
