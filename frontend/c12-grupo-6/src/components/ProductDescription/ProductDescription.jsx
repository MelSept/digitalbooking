import styles from "./ProductDescription.module.css";

const ProductDescription = () => {
  return (
    <div className={styles.descriptionContainer}>
      <h2>Alójate en el corazón de Buenos Aires</h2>
      <p className={styles.description}>
        El Hermitage Hotel se encuentra en Buenos Aires, a 600 metros de la
        cafetería Tortoni, y ofrece alojamiento con bicicletas gratuitas, WiFi
        gratuita, recepción 24 horas y mostrador de información turística. Este
        alojamiento con aire acondicionado está a 500 metros del Obelisco de
        Buenos Aires.
      </p>{" "}
      <p>
        Este apartamento cuenta con 2 dormitorios, cocina con microondas, TV de
        pantalla plana, zona de estar y baño con ducha. Se proporcionan toallas
        y ropa de cama.
      </p>{" "}
      <p>
        Cerca del apartamento hay varios lugares de interés, como el teatro
        Colón, el Centro Cultural Kirchner y el Palacio Barolo. El aeropuerto
        más cercano es el aeropuerto Jorge Newbery Airfield, ubicado a 9 km del
        Buenos Aires Downtown BAExperience09.
      </p>
    </div>
  );
};

export default ProductDescription;
