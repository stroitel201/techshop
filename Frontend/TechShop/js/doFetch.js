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
    if (!data.ok) return null;
    return data.json();
  });
}

function addItemToCartFetch(id, count) {
  let headers = {
    Accept: "application/json",
    "Content-Type": "application/json",
    Authorization: getCookie("Authorization"),
  };
  console.log(+id, count);
  return doFetch(
    "user/cart",
    "POST",
    headers,
    JSON.stringify({
      id: +id,
      quantity: count,
    })
  );
}
