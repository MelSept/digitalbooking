import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import styles from "./Login.module.css";

const Login = () => {
  const initialValues = { email: "", password: "" };
  const [formValues, setFormValues] = useState(initialValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);

  const user = {
    email: "grupo6@grupo6.com",
    password: "123456",
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setFormErrors(validate(formValues));
    if (
      formValues.email === user.email &&
      formValues.password === user.password
    ) {
      setIsSubmit(true);
    } else {
      console.log("No esta Loggueado");
    }
  };

  //Validation

  useEffect(() => {
    console.log(formErrors);
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      console.log(formValues);
    }
  });

  const validate = (values) => {
    const errors = {};
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
    if (!values.email) {
      errors.email = "Email is requerido!";
    } else if (!regex.test(values.email)) {
      errors.email = "Este no es un formato de correo electrónico válido";
    }
    if (!values.password) {
      errors.password = "Contraseña es requerida";
    } else if (values.password.length < 6) {
      errors.password = "La contraseña debe tener mas de 6 caracteres";
    }
    return errors;
  };

  return (
    <div className={styles.authFormContainer}>
      <h2>Iniciar Sesion</h2>
      <form className={styles.loginForm} onSubmit={handleSubmit}>
        <label htmlFor="email">Correo Electronico</label>
        <input
          size="32"
          id="email"
          name="email"
          type="email"
          placeholder=""
          value={formValues.email}
          onChange={handleChange}
        />

        <p>{formErrors.email}</p>

        <label htmlFor="password">Contraseña</label>
        <input
          id="password"
          name="password"
          type="password"
          placeholder=""
          value={formValues.password}
          onChange={handleChange}
        />

        <p>{formErrors.password}</p>
        <button type="submit">Ingresar</button>
      </form>
      <p>
        ¿Aun no tienes Cuenta? <Link to="/register">Registrate</Link>
      </p>
    </div>
  );
};

export default Login;
