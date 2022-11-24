import ReservationSuccess from "../../components/ReservationSuccess/ReservationSuccess";
import styles from "./SuccessReservation.module.css";

const SuccessReservation = () => {
  return (
    <div className={styles.successContainer}>
      <ReservationSuccess />
    </div>
  );
};

export default SuccessReservation;
