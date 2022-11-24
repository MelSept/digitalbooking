import { useState } from "react";
import styles from "./Home.module.css";
import useFetch from "../../hooks/useFetch";
import { PRODUCTS_BY_CATEGORY } from "../../constants/endpoints";
import SearchBar from "../../components/SearchBar/SearchBar";
import CategoryList from "../../components/CategoryList/CategoryList";
import RecomendationList from "../../components/RecomendationList/RecomendationList";

const Home = () => {
  const [category, setCategory] = useState("hotel");

  const { data: recomendations, isLoading } = useFetch(
    `${PRODUCTS_BY_CATEGORY}${category}`
  );

  const handleCategory = (category_name) => {
    setCategory(category_name);
  };

  if (isLoading || !recomendations) {
    return <div>Loading...</div>;
  }

  return (
    <div className={styles.home}>
      <SearchBar />
      <CategoryList handleCategory={handleCategory} />
      <RecomendationList recomendations={recomendations} />
    </div>
  );
};

export default Home;
