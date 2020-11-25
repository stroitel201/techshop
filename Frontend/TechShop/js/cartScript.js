"use strict";

document.addEventListener("DOMContentLoaded", function () {
  setLogout();
  setItemsInfo();
  setPersonalInfo();
});

function setLogout() {
  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };
  let logout = document.querySelector("#logout");
  logout.addEventListener("click", () => {
    doFetch("user/logout", "POST", headers).then(() =>
      window.location.replace("login.html")
    );
  });
}

async function setPersonalInfo() {
  let name = document.querySelector("#name");
  let email = document.querySelector("#email");
  let phone = document.querySelector("#phone");
  let city = document.querySelector("#address");
  let street = document.querySelector("#street");
  let country = document.querySelector("#country");

  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };
  let person = await doFetch("user/", "GET", headers);
  let contacts = await doFetch("user/contacts", "GET", headers);

  if (person && contacts) {
    console.log(contacts);
    console.log(city);
    name.innerHTML = person.username;
    email.innerHTML = person.email;
    phone.innerHTML = contacts.phone;
    city.value = contacts.address;
    country.value = contacts.cityAndRegion;
  } else window.location.replace("login.html");
}

function setItemsInfo() {
  let totalPrice = document.querySelector("#totalPrice");
  let deliveryIncluded = document.querySelector("#checkBox");
  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };

  doFetch("user/cart", "GET", headers).then((data) => {
    if (data) {
      renderCartList(data.list);
      renderCartCount(data.itemsCount);
      totalPrice.innerHTML = "Total price: " + data.itemsCost;
      if (data.deliveryIncluded) deliveryIncluded.checked = true;
      else deliveryIncluded.checked = false;
    } else {
      totalPrice.innerHTML = "Total price: " + 0;
      deliveryIncluded.checked = false;
      renderCartCount(0);
    }
  });
}

function renderCartList(listOfItems) {
  clearItemList();
  listOfItems.forEach((item) => {
    console.log(item);
    renderCartItem(item);
  });
  renderMinusPlusBtns();
}

function renderCartItem(item) {
  let li = document.createElement("li"),
    divItem = document.createElement("div"),
    divImage = document.createElement("div"),
    img = document.createElement("img"),
    divDesc = document.createElement("div"),
    pTitle = document.createElement("p"),
    divText = document.createElement("div"),
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
  spanCount.innerHTML = item.quantity;

  divBtn.classList.add("col-lg-6", "prodBtn");
  divBtn.append(btnMinus, spanCount, btnPlus);

  pPrice.innerHTML = "Price: " + item.product.price;

  divPrice.classList.add("col-lg-6", "price");
  divPrice.append(pPrice);

  pTitle.classList.add("title");
  pTitle.innerHTML = item.product.name;

  divText.classList.add("col-lg-12", "description");
  divText.innerHTML = item.product.description;

  divDesc.classList.add("col-lg-6", "row", "prodDesc");
  divDesc.append(pTitle, divText, divPrice, divBtn);

  img.classList.add("img-fluid", "prodPic");
  img.src = item.product.pictureRef;

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
