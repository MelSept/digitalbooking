import styles from "./ProductDescription.module.css";

const ProductDescription = ({ description, category, title }) => {
  let categoryName = category;
  if (categoryName === "bed-and-breakfast") {
    categoryName = category.replaceAll("-", " ");
  }

  return (
    <div className={styles.descriptionContainer}>
      <h2>
        {categoryName[0].toUpperCase() + categoryName.substring(1)} - {title}
      </h2>
      <p className={styles.description}>{description}</p>{" "}
    </div>
  );
};

export default ProductDescription;
