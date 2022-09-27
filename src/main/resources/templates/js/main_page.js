function out(any) {console.log(any)}
out("I am inside main_page.js")

//This method takes a string as parameter and converts it into html (i stole it from a tutorial. You can see the  function in action at line 15. work in progress)
function elementFromHtml(element){
    const template = document.createElement("template")

    //This method removes all the empty spaces in the string
    template.innerHTML = element.trim();
    //Line 11 should actually convert hte string 'element' into html elements
    return template.content.firstElementChild;
}

function addMovieCard(){
    const movieCard = elementFromHtml('<div class="movieCard">\n' +
        '            \n' +
        '            <img src="image/shrek_movie.jpg" class="movie_image">\n' +
        '            <div class="card_info">\n' +
        '                <h1 class="movieTitle">SHREK</h1>\n' +
        '\n' +
        '                <div class="importantMovieInfo">\n' +
        '                    <h4 class="movieInfo">2001</h4>\n' +
        '                    <h4 class="movieInfo">|</h4>\n' +
        '                    <h4 class="movieInfo">3+</h4>\n' +
        '                    <h4 class="movieInfo">|</h4>\n' +
        '                    <h4 class="movieInfo">90 minutes</h4>\n' +
        '                </div>\n' +
        '\n' +
        '                <h4 class="movieInfoHeader">Description:</h4>\n' +
        '                <h4 class="movieInfo">This is going to be the description of the film </h4>\n' +
        '\n' +
        '                <h4 class="movieInfoHeader">Cast:</h4>\n' +
        '                <h4 class="movieInfo"> I\'ve Never Seen Star Wars </h4>\n' +
        '\n' +
        '                <div class="cardButton_div">\n' +
        '                    <button class="button" id="edit_button">Edit</button>\n' +
        '                    <button class="button" id="delete_button">Delete</button>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>')

    //Line 42 stores in a varibale the <div> called 'section_center' (line 32 in the html file)
    const elementToAppendTO = document.querySelector('.section_center');
    //Now i can append the movie card inside the section_center div so that can follow its styling
    elementToAppendTO.appendChild(movieCard)
}

const button = document.getElementById("newMovie_button")
button.addEventListener('click', addMovieCard)