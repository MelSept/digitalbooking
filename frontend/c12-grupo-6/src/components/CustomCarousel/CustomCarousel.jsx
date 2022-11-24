import { useState } from "react";
import { Carousel } from "react-responsive-carousel";
import styles from "./CustomCarousel.module.css";

const CustomCarousel = ({
  images,
  autoPlay = false,
  infiniteLoop = false,
  showIndicators = false,
  showThumbs = false,
  dynamicHeight = true,
}) => {
  const [status, setStatus] = useState();

  return (
    <div className={styles.carouselContainer}>
      <Carousel
        showIndicators={showIndicators}
        dynamicHeight={dynamicHeight}
        infiniteLoop={infiniteLoop}
        autoPlay={autoPlay}
        showThumbs={showThumbs}
        statusFormatter={(current, total) => setStatus(`${current}/${total}`)}
        className={styles.carousel}
      >
        {images.map((image, index) => (
          <div key={index}>
            <img src={image.url} alt="" />
          </div>
        ))}
        <p className={styles.status}>{status}</p>
      </Carousel>
    </div>
  );
};

export default CustomCarousel;
