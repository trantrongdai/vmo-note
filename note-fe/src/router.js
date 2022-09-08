import { createWebHistory, createRouter } from "vue-router";
const routes = [
  {
    path: "/list-note",
    alias: "/list-note",
    name: "list note",
    component: () => import("./components/NoteList"),
  },
  {
    path: "/note/:id",
    name: "note-details",
    component: () => import("./components/NoteDetail")
  },
  {
    path: "/add-basic-note",
    alias: "/add-basic-note",
    name: "add Basic note",
    component: () => import("./components/AddBasicNote"),
  },
  {
    path: "/add-image-note",
    alias: "/add-image-note",
    name: "add image note",
    component: () => import("./components/AddImageNote"),
  },
  {
    path: "/add-checkbox-note",
    alias: "/add-checkbox-note",
    name: "add checkbox note",
    component: () => import("./components/AddCheckBoxNote"),
  },
  
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});
export default router;
