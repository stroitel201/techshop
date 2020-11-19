"use strict";

window.onload = () => {
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
};
