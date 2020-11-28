"use strict";

document.addEventListener("DOMContentLoaded", () => {
  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };
  setAddButton();
  doFetch("admin/products", "GET", headers).then((data) =>
    renderAdminItemList(data)
  );
});

function setBrowseBtns() {
  let btns = document.querySelectorAll(".browseBtn");
  btns.forEach((btn) =>
    btn.addEventListener("click", (e) => e.target.previousSibling.click())
  );
}

function setAddButton() {
  let headers = {
    Accept: "*/*",

    Authorization: getCookie("Authorization"),
  };
  document.querySelector("#addBtn").addEventListener("click", () => {
    let name = document.querySelector("#name"),
      desc = document.querySelector("#desc"),
      category = document.querySelector("#category"),
      price = document.querySelector("#price"),
      file = document.querySelector("#selectedFile");

    let data = new FormData();

    data.append("name", name.value);
    data.append("description", desc.value);
    data.append("category", category.value);
    data.append("price", +price.value);
    data.append("file", file.files[0]);
    console.log(data);
    doFetch("admin/products", "POST", headers, data).then((data) => {
      if (data) window.location.reload();
    });
  });
}

function renderAdminItemList(listOfItems) {
  clearItemList();
  listOfItems.forEach((item) => {
    console.log(item);
    renderAdminItem(item);
  });
  setSaveBtns();
  setBrowseBtns();
}

function clearItemList() {
  document.querySelector("#prodList").innerHTML = "";
}

function setSaveBtns() {
  let btns = document.querySelectorAll(".saveBtn");
  btns.forEach((btn) =>
    btn.addEventListener("click", (e) => {
      let headers = {
        Accept: "*/*",

        Authorization: getCookie("Authorization"),
      };

      let name = document.querySelector("#name" + e.target.id),
        desc = document.querySelector("#desc" + e.target.id),
        category = document.querySelector("#category" + e.target.id),
        price = document.querySelector("#price" + e.target.id),
        file = document.querySelector("#selectedFile" + e.target.id);

      let data = new FormData();

      data.append("name", name.value);
      data.append("description", desc.value);
      data.append("category", category.value);
      data.append("price", +price.value);
      if (file.files.length !== 0) data.append("file", file.files[0]);
      doFetch("admin/products/" + e.target.id, "PUT", headers, data).then(
        (data) => {
          if (data) window.location.reload();
        }
      );
    })
  );
}

function renderAdminItem(item) {
  let li = document.createElement("li"),
    divItem = document.createElement("div"),
    divImage = document.createElement("div"),
    img = document.createElement("img"),
    divDesc = document.createElement("div"),
    iCat = document.createElement("input"),
    iTitle = document.createElement("input"),
    iText = document.createElement("input"),
    divPrice = document.createElement("div"),
    iPrice = document.createElement("input"),
    divBtn = document.createElement("div"),
    btn = document.createElement("button"),
    iFile = document.createElement("input"),
    browseBtn = document.createElement("button");

  iFile.id = "selectedFile" + item.id;
  iFile.type = "file";
  iFile.style.display = "none";

  browseBtn.classList.add("buyBtn", "browseBtn");
  browseBtn.innerHTML = "Browse";
  browseBtn.id = item.id;

  btn.classList.add("buyBtn", "saveBtn");
  btn.innerHTML = "SAVE";
  btn.id = item.id;

  divBtn.classList.add("col-lg-6", "prodBtn");
  divBtn.append(btn, iFile, browseBtn);

  iPrice.placeholder = "Price: ";
  iPrice.id = "price" + item.id;
  iPrice.value = +item.price;

  divPrice.classList.add("col-lg-6", "price");
  divPrice.append(iPrice);

  iTitle.classList.add("title");
  iTitle.id = "name" + item.id;
  iTitle.value = item.name;

  iCat.classList.add("title");
  iCat.id = "category" + item.id;
  iCat.value = item.category;

  iText.classList.add("col-lg-12", "description");
  iText.id = "desc" + item.id;
  iText.value = item.description;

  divDesc.classList.add("col-lg-6", "row", "prodDesc");
  divDesc.append(iTitle, iCat, iText, divPrice, divBtn);

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
