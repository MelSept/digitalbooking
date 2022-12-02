import styles from "./HeaderAdministration.module.css";
import { FaAngleLeft } from "react-icons/fa";
import { Link } from "react-router-dom";

const HeaderAdministration = ({ path }) => {
  return (
    <div className={styles.containerAdmin}>
      <div className={styles.adminTitle}>
        <p className={styles.admin}>Administración</p>
      </div>
      <div className={styles.goBack}>
        <Link className={styles.goBack} to={path}>
          <FaAngleLeft />
        </Link>
      </div>
    </div>
  );
};

export default HeaderAdministration;
