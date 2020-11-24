"use strict";

document.addEventListener("DOMContentLoaded", () => {
  setFormButton();
  renderCartCount();
});

function setFormButton() {
  let button = document.querySelector("#formButton");

  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
  };

  button.addEventListener("click", (e) => {
    let arr = document.querySelectorAll(".input");
    let valid = true;
    arr.forEach((element) => {
      if (element.value === "") {
        element.parentNode.querySelector(".label-txt").style.color = "#b00000";
        valid = false;
      }
    });
    if (valid) {
      doFetch(
        "auth/login",
        "POST",
        headers,
        JSON.stringify(getCredentials())
      ).then((data) => {
        console.log(data);
        tokenCookie(data);
        window.location.replace("index.html");
      });
    }
  });
}

function getCredentials() {
  let credentials = {},
    inputs = [].slice.call(document.querySelectorAll(".input"));

  credentials.username = inputs[0].value;
  credentials.password = inputs[1].value;
  return credentials;
}

function tokenCookie(data) {
  document.cookie = "Authorization=Bearer " + data.token + "; path=/";
}
