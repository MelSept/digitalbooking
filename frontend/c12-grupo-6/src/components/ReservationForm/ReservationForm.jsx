import styles from "./ReservationForm.module.css";

const ReservationForm = ({ children, onChange, submitData }) => {
  return (
    <div className={styles.reservationFormContainer}>
      <h2>Completá tus Datos</h2>
      <form onSubmit={submitData}>
        <div className={styles.formSection}>
          <div className={styles.name}>
            <label htmlFor="firstName">Nombre</label>
            <input
              type="text"
              placeholder="Ingrese su/s Nombre/s"
              name="nombre"
              onChange={onChange}
            />
          </div>
          <div className={styles.surName}>
            <label htmlFor="surName">Apellido</label>
            <input
              type="text"
              placeholder="Ingrese su/s Apellido/s"
              name="apellido"
              onChange={onChange}
            />
          </div>
          <div className={styles.mail}>
            <label htmlFor="mail">E-mail</label>
            <input
              type="email"
              placeholder="Ingrese su e-mail"
              name="email"
              onChange={onChange}
            />
          </div>
          <div className={styles.city}>
            <label htmlFor="city">Ciudad</label>
            <input
              type="text"
              placeholder="Ingrese su ciudad"
              name="ciudad"
              onChange={onChange}
            />
          </div>
        </div>
        {children}
      </form>
    </div>
  );
};

export default ReservationForm;
