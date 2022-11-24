import CategoryCard from "../CategoryCard/CategoryCard";
import styles from "./CategoryList.module.css";
import { CATEGORY } from "../../constants/endpoints";
import useFetch from "../../hooks/useFetch";

const CategoryList = ({ handleCategory }) => {
  const { data: categories, isLoading } = useFetch(CATEGORY);

  if (isLoading || !categories) {
    return <div>Loading...</div>;
  }

  return (
    <div className={styles.categoryContainer}>
      <div className={styles.seccion}>
        <h2>Buscar por tipo de alojamiento</h2>
        <div className={styles.cardContainer}>
          {categories.map(({ id, imageUrl, title, productCount }) => (
            <CategoryCard
              id={id}
              image_url={imageUrl}
              category_name={title}
              quantity={productCount}
              handleCategory={handleCategory}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default CategoryList;
