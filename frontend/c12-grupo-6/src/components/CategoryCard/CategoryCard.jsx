import styles from "./CategoryCard.module.css";

const CategoryCard = ({
  id,
  image_url,
  category_name,
  quantity,
  handleCategory,
}) => {
  return (
    <button
      onClick={() => handleCategory(category_name)}
      className={styles.card}
      key={id}
    >
      <img src={image_url} alt={category_name} />
      <div className={styles.cardInfo}>
        <p className={styles.category}>{category_name}</p>
        <p className={styles.quantity}>{`${quantity} ${category_name}`}</p>
      </div>
    </button>
  );
};

export default CategoryCard;
