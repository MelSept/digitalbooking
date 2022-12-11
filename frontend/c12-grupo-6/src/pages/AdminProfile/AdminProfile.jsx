import styles from "./AdminProfile.module.css";
import AdminCreateProduct from "../../components/AdminCreateProduct/AdminCreateProduct";

const AdminProfile = () => {
  return (
    <div className={styles.adminContainer}>
      <AdminCreateProduct />
    </div>
  );
};

export default AdminProfile;
