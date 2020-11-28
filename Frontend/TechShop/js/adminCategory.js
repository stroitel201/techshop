"use strict";

document.addEventListener("DOMContentLoaded", () => {
  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };
  setAddButton();
  doFetch("admin/categories", "GET", headers).then((data) =>
    renderAdminCatList(data)
  );
});

function setAddButton() {
  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };

  document.querySelector("#addBtn").addEventListener("click", () => {
    let name = document.querySelector("#name");
    doFetch(
      "admin/categories",
      "POST",
      headers,
      JSON.stringify({ title: name.value })
    ).then((data) => {
      if (data) window.location.reload();
    });
  });
}

function renderAdminCatList(listOfCategories) {
  clearCatList();
  for (let i = 0; i < listOfCategories.length; i++) {
    let li = document.createElement("li"),
      div = document.createElement("div"),
      iTitle = document.createElement("input"),
      btn = document.createElement("button");

    btn.classList.add("buyBtn", "saveBtn");
    btn.innerHTML = "SAVE";
    btn.id = listOfCategories[i].id;

    div.classList.add("row", "catItem");

    iTitle.id = "name" + listOfCategories[i].id;
    iTitle.value = listOfCategories[i].title;

    div.append(iTitle, btn);
    li.append(div);
    document.querySelector("#categoryList").append(li);
  }
  setSaveCatBtns();
}

function clearCatList() {
  document.querySelector("#categoryList").innerHTML = "";
}

function setSaveCatBtns() {
  let btns = document.querySelectorAll(".saveBtn");
  btns.forEach((btn) =>
    btn.addEventListener("click", (e) => {
      let headers = {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: getCookie("Authorization"),
      };

      let name = document.querySelector("#name" + e.target.id);

      doFetch(
        "admin/categories/" + e.target.id,
        "PUT",
        headers,
        JSON.stringify({ title: name.value })
      ).then((data) => {
        if (data) window.location.reload();
      });
    })
  );
}
