function out(any){
    console.log(any)
}

const addBookings = document.getElementById("addBookings")
const bookingTbl = document.getElementById("tblBookings")



function createTable(user) {
    if (!user.id) return;

    let rowCount = bookingTbl.rows.length
    out("rowcount=" + rowCount)
    let row = bookingTbl.insertRow(rowCount)

    let cell1 = row.insertCell(0)
    cell1.innerHTML= user.id

    let cell2 = row.insertCell(1)
    cell2.innerHTML= user.email
    //
    // let cell3 = row.insertCell(2)
    // cell3.innerHTML= "Third"
    //
    // let cell4 = row.insertCell(3)
    // cell4.innerHTML= "Fourth"
    //
    // let cell5 = row.insertCell(4)
    // cell5.innerHTML= "Fifth"
    //
    // let cell6 = row.insertCell(5)
    // cell6.innerHTML= "Sixth"

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
    // let userArr = users.user;

    users.forEach(user => createTable(user))
}

createTblFromDB()