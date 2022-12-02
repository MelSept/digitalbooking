import { useState } from "react";
import styles from "./Home.module.css";
import useFetch from "../../hooks/useFetch";
import {
  PRODUCTS_BY_CATEGORY,
  PRODUCTS_FILTER,
} from "../../constants/endpoints";
import SearchBar from "../../components/SearchBar/SearchBar";
import CategoryList from "../../components/CategoryList/CategoryList";
import RecomendationList from "../../components/RecomendationList/RecomendationList";
import { useEffect } from "react";

const Home = () => {
  const [category, setCategory] = useState("hotel");
  const [recommend, setRecommend] = useState([]);
  const [selectedCity, setSelectedCity] = useState();
  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState();

  const { data: recomendations, isLoading } = useFetch(
    `${PRODUCTS_BY_CATEGORY}${category}`
  );

  useEffect(() => {
    if (recomendations) {
      setRecommend(recomendations);
    }
  }, [recomendations]);

  const handleCategory = (category_name) => {
    setCategory(category_name);
  };

  const handleChangeCity = (event) => {
    setSelectedCity(event.target.value);
  };

  const handleDatesChange = ({ start, end }) => {
    console.log(start);
    setStartDate(start);
    setEndDate(end);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const data = await fetch(
        `${PRODUCTS_FILTER}?city=${selectedCity}&checkin=${startDate}&checkOut=${endDate}`
      );
      const parsedData = await data.json();
      if (parsedData && parsedData.length) {
        setRecommend(parsedData);
      }
    } catch (error) {
      console.error(error);
    }

    //let msg = `Ciudad Seleccionada: ${selectedCity}`;
    //msg += `\nCheck in: ${startDate}`;
    //msg += `\nCheck out: ${endDate}`;
    //console.log(msg);
  };

  if (isLoading || !recomendations) {
    return <div>Loading...</div>;
  }

  return (
    <div className={styles.home}>
      <SearchBar
        handleSubmit={handleSubmit}
        handleChangeCity={handleChangeCity}
        handleDatesChange={handleDatesChange}
      />
      <CategoryList handleCategory={handleCategory} />
      <RecomendationList recomendations={recommend} />
    </div>
  );
};

export default Home;
