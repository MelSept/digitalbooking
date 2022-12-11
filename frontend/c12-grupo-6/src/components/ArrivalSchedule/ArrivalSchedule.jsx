import styles from "./ArrivalSchedule.module.css";
import hours from "../../assets/json/hours.json";
import { FaCheckCircle } from "react-icons/fa";

const ArrivalSchedule = ({ onChange, schedule }) => {
  const schedulePlusOne = parseInt(schedule) + 1;

  return (
    <div className={styles.arrivalContainer}>
      <h2>Tu horario de llegada</h2>
      <div className={styles.selectHour}>
        {schedule && schedule !== "-1" && (
          <p>
            <FaCheckCircle /> Tu habitación va a estar lista para el check-in
            entre las{" "}
            {schedule < 10
              ? `0${schedule}`
              : schedule == "24:00"
              ? "00:00"
              : schedule}{" "}
            Y LAS{" "}
            {schedulePlusOne < 10
              ? `0${schedulePlusOne}`
              : schedulePlusOne === 25
              ? "01"
              : schedulePlusOne === 24
              ? "00"
              : schedulePlusOne}
            :00
          </p>
        )}
        <label>Indica tu horario estimado de llegada</label>
        <select onChange={onChange}>
          <option value={-1}>Selecciona el horario</option>
          {hours.map((time) => (
            <option key={time.id} value={time.hour}>
              {time.hour === "24:00" ? "00:00" : time.hour}
            </option>
          ))}
        </select>
      </div>
    </div>
  );
};

export default ArrivalSchedule;
