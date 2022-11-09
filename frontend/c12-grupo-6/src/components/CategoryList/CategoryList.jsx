import { useState, useEffect } from "react";
import CategoryCard from "../CategoryCard/CategoryCard";
import data from "../../assets/json/dataCategorias.json";
import styles from "./CategoryList.module.css";

const CategoryList = ({ handleCategory }) => {
  //const [categories, setCategories] = useState([]);
  useEffect(() => {
    //Acá tengo que llamar al backend para obtener las categorías.
    //const getCategories = () async => {
    //  const result = await fetch(`http://localhost:3000/api/categories`);
    //  const parsedResult = await result.json();
    //  setCategories(parsedResult);
    //}
    //getCategories();
  }, []); //dependencia vacía se ejecuta UNA SOLA VEZ

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
              handleCategory={handleCategory}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default CategoryList;
