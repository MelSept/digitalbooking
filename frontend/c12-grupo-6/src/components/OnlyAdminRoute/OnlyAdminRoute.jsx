import { useContext } from "react";
import { Navigate } from "react-router-dom";
import { HOME } from "../../router/routes";
import UserContext from "../../context/UserContext";
import styles from "./OnlyAdminRoute.module.css";

const OnlyAdminRoute = ({ children }) => {
  const { user, isLoading } = useContext(UserContext);

  if (isLoading) {
    return (
      <div className={styles.loading}>
        <div className={styles.one}></div>
        <div className={styles.two}></div>
        <div className={styles.three}></div>
        <div className={styles.four}></div>
      </div>
    );
  }

  return user !== null && user.role === "admin" ? (
    children
  ) : (
    <Navigate to={HOME} />
  );
};

export default OnlyAdminRoute;
