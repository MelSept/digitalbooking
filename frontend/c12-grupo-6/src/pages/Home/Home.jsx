import { useState, useEffect } from "react";
import styles from "./Home.module.css";
import SearchBar from "../../components/SearchBar/SearchBar";
import CategoryList from "../../components/CategoryList/CategoryList";
import RecomendationList from "../../components/RecomendationList/RecomendationList";
import data from "../../assets/json/dataCategorias.json";

const recomendationsByCategory = {
  1: "hoteles",
  2: "hostels",
  3: "departamentos",
  4: "bedAndBreakfast",
};

const Home = () => {
  const [category, setCategory] = useState(1);
  //const [recomendations, setRecomendations] = useState([]);

  useEffect(() => {
    //fetch a la api, con el valor default ("hoteles")
    //const getRecomendationsByCategoryId = () async => {
    //  const result = await fetch(`http://localhost:3000/api/${category}`);
    //  const parsedResult = await result.json();
    //  setRecomendations(parsedResult);
    //}
    //getRecomendationsByCategoryId();
  }, [category]);

  const handleCategory = (id) => {
    setCategory(id);
  };

  return (
    <div className={styles.home}>
      <SearchBar />
      <CategoryList handleCategory={handleCategory} />
      <RecomendationList
        recomendations={
          data.recomendaciones[recomendationsByCategory[category]]
        }
      />
    </div>
  );
};

export default Home;
