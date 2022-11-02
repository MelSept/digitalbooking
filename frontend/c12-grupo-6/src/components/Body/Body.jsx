import css from "./Body.module.css";
import SearchBar from "../SearchBar/SearchBar";
import CategoryList from "../CategoryList/CategoryList";
import RecomendationList from "../RecomendationList/RecomendationList";

function Body() {
  return (
    <div className={css.body}>
      <SearchBar />
      <CategoryList />
      <RecomendationList />
    </div>
  );
}

export default Body;
