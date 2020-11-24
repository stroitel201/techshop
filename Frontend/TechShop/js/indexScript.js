"use strict";

document.addEventListener("DOMContentLoaded", () => {
  fetch("http://localhost:8080/api/v1/main/categories/", {
    mode: "no-cors",
    method: "GET",
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  })
    .then((data) => data.json())
    .then((data) => {
      renderCategoryList(data);
    });
});

function setSearch() {
  document.querySelector("#searchInput").addEventListener("keyup", (e) => {
    if (e.keyCode === 13 && e.target.value) {
      search(e.target.value);
    }
  });
}

function search(value) {
  alert(value);
}

function renderItemList(listOfItems) {
  let li = document.createElement("li"),
    divItem = document.createElement("div"),
    divImage = document.createElement("div"),
    img = document.createElement("img"),
    divDesc = document.createElement("div"),
    pTitle = document.createElement("p"),
    divPrice = document.createElement("div"),
    pPrice = document.createElement("p"),
    divBtn = document.createElement("div"),
    btn = document.createElement("button");

  btn.classList.add("buyBtn");
  btn.innerHTML = "BUYs";

  divBtn.classList.add("col-lg-6", "prodBtn");
  divBtn.append(btn);

  pPrice.innerHTML = "Price: " + 2500 + "s";

  divPrice.classList.add("col-lg-6", "price");
  divPrice.append(pPrice);

  pTitle.classList.add("title");
  pTitle.innerHTML = "Mi Notebook Pro 2018 Scripted";

  divDesc.classList.add("col-lg-6", "row", "prodDesc");
  let description =
    "Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex  officiis natus eligendi. Perspiciatis saepe at tempore optio  doloribus nam itaque illo. Eaque natus voluptatibus quasi minima  eos eum recusandae similique.";
  divDesc.append(pTitle, description, divPrice, divBtn);

  img.classList.add("img-fluid", "prodPic");
  let ref =
    "https://www.gizmochina.com/wp-content/uploads/2019/03/XIDU-PhilBook-Max-Laptop-600x600.jpg";
  img.src = ref;

  divImage.classList.add("col-lg-5", "image");
  divImage.append(img);

  divItem.classList.add("col-lg-12", "row", "prodItem");
  divItem.tabIndex = -1;
  divItem.append(divImage, divDesc);

  li.append(divItem);

  document.querySelector("#prodList").append(li);
}

function renderCategoryList(listOfCategories) {
  for (let i = 0; i < listOfCategories.length; i++) {
    let li = document.createElement("li"),
      div = document.createElement("div");
    div.classList.add("row", "catItem");
    div.innerHTML = listOfCategories[i].title;
    li.append(div);
    document.querySelector("#categoryList").append(li);
  }
}

function setItemsOpacity() {
  const items = document.querySelectorAll(".prodItem");

  items.forEach((element) => {
    element.onmouseover = () => {
      const arr = element.children[1].children;
      console.log(arr);
      arr[1].style.opacity = 1;
      arr[2].style.opacity = 1;
    };
  });

  items.forEach((element) => {
    element.onmouseleave = () => {
      const arr = element.children[1].children;
      console.log(arr);
      arr[1].style.opacity = 0;
      arr[2].style.opacity = 0;
    };
  });
}
