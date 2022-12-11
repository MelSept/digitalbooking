import { useState, useEffect } from "react";
import { format } from "date-fns";
import {
  CITIES,
  PRODUCTS_BY_CATEGORY,
  PRODUCTS_FILTER,
} from "../../constants/endpoints";
import { BASE_API_URL } from "../../constants/baseApi";
import useGet from "../../hooks/requests/useGet";
import SearchBar from "../../components/SearchBar/SearchBar";
import CategoryList from "../../components/CategoryList/CategoryList";
import RecomendationList from "../../components/RecomendationList/RecomendationList";
import styles from "./Home.module.css";

const Home = () => {
  const [category, setCategory] = useState("hotel");
  const [recommend, setRecommend] = useState([]);
  const [selectedCity, setSelectedCity] = useState();
  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState();

  const { data: recomendations, isLoading } = useGet(
    `${PRODUCTS_BY_CATEGORY}${category}`,
    true
  );

  const { data: cities } = useGet(CITIES, true);

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
        `${BASE_API_URL}${PRODUCTS_FILTER}?city=${selectedCity}&checkIn=${format(
          startDate,
          "yyyy-MM-dd"
        )}&checkOut=${format(endDate, "yyyy-MM-dd")}`
      );
      const parsedData = await data.json();

      if (parsedData && parsedData.length) {
        setRecommend(parsedData);
      }
    } catch (error) {
      console.error(error);
    }
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
        cities={cities}
        startDate={startDate}
        endDate={endDate}
      />
      <CategoryList handleCategory={handleCategory} />
      <RecomendationList recomendations={recommend} />
    </div>
  );
};

export default Home;
