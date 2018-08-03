<h2> Welcome to David's Hotel. </h2>

<p>To get you started with a compatable sql database please run hotel.sql on a newly created user in Oracle.<br>
It will get you started with the hotel rooms and a Host account with credentials username: "Admin" password: "p4ssw0rd".</p>

<p> Additionally please look into connection.properties to set your credentials.<br>
Finally, in src.main.java.com.revature.ConnectionUtil.java the file path to connection.properties will need to be altered. </p>
<p>
Known bugs: <br>

The junit testing assumes there is an "Issue" with id == 1. An "Issue" entry is added in hotel.sql, but it may not be id == 1; <br>
The junit testing assumes there exists a Reservation entry in the database, an entry is added in hotel.sql.</p>
