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
        "auth/register",
        "POST",
        headers,
        JSON.stringify(getRegisterCredentials())
      ).then((data) => {
        if (data.username == getRegisterCredentials().username)
          window.location.replace("login.html");
        else window.location.replace("register.html");
      });
    }
  });
}

function getRegisterCredentials() {
  let credentials = {},
    inputs = [].slice.call(document.querySelectorAll(".input"));

  credentials.username = inputs[0].value;
  credentials.password = inputs[1].value;
  credentials.email = inputs[2].value;
  credentials.phone = inputs[3].value;
  credentials.address = inputs[4].value;
  credentials.cityAndRegion = inputs[5].value;
  return credentials;
}
