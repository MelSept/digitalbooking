import { HOME } from "../../router/routes";
import { Link } from "react-router-dom";
import { FaCheckCircle } from "react-icons/fa";
import styles from "./CreateSuccess.module.css";

const CreateSuccess = () => {
  return (
    <div className={styles.container}>
      <div className={styles.createSection}>
        <div className={styles.iconCheck}>
          <FaCheckCircle />
        </div>

        <p>Tu propiedad se ha creado con éxito</p>
        <Link to={HOME} className={styles.back}>
          Volver
        </Link>
      </div>
    </div>
  );
};

export default CreateSuccess;
