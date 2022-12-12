import styles from "./Calendar.module.css";
import { useContext } from "react";
import { LOGIN, RESERVATION } from "../../router/routes";
import { Link } from "react-router-dom";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import useWindowSize from "../../hooks/useWindowSize";
import UserContext from "../../context/UserContext";

const Calendar = ({
  productId,
  showDisabledMonthNavigation = false,
  selectsRange = false,
  showInitReservation = false,
  excludeDateIntervals = [],
  title,
  startDate = null,
  endDate = null,
  className = "",
  onChange,
}) => {
  const { width } = useWindowSize();
  const { user } = useContext(UserContext);

  return (
    <div className={`${styles.calendarContainer} ${className}`}>
      <h2>{title}</h2>
      <div className={styles.reservationSection}>
        <div className={styles.secondCalendar}>
          <DatePicker
            className={styles.datePicker}
            startDate={startDate}
            endDate={endDate}
            minDate={new Date()}
            onChange={onChange}
            excludeDateIntervals={excludeDateIntervals}
            selectsRange={selectsRange}
            showDisabledMonthNavigation={showDisabledMonthNavigation}
            inline
            monthsShown={width > 700 ? 2 : 1}
          />
        </div>
        {showInitReservation && (
          <div className={styles.reservationWindow}>
            <h3>
              Agregá tus fechas de viaje para obtener precios <br />
              exactos
            </h3>
            <Link
              to={
                user
                  ? RESERVATION.replace(":id", productId)
                  : `${LOGIN}?product=true`
              }
            >
              <button className={styles.reservationBtn}>Iniciar Reserva</button>
            </Link>
          </div>
        )}
      </div>
    </div>
  );
};

export default Calendar;
