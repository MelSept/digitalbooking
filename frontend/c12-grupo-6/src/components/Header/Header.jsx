import Logo from "../../assets/images/logoDb.png";
import Menu from "../../assets/images/menu.png";
import styles from "./Header.module.css";
import { Link } from "react-router-dom";

const Header = () => {
  return (
    <div className={styles.header}>
      <div className={styles.izq}>
        <Link className={styles.inicio} to={"/"}>
          <img src={Logo} className={styles.logo} alt="logo" />
          <span className={styles.slogan}>Sentite como en tu hogar</span>
        </Link>
      </div>
      <div className={styles.der}>
        <Link className={styles.link} to={"/create"}>
          Crear cuenta
        </Link>
        <Link className={styles.link} to={"/login"}>
          Iniciar sesion
        </Link>
        <img className={styles.menu} src={Menu} alt="menu" />
      </div>
    </div>
  );
};

export default Header;
