"use strict";

window.getCookie = function (name) {
  const cookies = document.cookie.split("; ");
  const match = cookies.find((item) => item.startsWith(name));
  const token = match.split("=")[1];
  return token;
};

function setBuyBtns() {
  let btns = document.querySelectorAll(".buyBtn");
  btns.forEach((btn) =>
    btn.addEventListener("click", (e) => {
      addItemToCartFetch(e.target.id, 1).then((data) => {
        if (data == null) window.location.replace("login.html");
        else renderCartCount(data.itemsCount);
      });
    })
  );
}

function renderCartCount(count) {
  if (count) document.querySelector("#counter").innerHTML = count;
  else {
    let headers = {
      Accept: "application/json",
      "Content-Type": "application/json",
      Authorization: getCookie("Authorization"),
    };

    doFetch("user/cart", "GET", headers).then((data) => {
      if (data) document.querySelector("#counter").innerHTML = data.itemsCount;
      else document.querySelector("#counter").innerHTML = 0;
    });
  }
}

function clearItemList() {
  document.querySelector("#prodList").innerHTML = "";
}

function renderItemList(listOfItems) {
  clearItemList();
  listOfItems.forEach((item) => {
    console.log(item);
    renderItem(item);
  });
  setBuyBtns();
  setItemsOpacity();
}

function renderItem(item) {
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
    btn = document.createElement("button");

  btn.classList.add("buyBtn");
  btn.innerHTML = "BUY";
  btn.id = item.id;

  divBtn.classList.add("col-lg-6", "prodBtn");
  divBtn.append(btn);

  pPrice.innerHTML = "Price: " + +item.price;

  divPrice.classList.add("col-lg-6", "price");
  divPrice.append(pPrice);

  pTitle.classList.add("title");
  pTitle.innerHTML = item.name;

  divText.classList.add("col-lg-12", "description");
  divText.innerHTML = item.description;

  divDesc.classList.add("col-lg-6", "row", "prodDesc");
  divDesc.append(pTitle, divText, divPrice, divBtn);

  img.classList.add("img-fluid", "prodPic");
  img.src = item.pictureRef;

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
      arr[2].style.opacity = 1;
      arr[3].style.opacity = 1;
    };
  });

  items.forEach((element) => {
    element.onmouseleave = () => {
      const arr = element.children[1].children;
      arr[2].style.opacity = 0;
      arr[3].style.opacity = 0;
    };
  });
}
