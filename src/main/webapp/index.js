function CreateAccountForm() {
    document.getElementById("body").innerHTML = `
    <div class="container"><h2>Sign up sheet</h2>
    <form action="Register" method="POST" id="caform">
        <div class= "form-group" >
        <label for="email">Email:</label>
        <div class="col-4">
            <input type="text" class="form-control" id="email" placeholder="Enter email" width="200px" required>
                <br>
            </div>
            </div>
            <div class="form-group">
                <label for="username">Username:</label><div class="col-4">
                    <input type="text" class="form-control" id="username" placeholder="Enter username" required>
                        <br>
            </div>
            </div>
                    <div class="form-group">
                        <label for="name">Password:</label><div class="col-4">
                            <input type="text" class="form-control" id="password" placeholder="Enter name" required>
                                <br>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
            </div>           
        </form>
        </div>`;
}


function CreateAccount() {
    let x = { "id": -1, "email": document.forms['caform']['email'].value, "username": document.forms['caform']['username'].value, "password": document.forms['caform']['password'].value };
    let xhr = new XMLHttpRequest();
    //maybe onreadystatechange 

    xhr.open('POST', '/Hotel/Register');
    xhr.send(JSON.stringify(x));
}

function Login() {
	let x = {"username": document.forms['loginform']['lusername'].value , "password": document.forms['loginform']['lpassword'].value};
	let xhr = new XMLHttpRequest();
	xhr.open('POST', '/Hotel/Login');
	xhr.send(JSON.stringify(x));
}