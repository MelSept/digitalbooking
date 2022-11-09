import style from "./ProductGalery.module.css";
import { FaShareAlt, FaRegHeart } from "react-icons/fa";

const ProductGalery = ({ handleOpen, images }) => {
  return (
    <div className={style.gallery}>
      <div className={style.topIcons}>
        <div className={style.shareIcon}>
          <FaShareAlt />
        </div>
        <div className={style.heartIcon}>
          <FaRegHeart />
        </div>
      </div>
      <div className={style.imageContainer}>
        {images.map((image) => (
          <div key={image}>
            <img src={image} alt="" />
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
