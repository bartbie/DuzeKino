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
    cell2.innerHTML= booking.customer_id

    let cell3 = row.insertCell(2)
    cell3.innerHTML= booking.nrOfSeats

    // let cell4 = row.insertCell(3)
    // cell4.innerHTML= user.password
    //
    // let cell5 = row.insertCell(4)
    // cell5.innerHTML= "Fifth"
    //
    // let cell6 = row.insertCell(5)
    // cell6.className = "seatNr"
    // let showSeatNr = document.createElement("span")
    // showSeatNr.className = "showSeatNr"
    // // cell6.addEventListener("mouseover", showSeats)
    // cell6.appendChild(showSeatNr)
    // cell6.innerHTML= "Sixth"

    let cell7 = row.insertCell(3)
    let updateButton = document.createElement("button")
    updateButton.innerHTML = "Update"
    updateButton.className = "updateBtn"
    updateButton.addEventListener("click", openPupUpForUpdatingBookings)
    cell7.appendChild(updateButton)

    let cell8 = row.insertCell(4)
    let deleteButton = document.createElement("button")
    deleteButton.innerHTML = "Delete"
    deleteButton.className = "deleteBtn"
    deleteButton.addEventListener("click", booking => deleteFetching(cell1.innerHTML))
    cell8.appendChild(deleteButton)

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
function openPupUpForAddingBookings(){
    openPopUp.style.visibility = "visible"

}
addBookingBtn.addEventListener("click", openPupUpForAddingBookings)


    //Functionalities for Update bookings


function openPupUpForUpdatingBookings(){
    openPopUp.style.visibility = "visible"

}



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

