function out(any) {console.log(any)}
out("I am inside main_page.js")

function elementFromHtml(element){
    const template = document.createElement("template")

    template.innerHTML = element.trim();
    return template.content.firstElementChild;
}


document.getElementById("newMovie_button").addEventListener('click', test)