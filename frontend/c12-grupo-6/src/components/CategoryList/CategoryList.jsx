import CategoryCard from "../CategoryCard/CategoryCard";
import styles from "./CategoryList.module.css";
import { CATEGORY } from "../../constants/endpoints";
import useGet from "../../hooks/requests/useGet";

const CategoryList = ({ handleCategory }) => {
  const { data: categories, isLoading } = useGet(CATEGORY, true);

  if (isLoading || !categories) {
    return (
      <div className={styles.loading}>
        <div className={styles.one}></div>
        <div className={styles.two}></div>
        <div className={styles.three}></div>
        <div className={styles.four}></div>
      </div>
    );
  }

  return (
    <div className={styles.categoryContainer}>
      <div className={styles.seccion}>
        <h2>Buscar por tipo de alojamiento</h2>
        <div className={styles.cardContainer}>
          {categories.map(({ id, imageUrl, title, productCount }) => (
            <CategoryCard
              key={id}
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
