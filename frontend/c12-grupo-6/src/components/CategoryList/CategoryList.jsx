import CategoryCard from "../CategoryCard/CategoryCard";
import data from "../../assets/json/dataCategorias.json";
import styles from "./CategoryList.module.css";

const CategoryList = () => {
  return (
    <div className={styles.categoryContainer}>
      <div className={styles.seccion}>
        <h2>Buscar por tipo de alojamiento</h2>
        <div className={styles.cardContainer}>
          {data.categorias.map(({ id, image_url, category_name, quantity }) => (
            <CategoryCard
              id={id}
              image_url={image_url}
              category_name={category_name}
              quantity={quantity}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default CategoryList;
