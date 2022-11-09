import styles from "./RecomendationCard.module.css";
import {
  FaStar,
  FaSwimmer,
  FaWifi,
  FaMapMarkerAlt,
  FaHeart,
} from "react-icons/fa";
import { Link } from "react-router-dom";
import { PRODUCT } from "../../router/routes";

const RecomendationCard = ({
  id,
  URLimg,
  titulo,
  category_name,
  puntaje,
  calificacion,
  ubicacion,
  ver_mapa,
  parrafo,
}) => {
  return (
    <div className={styles.card} key={id}>
      <div className={styles.hotelImg}>
        <img src={URLimg} alt={titulo} />
        <span>
          <FaHeart />
        </span>
      </div>
      <div className={styles.cardInfo}>
        <div className={styles.header}>
          <div className={styles.stars}>
            <div className={styles.categoryStar}>
              <p className={styles.category}>{category_name}</p>
              <span className={styles.starsIcon}>
                <FaStar />
                <FaStar />
                <FaStar />
                <FaStar />
                <FaStar />
              </span>
            </div>
            <h3 className={styles.title}>{titulo}</h3>
          </div>
          <div className={styles.score}>
            <span className={styles.points}>{puntaje}</span>
            <p className={styles.calification}>{calificacion}</p>
          </div>
        </div>

        <div className={styles.content}>
          <div className={styles.addressSection}>
            <span className={styles.addrIcon}>
              <FaMapMarkerAlt />
            </span>
            <p className={styles.address}>{ubicacion}</p>
            <a href="#">{ver_mapa}</a>
          </div>
          <div className={styles.benefits}>
            <span className={styles.wifiIcon}>
              <FaWifi />
            </span>
            <span className={styles.poolIcon}>
              <FaSwimmer />
            </span>
          </div>
        </div>

        <p className={styles.description}>{parrafo}</p>
        <Link to={PRODUCT}>
          <button className={styles.detailBtn}>Ver detalle</button>
        </Link>
      </div>
    </div>
  );
};

export default RecomendationCard;
