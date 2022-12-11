import styles from "./UserReservation.module.css";
import { HOME } from "../../router/routes";
import { Link } from "react-router-dom";

const UserReservation = () => {
  return (
    <div className={styles.userContainer}>
      <h2>Tus reservas</h2>

      <div className={styles.reservationSection}>
        <p className={styles.notReservation}>Aún no posees ninguna reserva.</p>
        <Link to={HOME} className={styles.backHome}>
          <button className={styles.backHome}>Volver</button>
        </Link>
      </div>
    </div>
  );
};

export default UserReservation;
