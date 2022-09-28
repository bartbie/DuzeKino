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

function clear() {
    document.getElementById("popupTitle").value = ""
    document.getElementById("popupYear").value = ""
    document.getElementById("popupPG_id").value = ""
    document.getElementById("popupLenght").value = ""
    document.getElementById("popupCast_id").value = ""
    document.getElementById("popupDescription").value = ""
}

function getInputValue(){
    /*
    var inputValue = document.getElementById("popupTitle").value
    out(inputValue)
    return inputValue
     */

    let input = [];
    input['title'] = document.getElementById("popupTitle").value
    input['year'] = document.getElementById("popupYear").value
    input['pg'] = document.getElementById("popupPG_id").value
    input['duration'] = document.getElementById("popupLenght").value
    input['cast'] = document.getElementById("popupCast_id").value
    input['description'] = document.getElementById("popupDescription").value


    return input
}

//We definitely need paramater to pass in the movieCard variable. maybe something like: document.getElementById("movieYear").setAttribute(variableName)
function addMovieCard(){
    const movieCard = elementFromHtml('<div class="movieCard">\n' +
        '            \n' +
        '            <img src="image/shrek_movie.jpg" class="movie_image">\n' +
        '            <div class="card_info">\n' +
        '                <h1 class="movieTitle" id="movie">' + getInputValue().title+ '</h1>\n' +
        '\n' +
        '                <div class="importantMovieInfo">\n' +
        '                    <h4 class="movieInfo" id="inputYear">'+getInputValue().year+'</h4>\n' +
        '                    <h4 class="movieInfo">|</h4>\n' +
        '                    <h4 class="movieInfo" id="inputPG">'+getInputValue().pg +'</h4>\n' +
        '                    <h4 class="movieInfo">|</h4>\n' +
        '                    <h4 class="movieInfo" id="inputLength">'+getInputValue().duration + ' minutes' + '</h4>\n' +
        '                </div>\n' +
        '\n' +
        '                <h4 class="movieInfoHeader">Cast:</h4>\n' +
        '                <h4 class="movieInfo" id="inputCast">'+getInputValue().cast +'</h4>\n' +
        '\n' +
        '                <h4 class="movieInfoHeader">Description:</h4>\n' +
        '                <h4 class="movieInfo" id="inputDescription">'+getInputValue().description +'</h4>\n' +
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

//KEV AND MADALIN

let popup = document.getElementById('popup');

function openPopup(){
    popup.classList.add('open-popup')
}
function closePopup(){
    popup.classList.remove('open-popup')
}

const newMoviePopup_btn = document.getElementById("newMovie_button")
const editMoviePopup_btn = document.getElementById("editMovie_button")
const saveMoviePopup_btn = document.getElementById("saveMovie_btn")
const cancelPopup_btn = document.getElementById("cancel_btn")


newMoviePopup_btn.addEventListener('click', openPopup)

saveMoviePopup_btn.addEventListener('click', addMovieCard)
saveMoviePopup_btn.addEventListener('click', closePopup)
saveMoviePopup_btn.addEventListener('click', clear)

cancelPopup_btn.addEventListener('click', closePopup)

editMoviePopup_btn.addEventListener('click', openPopup)




