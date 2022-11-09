import styles from "./HeaderGoBack.module.css";
import { FaAngleLeft } from "react-icons/fa";
import { Link } from "react-router-dom";

const HeaderGoBack = ({ name, category }) => {
  return (
    <div className={styles.containerGoBack}>
      <div className={styles.categoryName}>
        <p className={styles.category}>{category}</p>
        <p className={styles.name}>{name}</p>
      </div>
      <div className={styles.goBack}>
        <Link className={styles.goBack} to="/">
          <FaAngleLeft />
        </Link>
      </div>
    </div>
  );
};

export default HeaderGoBack;
