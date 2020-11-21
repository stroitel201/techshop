"use strict";

document.addEventListener("DOMContentLoaded", function () {
  renderMinusPlusBtns();
});

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
