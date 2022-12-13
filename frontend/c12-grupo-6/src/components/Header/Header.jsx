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
import { AiOutlineClose } from "react-icons/ai";
import { useContext, useState, useEffect } from "react";
import UserContext from "../../context/UserContext";
import { HOME, LOGIN, REGISTER } from "../../router/routes";

const Header = () => {
  const [menu, setMenu] = useState(false);
  const location = useLocation();

  const toggleMenu = () => {
    setMenu(!menu);
  };

  //implementar context
  const { user, disconnectUser } = useContext(UserContext);

  useEffect(() => {
    setMenu(false);
  }, [location.pathname]);

  return (
    <div className={styles.header}>
      <div className={styles.izq}>
        <Link className={styles.inicio} to={HOME}>
          <img src={Logo} className={styles.logo} alt="logo" />
          <span className={styles.slogan}>Sentite como en tu hogar</span>
        </Link>
      </div>
      {user ? (
        <div className={styles.der}>
          <div className={styles.avatar} size="70px" color="#383b58">
            {user?.role === "admin" ? (
              <Link to={"/admin"}>
                <span className={styles.avatarLetters}>
                  {user?.username?.charAt(0).toUpperCase()}
                </span>
              </Link>
            ) : (
              <span className={styles.avatarLetters}>
                {user?.username?.charAt(0).toUpperCase()}
              </span>
            )}
          </div>
          {/*<Avatar ClassName={styles.avatarIcon}  name={user.nombre} round size="40px" color="#000000" />*/}
          <div className={styles.userInfo}>
            <div className={styles.greeting}>
              Hola,
              <AiOutlineClose
                style={{ cursor: "pointer" }}
                onClick={disconnectUser}
              />
            </div>
            <div className={styles.userName}>{user?.username}</div>
          </div>
        </div>
      ) : (
        <div className={styles.der}>
          <button onClick={toggleMenu} className={styles.toggle}>
            <FaBars />
          </button>
          <div className={styles.menuDesktop}>
            {location !== REGISTER ? (
              <Link className={styles.link} to={REGISTER}>
                Crear cuenta
              </Link>
            ) : null}
            {location !== LOGIN ? (
              <Link className={styles.link} to={LOGIN}>
                Iniciar sesion
              </Link>
            ) : null}
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
      )}
    </div>
  );
};

export default Header;
