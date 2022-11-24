import styles from "./ReservationCalendar.module.css";
import { useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import useWindowSize from "../../hooks/useWindowSize";

const ReservationCalendar = () => {
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(null);

  const { width } = useWindowSize();

  const onChange = (dates) => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
  };
  return (
    <div className={styles.ReservationCalendarContainer}>
      <h2>Selecciona tu fecha de reserva</h2>
      <div className={styles.thirdCalendar}>
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
    </div>
  );
};

export default ReservationCalendar;
