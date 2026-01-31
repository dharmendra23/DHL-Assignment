import { useEffect, useState } from "react";
import { getUsers, createUser, updateUser, deleteUser } from "./api";

function App() {
  const [users, setUsers] = useState([]);
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [editId, setEditId] = useState(null);

  const loadUsers = () => {
    getUsers().then(setUsers);
  };

  useEffect(() => {
    loadUsers();
  }, []);

  const handleSubmit = async () => {
    if (editId) {
      await updateUser(editId, { name, email });
    } else {
      await createUser({ name, email });
    }
    resetForm();
    loadUsers();
  };

  const handleEdit = (user) => {
    setEditId(user.id);
    setName(user.name);
    setEmail(user.email);
  };

  const resetForm = () => {
    setEditId(null);
    setName("");
    setEmail("");
  };

  return (
    <div style={{ padding: "40px", fontFamily: "Arial" }}>
      <h2>User Management (React)</h2>

      <h3>{editId ? "Update User" : "Create User"}</h3>

      <input
        placeholder="Name"
        value={name}
        onChange={e => setName(e.target.value)}
      />
      <input
        placeholder="Email"
        value={email}
        onChange={e => setEmail(e.target.value)}
      />

      <button onClick={handleSubmit}>
        {editId ? "Update" : "Create"}
      </button>

      {editId && <button onClick={resetForm}>Cancel</button>}

      <h3>Users</h3>

      <table border="1" cellPadding="8">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map(u => (
            <tr key={u.id}>
              <td>{u.id}</td>
              <td>{u.name}</td>
              <td>{u.email}</td>
              <td>
                <button onClick={() => handleEdit(u)}>Edit</button>
                <button onClick={() => deleteUser(u.id).then(loadUsers)}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
