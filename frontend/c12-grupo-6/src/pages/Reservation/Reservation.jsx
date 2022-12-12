import { useContext, useState } from "react";
import { addDays, format } from "date-fns";
import { Navigate, useNavigate, useParams } from "react-router-dom";
import {
  PRODUCTS_BY_ID,
  RESERVATIONS_BY_PRODUCT_ID,
} from "../../constants/endpoints";
import { HOME, PRODUCT, SUCCESS } from "../../router/routes";
import { postReservation } from "../../service/reservation/reservation";
import useGet from "../../hooks/requests/useGet";
import HeaderGoBack from "../../components/HeaderGoBack/HeaderGoBack";
import ReservationForm from "../../components/ReservationForm/ReservationForm";
import Policies from "../../components/Policies/Policies";
import ArrivalSchedule from "../../components/ArrivalSchedule/ArrivalSchedule";
import ReservationDetail from "../../components/ReservationDetail/ReservationDetail";
import Calendar from "../../components/Calendar/Calendar";
import UserContext from "../../context/UserContext";
import styles from "./Reservation.module.css";

const Reservation = () => {
  const { id } = useParams();
  const { user } = useContext(UserContext);
  const navigate = useNavigate();
  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState(null);
  const [schedule, setSchedule] = useState(null);
  const [errors, setErrors] = useState(null);

  const {
    data: product,
    isLoading: isLoadingProduct,
    error: errorProduct,
  } = useGet(PRODUCTS_BY_ID.replace(":id", id), true);

  const { data: reservation } = useGet(
    RESERVATIONS_BY_PRODUCT_ID.replace(":id", id),
    true
  );

  const formChange = (event) => {
    //setData({ ...data, [event.target.name]: event.target.value });
    //setData({ ...data, [event.target.name]: event.target.value });
  };

  const calendarOnChange = (dates) => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
  };

  const scheduleOnChange = (e) => {
    setSchedule(e.target.value);
  };

  const validate = () => {
    const errors = {};
    if (!startDate) {
      errors.startDate = "Fecha de inicio requerida.";
    } else if (!endDate) {
      errors.endDate = "Fecha de fin requerida.";
    } else if (!schedule) {
      errors.schedule = "Selecciona un horario.";
    }
    return errors;
  };

  const submitFormData = async (e) => {
    e.preventDefault();
    const errors = validate();
    setErrors(errors);

    if (Object.keys(errors).length === 0) {
      const resData = {
        checkIn: startDate && format(startDate, "yyyy-MM-dd"),
        checkOut: endDate && format(endDate, "yyyy-MM-dd"),
        startTime: `${schedule}:00`,
        userId: user.id,
        productId: parseInt(id),
      };

      const created = await postReservation({
        data: resData,
        tokenType: user.tokenType,
        accessToken: user.accessToken,
      });

      if (created) {
        navigate(SUCCESS);
      }
    }
  };

  if (isLoadingProduct || !product) {
    return <div>Loading...</div>;
  }

  if (errorProduct) {
    return <Navigate to={HOME} />;
  }

  const {
    place: {
      address,
      title: placeTitle,
      category: { title: categoryTitle, imageUrl },
    },
  } = product;

  return (
    <div className={styles.reservation}>
      <HeaderGoBack
        category={categoryTitle}
        placeTitle={placeTitle}
        path={PRODUCT.replace(":id", id)}
      />
      <ReservationForm
        user={user}
        onChange={formChange}
        submitData={submitFormData}
      >
        <Calendar
          productId={id}
          className={styles.calendar}
          showDisabledMonthNavigation={true}
          selectsRange={true}
          excludeDateIntervals={reservation?.map((res) => ({
            start: new Date(res.checkIn),
            end: addDays(new Date(res.checkOut), 1),
          }))}
          title={"Selecciona tu fecha de reserva"}
          startDate={startDate}
          endDate={endDate}
          onChange={calendarOnChange}
          errors={errors}
        />
        <ArrivalSchedule
          onChange={scheduleOnChange}
          schedule={schedule}
          errors={errors}
        />
        <ReservationDetail
          mainImage={imageUrl}
          category={categoryTitle}
          placeTitle={placeTitle}
          address={address}
          checkIn={startDate && format(startDate, "dd/MM/yyyy")}
          checkOut={endDate && format(endDate, "dd/MM/yyyy")}
        />
      </ReservationForm>
      <Policies />
    </div>
  );
};

export default Reservation;
