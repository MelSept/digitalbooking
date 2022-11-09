import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../../pages/Home/Home";
import Product from "../../pages/Product/Product";
import { HOME, PRODUCT } from "../../router/routes";

const Router = () => {
  return (
    <div>
      <Routes>
        <Route exact path={HOME} element={<Home />} />
        <Route exact path={PRODUCT} element={<Product />} />
      </Routes>
    </div>
  );
};

export default Router;
