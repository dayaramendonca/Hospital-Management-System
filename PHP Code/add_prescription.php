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
	$query = "INSERT INTO prescription (patient_id, doctor_id, complain_details, patient_history, main_prescription, doctors_advice, general_checkup, investigation1, investigation2, investigation3) VALUES (:ptid, :docid, :cmdl, :pth, :pres, :docad, :gc, :inv1, :inv2, :inv3) ";


    //Update query
    $query_params = array(
        ':ptid' => $_POST['ptid'],
        ':docid' => $_POST['docid'],
        ':cmdl' => $_POST['cmdl'],
	':pth' => $_POST['pth'],
	':pres' => $_POST['pres'],
        ':docad' => $_POST['docad'],
        ':gc' => $_POST['gc'],
	':inv1' => $_POST['inv1'],
	':inv2' => $_POST['inv2'],
	':inv3' => $_POST['inv3']
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
    $response["message"] = "Prescription Generated";
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
