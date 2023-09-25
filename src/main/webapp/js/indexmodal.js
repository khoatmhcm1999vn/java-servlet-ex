// login modal
var loginModal = document.getElementById("loginModal");
var btnOpen_loginModal = document.getElementById("btn-open-loginModal");
var btnClose_loginModal = document.getElementById("btn-close-loginModal");

btnOpen_loginModal.addEventListener("click", open_loginModal);
btnClose_loginModal.addEventListener("click", close_loginModal);
window.addEventListener("click", outside_loginModal_Click);


function open_loginModal() {
    loginModal.style.display = "flex";
}

function close_loginModal() {
    loginModal.style.display = "none";
}

function outside_loginModal_Click(e) {
    if (e.target == loginModal) {
        loginModal.style.display = "none";
    }
}

