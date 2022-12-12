import { useState } from "react";
import { v4 as uuid } from "uuid";
import { FaPlusSquare, FaMinusSquare } from "react-icons/fa";
import styles from "./AdministrationLink.module.css";

const AdministrationLink = ({
  images,
  handleAddImage,
  handleDeleteImage,
  errors,
}) => {
  const [imageUrl, setImageUrl] = useState("");
  const [err, setErr] = useState({});

  const createNewImage = () => {
    let fieldErr = {};
    if (!imageUrl) {
      fieldErr.imageUrl = "El Link es obligatorio";
    }

    setErr(fieldErr);

    if (Object.keys(fieldErr).length === 0) {
      const newImage = { id: uuid(), imageUrl };
      handleAddImage(newImage);
      setImageUrl("");
    }
  };

  const deleteImage = (id) => {
    handleDeleteImage(id);
  };

  return (
    <div className={styles.imagesUpload}>
      <h3>Cargar imágenes</h3>
      <div className={styles.linkContainer}>
        {images.map(({ id, imageUrl }) => (
          <div className={styles.linkForm}>
            <div className={styles.inputImage} key={id}>
              <input type="text" value={imageUrl} disabled />
            </div>
            <button onClick={() => deleteImage(id)}>
              <FaMinusSquare />
            </button>
          </div>
        ))}

        <div className={styles.linkForm}>
          <div className={styles.inputImage}>
            <input
              type="text"
              placeholder="Insertar https://"
              name="imageUrl"
              value={imageUrl}
              onChange={(e) => setImageUrl(e.target.value)}
              autoComplete="off"
            />
            {err.imageUrl && <div className={styles.error}>{err.imageUrl}</div>}
          </div>
          <button type="button" onClick={createNewImage}>
            <FaPlusSquare />
          </button>
        </div>
      </div>
      {errors.images && <div className={styles.error}>{errors.images}</div>}
    </div>
  );
};

export default AdministrationLink;
