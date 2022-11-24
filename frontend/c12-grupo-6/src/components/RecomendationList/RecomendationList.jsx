import RecomendationCard from "../RecomendationCard/RecomendationCard";
import styles from "./RecomendationList.module.css";

const RecomendationList = ({ recomendations }) => {
  return (
    <div className={styles.recomendationContainer}>
      <div className={styles.section}>
        <h2>Recomendaciones</h2>
        <div className={styles.cardContainer}>
          {recomendations.map(
            ({
              id,
              description,
              thumbnail,
              category_name,
              calificacion,
              puntaje,
              title,
              place,
              ver_mapa,
            }) => (
              <RecomendationCard
                key={id}
                id={id}
                thumbnail={thumbnail}
                category_name={category_name}
                calificacion={calificacion}
                description={description}
                score={9}
                title={title}
                place={place}
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
