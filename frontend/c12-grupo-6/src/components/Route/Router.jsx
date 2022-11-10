import React from "react";
import { Route, Routes } from "react-router-dom";
import Home from "../../pages/Home/Home";
import Product from "../../pages/Product/Product";
import Login from "../../pages/Login/Login";
import Register from "../../pages/Register/Register";
import { HOME, LOGIN, PRODUCT, REGISTER } from "../../router/routes";

const Router = () => {
  return (
    <div>
      <Routes>
        <Route exact path={HOME} element={<Home />} />
        <Route exact path={PRODUCT} element={<Product />} />
        <Route exact path={LOGIN} element={<Login />} />
        <Route exact path={REGISTER} element={<Register />} />
      </Routes>
    </div>
  );
};

export default Router;
