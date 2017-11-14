<?php

/*
Our "config.inc.php" file connects to database every time we include or require
it within a php script.  Since we want this script to add a new user to our db,
we will be talking with our database, and therefore,
let's require the connection to happen:
*/
require("config.inc.php");

//initial query

if (!empty($_POST)) {

        //date_default_timezone_set("Asia/Dhaka"); 
        //$date_time = date('Y-m-d H:i:s');
	//initial query
	$query = "INSERT INTO appointment_requests ( person_name, sex, age, problem_details, phone_no ) VALUES ( :pid, :sid, :aid , :rid , :hid) ";

    //Update query
    $query_params = array(
        ':pid' => $_POST['name'],
        ':sid' => $_POST['sex'],
	':aid' => $_POST['age'],
	':rid' => $_POST['problem'], 
	':hid' => $_POST['phone']
    );
  
	//execute query
    try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one:
        $response["success"] = 0;
        $response["message"] = "Database Error. Please Try Again Later!";
        die(json_encode($response));
    }

    $response["success"] = 1;
    //$response["message"] = date('d-m-Y H:i:s');
    $response["message"] = "New Appointment Created Successfully";
    echo json_encode($response);
   
} else {

?>
		<h1>New_Appointment</h1> 
		<form action="add_appointment.php" method="post"> 
		    Patient_name:<br /> 
		    <input type="text" name="name" placeholder="" /> 
		    <br /><br /> 
		    Patient_age:<br /> 
		    <input type="text" name="age" placeholder="" /> 
		    <br /><br />
	            Patient_sex:<br /> 
		    <input type="text" name="sex" placeholder="" /> 
		    <br /><br />
		    Patient_phone:<br /> 
		    <input type="text" name="phone" placeholder="" /> 
		    <br /><br />
		    Patient_problem:<br /> 
		    <input type="text" name="problem" placeholder="" /> 
		    <br /><br />
		    <input type="submit" value="Add Appointment" /> 
		</form> 
	<?php
}

?> 
