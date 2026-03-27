import { useState } from "react";
import Input from "../component/input";
import { useNavigate } from "react-router-dom";
import { api } from "../services/api";
import "../styles/auth.css";

function Register() {
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm((prev) => ({
      ...prev,
      [e.target.name]: e.target.value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const res = await api.auth.register(form);
      console.log(res.data);

      alert("User Registered Successfully");

      navigate("/");
    } catch (err) {
      alert(err.response.data.error);
    }
  };

  return (
    <div className="register-container">
      <div className="card shadow p-4 register-card">
        <h3 className="text-center mb-2">Create an Account</h3>
        <p className="text-center text-muted mb-4">
          Join us today! It's quick and easy.
        </p>

        <form onSubmit={handleSubmit}>
          <label>Full Name</label>
          <Input
            type="text"
            name="name"
            placeholder="Enter your full name"
            value={form.name}
            onChange={handleChange}
          />

          <label className="mt-3">Email Address</label>
          <Input
            type="email"
            name="email"
            placeholder="your.email@example.com"
            value={form.email}
            onChange={handleChange}
          />

          <label className="mt-3">Password</label>
          <Input
            type="password"
            name="password"
            placeholder="At least 6 characters"
            value={form.password}
            onChange={handleChange}
          />

          <button className="btn btn-success w-100 mt-4">Create Account</button>

          <p className="text-center mt-3">
            Already have an account? <a href="/">Login</a>
          </p>
        </form>
      </div>
    </div>
  );
}

export default Register;
