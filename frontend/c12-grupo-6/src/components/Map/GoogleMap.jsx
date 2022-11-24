import { useEffect } from "react";
import styles from "./GoogleMap.module.css";

const GoogleMap = ({ longitude, latitude }) => {
  useEffect(() => {
    const ifameData = document.getElementById("iframeId");
    ifameData.src = `https://maps.google.com/maps?q=${latitude},${longitude}&hl=es;&output=embed`;
  });
  return (
    <div className={styles.map}>
      <iframe id="iframeId" title="map" height="400px" width="100%"></iframe>
    </div>
  );
};

export default GoogleMap;
