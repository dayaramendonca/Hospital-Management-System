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
	$query = "INSERT INTO admission ( patient_id, consulting_doctor_id, ward_no, cabin_no ) VALUES ( :pid, :did, :adt , :ddt) ";

    //Update query
    $query_params = array(
        ':pid' => $_POST['patient_id'],
        ':did' => $_POST['doctor_id'],
		':adt' => $_POST['ward_no'],
		':ddt' => $_POST['cabin_no']
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
        $response["message"] = "Database Error. Couldn't add post!";
        die(json_encode($response));
    }

    $response["success"] = 1;
    //$response["message"] = date('d-m-Y H:i:s');
    $response["message"] = "New Admission Created";
    echo json_encode($response);
   
} else {

?>
		<h1>New_Admission</h1> 
		<form action="new_admission.php" method="post"> 
		    Patient_id:<br /> 
		    <input type="text" name="patient_id" placeholder="Patient_id" /> 
		    <br /><br /> 
		    Doctor_id:<br /> 
		    <input type="text" name="doctor_id" placeholder="Doctor_id" /> 
		    <br /><br />
	            Ward_no:<br /> 
		    <input type="text" name="ward_no" placeholder="Ward_no" /> 
		    <br /><br />
		    Cabin_no:<br /> 
		    <input type="text" name="cabin_no" placeholder="Cabin_no" /> 
		    <br /><br />
		    <input type="submit" value="Add Patient" /> 
		</form> 
	<?php
}

?> 
