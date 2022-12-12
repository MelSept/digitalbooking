import styles from "./AdministrationForm.module.css";
import AdministrationAttributes from "../AdministrationAttributes/AdministrationAttributes";
import AdministrationLink from "../AdministrationLink/AdministrationLink";

const AdministrationForm = ({
  category,
  location,
  features,
  images,
  onChange,
  handleSubmit,
  handleAddFeature,
  handleDeleteFeature,
  handleAddImage,
  handleDeleteImage,
  errors,
}) => {
  return (
    <div className={styles.administrationFormContainer}>
      <h2>Crear propiedad</h2>
      <form onSubmit={handleSubmit}>
        <div className={styles.formSection}>
          <div className={styles.principalData}>
            <div className={styles.property}>
              <label htmlFor="title">Nombre de la propiedad</label>
              <input
                type="text"
                placeholder="Nombre de la propiedad"
                name="title"
                onChange={onChange}
                autoComplete="off"
              />
              {errors.title && (
                <div className={styles.error}>{errors.title}</div>
              )}
            </div>
            <div className={styles.category}>
              <label htmlFor="category">Categoria</label>
              <select name="category" onChange={onChange}>
                {category.map((item) => (
                  <option key={item.id} value={item.title}>
                    {item.title}
                  </option>
                ))}
              </select>
            </div>
            <div className={styles.address}>
              <label htmlFor="address">Dirección</label>
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
              <select name="city" onChange={onChange}>
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
            features={features}
            handleAddFeature={handleAddFeature}
            handleDeleteFeature={handleDeleteFeature}
            errors={errors}
          />

          <div className={styles.policiesSection}>
            <h3>Políticas del producto</h3>
            <div className={styles.policies}>
              <div className={styles.rulesHome}>
                <p className={styles.policiesSubtitle}>Normas de la casa</p>
                <label htmlFor="norm">Descripción</label>
                <textarea
                  className={styles.rulesText}
                  type="textarea"
                  placeholder="Escribir aquí..."
                  name="norm"
                  onChange={onChange}
                  autoComplete="off"
                />
                {errors.norm && (
                  <div className={styles.error}>{errors.norm}</div>
                )}
              </div>
              <div className={styles.safeSecurityHome}>
                <p className={styles.policiesSubtitle}>Salud y seguridad</p>
                <label htmlFor="security">Descripción</label>
                <textarea
                  className={styles.rulesText}
                  type="textarea"
                  placeholder="Escribir aquí..."
                  name="security"
                  onChange={onChange}
                  autoComplete="off"
                />
                {errors.security && (
                  <div className={styles.error}>{errors.security}</div>
                )}
              </div>
              <div className={styles.cancellationHome}>
                <p className={styles.policiesSubtitle}>
                  Politicas de cancelacion
                </p>
                <label htmlFor="cancellation">Descripción</label>
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
            images={images}
            handleAddImage={handleAddImage}
            handleDeleteImage={handleDeleteImage}
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
