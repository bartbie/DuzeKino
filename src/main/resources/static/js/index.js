const rgForm = document.querySelector(".registrationForm")
const loginForm = document.querySelector(".loginForm")

const rgFormlinkTag = document.querySelector(".rgFormlinkTag")
const loginFormlinkTag = document.querySelector(".loginFormlinkTag")

const headingOfForm = document.querySelector(".headingOfForm")

const inpFld = document.querySelector(".inpFld")


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

function switchToRGForm(){

    //For type writing
    headingOfForm.innerHTML = "";
    i = 0;
    typeWriter();

    //Changes in CSS
    loginForm.style.visibility = "hidden";
    rgForm.style.visibility = "visible";
    inpFld.value = "";
}
rgFormlinkTag.addEventListener("click", switchToRGForm)


function switchTologinForm(){

    //For type writing
    headingOfForm.innerHTML = "";
    i = 0;
    typeWriter();

    //Changes in CSS
    rgForm.style.visibility = "hidden";
    loginForm.style.visibility = "visible";
    inpFld.value = "";
}
loginFormlinkTag.addEventListener("click", switchTologinForm)


const userUrl = "http://"


//Fetching
const out = (any) => console.log(any);


// const userUrl = "http://localhost:9090/users"





// function fetchUsers(url){
//
//     return fetch(url).then(response => response.json())
// }
//
// async function doFetchUser(){
//     let users = await fetchUsers(userUrl);
//     out(users)
//
//     users.forEach(f => out(f));
// }
//
// doFetchUser();




// function loginJsonObj(e){
//     e.preventDefault()
//     data = {
//         "username" : userInpFld.value,
//         "password" : passInpFld.value
//     }
//     const jsonString = JSON.stringify(data)
//
//     out(jsonString)
// }
//
// loginBtn.addEventListener("click", loginJsonObj)
//
//
// function registerJsonObj(e){
//     e.preventDefault();
//     if(regPassFld.value === confirmPassFld.value){
//         data = {
//             "username" : regUserFld.value,
//             "email" : regEmailFld.value,
//             "password" : regPassFld.value,
//             "confirmPass" : confirmPassFld.value
//         }
//         const jsonString = JSON.stringify(data)
//         out(jsonString)
//     }
//     else {
//         out("Passwords do not match")
//     }
//
// }
//
// registerBtn.addEventListener("click", registerJsonObj)

const loginBtn = document.getElementById("login-btn")
const registerBtn = document.getElementById("register-btn")

const userInpFld = document.querySelector(".userInpFld")
const passInpFld = document.querySelector(".passInpFld")
const regUserFld = document.querySelector(".regUserFld")
const regEmailFld = document.querySelector(".regEmailFld")
const regPassFld = document.querySelector(".regPassFld")
const confirmPassFld = document.querySelector(".confirmPassFld")


registerBtn.addEventListener("click", function (e){
    e.preventDefault();

    out("Before Fetch")
    fetch("http://localhost:9090/user", {
        method: "POST",
        body: JSON.stringify({
            username:regUserFld.value,
            email: regEmailFld.value,
            password:regPassFld.value
        }),
        headers: {
            "Content-type": "application/json"
        }
    }).then(function (response){
        return response.json()
    }).then(function (data){
        console.log(data)
    })
})

loginBtn.addEventListener("click", function (e){
    e.preventDefault();

    out("Before Fetch")
    fetch("http://localhost:9090/login", {
        method: "POST",
        body: JSON.stringify({
            username:userInpFld.value,
            password:passInpFld.value
        }),
        headers: {
            "Content-type": "application/json"
        }
    }).then(function (response){
        return response.json()
    }).then(function (data){
        console.log(data)
    })
})



// end
