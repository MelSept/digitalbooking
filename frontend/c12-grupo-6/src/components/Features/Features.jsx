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

const iconsMapper = {
  cocina: { title: "Cocina", icon: <FaIcons /> },
  tv: { title: "Televisor", icon: <FaTv /> },
  "aire acondicionado": { title: "Aire Acondicionado", icon: <FaSnowflake /> },
  pileta: { title: "Pileta", icon: <FaSwimmer /> },
  "estacionamiento gratuito": {
    title: "Estacionamiento Gratuito",
    icon: <FaCar />,
  },
  wifi: { title: "WiFi", icon: <FaWifi /> },
  "apto mascotas": { title: "Apto Mascota", icon: <FaPaw /> },
};

const Features = ({ features }) => {
  return (
    <div className={styles.featuresContainer}>
      <h2>¿Qué ofrece este lugar?</h2>
      <div className={styles.lineBottom}></div>
      <div className={styles.featureIcons}>
        {features.map(({ title, id }) => (
          <div className={styles.cocinaIcon} key={id}>
            <span>{iconsMapper[title].icon}</span>
            {iconsMapper[title].title}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Features;
