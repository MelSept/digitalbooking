import Logo from "../../assets/images/logoDb.png";
import styles from "./Header.module.css";
import { Link, useLocation } from "react-router-dom";
import {
  FaBars,
  FaFacebook,
  FaLinkedinIn,
  FaTwitter,
  FaInstagram,
} from "react-icons/fa";
import { useState } from "react";
import { useEffect } from "react";

const Header = () => {
  const [menu, setMenu] = useState(false);
  const location = useLocation();

  const toggleMenu = () => {
    setMenu(!menu);
  };

  useEffect(() => {
    setMenu(false);
  }, [location.pathname]);

  return (
    <div className={styles.header}>
      <div className={styles.izq}>
        <Link className={styles.inicio} to={"/"}>
          <img src={Logo} className={styles.logo} alt="logo" />
          <span className={styles.slogan}>Sentite como en tu hogar</span>
        </Link>
      </div>
      <div className={styles.der}>
        <button onClick={toggleMenu} className={styles.toggle}>
          <FaBars />
        </button>

        <div className={styles.menuDesktop}>
          <Link className={styles.link} to={"/register"}>
            Crear cuenta
          </Link>
          <Link className={styles.link} to={"/login"}>
            Iniciar sesion
          </Link>
        </div>

        <div
          className={`${styles.menuMobile} ${
            menu ? styles.isActive : styles.isInactive
          }`}
        >
          <button className={styles.close} onClick={toggleMenu}>
            X
          </button>
          <div className={styles.menuTitle}>
            <p>Menú</p>
          </div>
          <Link className={styles.link} to={"/register"}>
            Crear cuenta
          </Link>
          <hr />
          <Link className={styles.link} to={"/login"}>
            Iniciar sesion
          </Link>

          <div className={styles.social}>
            <ul>
              <li>
                <FaFacebook />
              </li>
              <li>
                <FaLinkedinIn />
              </li>
              <li>
                <FaTwitter />
              </li>
              <li>
                <FaInstagram />
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Header;
