import CreateSuccess from "../../components/CreateSuccess/CreateSuccess";
import styles from "./Create.module.css";

const Create = () => {
  return (
    <div className={styles.createContainer}>
      <CreateSuccess />
    </div>
  );
};

export default Create;
