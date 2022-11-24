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
  thumbnail,
  title,
  category_name,
  score,
  calificacion,
  place,
  ver_mapa,
  description,
}) => {
  return (
    <div className={styles.card} key={id}>
      <div className={styles.hotelImg}>
        <img src={thumbnail} alt={title} />
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
            <h3 className={styles.title}>{place?.title}</h3>
            <p>{title}</p>
          </div>
          <div className={styles.score}>
            <span className={styles.points}>{score}</span>
            <p className={styles.calification}>{calificacion}</p>
          </div>
        </div>

        <div className={styles.content}>
          <div className={styles.addressSection}>
            <span className={styles.addrIcon}>
              <FaMapMarkerAlt />
            </span>
            <p className={styles.address}>{place?.address}</p>
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

        <p className={styles.description}>{description}</p>
        <Link to={PRODUCT.replace(":id", id)} className={styles.detailBtn}>
          Ver detalle
        </Link>
      </div>
    </div>
  );
};

export default RecomendationCard;
