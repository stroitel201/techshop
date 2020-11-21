"use strict";

document.addEventListener("DOMContentLoaded", function () {
  renderCartItems();
  renderMinusPlusBtns();
});

function renderCartItems() {
  let li = document.createElement("li"),
    divItem = document.createElement("div"),
    divImage = document.createElement("div"),
    img = document.createElement("img"),
    divDesc = document.createElement("div"),
    pTitle = document.createElement("p"),
    divPrice = document.createElement("div"),
    pPrice = document.createElement("p"),
    divBtn = document.createElement("div"),
    btnPlus = document.createElement("button"),
    btnMinus = document.createElement("button"),
    spanCount = document.createElement("span");

  btnMinus.classList.add("cartBtn", "minus");
  btnMinus.innerHTML = "-";

  btnPlus.classList.add("cartBtn", "plus");
  btnPlus.innerHTML = "+";

  spanCount.classList.add("value");
  spanCount.innerHTML = "6";

  divBtn.classList.add("col-lg-6", "prodBtn");
  divBtn.append(btnMinus, spanCount, btnPlus);

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

function renderMinusPlusBtns() {
  const plusBtns = document.querySelectorAll(".plus");

  plusBtns.forEach((element) => {
    element.onclick = () => {
      let span = element.previousElementSibling;
      let value = +span.innerHTML;
      span.innerHTML = value + 1;
    };
  });

  const minusBtns = document.querySelectorAll(".minus");

  minusBtns.forEach((element) => {
    element.onclick = () => {
      let span = element.nextElementSibling;
      let value = +span.innerHTML;
      if (value == 0) return;
      span.innerHTML = value - 1;
    };
  });
}
