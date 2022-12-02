import { useState } from "react";
import HeaderGoBack from "../../components/HeaderGoBack/HeaderGoBack";
import ReservationForm from "../../components/ReservationForm/ReservationForm";
import Policies from "../../components/Policies/Policies";
import styles from "./Reservation.module.css";
import ArrivalSchedule from "../../components/ArrivalSchedule/ArrivalSchedule";
import ReservationDetail from "../../components/ReservationDetail/ReservationDetail";

import { Navigate, useParams } from "react-router-dom";
import { PRODUCTS_BY_ID } from "../../constants/endpoints";
import { HOME, PRODUCT } from "../../router/routes";
import Calendar from "../../components/Calendar/Calendar";
import useFetch from "../../hooks/useFetch";

const Reservation = () => {
  const { id } = useParams();

  const [startDate, setStartDate] = useState();
  const [endDate, setEndDate] = useState(null);
  const [schedule, setSchedule] = useState(null);
  const [data, setData] = useState({
    nombre: "",
    apellido: "",
    email: "",
    ciudad: "",
  });

  const {
    data: product,
    isLoading,
    error,
  } = useFetch(PRODUCTS_BY_ID.replace(":id", id));

  const formChange = (event) => {
    setData({ ...data, [event.target.name]: event.target.value });
  };

  const calendarOnChange = (dates) => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
  };

  const scheduleOnChange = (e) => {
    setSchedule(e.target.value);
  };

  const submitFormData = (e) => {
    e.preventDefault();
    console.log("ENVIANDO DATOS");
    //1- llamo al POST de la API para guardar la reserva
    /* const dataAEnviar = {
       nombre: data.name,
       apellido: data.apellido,
       email: data.email,
       ciudad: data.ciudad,
       startDate: startDate,
       endDate: endDate,
       schedule: schedule,
    }*/
    //2- Si está OK, redirijo al usuario a la pantalla de confirmacion con el paso 3.
    //3- Usamos useNavigate así: navigate(CONFIRMATION);
  };

  if (isLoading || !product) {
    return <div>Loading...</div>;
  }

  if (error) {
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
      <ReservationForm onChange={formChange} submitData={submitFormData}>
        <Calendar
          productId={id}
          className={styles.calendar}
          showDisabledMonthNavigation={true}
          selectsRange={true}
          title={"Selecciona tu fecha de reserva"}
          startDate={startDate}
          endDate={endDate}
          onChange={calendarOnChange}
        />
        <ArrivalSchedule onChange={scheduleOnChange} />
        <ReservationDetail
          mainImage={imageUrl}
          category={categoryTitle}
          placeTitle={placeTitle}
          address={address}
        />
      </ReservationForm>
      <Policies />
    </div>
  );
};

export default Reservation;
