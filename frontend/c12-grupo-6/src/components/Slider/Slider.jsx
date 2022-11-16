import styles from "./Slider.module.css";
import { Carousel } from "react-responsive-carousel";
import Modal from "../Modal/Modal";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import React, { useState } from "react";

const Slider = ({ handleClose, images }) => {
  const [status, setStatus] = useState("");

  return (
    <Modal handleClose={handleClose}>
      <div className={styles.carouselImg}>
        <Carousel
          showIndicators={false}
          dynamicHeight={true}
          infiniteLoop={true}
          autoPlay={true}
          statusFormatter={(current, total) => setStatus(`${current}/${total}`)}
          className={styles.carousel}
        >
          {images.map((image) => (
            <div key={image}>
              <img src={image} />
            </div>
          ))}
        </Carousel>
        <p className={styles.status}>{status}</p>
      </div>
    </Modal>
  );
};

export default Slider;
