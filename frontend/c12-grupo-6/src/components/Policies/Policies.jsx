import styles from "./Policies.module.css";

const Policies = () => {
  return (
    <div className={styles.policiesContainer}>
      <h2>¿Qué tenes que saber?</h2>
      <div className={styles.lineBottom}></div>
      <div className={styles.policiesSection}>
        <div className={styles.homePolicies}>
          <h3>Normas de la casa</h3>
          <div className={styles.homeChecks}>
            <p>Check-out: 10:00</p>
            <p>No se permiten fiestas</p>
            <p>No fumar</p>
          </div>
        </div>
        <div className={styles.safePolicies}>
          <h3>Salud y seguridad</h3>
          <div className={styles.safeChecks}>
            <p>
              Se aplican las pautas de distanciamiento social y <br />
              otras normas relacionadas con el coronavirus
            </p>
            <p>Detector de humo</p>
            <p>Depósito de seguridad</p>
          </div>
        </div>
        <div className={styles.cancelPolicies}>
          <h3>Políticas de cancelación</h3>
          <div className={styles.safeChecks}>
            <p>
              Agrega las fechas de tu viaje para obtener los detalles de
              cancelacion de esta estadía.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Policies;
