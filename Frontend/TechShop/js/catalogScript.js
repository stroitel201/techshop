"use strict";

document.addEventListener("DOMContentLoaded", () => {
  setCategories();
  doFetch("main/categories/", "GET").then((data) => {
    renderCategoryList(data);
  });
  doFetch("main/products/?page=0", "GET").then((data) => {
    renderItemList(data);
  });
  renderCartCount();
});

function setCategories() {
  let list = document.querySelector("#categoryList");

  list.addEventListener("click", (e) => {
    findItemsByCategory(e);
  });
}

function findItemsByCategory(e) {
  doFetch("main/products/?category=" + e.target.innerText, "GET").then((data) =>
    renderItemList(data)
  );
}
