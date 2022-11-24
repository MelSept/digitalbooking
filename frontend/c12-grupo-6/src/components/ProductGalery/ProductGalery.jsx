import style from "./ProductGalery.module.css";
import { FaShareAlt, FaRegHeart } from "react-icons/fa";

const ProductGalery = ({ handleOpen, images, mainImage }) => {
  return (
    <div className={style.gallery}>
      <div className={style.topIcons}>
        <div className={style.shareIcon}>
          <FaShareAlt />
        </div>
        <div className={style.heaIcon}>
          <FaRegHeart />
        </div>
      </div>
      <div className={style.imageContainer}>
        <div>
          <img src={mainImage} alt="" />
        </div>
        {images.map((image, index) => (
          <div key={index}>
            <img src={image.url} alt="" />
          </div>
        ))}
        <button className={style.seeMore} onClick={handleOpen}>
          Ver mas
        </button>
      </div>
    </div>
  );
};

export default ProductGalery;
