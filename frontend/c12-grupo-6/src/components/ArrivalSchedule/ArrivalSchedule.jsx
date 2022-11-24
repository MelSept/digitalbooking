import styles from "./ArrivalSchedule.module.css";
import hours from "../../assets/json/hours.json";
import { FaCheckCircle } from "react-icons/fa";

const ArrivalSchedule = ({ onChange }) => {
  return (
    <div className={styles.arrivalContainer}>
      <h2>Tu horario de llegada</h2>
      <div className={styles.selectHour}>
        <p>
          <FaCheckCircle /> Tu habitación va a estar lista para el check-in
          entre las 10:00 AM Y 11:00 PM
        </p>
        <label>Indica tu horario estimado de llegada</label>
        <select onChange={onChange}>
          {hours.map((hour) => (
            <option key={hour.id} value={hour.hour}>
              {hour.hour}
            </option>
          ))}
        </select>
      </div>
    </div>
  );
};

export default ArrivalSchedule;
