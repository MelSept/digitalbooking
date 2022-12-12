import { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { CATEGORY, CITIES } from "../../constants/endpoints";
import { CREATE, HOME } from "../../router/routes";
import { createProduct } from "../../service/product/product";
import UserContext from "../../context/UserContext";
import AdministrationForm from "../../components/AdministrationForm/AdministrationForm";
import HeaderAdministration from "../../components/HeaderAdministration/HeaderAdministration";
import useGet from "../../hooks/requests/useGet";
import styles from "./Administration.module.css";

const Administration = () => {
  const { user } = useContext(UserContext);
  const [errors, setErrors] = useState({});
  const [product, setProduct] = useState({
    title: "",
    description: "",
    images: [],
    features: [],
    address: "",
    category: "",
    city: "",
    policy: {
      norm: "",
      security: "",
      cancellation: "",
    },
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
    const inputName = event.target.name;

    if (
      inputName === "cancellation" ||
      inputName === "norm" ||
      inputName === "security"
    ) {
      setProduct({
        ...product,
        policy: {
          ...product.policy,
          [inputName]: event.target.value,
        },
      });
      return;
    }

    setProduct({ ...product, [inputName]: event.target.value });
  };

  const handleAddFeature = (newFeature) => {
    const newFeatureList = [...product.features, newFeature];
    setProduct({
      ...product,
      features: newFeatureList,
    });
  };

  const handleDeleteFeature = (id) => {
    const newFeatureList = product.features.filter(
      (feature) => feature.id !== id
    );
    setProduct({
      ...product,
      features: newFeatureList,
    });
  };

  const handleAddImage = (imageUrl) => {
    const newImageList = [...product.images, imageUrl];
    setProduct({ ...product, images: newImageList });
  };

  const handleDeleteImage = (id) => {
    const newImageList = product.images.filter((image) => image.id !== id);
    setProduct({
      ...product,
      images: newImageList,
    });
  };

  const validate = (values) => {
    let errors = {};

    if (!values.title) {
      errors.title = 'El campo "Nombre" no debe ser vacio.';
    }

    if (!values.address) {
      errors.address = 'El campo "Direccion" no debe ser vacio.';
    }

    if (!values.description) {
      errors.description = 'El campo "Descripcion" no debe ser vacio.';
    }

    if (values.features.length < 5) {
      errors.attributes = "El mínimo de atributos debe ser 5.";
    }

    if (!values.policy.norm) {
      errors.norm = 'El campo "Normas de la casa" no debe ser vacio.';
    }

    if (!values.policy.security) {
      errors.security = 'El campo "Salud y Seguridad" no debe ser vacio.';
    }

    if (!values.policy.cancellation) {
      errors.cancellation =
        'El campo "Politicas de cancelacion" no debe ser vacio.';
    }

    if (values.images.length < 5) {
      errors.images = "Debe ingresar al menos 5 imágenes.";
    }

    return errors;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const err = validate(product);
    setErrors(err);
    console.log("asdasd", err);
    if (Object.keys(err).length === 0) {
      const {
        title,
        description,
        images,
        features,
        address,
        category,
        city,
        policy,
      } = product;

      const newProduct = {
        title,
        description,
        thumbnail: images[0].imageUrl,
        images: images.map((i) => ({ url: i.imageUrl })),
        features: features.map((f) => ({ title: f.title, icon: f.icon })),
        address,
        category,
        city,
        policy,
      };

      console.log("NEW", newProduct);

      const created = await createProduct({
        data: newProduct,
        tokenType: user.tokenType,
        accessToken: user.accessToken,
      });

      if (created) {
        navigate(CREATE);
      }
    }
  };

  if ((isLoadingCities && isLoadingCategories) || !cities || !categories) {
    return (
      <div className={styles.loading}>
        <div className={styles.one}></div>
        <div className={styles.two}></div>
        <div className={styles.three}></div>
        <div className={styles.four}></div>
      </div>
    );
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
        features={product.features}
        images={product.images}
        onChange={formChange}
        handleSubmit={handleSubmit}
        handleAddFeature={handleAddFeature}
        handleDeleteFeature={handleDeleteFeature}
        handleAddImage={handleAddImage}
        handleDeleteImage={handleDeleteImage}
        errors={errors}
      />
    </div>
  );
};

export default Administration;
