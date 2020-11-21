"use strict";

$(document).ready(function () {
  $(".input").focus(function () {
    let label = $(this).parent().find(".label-txt");
    label.addClass("label-active");
    label.css("color", "rgb(120, 120, 120)");
  });

  $(".input").focusout(function () {
    if ($(this).val() == "") {
      let label = $(this).parent().find(".label-txt");
      label.removeClass("label-active");
    }
  });

  document.querySelector(".formBtn").addEventListener("click", () => {
    let arr = document.querySelectorAll(".input");
    console.log(arr);
    let valid = true;
    arr.forEach((element) => {
      if (element.value === "") {
        element.parentNode.querySelector(".label-txt").style.color = "#b00000";
        valid = false;
      }
    });
    if (valid) window.location.replace("index.html");
  });
});
