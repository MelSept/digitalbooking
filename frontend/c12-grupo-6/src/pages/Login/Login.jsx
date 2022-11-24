import React, { useState, useEffect, useContext } from "react";
import { Link, Navigate, useNavigate, useSearchParams } from "react-router-dom";
import UserContext from "../../context/UserContext";
import styles from "./Login.module.css";

const Login = () => {
  const initialValues = { email: "", password: "" };
  const [formValues, setFormValues] = useState(initialValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);

  /*const user = {
    email: "grupo6@grupo6.com",
    password: "123456",
  };*/

  const [searchParams, setSearchParams] = useSearchParams();
  const isFromProduct = searchParams.get("product");

  const navigate = useNavigate();
  const { user, connectUser } = useContext(UserContext);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const isLogin = () => {
    connectUser({
      email: formValues.email,
      nombre: formValues.email.split("@")[0],
    });
    navigate("/");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setFormErrors(validate(formValues));
    setIsSubmit(true);
  };

  //Validation
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

  useEffect(() => {
    if (Object.keys(formErrors).length === 0 && isSubmit) {
      isLogin();
    }
  }, [formErrors]);

  if (user) {
    return <Navigate to={"/"} />;
  } else
    return (
      <div className={styles.authFormContainer}>
        {isFromProduct && (
          <div className="error">
            Para realizar una reserva necesitas estar logeado
          </div>
        )}

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
