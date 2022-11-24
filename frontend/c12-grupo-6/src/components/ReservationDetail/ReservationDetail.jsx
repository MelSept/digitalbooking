import styles from "./ReservationDetail.module.css";
import { FaStar, FaMapMarkerAlt } from "react-icons/fa";
import { SUCCESS } from "../../router/routes";
import { Link } from "react-router-dom";

const ReservationDetail = ({ mainImage, category, placeTitle, address }) => {
  return (
    <div className={styles.reservationDetailContainer}>
      <div className={styles.imgCardDetail}>
        <h2>Detalle de la Reserva</h2>
        <img src={mainImage} alt={placeTitle} />
      </div>
      <div className={styles.cardInfo}>
        <div className={styles.header}>
          <div className={styles.stars}>
            <div className={styles.categoryTitle}>
              <p className={styles.category}>{category}</p>
              <h3 className={styles.title}>{placeTitle}</h3>
              <span className={styles.starsIcon}>
                <FaStar />
                <FaStar />
                <FaStar />
                <FaStar />
                <FaStar />
              </span>
            </div>
          </div>
        </div>

        <div className={styles.content}>
          <div className={styles.addressSection}>
            <span className={styles.addrIcon}>
              <FaMapMarkerAlt />
            </span>
            <p className={styles.address}>{address}</p>
          </div>
          <div className={styles.checkContainer}>
            <div className={styles.lineBottom}></div>

            <div className={styles.checks}>
              <p>Check in</p>
              <span>23/11/2022</span>
            </div>
            <div className={styles.lineBottom}></div>

            <div className={styles.checks}>
              <p>Check out</p>
              <span>27/11/2022</span>
            </div>
            <div className={styles.lineBottom}></div>
          </div>
        </div>

        <Link to={SUCCESS}>
          <button className={styles.successful}>Confirmar Reserva</button>
        </Link>
      </div>
    </div>
  );
};

export default ReservationDetail;
