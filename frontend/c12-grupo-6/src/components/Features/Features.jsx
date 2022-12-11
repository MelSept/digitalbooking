import React from "react";
import styles from "./Features.module.css";
import * as FontAwesome from "react-icons/fa";

const Icon = (props) => {
  const { iconName, size, color } = props;
  const icon = React.createElement(FontAwesome[iconName]);
  return <div style={{ fontSize: size, color: color }}>{icon}</div>;
};

const Features = ({ features }) => {
  return (
    <div className={styles.featuresContainer}>
      <h2>¿Qué ofrece este lugar?</h2>
      <div className={styles.lineBottom}></div>
      <div className={styles.featureIcons}>
        {features.map(({ title, icon, id }) => (
          <div className={styles.feature} key={id}>
            <span>
              <Icon iconName={icon} />
            </span>
            {title[0].toUpperCase() + title.slice(1).toLowerCase()}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Features;
