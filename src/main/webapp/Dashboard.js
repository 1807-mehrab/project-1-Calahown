var user;
function getSessionAttribute() {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(this.readyState == 4) {
            if(this.status == 200) {
                let data= xhr.responseText;
                user = JSON.parse(data);
                if (user.is_host == 1) {
                	document.getElementById("navbar").innerHTML += `<a class="nav-item nav-link" onclick=Guests()>Guests</a>`
                }
            }
        }
    }
    xhr.open('POST', '/Hotel/Session');
    xhr.send();
}

getSessionAttribute();

function Rooms() {
	let jsondata = false;
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
            if (this.status == 200) {
                let data = xhr.responseText;
                if (data) {
					jsondata = JSON.parse(data);
				} 
                if (jsondata){
					var str = `<table class="table table-striped table-bordered"><tr><th>Room #</th><th>Room Type</th><th>Occupant</th><th>Status</th><th>Image</th></tr>`;
					jsondata.forEach(function (element) { if (element.roomstate == 1) { element.roomstate = "Available";} else {element.roomstate = "Occupied";} str += `<tr><td>${element.r_id}</td><td>${element.roomtype}</td><td>${element.a_id}</td><td>${element.roomstate}</td><td><img style="max-height : 200px; max-width : 200px" class="img-responsive img-thumbnail" src="${element.imageurl}"></td></tr>`});
					str += `</table>`;
					document.getElementById("body").innerHTML = str;
				}
                if (user.is_host) {
                	CreateRoomImageForm();
                }
            }
        }
    }
    xhr.open('POST', '/Hotel/Rooms');
    xhr.send();
} 

function Reservations() {
	let jsondata = false;
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				let data = xhr.responseText;
				if (data) {
					jsondata = JSON.parse(data);
				}
				if (jsondata) {
	                var str = `<table class="table table-striped table-bordered"><tr><th>Reservation #</th><th>Room #</th><th>Checkin</th><th>Checkout</th><th>Status</th></tr>`;
	                jsondata.forEach(function (element) {if (element.approve == 2) {element.approve = `Approved`;} else if (element.approve== 1) {element.approve = `Denied`;} else {element.approve = `Pending`;}  str += `<tr><td>${element.resv_id}</td><td>${element.r_id}</td><td>${new Date(element.checkin).toString()}</td><td>${new Date(element.checkout).toString()}</td><td>${element.approve}</td></tr>`});
	                str += `</table>`;
	                document.getElementById("body").innerHTML = str;
				}
				CreateReservationForm();
				if (user.is_host == 1) {
					CreateReservationApprovalForm();
				}
			}
		} 
	}
	xhr.open('GET', '/Hotel/Reservations');
	xhr.send();
}

function Issues () {
	let jsondata = false;
	let xhr = new XMLHttpRequest();
	document.getElementById("body").innerHTML = "";
	xhr.onreadystatechange = function() {
		if (this.readyState == 4) {
			if (this.status == 200) {
				let data = xhr.responseText;
				if (data) {
					jsondata = JSON.parse(data);
				}
				if (jsondata.length > 0) {
	                var str = `<table class="table table-striped table-bordered"><tr><th>Issue #</th><th>Room #</th><th>Report Message</th><th>Reply</th></tr>`;
	                jsondata.forEach(function (element) {str += `<tr><td>${element.i_id}</td><td>${element.r_id}</td><td>${element.reportMessage}</td><td>${element.responderMessage}</td></tr>`});
	                str += `</table>`;
	                document.getElementById("body").innerHTML = str;
				}
				
				CreateIssueForm();
				if (user.is_host == 1) {
					CreateFullIssueForm();
				}
			}
		} 
	}
	xhr.open('GET', '/Hotel/Issues');
	xhr.send();
}

function Guests() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function () {
		if (this.readyState == 4) {
			if (this.status == 200) {
				let data = xhr.responseText;
				if (data) {
					jsondata = JSON.parse(data);
				}
				if (jsondata.length > 0) {
					var str = `<table class="table table-striped table-bordered"><tr><th>User id</th><th>Email</th><th>Username</th><th>Pass</th></tr>`;
	                jsondata.forEach(function (element) {str += `<tr><td>${element.id}</td><td>${element.email}</td><td>${element.username}</td><td>${element.password}</td></tr>`});
	                str += `</table>`;
	                document.getElementById("body").innerHTML = str;
				}
				//GuestRegistration();
			}
		}
	}
	xhr.open('POST', '/Hotel/Guests');
	xhr.send();
}

function CreateReservationForm () {
	document.getElementById("body").innerHTML += `<div class="container">
        <h2>Add a Reservation</h2>
        <h6>Check rooms for availability.</h6>
        <form action="Reservations" method="POST" id="resvform">
            <div class="form-group">
                <label for="email">Room #:</label>
                <div class="col-4">
                    <input type="number" class="form-control" name="room" id="room" placeholder="Desired Room" width="200px" required>
                    <br>
                </div>
            </div>
            <div class="form-group">
                <label for="username">Check In:</label>
                <div class="col-4">
                    <input type="date" class="form-control" name="checkin" id="checkin" placeholder="Enter CheckIn Date" required>
                    <br>
                </div>
            </div>
            <div class="form-group">
                <label for="name">Check out:</label>
                <div class="col-4">
                    <input type="date" class="form-control" name="checkout" id="checkout" placeholder="Enter Checkout Date" required>
                    <br>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>`
}
function CreateReservationApprovalForm() {
	document.getElementById("body").innerHTML += `<div class="container">
        <h2>Approve a Reservation</h2>
        <form action="ReservationApproval" method="POST" id="approvalform">
            <div class="form-group">
                <label for="email">Reservation #:</label>
                <div class="col-4">
                    <input type="number" class="form-control" name="reservation" id="reservation" placeholder="Reservation #" width="200px" required>
                    <br>
                </div>
                <button type="submit" class="btn btn-primary" name="permission" value="2">Approve</button>
                <button type="submit" class="btn btn-primary" name="permission" value="1">Deny</button>
            </div>
        </form>
    </div>
	`;
}

function CreateIssueForm() {
	document.getElementById("body").innerHTML += `<div class="container">
        <h2>Report an Issue</h2>
        <h6>Pennding tickets above.</h6>
        <form action="Issues" method="POST" id="issueform">
            <div class="form-group">
                <label for="email">Room #:</label>
                <div class="col-4">
                    <input type="number" class="form-control" name="room" id="room" placeholder="Room where issue occurred" width="200px" required>
                    <br>
                </div>
            </div>
            <div class="form-group">
                <label for="username">Issue Message:</label>
                <div class="col-4">
                    <textarea rows="4" cols ="50" class="form-control" form="issueform" name="message" id="message" placeholder="Enter a description" required></textarea>
                    <input type="hidden" value="insert" name="hidden">
                    <br>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>`;
}

function CreateFullIssueForm() {
	document.getElementById("body").innerHTML += `<div class="container">
        <h2>Reply to an Issue</h2>
        <h6>Pennding tickets above.</h6>
        <form action="Issues" method="POST" id="issueform2">
            <div class="form-group">
                <label for="room">Issue #:</label>
                <div class="col-4">
                    <input type="number" class="form-control" name="issueid" id="issueid" placeholder="Issue ID" width="200px" required>
                    <br>
                </div>
            </div>
            <div class="form-group">
                <label for="message">Issue Message:</label>
                <div class="col-4">
                    <textarea rows="4" cols ="50" class="form-control" form="issueform2" name="message" id="message" placeholder="Enter a description" required></textarea>
                    <input type="hidden" value="update" name="hidden">
                    <br>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </form>
    </div>`;
}

function Profile() {
	document.getElementById("body").innerHTML = `<div class="container">
        <div class="col-4 justify-content-center">
            <p>Username: ${user.username}</p>
            <p>Email: ${user.email}</p>
            <button class="btn btn-primary" onclick="ChangePassword()">Change Password</button>
        </div>
    </div>`;
}

function ChangePassword() {
	document.getElementById("body").innerHTML += `<div class="container">
    <form action="Profile" method="POST" id="changepasswordform">
        <div class="form-group">
            <label for="oldpassword">Old password:</label>
            <div class="col-4 justify-content-center">
                <input type="password" class="form-control" name="oldpassword" id="oldpassword" placeholder="Old password" width="200px" required>
                <br>
            </div>
        </div>
        <div class="form-group">
            <label for="newpassword">New password :</label>
            <div class="col-4 justify-content-center">
                <input type="password" class="form-control" name="newpassword" id="newpassword" placeholder="New password" width="200px" required>
                <br>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>
	`;
}

function Logout() {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() { 
		if (this.readyState == 4) {
			if (this.status == 200) {
				user = null;
				window.location.replace("/Hotel/index.html")
			}
		}
	}
	xhr.open('POST', '/Hotel/Logout');
	xhr.send();
}


function CreateRoomImageForm() {
    document.getElementById("body").innerHTML += `<div class="container">
    <form onsubmit=RoomImage64() id="roomimageform" name="roomimageform">
        <div class="form-group">
        <label for="room">Room #</label>
        <div class="col-4 justify-content-center">
            <input type="number" class="form-control" name="roomimage" id="roomimage" placeholder="Room number" width="200px" required>
            <br>
        </div>
    	</div>
        <div class="form-group">
            <label for="newpassword">Upload Image</label>
            <div class="col-4 justify-content-center">
                <input type="file" class="form-control" id="imagefile" name="imagefile" accept="image/*;capture=camera" required>
                <br>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
    </form>
</div>
	`;
}

function RoomImage64() {
	var filesSelected = document.getElementById('imagefile').files;
    if (filesSelected.length > 0) {
        var fileToLoad = filesSelected[0];
        var fileReader = new FileReader();
        fileReader.onload = function(fileLoadedEvent) {
            var base64value = fileLoadedEvent.target.result;
            let xhr = new XMLHttpRequest();
            let room = document.forms['roomimageform']['roomimage'].value;
            let jsondata = { "room" : room , "image": base64value };
            xhr.open('POST', '/Hotel/RoomImage');
            xhr.send(JSON.stringify(jsondata));
        };
        fileReader.readAsDataURL(fileToLoad);
    }
}

function GuestRegistration() {
	document.getElementById("body").innerHTML += `
	    <div class="container"><h2>Sign up sheet</h2>
	    <form action="HostRegistration" method="POST" id="caform">
	        <div class= "form-group" >
	        <label for="email">Email:</label>
	        <div class="col-4">
	            <input type="text" class="form-control" id="email" name="email" placeholder="Enter email" width="200px" required>
	                <br>
	            </div>
	            </div>
	            <div class="form-group">
	                <label for="username">Username:</label><div class="col-4">
	                    <input type="text" class="form-control" id="username" name="username" placeholder="Enter username" required>
	                        <br>
	            </div>
	            </div>
	                    <div class="form-group">
	                        <label for="name">Password:</label><div class="col-4">
	                            <input type="text" class="form-control" id="password" name="password" placeholder="Enter name" required>
	                                <br>
	            </div>
	            <button type="submit" class="btn btn-primary">Submit</button>
	            </div>           
	        </form>
	        </div>`;
}