import { useEffect } from "react";
import styles from "./GoogleMap.module.css";

const GoogleMap = () => {
  useEffect(() => {
    const ifameData = document.getElementById("iframeId");
    const lat = -34.764997;
    const lon = -58.4427481;
    ifameData.src = `https://maps.google.com/maps?q=${lat},${lon}&hl=es;&output=embed`;
  });
  return (
    <div className={styles.map}>
      <iframe id="iframeId" height="400px" width="100%"></iframe>
    </div>
  );
};

export default GoogleMap;
