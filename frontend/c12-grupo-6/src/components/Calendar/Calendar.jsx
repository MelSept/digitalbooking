import styles from "./Calendar.module.css";
import { useState } from "react";
import { RESERVATION } from "../../router/routes";
import { Link } from "react-router-dom";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import useWindowSize from "../../hooks/useWindowSize";

const Calendar = () => {
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(null);

  const { width } = useWindowSize();

  const onChange = (dates) => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
  };

  return (
    <div className={styles.calendarContainer}>
      <h2>Fechas disponibles</h2>
      <div className={styles.reservationSection}>
        <div className={styles.secondCalendar}>
          <DatePicker
            className={styles.datePicker}
            selected={startDate}
            onChange={onChange}
            startDate={startDate}
            endDate={endDate}
            minDate={new Date()}
            /*excludeDates={[addDays(new Date(), 1), addDays(new Date(), 5)]}*/
            selectsRange
            showDisabledMonthNavigation
            inline
            monthsShown={width > 700 ? 2 : 1}
          />
        </div>
        <div className={styles.reservationWindow}>
          <h3>
            Agregá tus fechas de viaje para obtener precios <br />
            exactos
          </h3>
          <Link to={RESERVATION}>
            <button className={styles.reservationBtn}>Iniciar Reserva</button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Calendar;
