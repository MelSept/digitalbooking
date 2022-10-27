import Logo from "../../assets/images/logoDb.png";
import Menu from "../../assets/images/menu.png";
import css from "./Header.module.css";

function Header() {
  return (
    <div className={css.header}>
      <div className={css.izq}>
        <button className={css.inicio}>
          <a className="linkLogo" href="#">
            <img src={Logo} className={css.logo} alt="logo" />
          </a>
        </button>
        <a href="#"> Sentite como en tu hogar</a>
      </div>
      <div className={css.der}>
        <button className={css.boton} onClick="">
          Crear cuenta
        </button>
        <button className={css.boton}>Iniciar sesion</button>
        <img id={css.menu} src={Menu} alt="menu" />
      </div>
    </div>
  );
}

export default Header;
