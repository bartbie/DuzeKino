function out(any){
    console.log(any)
}

const rgForm = document.querySelector(".registrationForm")
const loginForm = document.querySelector(".loginForm")

const rgFormlinkTag = document.querySelector(".rgFormlinkTag")
const loginFormlinkTag = document.querySelector(".loginFormlinkTag")

const headingOfForm = document.querySelector(".headingOfForm")


// Type Writing varriable and function
var i = 0;
var txt = 'Kino XP Theater'; /* The text */
var speed = 150; /* The speed/duration of the effect in milliseconds */

function typeWriter() {

    if (i < txt.length) {
        headingOfForm.innerHTML += txt.charAt(i);
        i++;
        setTimeout(typeWriter, speed);
    }
}

typeWriter();

// Form switching function

function switchToRGForm() {

    //For type writing
    headingOfForm.innerHTML = "";
    i = 0;
    typeWriter();

    //Changes in CSS
    loginForm.style.visibility = "hidden";
    rgForm.style.visibility = "visible";
}

rgFormlinkTag.addEventListener("click", switchToRGForm)


function switchTologinForm() {

    //For type writing
    headingOfForm.innerHTML = "";
    i = 0;
    typeWriter();

    //Changes in CSS
    rgForm.style.visibility = "hidden";
    loginForm.style.visibility = "visible";
}

loginFormlinkTag.addEventListener("click", switchTologinForm)







//Fetching started for CURD operations

const loginBtn = document.getElementById("login-btn")
const registerBtn = document.getElementById("register-btn")

const userInpFld = document.querySelector(".userInpFld")
const passInpFld = document.querySelector(".passInpFld")
const regUserFld = document.querySelector(".regUserFld")
const regEmailFld = document.querySelector(".regEmailFld")
const regPassFld = document.querySelector(".regPassFld")
const confirmPassFld = document.querySelector(".confirmPassFld")


// handling error msg

let lgAlertMsg = document.querySelector(".lgAlertMsg")
let rgAlertMsg = document.querySelector(".rgAlertMsg")


userInpFld.addEventListener("click", function (){
    lgAlertMsg.innerHTML = ""
})
passInpFld.addEventListener("click", function (){
    lgAlertMsg.innerHTML = ""
})
regUserFld.addEventListener("click", function (){
    rgAlertMsg.innerHTML = ""
})
regEmailFld.addEventListener("click", function (){
    rgAlertMsg.innerHTML = ""
})
regPassFld.addEventListener("click", function (){
    rgAlertMsg.innerHTML = ""
})
confirmPassFld.addEventListener("click", function (){
    rgAlertMsg.innerHTML = ""
})




// Register a new user

registerBtn.addEventListener("click", function (e) {
    e.preventDefault();

    out("Before registration Fetch")
    fetch("http://localhost:8080/registration", {
        method: "POST",
        body: JSON.stringify({
            username: regUserFld.value,
            email: regEmailFld.value,
            password: regPassFld.value
        }),
        headers: {
            "Content-type": "application/json"
        }
    }).then(function (response) {
        return response.json()
    }).then(function (data) {
        if (data.id) {
            window.location.href = "main_page.html"
        } else {
            regUserFld.value = ""
            regEmailFld.value = ""
            regPassFld.value = ""
            confirmPassFld.value = ""
            rgAlertMsg.innerHTML = "username is already exist"
            console.log(data)
        }
    })
})



let loggedInUser = document.querySelector(".loggedInUsername");

//Logging In'
loginBtn.addEventListener("click", function (e) {
    e.preventDefault();

    // out("Before Fetch")
    fetch("http://localhost:8080/login", {
        method: "POST",
        body: JSON.stringify({
            username: userInpFld.value,
            password: passInpFld.value
        }),
        headers: {
            "Content-type": "application/json"
        }
    })
        .then(function (response) {
            return response.json()
        })
        .then(async function (data) {
            if (data.id) {
                window.location.href = "main_page.html"
            } else {
                userInpFld.value = ""
                passInpFld.value = ""
                lgAlertMsg.innerHTML = "Invalid user credentials"
                out(lgAlertMsg)

            }

        })
})

loginBtn.addEventListener("click", function(){
    loggedInUser.innerHTML = "fuck u"
})





// end
