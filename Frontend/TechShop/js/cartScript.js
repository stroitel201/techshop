"use strict";

document.addEventListener("DOMContentLoaded", function () {
  setLogout();
  setCheckBox();
  setOrderBtn();
  setClearBtn();
  setItemsInfo();
  setPersonalInfo();
});

function setOrderBtn() {
  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };
  let btn = document.querySelector(".ordrBtn");
  btn.addEventListener("click", () => {
    doFetch("user/cart", "GET", headers).then((data) => {
      if (data.itemsCount > 0) window.location.replace("payment.html");
    });
  });
}

function setCheckBox() {
  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };
  let deliveryIncluded = document.querySelector("#checkBox");
  deliveryIncluded.addEventListener("click", (e) => {
    console.log(e.target.checked);
    doFetch(
      "user/cart",
      "PUT",
      headers,
      JSON.stringify({ boolValue: e.target.checked })
    );
  });
}

function setClearBtn() {
  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };

  let btn = document.querySelector("#clear");
  btn.addEventListener("click", () => {
    doFetch("user/cart", "DELETE", headers).then((data) =>
      renderItemsInfo(data)
    );
  });
}
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
  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };

  doFetch("user/cart", "GET", headers).then((data) => {
    renderItemsInfo(data);
  });
}

function renderItemsInfo(data) {
  let totalPrice = document.querySelector("#totalPrice");
  let deliveryIncluded = document.querySelector("#checkBox");

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
}

function renderCartList(listOfItems) {
  clearItemList();
  listOfItems.forEach((item) => {
    console.log(item);
    renderCartItem(item);
  });
  renderRemoveBtns();
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
    btnRemove = document.createElement("button"),
    btnPlus = document.createElement("button"),
    btnMinus = document.createElement("button"),
    spanCount = document.createElement("span");

  btnRemove.classList.add("removeBtn");
  btnRemove.id = item.product.id;
  btnRemove.innerHTML = "X";

  btnMinus.classList.add("cartBtn", "minus");
  btnMinus.id = item.product.id;
  btnMinus.innerHTML = "-";

  btnPlus.classList.add("cartBtn", "plus");
  btnPlus.id = item.product.id;
  btnPlus.innerHTML = "+";

  spanCount.classList.add("value");
  spanCount.innerHTML = item.quantity;

  divBtn.classList.add("col-lg-6", "prodBtn");
  divBtn.append(btnRemove, btnMinus, spanCount, btnPlus);

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

function renderRemoveBtns() {
  const btns = document.querySelectorAll(".removeBtn");

  btns.forEach((element) => {
    element.addEventListener("click", (e) => {
      addItemToCartFetch(e.target.id, 0).then((data) => {
        if (data) renderItemsInfo(data);
        else window.location.replace(login.html);
      });
    });
  });
}

function renderMinusPlusBtns() {
  const plusBtns = document.querySelectorAll(".plus");

  plusBtns.forEach((element) => {
    element.addEventListener("click", (e) => {
      let span = e.target.previousElementSibling;
      let value = +span.innerHTML;
      addItemToCartFetch(e.target.id, value + 1).then((data) => {
        if (data) renderItemsInfo(data);
        else window.location.replace(login.html);
      });
    });
  });

  const minusBtns = document.querySelectorAll(".minus");

  minusBtns.forEach((element) => {
    element.addEventListener("click", (e) => {
      let span = e.target.nextElementSibling;
      let value = +span.innerHTML;
      addItemToCartFetch(e.target.id, value - 1).then((data) => {
        if (data) renderItemsInfo(data);
        else window.location.replace(login.html);
      });
    });
  });
}
