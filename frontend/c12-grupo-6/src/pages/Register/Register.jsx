import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import styles from "./Register.module.css";

const Register = () => {
  const initialValues = {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    passwordAgain: "",
  };
  const [formValues, setFormValues] = useState(initialValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setFormErrors(validate(formValues));
    setIsSubmit(true);
  };

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
      errors.email = "Email es requerido!";
    } else if (!regex.test(values.email)) {
      errors.email = "Formato no valido!";
    }
    if (!values.password) {
      errors.password = "Contraseña requerida";
    } else if (values.password.length < 4) {
      errors.password = " Contraseña debe contener mas de 6 caracteres";
    }
    if (!values.passwordAgain) {
      errors.passwordAgain = "Contraseña requerida";
    } else if (values.passwordAgain !== values.password) {
      errors.passwordAgain = " Contraseñas no coinciden";
    }
    return errors;
  };

  return (
    <div className={styles.authFormContainer}>
      <h2>Crear Cuenta</h2>
      <form className={styles.registerForm} onSubmit={handleSubmit}>
        <label htmlFor="firstName">Nombre</label>
        <input
          size="32"
          id="firstName"
          name="firstName"
          type="text"
          placeholder=""
          value={formValues.firstName}
          onChange={handleChange}
        />
        <p>{formErrors.firstName}</p>

        <label htmlFor="LastName">Apellido</label>
        <input
          id="lastName"
          name="lastName"
          type="text"
          placeholder=""
          value={formValues.lastName}
          onChange={handleChange}
        />
        <p>{formErrors.lastName}</p>

        <label htmlFor="email">Correo Electrónico</label>
        <input
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
        <label htmlFor="passwordAgain">Confirmar Contraseña</label>
        <input
          id="passwordAgain"
          name="passwordAgain"
          type="password"
          placeholder=""
          value={formValues.passwordAgain}
          onChange={handleChange}
        />
        <p>{formErrors.passwordAgain}</p>
        <button type="submit">Crear Cuenta</button>
      </form>
      <p>
        ¿Ya tienes cuenta? <Link to="/login">Iniciar Sesión</Link>
      </p>
    </div>
  );
};

export default Register;
