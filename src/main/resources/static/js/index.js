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
    console.log("I called");
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

