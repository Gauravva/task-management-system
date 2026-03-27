import axios from "axios";

const client = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
});

client.interceptors.request.use((req) => {
  const token = localStorage.getItem("token");
  if (token) {
    req.headers.Authorization = `Bearer ${token}`;
  }
  return req;
});

export const api = {
  auth: {
    register: (data) => client.post("/auth/register", data),
    login: (data) => client.post("/auth/login", data),
  },

  task: {
    getAll: () => client.get("/tasks"),
    create: (data) => client.post("/tasks", data),
    update: (id, data) => client.put(`/tasks/${id}`, data),
    delete: (id) => client.delete(`/tasks/${id}`),
  },
};
