"use strict";

document.addEventListener("DOMContentLoaded", () => {
  setSearch();
  doFetch("main/products/?page=0", "GET").then((data) => {
    renderItemList(data);
  });
  renderCartCount();
});

function setSearch() {
  document.querySelector("#searchInput").addEventListener("keyup", (e) => {
    if (e.keyCode === 13 && e.target.value) {
      search(e);
    }
  });
}

function search(e) {
  doFetch("main/products/?name=" + e.target.value, "GET").then((data) => {
    clearItemList();
    renderItemList(data);
    setItemsOpacity();
  });
}
