const editButton = document.querySelector("#editSchedule_button")
const saveButton = document.querySelector("#saveSchedule_button")
let cellData = document.querySelector(".data")
let cellDropMenu = document.querySelector(".dropdown_div")
let name = document.querySelector(".name")
let time = document.querySelector(".time")
const selectedName = document.querySelector(".drop_menu_name");
const selectedTime = document.querySelector(".drop_menu_time");


//don't know why it only works for only one cell - "Monday; movie operator"

function editSchedule(){
    cellData.style.display = "none"
    cellDropMenu.style.display = "flex"
    saveButton.style.display = "inline"
    editButton.style.display = "none"
}

function saveEdit(){
    // event.preventDefault();

    name.innerText = `${selectedName.value}`;
    time.innerHTML = `${selectedTime.value}`;
    cellData.style.display = "flex"
    cellDropMenu.style.display = "none"
    saveButton.style.display = "none"
    editButton.style.display = "inline"
}
editButton.addEventListener("click" , editSchedule)
saveButton.addEventListener("click", saveEdit)


