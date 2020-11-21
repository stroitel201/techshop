"use strict";

window.onload = () => {
  setItemsOpacity();
  renderCategoryList();
};

function renderCategoryList(listOfCategories) {
  for (let i = 0; i < 2; i++) {
    let li = document.createElement("li");
    let div = document.createElement("div");
    div.classList.add("row");
    div.classList.add("catItem");
    div.innerHTML = "scripted";
    li.append(div);
    document.querySelector("#categoryList").append(li);

    // let scripted = "scripted";
    // document.querySelector("#categoryList").innerHTML += `<li>
    //   <div class="row catItem">${scripted}</div>
    // </li>`;
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
