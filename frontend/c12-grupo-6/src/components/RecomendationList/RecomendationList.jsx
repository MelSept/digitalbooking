import RecomendationCard from "../RecomendationCard/RecomendationCard";
import data from "../../assets/json/dataCategorias.json";
import styles from "./RecomendationList.module.css";

const RecomendationList = () => {
  return (
    <div className={styles.recomendationContainer}>
      <div className={styles.section}>
        <h2>Recomendaciones</h2>
        <div className={styles.cardContainer}>
          {data.recomendaciones.map(
            ({
              id,
              URLimg,
              category_name,
              calificacion,
              parrafo,
              puntaje,
              titulo,
              ubicacion,
              ver_mapa,
            }) => (
              <RecomendationCard
                id={id}
                URLimg={URLimg}
                category_name={category_name}
                calificacion={calificacion}
                parrafo={parrafo}
                puntaje={puntaje}
                titulo={titulo}
                ubicacion={ubicacion}
                ver_mapa={ver_mapa}
              />
            )
          )}
        </div>
      </div>
    </div>
  );
};

export default RecomendationList;
