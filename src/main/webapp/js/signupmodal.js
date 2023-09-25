// sign up modal
var signupModal = document.getElementById("signupModal");
var btnOpen_signupModal = document.getElementById("btn-open-signupModal");
var btnClose_signupModal = document.getElementById("btn-close-signupModal");

btnOpen_signupModal.addEventListener("click", open_signupModal);
btnClose_signupModal.addEventListener("click", close_signupModal);
window.addEventListener("click", outside_signupModal_Click);


function open_signupModal() {
    signupModal.style.display = "flex";
}

function close_signupModal() {
    signupModal.style.display = "none";
}

function outside_signupModal_Click(e) {
    if (e.target == signupModal) {
        signupModal.style.display = "none";
    }
}