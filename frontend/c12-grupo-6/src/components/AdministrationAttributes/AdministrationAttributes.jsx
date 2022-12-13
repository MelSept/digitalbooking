import React, { useState } from "react";
import * as FontAwesome from "react-icons/fa";
import { FEATURES } from "../../constants/endpoints";
import useGet from "../../hooks/requests/useGet";
import styles from "./AdministrationAttributes.module.css";
import { useEffect } from "react";

const Icon = (props) => {
  const { iconName, size, color } = props;
  const icon = React.createElement(FontAwesome[iconName]);
  return <div style={{ fontSize: size, color: color }}>{icon}</div>;
};

const AdministrationAttributes = ({
  handleAddFeature,
  handleDeleteFeature,
  features,
  errors,
}) => {
  const [feature, setFeature] = useState({
    id: null,
    title: "",
    icon: "",
  });
  const [err, setErr] = useState({});

  const {
    data: featureList,
    isLoading: isLoadingFeatures,
    error: featureError,
  } = useGet(FEATURES, true);

  useEffect(() => {
    if (featureList) {
      setFeature(featureList[0]);
    }
  }, [featureList]);

  const handleChange = (e) => {
    const id = parseInt(e.target.value);
    const ft = featureList.find((f) => f.id === id);
    setFeature(ft);
  };

  const createNewFeature = () => {
    let fieldErr = {};
    if (!feature.title) {
      fieldErr.name = "El nombre es obligatorio";
    }

    setErr(fieldErr);

    if (Object.keys(fieldErr).length === 0) {
      handleAddFeature(feature);
    }
  };

  const deleteFeature = (id) => {
    handleDeleteFeature(id);
  };

  return (
    <div className={styles.attributesSection}>
      <h3>Agregar Atributos</h3>
      <div className={styles.iconSection}>
        {features.map(({ id, title, icon }) => (
          <div className={styles.iconForm} key={id}>
            <div className={styles.attributesName}>
              <label htmlFor="attribute">Nombre</label>
              <input
                className={styles.icon}
                type="text"
                value={title}
                disabled
              />
            </div>
            <div className={styles.attributesName}>
              <label htmlFor="icon">Icono</label>
              <Icon iconName={icon} size={28} />
            </div>
            <button onClick={() => deleteFeature(id)}>
              <Icon iconName={"FaMinusSquare"} />
            </button>
          </div>
        ))}

        <div className={styles.iconForm}>
          <div className={styles.attributesName}>
            <label htmlFor="featureName">Nombre</label>
            <select name="featureName" onChange={handleChange}>
              {featureList &&
                featureList.map((ft) => (
                  <option
                    key={ft.id}
                    value={ft.id}
                    disabled={features.some((feature) => feature.id === ft.id)}
                  >
                    {ft.title}
                  </option>
                ))}
            </select>
            {err.title && <div className={styles.error}>{err.title}</div>}
          </div>

          <div className={styles.attributesName}>
            <label htmlFor="icon">Icono</label>
            {feature.icon && <Icon iconName={feature.icon} size={28} />}
            {err.icon && <div className={styles.error}>{err.icon}</div>}
          </div>
          <button type="button" onClick={createNewFeature}>
            <Icon iconName={"FaPlusSquare"} />
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
