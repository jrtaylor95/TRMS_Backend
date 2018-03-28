"use strict";

function login() {
    let email = document.getElementById("inpt_loginEmail").value;
    let password = document.getElementById("inpt_loginPassword").value;

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            $("#div_body").load("app.html");
        }
    }

    xhr.open("POST", "login", true);
    xhr.send(JSON.stringify({"email": email, "password": password}));
}

function init() {
    $("#div_body").load("login.html #content", "", document.getElementById("btn_login").addEventListener("click", login));
}