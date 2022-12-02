import css from "./SearchBar.module.css";
import DateRangePicker from "./DatePicker/DateRangePicker";

// Cities ared loaded from static file
import cities from "../../assets/json/cities.json";

const SearchBar = ({ handleChangeCity, handleDatesChange, handleSubmit }) => {
  return (
    <div className={css.searchBar}>
      <h1>Busca ofertas en hoteles, casas y mucho más:</h1>
      <p></p>
      <form className={css.searchForm}>
        <select
          className={css.inputField}
          onChange={handleChangeCity}
          defaultValue="¿A dónde vamos?"
        >
          <option disabled hidden>
            ¿A dónde vamos?
          </option>
          {cities.map((city) => (
            <option key={city.id}>
              {city.name}, {city.country}
            </option>
          ))}
        </select>
        <div className={css.inputField}>
          <DateRangePicker onPickDates={handleDatesChange} />
        </div>

        <button className={css.submit} onClick={handleSubmit}>
          Buscar
        </button>
      </form>
    </div>
  );
};

export default SearchBar;
