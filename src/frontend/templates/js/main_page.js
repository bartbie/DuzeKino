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

let popupMovieTitle =document.getElementById("popupTitle")
let popupYear = document.getElementById("popupYear")
let popupPG_id = document.getElementById("popupPG_id")
let popupLength = document.getElementById("popupLength")
let popupCast_id = document.getElementById("popupCast_id")
let popupDescription = document.getElementById("popupDescription")

function toClosest(integer) {
    if (integer >= 17) {
        return "SEVENTEEN";
    } else if (integer >= 15) {
        return "FIFTEEN";
    } else if (integer >= 13) {
        return "THIRTEEN";
    } else {
        return "ANY";
    }
}

function clear() {
    popupMovieTitle.value = ""
    popupYear.value = ""
    popupPG_id.value = ""
    popupLength.value = ""
    popupCast_id.value = ""
    popupDescription.value = ""
}

function setInputValue(){

    let input = [];
    input['title'] = popupMovieTitle.value
    input['year'] = popupYear.value
    input['pg'] = popupPG_id.value
    input['duration'] = popupLength.value
    input['cast'] = popupCast_id.value
    input['description'] =  popupDescription.value

    return input
}


function movieFetch(method, body) {
    return fetch("http://localhost:8080/api/v1/Movie", {
        method: method,
        body: JSON.stringify(body),
        headers: {
            "Content-type": "application/json"
        }
    })
}


//We definitely need paramater to pass in the movieCard variable. maybe something like: document.getElementById("movieYear").setAttribute(variableName)
//We definitely need paramater to pass in the movieCard variable. maybe something like: document.getElementById("movieYear").setAttribute(variableName)
function addMovieCard(){
    const movieCard = elementFromHtml('<div class="movieCard">\n' +
        '            \n' +
        '            <img src="image/shrek_movie.jpg" class="movie_image">\n' +
        '            <div class="card_info">\n' +
        '                <h1 class="movieTitle" id="movie">' + setInputValue().title+ '</h1>\n' +
        '\n' +
        '                <div class="importantMovieInfo">\n' +
        '                    <h4 class="movieInfo" id="inputYear">'+setInputValue().year+'</h4>\n' +
        '                    <h4 class="movieInfo">|</h4>\n' +
        '                    <h4 class="movieInfo" id="inputPG">'+ "PG: "+setInputValue().pg +' </h4>\n' +
        '                    <h4 class="movieInfo">|</h4>\n' +
        '                    <h4 class="movieInfo" id="inputLength">'+setInputValue().duration + ' minutes' + '</h4>\n' +
        '                </div>\n' +
        '\n' +
        '                <h4 class="movieInfoHeader">Cast:</h4>\n' +
        '                <h4 class="movieInfo" id="inputCast">'+setInputValue().cast +'</h4>\n' +
        '\n' +
        '                <h4 class="movieInfoHeader">Description:</h4>\n' +
        '                <h4 class="movieInfo" id="inputDescription">'+setInputValue().description +'</h4>\n' +
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

function saveMovie(event){
    event.preventDefault()

    out("before fetch")
    fetch("http://localhost:8080/api/v1/Movie", {
        method: "POST",
        body: JSON.stringify({
            title: popupMovieTitle.value,
            description: popupDescription.value,
            year: popupYear.value,
            length: `PT${popupLength.value}M`,
            rating: toClosest(popupPG_id.value)
        }),
        headers: {
            "Content-type": "application/json"
        }
    }).then(function (response){
        return response.json()
    }).then(function (data){out(data)})

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

saveMoviePopup_btn.addEventListener('click', saveMovie)
saveMoviePopup_btn.addEventListener('click', addMovieCard)
saveMoviePopup_btn.addEventListener('click', closePopup)
saveMoviePopup_btn.addEventListener('click', clear)

cancelPopup_btn.addEventListener('click', closePopup)

editMoviePopup_btn.addEventListener('click', openPopup)




