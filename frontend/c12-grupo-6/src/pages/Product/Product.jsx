import React, { useEffect, useState } from "react";
/*import { useParams } from "react-router-dom";*/
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

import img1 from "../../assets/images/imageGallery/img1.jpg";
import img2 from "../../assets/images/imageGallery/img2.jpg";
import img3 from "../../assets/images/imageGallery/img3.jpg";
import img4 from "../../assets/images/imageGallery/img4.jpg";
import img5 from "../../assets/images/imageGallery/img5.jpg";
import img6 from "../../assets/images/imageCarousel/img6.jpg";
import img7 from "../../assets/images/imageCarousel/img7.jpg";
import img8 from "../../assets/images/imageCarousel/img8.jpg";
import img9 from "../../assets/images/imageCarousel/img9.jpg";
import img10 from "../../assets/images/imageCarousel/img10.jpg";
import img11 from "../../assets/images/imageCarousel/img11.jpg";
import img12 from "../../assets/images/imageCarousel/img12.jpg";
import img13 from "../../assets/images/imageCarousel/img13.jpg";
import img14 from "../../assets/images/imageCarousel/img14.jpg";
import img15 from "../../assets/images/imageCarousel/img15.jpg";

const Product = () => {
  /*const [product, setProduct] = useState();*/
  const [showSlider, setShowSlider] = useState(false);
  const { width } = useWindowSize();

  useEffect(() => {
    //fetch a la api, con el valor default ("hoteles")
    //const getProductById = () async => {
    //  const result = await fetch(`http://localhost:3000/api/product/${id}`);
    //  const parsedResult = await result.json();
    //  setProduct(parsedResult);
    //}
    //getProductById();
  }, []);

  const handleClose = () => {
    setShowSlider(false);
  };

  const handleOpen = () => {
    setShowSlider(true);
  };

  return (
    <div>
      <HeaderGoBack name={"Hermitage Hotel"} category={"HOTEL"} />
      <LocationData
        location={"Buenos Aires, Ciudad Autonoma de Buenos Aires, Argentina"}
        score={"Muy bueno"}
        address={"a 90 metros del centro"}
        points={9}
      />
      {width > 1024 ? (
        <ProductGalery
          handleOpen={handleOpen}
          images={[img1, img2, img3, img4, img5]}
        />
      ) : (
        <CustomCarousel
          autoPlay={true}
          infiniteLoop={true}
          images={[
            img1,
            img2,
            img3,
            img4,
            img5,
            img6,
            img7,
            img8,
            img9,
            img10,
            img11,
            img12,
            img13,
            img14,
            img15,
          ]}
        />
      )}

      {showSlider && (
        <Slider
          handleClose={handleClose}
          images={[
            img1,
            img2,
            img3,
            img4,
            img5,
            img6,
            img7,
            img8,
            img9,
            img10,
            img11,
            img12,
            img13,
            img14,
            img15,
          ]}
        />
      )}
      <ProductDescription />
      <Features />
      <Calendar />
      <GoogleMap />
      <Policies />
    </div>
  );
};

export default Product;
