const validateEmailPassword = (values) => {
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

export default validateEmailPassword;
