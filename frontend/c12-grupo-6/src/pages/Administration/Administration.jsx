import { useState } from "react";
import { CATEGORY, CITIES } from "../../constants/endpoints";
import { HOME } from "../../router/routes";
import AdministrationForm from "../../components/AdministrationForm/AdministrationForm";
import HeaderAdministration from "../../components/HeaderAdministration/HeaderAdministration";
import useFetch from "../../hooks/useFetch";
import styles from "./Administration.module.css";

const Administration = () => {
  const [data, setData] = useState({
    property: "",
    address: "",
    description: "",
    attribute: "",
    icon: "",
    rules: "",
    safeSecurity: "",
    cancellation: "",
  });

  const {
    data: cities,
    isLoading: isLoadingCities,
    error: cityError,
  } = useFetch(CITIES);

  const {
    data: categories,
    isLoading: isLoadingCategories,
    error: categoryError,
  } = useFetch(CATEGORY);

  const formChange = (event) => {
    setData({ ...data, [event.target.name]: event.target.value });
  };

  if ((isLoadingCities && isLoadingCategories) || !cities || !categories) {
    return <div>Loading...</div>;
  }

  if (cityError || categoryError) {
    return <div>Error cargando los datos...</div>;
  }

  return (
    <div className={styles.administration}>
      <HeaderAdministration path={HOME} />
      <AdministrationForm
        category={categories}
        location={cities}
        onChange={formChange}
      />
    </div>
  );
};

export default Administration;
