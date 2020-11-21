"use strict";

window.onload = function () {
  renderMinusPlusBtns();
};

function renderMinusPlusBtns() {
  const plusBtns = document.querySelectorAll(".plus");

  plusBtns.forEach((element) => {
    element.onclick = () => {
      const arr = element.parentElement.children;
      let span = arr[1];
      let value = +span.innerHTML;
      span.innerHTML = value + 1;
    };
  });

  const minusBtns = document.querySelectorAll(".minus");

  minusBtns.forEach((element) => {
    element.onclick = () => {
      const arr = element.parentElement.children;
      let span = arr[1];
      let value = +span.innerHTML;
      if (value === 0) return;
      span.innerHTML = value - 1;
    };
  });
}
