import React, { useState, useEffect, useContext } from "react";
import { Link, Navigate, useNavigate, useSearchParams } from "react-router-dom";
import { login } from "../../service/auth/auth";
import { HOME } from "../../router/routes";
import UserContext from "../../context/UserContext";
import styles from "./Login.module.css";

const Login = () => {
  const initialValues = { email: "", password: "" };
  const [formValues, setFormValues] = useState(initialValues);
  const [formErrors, setFormErrors] = useState({});
  const [isSubmit, setIsSubmit] = useState(false);

  //ADMIN
  //pass: adming6c12
  //mail: admin@dh.com

  const [searchParams, _] = useSearchParams(); //Como no uso el setSearchParams, lo nombramos _.
  const isFromProduct = searchParams.get("product");

  const navigate = useNavigate();
  const { user, connectUser } = useContext(UserContext);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const isLogin = async () => {
    const user = await login({
      data: {
        username: formValues.email,
        password: formValues.password,
      },
    });

    if (user) {
      connectUser({ ...user });
      navigate(HOME);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    setFormErrors(validate(formValues));
    setIsSubmit(true);
  };

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
    return <Navigate to={HOME} />;
  }

  return (
    <div className={styles.authFormContainer}>
      {isFromProduct && (
        <div className={styles.error}>
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
