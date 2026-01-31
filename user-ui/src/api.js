const API_URL = "http://localhost:8080/api/users";

export const getUsers = () =>
  fetch(API_URL).then(res => res.json());

export const createUser = (user) =>
  fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user)
  });

export const updateUser = (id, user) =>
  fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(user)
  });

export const deleteUser = (id) =>
  fetch(`${API_URL}/${id}`, { method: "DELETE" });
