import { HOME } from "../../router/routes";
import { Link } from "react-router-dom";
import { FaCheckCircle } from "react-icons/fa";
import styles from "./ReservationSuccess.module.css";

const ReservationSuccess = () => {
  return (
    <div className={styles.container}>
      <div className={styles.successSection}>
        <div className={styles.iconCheck}>
          <FaCheckCircle />
        </div>

        <h1>¡Muchas Gracias!</h1>
        <p>Su reserva se ha realizado con éxito</p>
        <Link to={HOME} className={styles.ok}>
          ok
        </Link>
      </div>
    </div>
  );
};

export default ReservationSuccess;
