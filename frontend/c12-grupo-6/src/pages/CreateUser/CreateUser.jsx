import React, { useState } from "react";
import "./CreateUser.css";
import Input from "../Login/components/Input";
import Item from "../Login/components/Item";
import Title from "../Login/components/Title";
import ErrorNotification from "../../commons/ErrorNotification";
import Button from "../../commons/RegularButton";
import { Redirect, Link } from "react-router-dom";
import { makeStyles } from "@material-ui/core/styles";
import Backdrop from "@material-ui/core/Backdrop";
import CircularProgress from "@material-ui/core/CircularProgress";

const useStyles = makeStyles((theme) => ({
  backdrop: {
    zIndex: 2,
    color: "#fff",
  },
}));

const CreateUser = () => {
  const classes = useStyles();

  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordAgain, setPasswordAgain] = useState("");

  const [isLoading, setIsLoading] = useState(false);
  const [created, setCreated] = useState(false);

  const [errors, setErrors] = useState({
    firstNameError: false,
    lastNameError: false,
    emailError: false,
    passwordError: false,
    passwordAgainError: false,
  });

  function handleChange(name, value) {
    switch (name) {
      case "firstName":
        if (value < 1) {
          setErrors({ ...errors, firstNameError: true });
        } else {
          setErrors({ ...errors, firstNameError: false });
          setFirstName(value);
        }
        break;
      case "lastName":
        if (value < 1) {
          setErrors({ ...errors, lastNameError: true });
        } else {
          setErrors({ ...errors, lastNameError: false });
          setLastName(value);
        }
        break;

      case "email":
        if (value < 1) {
          setErrors({ ...errors, emailError: true });
        } else {
          setErrors({ ...errors, emailError: false });
          setEmail(value);
        }
        break;
      case "password":
        if (value < 1) {
          setErrors({ ...errors, passwordError: true });
        } else {
          setErrors({ ...errors, passwordError: false });
          setPassword(value);
        }
        break;
      case "passwordAgain":
        if (password.length < 6) {
          setErrors({ ...errors, passwordError: true });
        } else if (password === value) {
          setErrors({
            ...errors,
            passwordError: false,
            passwordAgainError: false,
          });
          setPasswordAgain(value);
        } else {
          setErrors({
            ...errors,
            passwordError: false,
            passwordAgainError: true,
          });
        }
        break;
      default:
        console.log(
          "Por favor vuelva a intentarlo, sus credenciales son inválidas."
        );
    }
  }

  let params =
    errors.firstNameError === false &&
    errors.lastNameError === false &&
    errors.emailError === false &&
    errors.passwordError === false &&
    errors.passwordAgainError === false &&
    firstName.length > 1 &&
    lastName.length > 1 &&
    email.length > 1 &&
    password.length > 5 &&
    password === passwordAgain;
  function handleSubmit() {
    setIsLoading(true);
    let account = { firstName, lastName, password };
    if (account) {
      let ac = JSON.stringify(account);
      localStorage.setItem("account", ac);
      setTimeout(() => setCreated(true), 2000);
    }
  }

  let open = true;

  let screenWidth = window.innerWidth;

  return (
    <>
      {created && <Redirect to="/home" />}

      <div className="CreateUserContainer">
        <div className="createUserContent">
          <div className="formCreateUser">
            {screenWidth > 1030 && <Title text="Crear Cuenta" />}

            <Item text="Nombre" />
            <Input
              attribute={{
                name: "firstName",
                inputType: "text",
                ph: "",
              }}
              handleChange={handleChange}
              param={errors.firstNameError}
            />
            {errors.firstNameError && <ErrorNotification text="Requerido." />}

            <Item text="Apellido" />
            <Input
              attribute={{
                name: "lastName",
                inputType: "text",
                ph: "",
              }}
              handleChange={handleChange}
              param={errors.lastNameError}
            />
            {errors.lastNameError && <ErrorNotification text="Requerido." />}

            <Item text="Correo Electronico" />
            <Input
              attribute={{
                name: "email",
                inputType: "email",
                ph: "",
              }}
              handleChange={handleChange}
              param={errors.passwordError}
            />
            {errors.passwordError && (
              <ErrorNotification text="Correo Electrónico no valido" />
            )}

            <Item text="Contraseña" />
            <Input
              attribute={{
                name: "password",
                inputType: "password",
                ph: "",
              }}
              handleChange={handleChange}
              param={errors.passwordError}
            />
            {errors.passwordError && (
              <ErrorNotification text="min. 6 caracteres" />
            )}

            <Item text="Repetir Contraseña" />
            <Input
              attribute={{
                name: "passwordAgain",
                inputType: "password",
                ph: "",
              }}
              handleChange={handleChange}
              param={errors.passwordAgainError}
            />
            {errors.passwordAgainError && (
              <ErrorNotification text="Contraseñas no coiciden" />
            )}

            <Button
              text="Crear Cuenta"
              handleOnClick={handleSubmit}
              param={params}
            />

            <div
              style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              <span>¿Ya tienes una cuenta?</span>
              <Link to="/" style={{ color: "#734488" }}>
                <Item text="Iniciar Sesion" />
              </Link>
            </div>
          </div>

          {isLoading && (
            <Backdrop open={open} className={classes.backdrop}>
              <CircularProgress color="inherit" />
            </Backdrop>
          )}
        </div>
      </div>
    </>
  );
};

export default CreateUser;
