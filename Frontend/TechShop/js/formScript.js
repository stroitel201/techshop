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
});
