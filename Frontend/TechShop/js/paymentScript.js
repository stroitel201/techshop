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

  document.querySelector("#payButton").addEventListener("click", () => {
    let valid = true;
    let arr = document.querySelectorAll(".input");
    console.log(arr);
    arr.forEach((element) => {
      if (element.value === "") {
        element.parentNode.querySelector(".label-txt").style.color = "#b00000";
        valid = false;
      }
    });
    if (valid) {
      let headers = {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: getCookie("Authorization"),
      };

      doFetch("user/order", "POST", headers).then((data) =>
        window.location.replace("index.html")
      );
    }
  });

  document.querySelector("#cancelButton").addEventListener("click", () => {
    window.location.replace("cart.html");
  });
});
