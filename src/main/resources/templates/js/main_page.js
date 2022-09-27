function out(any) {console.log(any)}

out("I am inside main_page.js")

function addMovieCard(){
    out("It is working")
}

const addMovie_button = document.getElementById("newMovie_button")

addMovie_button.addEventListener('click', addMovieCard)