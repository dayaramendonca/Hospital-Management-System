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
	//initial query
	$query = "UPDATE admission SET discharge_date = NOW(), is_discharged = 1 WHERE patient_id = :pid AND admission_id = :aid";

    //Update query
    $query_params = array(
        ':pid' => $_POST['patient_id'],
        ':aid' => $_POST['admission_id']
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
        $response["message"] = "Database Error. Couldn't update!";
        die(json_encode($response));
    }

    $response["success"] = 1;
    $response["message"] = "Patient Successfully Discharged!";
    echo json_encode($response);
   
} else {
?>
		<h1>Discharge_Patient</h1> 
		<form action="discharge.php" method="post"> 
		    Patient_id:<br /> 
		    <input type="text" name="patient_id" placeholder="Patient_id" /> 
		    <br /><br /> 
		    Admission_id:<br /> 
		    <input type="text" name="admission_id" placeholder="Admission_id" /> 
		    <br /><br />
		    <input type="submit" value="Discharge Patient" /> 
		</form> 
	<?php
}

?> 
