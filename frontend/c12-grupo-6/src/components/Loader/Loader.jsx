import React from "react";
import styles from "./Loader.module.css";

const Loader = () => {
  return (
    <div className={styles.loaderContainer}>
      <div className={styles.loading}>
        <div className={styles.one}></div>
        <div className={styles.two}></div>
        <div className={styles.three}></div>
        <div className={styles.four}></div>
      </div>
    </div>
  );
};

export default Loader;
