import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { CATEGORY, CITIES } from "../../constants/endpoints";
import { CREATE, HOME } from "../../router/routes";
import AdministrationForm from "../../components/AdministrationForm/AdministrationForm";
import HeaderAdministration from "../../components/HeaderAdministration/HeaderAdministration";
import useGet from "../../hooks/requests/useGet";
import styles from "./Administration.module.css";

const Administration = () => {
  const [errors, setErrors] = useState({});
  const [data, setData] = useState({
    property: "",
    address: "",
    description: "",
    attributes: [],
    icon: "",
    rules: "",
    safeSecurity: "",
    cancellation: "",
    links: [],
  });

  const navigate = useNavigate();

  const {
    data: cities,
    isLoading: isLoadingCities,
    error: cityError,
  } = useGet(CITIES, true);

  const {
    data: categories,
    isLoading: isLoadingCategories,
    error: categoryError,
  } = useGet(CATEGORY, true);

  const formChange = (event) => {
    setData({ ...data, [event.target.name]: event.target.value });
  };

  const handleAddLink = (newLink) => {
    const newLinkList = [...data.links, newLink];
    setData({ ...data, links: newLinkList });
  };

  const handleAddAttr = (newAttr) => {
    const newAttrList = [...data.attributes, newAttr];
    setData({
      ...data,
      attributes: newAttrList,
    });
  };

  const handleDeleteAttr = (id) => {
    const newAttrList = data.attributes.filter((attr) => attr.id !== id);
    setData({
      ...data,
      attributes: newAttrList,
    });
  };

  const handleDeleteLink = (id) => {
    const newLinkList = data.links.filter((link) => link.id !== id);
    setData({
      ...data,
      links: newLinkList,
    });
  };

  if ((isLoadingCities && isLoadingCategories) || !cities || !categories) {
    return <div>Loading...</div>;
  }

  if (cityError || categoryError) {
    return <div>Error cargando los datos...</div>;
  }

  const validate = (values) => {
    let errors = {};

    if (!values.property) {
      errors.property = 'El campo "Nombre" no debe ser vacio.';
    }

    if (!values.address) {
      errors.address = 'El campo "Direccion" no debe ser vacio.';
    }

    if (!values.description) {
      errors.description = 'El campo "Descripcion" no debe ser vacio.';
    }

    if (!values.attributes.length) {
      errors.attributes = "Debe ingresar al menos un atributo.";
    }

    if (!values.rules) {
      errors.rules = 'El campo "Normas de la casa" no debe ser vacio.';
    }

    if (!values.safeSecurity) {
      errors.safeSecurity = 'El campo "Salud y Seguridad" no debe ser vacio.';
    }

    if (!values.cancellation) {
      errors.cancellation =
        'El campo "Politicas de cancelacion" no debe ser vacio.';
    }

    if (!values.links) {
      errors.links = 'El campo "Link" no debe ser vacio.';
    }

    return errors;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const err = validate(data);
    setErrors(err);
    if (Object.keys(err).length === 0) {
      navigate(CREATE);
    }
  };

  return (
    <div className={styles.administration}>
      <HeaderAdministration path={HOME} />
      <AdministrationForm
        category={categories}
        location={cities}
        attributes={data.attributes}
        links={data.links}
        onChange={formChange}
        handleSubmit={handleSubmit}
        handleAddAttr={handleAddAttr}
        handleAddLink={handleAddLink}
        handleDeleteAttr={handleDeleteAttr}
        handleDeleteLink={handleDeleteLink}
        errors={errors}
      />
    </div>
  );
};

export default Administration;
