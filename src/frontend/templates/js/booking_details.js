function out(any){
    console.log(any)
}

const addBookings = document.getElementById("addBookings")
const bookingTbl = document.getElementById("bookingsTbl")



function createTable(user) {
    if (!user.id) return;

    let rowCount = bookingTbl.rows.length
    out("rowcount=" + rowCount)
    let row = bookingTbl.insertRow(rowCount)

    let cell1 = row.insertCell(0)
    cell1.innerHTML= user.id

    let cell2 = row.insertCell(1)
    cell2.innerHTML= user.email

    let cell3 = row.insertCell(2)
    cell3.innerHTML= "Third"

    let cell4 = row.insertCell(3)
    cell4.innerHTML= "Fourth"

    let cell5 = row.insertCell(4)
    cell5.innerHTML= "Fifth"

    let cell6 = row.insertCell(5)
    cell6.className = "seatNr"
    let showSeatNr = document.createElement("span")
    showSeatNr.className = "showSeatNr"
    cell6.addEventListener("mouseover", showSeats)
    cell6.appendChild(showSeatNr)
    cell6.innerHTML= "Sixth"

    let cell7 = row.insertCell(6)
    let updateButton = document.createElement("button")
    updateButton.innerHTML = "Update"
    updateButton.className = "updateBtn"
    cell7.appendChild(updateButton)

    let cell8 = row.insertCell(7)
    let deleteButton = document.createElement("button")
    deleteButton.innerHTML = "Delete"
    deleteButton.className = "deleteBtn"
    cell8.appendChild(deleteButton)

}

addBookings.addEventListener("click", createTable)


function fetchUsers() {
    out("inside fetch")
    return  fetch("http://localhost:7070/users").then(response => response.json());
}


async function createTblFromDB(btn) {
    out("fetch photos")
    let users = await fetchUsers();
    out(users);
    users.forEach(user => createTable(user))
}

createTblFromDB()

let showingSeats = document.querySelector(".showSeatNr")

function showSeats (){
    showingSeats = "A1, A2"
    // showingSeats.style.visibility = "visible"

}

// .tooltip {
//     position: relative;
//     display: inline-block;
//     border-bottom: 1px dotted black;
// }
//
// .tooltip .tooltiptext {
//     visibility: hidden;
//     width: 120px;
//     background-color: black;
//     color: #fff;
//     text-align: center;
//     border-radius: 6px;
//     padding: 5px 0;
//
//     /* Position the tooltip */
//     position: absolute;
//     z-index: 1;
// }
//
// .tooltip:hover .tooltiptext {
//     visibility: visible;
// }