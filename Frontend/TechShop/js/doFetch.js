"use strict";

function doFetch(
  url,
  method,
  headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
  },
  body = null
) {
  let host = "http://localhost:8080/api/v1/";

  return fetch(host + url, {
    method,
    headers,
    body,
  }).then((data) => {
    if (data.status != 200 || data.status === 403)
      window.location.replace("login.html");
    else return data.json();
  });
}
