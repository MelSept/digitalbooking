import styles from "./LocationData.module.css";
import { FaStar, FaMapMarkerAlt } from "react-icons/fa";

const LocationData = ({ location, score, points, address }) => {
  return (
    <div className={styles.containerLocation}>
      <div className={styles.locationCity}>
        <div className={styles.location}>
          <FaMapMarkerAlt />
          {location}
        </div>
        <div className={styles.adress}> {address}</div>
      </div>
      <div className={styles.productScore}>
        <div className={styles.comentCalification}>
          <p className={styles.calification}>{score}</p>
          <div className={styles.starIcons}>
            <FaStar />
            <FaStar />
            <FaStar />
            <FaStar />
            <FaStar />
          </div>
        </div>
        <span className={styles.points}>{points}</span>
      </div>
    </div>
  );
};

export default LocationData;
