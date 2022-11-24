import styles from "./HeaderGoBack.module.css";
import { FaAngleLeft } from "react-icons/fa";
import { Link } from "react-router-dom";

const HeaderGoBack = ({ category, placeTitle, path }) => {
  return (
    <div className={styles.containerGoBack}>
      <div className={styles.categoryName}>
        <p className={styles.category}>{category}</p>
        <p className={styles.name}>{placeTitle}</p>
      </div>
      <div className={styles.goBack}>
        <Link className={styles.goBack} to={path}>
          <FaAngleLeft />
        </Link>
      </div>
    </div>
  );
};

export default HeaderGoBack;
