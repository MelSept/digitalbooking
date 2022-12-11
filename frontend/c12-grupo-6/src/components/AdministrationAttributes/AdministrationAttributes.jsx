import { useState } from "react";
import { v4 as uuid } from "uuid";
import { FaPlusSquare, FaMinusSquare } from "react-icons/fa";
import styles from "./AdministrationAttributes.module.css";

const AdministrationAttributes = ({
  handleAddAttr,
  handleDeleteAttr,
  attributes,
  errors,
}) => {
  const [name, setName] = useState("");
  const [icon, setIcon] = useState("");
  const [err, setErr] = useState({});

  const createNewAttr = () => {
    let fieldErr = {};
    if (!name) {
      fieldErr.name = "El nombre es obligatorio";
    }

    if (!icon) {
      fieldErr.icon = "El ícono es obligatorio";
    }

    setErr(fieldErr);

    if (Object.keys(fieldErr).length === 0) {
      const newAttr = { id: uuid(), name, icon };
      handleAddAttr(newAttr);
      setName("");
      setIcon("");
    }
  };

  const deleteAttr = (id) => {
    handleDeleteAttr(id);
  };

  return (
    <div className={styles.attributesSection}>
      <h3>Agregar Atributos</h3>
      <div className={styles.iconSection}>
        {attributes.map(({ id, name, icon }) => (
          <div className={styles.iconForm} key={id}>
            <div className={styles.attributesName}>
              <label htmlFor="attribute">Nombre</label>
              <input
                className={styles.icon}
                type="text"
                value={name}
                disabled
              />
            </div>
            <div className={styles.attributesName}>
              <label htmlFor="icon">Icono</label>
              <input
                className={styles.icon}
                type="text"
                value={icon}
                disabled
              />
            </div>
            <button onClick={() => deleteAttr(id)}>
              <FaMinusSquare />
            </button>
          </div>
        ))}

        <div className={styles.iconForm}>
          <div className={styles.attributesName}>
            <label htmlFor="attribute">Nombre</label>
            <input
              className={styles.icon}
              type="text"
              placeholder="Nombre atributo"
              name="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              autoComplete="off"
            />
            {err.name && <div className={styles.error}>{err.name}</div>}
          </div>
          <div className={styles.attributesName}>
            <label htmlFor="icon">Icono</label>
            <input
              className={styles.icon}
              type="text"
              placeholder="icono"
              name="icon"
              value={icon}
              onChange={(e) => setIcon(e.target.value)}
              autoComplete="off"
            />
            {err.icon && <div className={styles.error}>{err.icon}</div>}
          </div>
          <button type="button" onClick={createNewAttr}>
            <FaPlusSquare />
          </button>
        </div>
      </div>
      {errors.attributes && (
        <div className={styles.error}>{errors.attributes}</div>
      )}
    </div>
  );
};

export default AdministrationAttributes;
