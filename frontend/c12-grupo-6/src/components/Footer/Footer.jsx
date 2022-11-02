import css from "./Footer.module.css";
import {
  FaFacebook,
  FaLinkedinIn,
  FaTwitter,
  FaInstagram,
} from "react-icons/fa";

function Footer() {
  return (
    <div className={css.footer}>
      <div className={css.footerName}>
        <span>©2021 Digital Booking</span>
      </div>
      <div className={css.iconsFooter}>
        <span className={css.iconFooter}>
          <FaFacebook />
        </span>
        <span className={css.iconFooter}>
          <FaLinkedinIn />
        </span>
        <span className={css.iconFooter}>
          <FaTwitter />
        </span>
        <span className={css.iconFooter}>
          <FaInstagram />
        </span>
      </div>
    </div>
  );
}

export default Footer;
