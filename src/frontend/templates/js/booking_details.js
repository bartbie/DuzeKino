function out(any){
    console.log(any)
}

    //Declaring Variables
const addBookingBtn = document.getElementById("addBookings")
const bookingTbl = document.getElementById("bookingsTbl")



    //Creating table for showing data

function createTable(booking) {
    if (!booking.booking_Id) return;

    let rowCount = bookingTbl.rows.length
    out("rowcount=" + rowCount)
    let row = bookingTbl.insertRow(rowCount)

    let cell1 = row.insertCell(0)
    cell1.innerHTML= booking.booking_Id

    let cell2 = row.insertCell(1)
    cell2.innerHTML= "Second"

    let cell3 = row.insertCell(2)
    cell3.innerHTML= booking.customer_id

    let cell4 = row.insertCell(3)
    cell4.innerHTML= booking.nrOfSeats

    let cell5 = row.insertCell(4)
    cell5.innerHTML= "Fifth"


    let cell6 = row.insertCell(5)
    let updateButton = document.createElement("button")
    updateButton.innerHTML = "Update"
    updateButton.className = "updateBtn"
    updateButton.addEventListener("click", function (){
        openPupUpForUpdatingBookings(cell1.innerHTML, cell3.innerHTML, cell4.innerHTML)
        updateBookingBtnFromPopUp.addEventListener("click", booking => updateBooking(cell1.innerHTML))

    })
    cell6.appendChild(updateButton)


    let cell7 = row.insertCell(6)
    let deleteButton = document.createElement("button")
    deleteButton.innerHTML = "Delete"
    deleteButton.className = "deleteBtn"
    deleteButton.addEventListener("click", booking => deleteFetching(cell1.innerHTML))
    cell7.appendChild(deleteButton)

}

    /* Getting data from database and showing it in the table */

function fetchBookings() {
    out("inside fetch")
    return  fetch("http://localhost:8080/api/v1/Booking").then(response => response.json());
}


async function createTblFromDB(btn) {
    let bookings = await fetchBookings();
    out(bookings);
    bookings.forEach(booking => createTable(booking))
}

createTblFromDB()


    //Functionalities for save bookings


const openPopUp = document.querySelector(".popupBookings")
const closingPopUp = document.getElementById("cancelBooking_btn")

let bookingIdFromPopUp = document.getElementById("bookingID")
let showingIdFromPopUp = document.getElementById("showingID")
let customerIdFromPopUp = document.getElementById("customerID")
let nrOfSeatsFromPopUp = document.getElementById("nrOfSeats")
let snackIdFromPopUp = document.getElementById("snackID")

const saveBookingBtnFromPopUp = document.getElementById("saveBooking_btn")


function openPupUpForAddingBookings(){
    openPopUp.style.visibility = "visible"
    openPopUp.style.marginTop = "26%";
    openPopUp.style.transform = "translate(-50%,-50%) scale(1)";

}
addBookingBtn.addEventListener("click", openPupUpForAddingBookings)

function closePopUpWindow(){
    openPopUp.style.visibility = "hidden"
}

closingPopUp.addEventListener("click", closePopUpWindow)

function saveBooking(event){
    event.preventDefault()

    out("before fetch")
    fetch("http://localhost:8080/api/v1/Booking", {
        method: "POST",
        body: JSON.stringify({
            customer_id: customerIdFromPopUp.value,
            nrOfSeats: nrOfSeatsFromPopUp.value,
        }),
        headers: {
            "Content-type": "application/json"
        }
    }).then(function (response){
        return response.json()
    }).then(function (bookings){
        bookings.forEach(booking => createTable(booking))
        window.location.href = "bookings_page.html"
    })
}

saveBookingBtnFromPopUp.addEventListener("click", saveBooking)
saveBookingBtnFromPopUp.addEventListener("click", closePopUpWindow)
saveBookingBtnFromPopUp.addEventListener("click", () => window.location.href = "bookings_page.html")











    //Functionalities for Update bookings


function openPupUpForUpdatingBookings(bookingId,customerID,nrOfSeats){
    openPopUp.style.visibility = "visible"
    openPopUp.style.marginTop = "26%";
    openPopUp.style.transform = "translate(-50%,-50%) scale(1)";

    bookingIdFromPopUp.value = bookingId;
    customerIdFromPopUp.value = customerID;
    nrOfSeatsFromPopUp.value = nrOfSeats;

    //
    // saveBookingBtnFromPopUp.innerHTML = "Update"
    // saveBookingBtnFromPopUp.id = "updateBooking_btn"
}

function updateBooking(id){


    out("before fetch")
    fetch("http://localhost:8080/api/v1/Booking/" + id, {
        method: "PUT",
        body: JSON.stringify({
            booking_id: id,
            customer_id: customerIdFromPopUp.value,
            nrOfSeats: nrOfSeatsFromPopUp.value,
        }),
        headers: {
            "Content-type": "application/json"
        }
    }).then(function (response){
        return response.json()
    }).then(function (data){
        out(data)
    })
    //     .then(function (bookings){
    //     window.location.href = "bookings_page.html"
    // })
}

const updateBookingBtnFromPopUp = document.getElementById("updateBooking_btn")

updateBookingBtnFromPopUp.addEventListener("click", closePopUpWindow)
updateBookingBtnFromPopUp.addEventListener("click", () => window.location.href = "bookings_page.html")





    //Functionalities for Delete bookings

function deleteFetching(booking_id){

    out("I am in delete fetching")
    fetch("http://localhost:8080/api/v1/Booking/" + booking_id , {
        method: "DELETE",
        body :"",
        headers: {
            "Content-type": "application/json"
        }
    }).then(function (response) {
        window.location.href = "bookings_page.html"
    })
}

