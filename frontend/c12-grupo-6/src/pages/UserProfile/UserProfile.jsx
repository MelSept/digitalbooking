import UserReservation from "../../components/UserReservation/UserReservation";
import styles from "./UserProfile.module.css";

const UserProfile = () => {
  return (
    <div className={styles.userContainer}>
      <UserReservation />
    </div>
  );
};

export default UserProfile;
