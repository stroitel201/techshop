"use strict";

let items;
let categories;

document.addEventListener("DOMContentLoaded", () => {
  setCategories();
  doFetch("main/categories/", "GET").then((data) => {
    categories = data;
    renderCategoryList(data);
  });
  doFetch("main/products/?page=0", "GET").then((data) => {
    items = data;
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
