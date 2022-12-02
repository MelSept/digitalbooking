import styles from "./AdministrationForm.module.css";
import { FaPlusSquare, FaMinusSquare } from "react-icons/fa";

const AdministrationForm = ({ category, location, onChange }) => {
  return (
    <div className={styles.administrationFormContainer}>
      <h2>Crear propiedad</h2>
      <form>
        <div className={styles.formSection}>
          <div className={styles.principalData}>
            <div className={styles.property}>
              <label htmlFor="property">Nombre de la propiedad</label>
              <input
                type="text"
                placeholder="Nombre de la propiedad"
                name="property"
                onChange={onChange}
              />
            </div>
            <div className={styles.category}>
              <label htmlFor="category">Categoria</label>
              <select>
                {category.map((item) => (
                  <option key={item.id} value={item.title}>
                    {item.title}
                  </option>
                ))}
              </select>
            </div>
            <div className={styles.address}>
              <label htmlFor="mail">Dirección</label>
              <input
                type="text"
                placeholder="direccion"
                name="address"
                onChange={onChange}
              />
            </div>
            <div className={styles.city}>
              <label htmlFor="city">Ciudad</label>
              <select>
                {location.map((item) => (
                  <option key={item.id} value={item.title}>
                    {item.title}
                  </option>
                ))}
              </select>
            </div>
            <div className={styles.description}>
              <label htmlFor="description">Descripción</label>
              <textarea
                className={styles.descriptionText}
                type="textarea"
                placeholder="Descripcion del producto"
                name="description"
                onChange={onChange}
              />
            </div>
          </div>

          <div className={styles.attributesSection}>
            <h3>Agregar Atributos</h3>
            <div className={styles.iconSection}>
              <div className={styles.attributesName}>
                <label htmlFor="attribute">Nombre</label>
                <input
                  type="text"
                  placeholder="Nombre atributo"
                  name="attribute"
                  onChange={onChange}
                />
              </div>
              <div className={styles.attributesName}>
                <label htmlFor="icon">Icono</label>
                <input
                  type="text"
                  placeholder="icono"
                  name="icon"
                  onChange={onChange}
                />
                <button>
                  <FaPlusSquare />
                </button>
                <button>
                  <FaMinusSquare />
                </button>
              </div>
            </div>
          </div>
          <div className={styles.policiesSection}>
            <h3>Políticas del producto</h3>
            <div className={styles.policies}>
              <div className={styles.rulesHome}>
                <p className={styles.policiesSubtitle}>Normas de la casa</p>
                <label htmlFor="rules">Descripción</label>
                <textarea
                  className={styles.rulesText}
                  type="textarea"
                  placeholder="Escribir aquí..."
                  name="rules"
                  onChange={onChange}
                />
              </div>
              <div className={styles.safeSecurityHome}>
                <p className={styles.policiesSubtitle}>Salud y seguridad</p>
                <label htmlFor="rules">Descripción</label>
                <textarea
                  className={styles.rulesText}
                  type="textarea"
                  placeholder="Escribir aquí..."
                  name="safeSecurity"
                  onChange={onChange}
                />
              </div>
              <div className={styles.cancellationHome}>
                <p className={styles.policiesSubtitle}>
                  Politicas de cancelacion
                </p>
                <label htmlFor="rules">Descripción</label>
                <textarea
                  className={styles.rulesText}
                  type="textarea"
                  placeholder="Escribir aquí..."
                  name="cancellation"
                  onChange={onChange}
                />
              </div>
            </div>
          </div>
          <div className={styles.imagesUpload}>
            <h3>Cargar imágenes</h3>
            <div className={styles.inputImage}>
              <input
                type="text"
                placeholder="Insertar https://"
                name="icon"
                onChange={onChange}
              />
              <button>
                <FaPlusSquare />
              </button>
              <button>
                <FaMinusSquare />
              </button>
            </div>
          </div>
        </div>
      </form>
    </div>
  );
};

export default AdministrationForm;
