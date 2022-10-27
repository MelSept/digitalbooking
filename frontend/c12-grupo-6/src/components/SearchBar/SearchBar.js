import css from "./SearchBar.module.css";
import { useState, useEffect } from "react";
import DateRangePicker from "./DatePicker/DateRangePicker";

// Cities ared loaded from static file
import cities from "../../assets/json/cities.json";

function SearchBar() {
  const [selectedCity, setSelectedCity] = useState();
  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState();

  function handleSubmit(event) {
    event.preventDefault();
    let msg = `Ciudad Seleccionada: ${selectedCity}`;
    msg += `\nCheck in: ${startDate}`;
    msg += `\nCheck out: ${endDate}`;
    console.log(msg);
  }

  function handleChangeCity(event) {
    setSelectedCity(event.target.value);
  }

  function setDates({ start, end }) {
    setStartDate(start);
    setEndDate(end);
  }

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
          <DateRangePicker onPickDates={setDates} />
        </div>

        <button className={css.submit} onClick={handleSubmit}>
          Buscar
        </button>
      </form>
    </div>
  );
}

export default SearchBar;
