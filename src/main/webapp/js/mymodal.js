
// add todo modal
var addTodoModal = document.getElementById("addTodoModal");
var btnOpen_addTodoModal = document.getElementById("btn-open-addTodoModal");
var btnClose_addTodoModal = document.getElementById("btn-close-addTodoModal");

btnOpen_addTodoModal.addEventListener("click", open_addTodoModal);
btnClose_addTodoModal.addEventListener("click", close_addTodoModal);
window.addEventListener("click", outside_addTodoModal_Click);


function open_addTodoModal() {
    addTodoModal.style.display = "flex";
}

function close_addTodoModal() {
    addTodoModal.style.display = "none";
}

function outside_addTodoModal_Click(e) {
    if (e.target == addTodoModal) {
        addTodoModal.style.display = "none";
    }
}

// add tag modal
var addTagModal = document.getElementById("addTagModal");
var btnOpen_addTagModal = document.getElementById("btn-open-addTagModal");
var btnClose_addTagModal = document.getElementById("btn-close-addTagModal");

btnOpen_addTagModal.addEventListener("click", open_addTagModal);
btnClose_addTagModal.addEventListener("click", close_addTagModal);
window.addEventListener("click", outside_addTagModal_Click);


function open_addTagModal() {
    addTagModal.style.display = "flex";
}

function close_addTagModal() {
    addTagModal.style.display = "none";
}

function outside_addTagModal_Click(e) {
    if (e.target == addTagModal) {
        addTagModal.style.display = "none";
    }
}




// edit todo modal
var editTodoModal = document.getElementById("editTodoModal");
// var btnOpen_editTodoModal = document.getElementById("btn-open-editTodoModal");
var btnClose_editTodoModal = document.getElementById("btn-close-editTodoModal");

// btnOpen_editTodoModal.addEventListener("click", open_editTodoModal);
btnClose_editTodoModal.addEventListener("click", close_editTodoModal);
window.addEventListener("click", outside_editTodoModal_Click);


// function open_editTodoModal() {
//     editTodoModal.style.display = "flex";
// }

function close_editTodoModal() {
     editTodoModal.style.display = "none";
}

function outside_editTodoModal_Click(e) {
     if (e.target == editTodoModal) {
         editTodoModal.style.display = "none";
     }
}



// edit tag modal
var editTagModal = document.getElementById("editTagModal");
// var btnOpen_editTagModal = document.getElementById("btn-open-editTagModal");
var btnClose_editTagModal = document.getElementById("btn-close-editTagModal");

// btnOpen_editTagModal.addEventListener("click", open_editTagModal);
btnClose_editTagModal.addEventListener("click", close_editTagModal);
window.addEventListener("click", outside_editTagModal_Click);


// function open_editTagModal() {
//     editTagModal.style.display = "flex";
// }

function close_editTagModal() {
     editTagModal.style.display = "none";
}

function outside_editTagModal_Click(e) {
     if (e.target == editTagModal) {
         editTagModal.style.display = "none";
     }
}

// filter todo modal
var filterTodoModal = document.getElementById("filterTodoModal");
var btnOpen_filterTodoModal = document.getElementById("btn-open-filterTodoModal");
var btnClose_filterTodoModal = document.getElementById("btn-close-filterTodoModal");

btnOpen_filterTodoModal.addEventListener("click", open_filterTodoModal);
btnClose_filterTodoModal.addEventListener("click", close_filterTodoModal);
window.addEventListener("click", outside_filterTodoModal_Click);


function open_filterTodoModal() {
    filterTodoModal.style.display = "flex";
}

function close_filterTodoModal() {
    filterTodoModal.style.display = "none";
}

function outside_filterTodoModal_Click(e) {
    if (e.target == filterTodoModal) {
        filterTodoModal.style.display = "none";
    }
}


// login modal
// var loginModal = document.getElementById("loginModal");
// var btnOpen_loginModal = document.getElementById("btn-open-loginModal");
// var btnClose_loginModal = document.getElementById("btn-close-loginModal");

// btnOpen_loginModal.addEventListener("click", open_loginModal);
// btnClose_loginModal.addEventListener("click", close_loginModal);
// window.addEventListener("click", outside_loginModal_Click);


// function open_loginModal() {
//     loginModal.style.display = "flex";
// }

// function close_loginModal() {
//     loginModal.style.display = "none";
// }

// function outside_loginModal_Click(e) {
//     if (e.target == loginModal) {
//         loginModal.style.display = "none";
//     }
// }

// sign up modal
// var signupModal = document.getElementById("signupModal");
// var btnOpen_signupModal = document.getElementById("btn-open-signupModal");
// var btnClose_signupModal = document.getElementById("btn-close-signupModal");

// btnOpen_signupModal.addEventListener("click", open_signupModal);
// btnClose_signupModal.addEventListener("click", close_signupModal);
// window.addEventListener("click", outside_signupModal_Click);


// function open_signupModal() {
//     signupModal.style.display = "flex";
// }

// function close_signupModal() {
//     signupModal.style.display = "none";
// }

// function outside_signupModal_Click(e) {
//     if (e.target == signupModal) {
//         signupModal.style.display = "none";
//     }
// }