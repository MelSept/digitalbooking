import React, { useState } from "react";
import { useParams, Navigate } from "react-router-dom";
import HeaderGoBack from "../../components/HeaderGoBack/HeaderGoBack";
import LocationData from "../../components/LocationData/LocationData";
import ProductGalery from "../../components/ProductGalery/ProductGalery";
import CustomCarousel from "../../components/CustomCarousel/CustomCarousel";
import Slider from "../../components/Slider/Slider";
import ProductDescription from "../../components/ProductDescription/ProductDescription";
import Features from "../../components/Features/Features";
import Calendar from "../../components/Calendar/Calendar";
import GoogleMap from "../../components/Map/GoogleMap";
import Policies from "../../components/Policies/Policies";
import useWindowSize from "../../hooks/useWindowSize";
import useFetch from "../../hooks/useFetch";
import { PRODUCTS_BY_ID } from "../../constants/endpoints";
import { HOME } from "../../router/routes";

const Product = () => {
  const { id } = useParams();
  const { width } = useWindowSize();
  const [showSlider, setShowSlider] = useState(false);

  const {
    data: product,
    isLoading,
    error,
  } = useFetch(PRODUCTS_BY_ID.replace(":id", id));

  const handleClose = () => {
    setShowSlider(false);
  };

  const handleOpen = () => {
    setShowSlider(true);
  };

  if (isLoading || !product) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <Navigate to={HOME} />;
  }

  const {
    /*id: productId,
    title,
    available,
    description,*/
    place: {
      title: placeTitle,
      /*description: placeDescription,*/
      latitude,
      longitude,
      address,
      category: {
        title: categoryTitle,
        /*description: categoryDescription,*/
        imageUrl,
      },
      city: { title: cityTitle },
    },
    images,
    features,
  } = product;

  return (
    <div>
      <HeaderGoBack
        category={categoryTitle}
        placeTitle={placeTitle}
        path={HOME}
      />
      <LocationData
        location={cityTitle}
        score={"Muy bueno"}
        address={address}
        points={9}
      />

      {width > 1024 ? (
        <ProductGalery
          handleOpen={handleOpen}
          images={images.slice(0, 4)}
          mainImage={imageUrl}
        />
      ) : (
        <CustomCarousel autoPlay={true} infiniteLoop={true} images={images} />
      )}

      {showSlider && <Slider handleClose={handleClose} images={images} />}

      <ProductDescription />
      <Features features={features} />
      <Calendar
        productId={id}
        showDisabledMonthNavigation={true}
        selectsRange={false}
        showInitReservation={true}
        title={"Fechas disponibles"}
      />
      <GoogleMap longitude={longitude} latitude={latitude} />
      <Policies />
    </div>
  );
};

export default Product;
