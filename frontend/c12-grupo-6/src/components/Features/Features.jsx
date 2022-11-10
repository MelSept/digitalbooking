import styles from "./Features.module.css";
import {
  FaIcons,
  FaTv,
  FaSnowflake,
  FaPaw,
  FaCar,
  FaSwimmer,
  FaWifi,
} from "react-icons/fa";

const Features = () => {
  return (
    <div className={styles.featuresContainer}>
      <h2>¿Qué ofrece este lugar?</h2>
      <div className={styles.lineBottom}></div>
      <div className={styles.featureIcons}>
        <div className={styles.cocinaIcon}>
          <span>
            <FaIcons />
          </span>
          Cocina
        </div>
        <div className={styles.cocinaIcon}>
          <span>
            <FaTv />
          </span>
          Televisor
        </div>
        <div className={styles.cocinaIcon}>
          <span>
            <FaSnowflake />
          </span>
          Aire Acondicionado
        </div>
        <div className={styles.cocinaIcon}>
          <span>
            <FaPaw />
          </span>
          Apto Mascotas
        </div>
        <div className={styles.cocinaIcon}>
          <span>
            <FaCar />
          </span>
          Estacionamiento Gratuito
        </div>
        <div className={styles.cocinaIcon}>
          <span>
            <FaSwimmer />
          </span>
          Pileta
        </div>
        <div className={styles.cocinaIcon}>
          <span>
            <FaWifi />
          </span>
          Wifi
        </div>
      </div>
    </div>
  );
};

export default Features;
