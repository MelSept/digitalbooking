import styles from "./AdministrationForm.module.css";
import AdministrationAttributes from "../AdministrationAttributes/AdministrationAttributes";
import AdministrationLink from "../AdministrationLink/AdministrationLink";

const AdministrationForm = ({
  category,
  location,
  attributes,
  links,
  onChange,
  handleSubmit,
  handleAddAttr,
  handleDeleteAttr,
  handleAddLink,
  handleDeleteLink,
  errors,
}) => {
  return (
    <div className={styles.administrationFormContainer}>
      <h2>Crear propiedad</h2>
      <form onSubmit={handleSubmit}>
        <div className={styles.formSection}>
          <div className={styles.principalData}>
            <div className={styles.property}>
              <label htmlFor="property">Nombre de la propiedad</label>
              <input
                type="text"
                placeholder="Nombre de la propiedad"
                name="property"
                onChange={onChange}
                autoComplete="off"
              />
              {errors.property && (
                <div className={styles.error}>{errors.property}</div>
              )}
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
                autoComplete="off"
              />
              {errors.address && (
                <div className={styles.error}>{errors.address}</div>
              )}
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
          </div>
          <div className={styles.descriptionSection}>
            <div className={styles.description}>
              <label htmlFor="description">Descripción</label>
              <textarea
                className={styles.descriptionText}
                type="textarea"
                placeholder="Descripcion del producto"
                name="description"
                onChange={onChange}
                autoComplete="off"
              />
              {errors.description && (
                <div className={styles.error}>{errors.description}</div>
              )}
            </div>
          </div>

          <AdministrationAttributes
            attributes={attributes}
            handleAddAttr={handleAddAttr}
            handleDeleteAttr={handleDeleteAttr}
            errors={errors}
          />

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
                  autoComplete="off"
                />
                {errors.rules && (
                  <div className={styles.error}>{errors.rules}</div>
                )}
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
                  autoComplete="off"
                />
                {errors.safeSecurity && (
                  <div className={styles.error}>{errors.safeSecurity}</div>
                )}
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
                  autoComplete="off"
                />
                {errors.cancellation && (
                  <div className={styles.error}>{errors.cancellation}</div>
                )}
              </div>
            </div>
          </div>

          <AdministrationLink
            links={links}
            handleAddLink={handleAddLink}
            handleDeleteLink={handleDeleteLink}
            errors={errors}
          />

          <div className={styles.createContainer}>
            <button type="submit" className={styles.create}>
              Crear
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default AdministrationForm;
