import axios from "axios";
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

export default function AddUser() {
  let navigate = useNavigate();

  const [user, setUser] = useState({
    id: "",
    name: "",
    language: "",
    framework:""
  });

  const { id,name,language, framework } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:6039/create", user);
    navigate("/");
  };

  return (
    <div className="container">
      <div className="row">
        <div className="col-md-6 offset-md-3 border rounded p-4 mt-2 shadow">
          <h2 className="text-center m-4">Register Server</h2>

          <form onSubmit={(e) => onSubmit(e)}>
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                server id
              </label>
              <input
                type={"number"}
                className="form-control"
                placeholder="enter server id"
                name="id"
                value={id}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Username" className="form-label">
                servername
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your servername"
                name="name"
                value={name}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="Email" className="form-label">
                language
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your language"
                name="language"
                value={language}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <div className="mb-3">
              <label  className="form-label">
                framework
              </label>
              <input
                type={"text"}
                className="form-control"
                placeholder="Enter your framework"
                name="framework"
                value={framework}
                onChange={(e) => onInputChange(e)}
              />
            </div>
            <button type="submit" className="btn btn-outline-primary">
              Submit
            </button>
            <Link className="btn btn-outline-danger mx-2" to="/">
              Cancel
            </Link>
          </form>
        </div>
      </div>
    </div>
  );
}
