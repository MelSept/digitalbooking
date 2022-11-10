import styles from "./Slider.module.css";
import { Carousel } from "react-responsive-carousel";
import Modal from "../Modal/Modal";
import "react-responsive-carousel/lib/styles/carousel.min.css";
import img1 from "../../assets/images/imageCarousel/img1.jpg";
import img2 from "../../assets/images/imageCarousel/img2.jpg";
import img3 from "../../assets/images/imageCarousel/img3.jpg";
import img4 from "../../assets/images/imageCarousel/img4.jpg";
import img5 from "../../assets/images/imageCarousel/img5.jpg";
import img6 from "../../assets/images/imageCarousel/img6.jpg";
import img7 from "../../assets/images/imageCarousel/img7.jpg";
import img8 from "../../assets/images/imageCarousel/img8.jpg";
import img9 from "../../assets/images/imageCarousel/img9.jpg";
import img10 from "../../assets/images/imageCarousel/img10.jpg";
import img11 from "../../assets/images/imageCarousel/img11.jpg";
import img12 from "../../assets/images/imageCarousel/img12.jpg";
import img13 from "../../assets/images/imageCarousel/img13.jpg";
import img14 from "../../assets/images/imageCarousel/img14.jpg";
import img15 from "../../assets/images/imageCarousel/img15.jpg";
import React, { useState } from "react";

const Slider = ({ handleClose }) => {
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
          <div>
            <img src={img1} />
          </div>
          <div>
            <img src={img2} />
          </div>
          <div>
            <img src={img3} />
          </div>
          <div>
            <img src={img4} />
          </div>
          <div>
            <img src={img5} />
          </div>
          <div>
            <img src={img6} />
          </div>
          <div>
            <img src={img7} />
          </div>
          <div>
            <img src={img8} />
          </div>
          <div>
            <img src={img9} />
          </div>
          <div>
            <img src={img10} />
          </div>
          <div>
            <img src={img11} />
          </div>
          <div>
            <img src={img12} />
          </div>
          <div>
            <img src={img13} />
          </div>
          <div>
            <img src={img14} />
          </div>
          <div>
            <img src={img15} />
          </div>
        </Carousel>
        <p className={styles.status}>{status}</p>
      </div>
    </Modal>
  );
};

export default Slider;
