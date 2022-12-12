import { ADMINISTRATION, HOME } from "../../router/routes";
import { Link } from "react-router-dom";
import styles from "./AdminCreateProduct.module.css";

const AdminCreateProduct = () => {
  return (
    <div className={styles.adminContainer}>
      <div className={styles.propertySection}>
        <Link className={styles.backToHome} to={HOME}>
          <button className={styles.backToHome}>Volver</button>
        </Link>

        <Link className={styles.goToAdministration} to={ADMINISTRATION}>
          <button className={styles.goToAdministration}>Crear Producto</button>
        </Link>
      </div>
    </div>
  );
};

export default AdminCreateProduct;
