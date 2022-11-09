import React, { useEffect, useState } from "react";
/*import { useParams } from "react-router-dom";*/
import HeaderGoBack from "../../components/HeaderGoBack/HeaderGoBack";
import LocationData from "../../components/LocationData/LocationData";
import ProductGalery from "../../components/ProductGalery/ProductGalery";
import Slider from "../../components/Slider/Slider";
import img1 from "../../assets/images/imageGallery/img1.jpg";
import img2 from "../../assets/images/imageGallery/img2.jpg";
import img3 from "../../assets/images/imageGallery/img3.jpg";
import img4 from "../../assets/images/imageGallery/img4.jpg";
import img5 from "../../assets/images/imageGallery/img5.jpg";
import ProductDescription from "../../components/ProductDescription/ProductDescription";
import Features from "../../components/Features/Features";

const Product = () => {
  /*const [product, setProduct] = useState();*/
  const [showSlider, setShowSlider] = useState(false);

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
      <ProductGalery
        handleOpen={handleOpen}
        images={[img1, img2, img3, img4, img5]}
      />
      {showSlider && <Slider handleClose={handleClose} />}
      <ProductDescription />
      <Features />
    </div>
  );
};

export default Product;
