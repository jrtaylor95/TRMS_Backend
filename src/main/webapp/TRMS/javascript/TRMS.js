"use strict";

function checkLogin() {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            $("#div_body").load("app.html #content");
        }
    }

    xhr.open("GET", "/TRMS/login", true);
    xhr.send();
}

function login() {
    let email = document.getElementById("inpt_loginEmail").value;
    let password = document.getElementById("inpt_loginPassword").value;

    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            $("#div_body").load("app.html #content");
        }
    }

    xhr.open("POST", "/TRMS/login", true);
    xhr.send(JSON.stringify({"email": email, "password": password}));
}

function fillMyRequests() {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            let myRequestsTable = document.getElementById("tbl_myRequests");

            let requests = JSON.parse(xhr.responseText);

            requests.foreach(function(request) {
                let row = myRequestsTable.insertRow(-1);
                row.insertCell(0).innerHTML = requests
            })
        }
    }

    xhr.open("GET", "/TRMS/login", true);
    xhr.send();
}

function init() {
    checkLogin();

    $("#div_body").load("login.html #content", "", function() {document.getElementById("btn_login").addEventListener("click", login)});
}

window.onload = init();