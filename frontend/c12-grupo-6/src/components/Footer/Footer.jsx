import css from "./Footer.module.css";
import Icofb from "../../assets/icons/facebook.png"
import Icoig from "../../assets/icons/instagram.png";
import Icoli from "../../assets/icons/linkedin.png";
import Icotw from "../../assets/icons/twitter.png";

function Footer() {
  return (
    <div className={css.footer}>
      <div>
        <span>©2021 Digital Booking</span>
      </div>
      <div>
        <img src={Icofb} alt="logofb" />
        <img src={Icoli} alt="logoli" />
        <img src={Icotw} alt="logotw" />
        <img src={Icoig} alt="logoig" />
      </div>
    </div>
  );
}

export default Footer;