import React from "react";
import style from "./Modal.module.css";

const Modal = ({ children, handleClose }) => {
  return (
    <div className={style.backgroundModal}>
      <div className={style.modalContainer}>
        <button className={style.close} onClick={handleClose}>
          x
        </button>
        {children}
      </div>
    </div>
  );
};

export default Modal;
