import { useState } from "react";
import { v4 as uuid } from "uuid";
import { FaPlusSquare, FaMinusSquare } from "react-icons/fa";
import styles from "./AdministrationLink.module.css";

const AdministrationLink = ({
  links,
  handleAddLink,
  handleDeleteLink,
  errors,
}) => {
  const [nameLink, setNameLink] = useState("");
  const [err, setErr] = useState({});

  const createNewLink = () => {
    let fieldErr = {};
    if (!nameLink) {
      fieldErr.links = "El Link es obligatorio";
    }

    setErr(fieldErr);

    if (Object.keys(fieldErr).length === 0) {
      const newLink = { id: uuid(), nameLink };
      handleAddLink(newLink);
      setNameLink("");
    }
  };

  const deleteLink = (id) => {
    handleDeleteLink(id);
  };

  return (
    <div className={styles.imagesUpload}>
      <h3>Cargar imágenes</h3>
      <div className={styles.linkContainer}>
        {links.map(({ id, nameLink }) => (
          <div className={styles.inputImage} key={id}>
            <input type="text" value={nameLink} disabled />
            <button onClick={() => deleteLink(id)}>
              <FaMinusSquare />
            </button>
          </div>
        ))}

        <div className={styles.linkForm}>
          <div className={styles.inputImage}>
            <input
              type="text"
              placeholder="Insertar https://"
              name="links"
              value={nameLink}
              onChange={(e) => setNameLink(e.target.value)}
              autoComplete="off"
            />
            {err.links && <div className={styles.error}>{err.links}</div>}
          </div>

          <button type="button" onClick={createNewLink}>
            <FaPlusSquare />
          </button>
        </div>
      </div>
      {errors.links && <div className={styles.error}>{errors.links}</div>}
    </div>
  );
};

export default AdministrationLink;
