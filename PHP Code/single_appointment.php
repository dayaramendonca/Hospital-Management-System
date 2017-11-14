<?php

//load and connect to MySQL database stuff
require("config.inc.php");

if (!empty($_POST)) {
    //gets user's info based off of a username.
    $query = " 
            SELECT 
               *
            FROM appointment_requests
            WHERE 
                request_id = :appoint_id 
        ";
    
   $query_params = array(
        ':appoint_id' => $_POST['userid']
    );
    
    try {
        $stmt   = $db->prepare($query);
        $result = $stmt->execute($query_params);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error. Please Try Again!";
        die(json_encode($response));
        
    }
    
    //This will be the variable to determine whether or not the user's information is correct.
    //we initialize it as false.
    $validated_info = false;
    
    //fetching all the rows from the query
    $rows = $stmt->fetchAll();
    if ($rows) {
    
        foreach ($rows as $row) {
    
        $person_name = $row['person_name'];
        $sex = $row['sex'];
        $age = $row['age'];
        $problem_details = $row['problem_details'];
        $phone_no = $row['phone_no'];
        $request_date = $row['request_date'];
        }    
        
        $request_details = "Patient Name: $person_name \n\n Age: $age, Sex: $sex \n Contact No: $phone_no \n\n Problem Details: \n $problem_details \n\n Request Date : $request_date" ;

        $response["success"] = 1;
        $response["message"] = $request_details;
        die(json_encode($response));
    } else {
        $response["success"] = 0;
        $response["message"] = "Appointment Request Not Available";
        die(json_encode($response));
    }
} else {
?>
		<h1>Appointment Details</h1> 
		<form action="single_appointment.php" method="post"> 
		    Patient ID:<br /> 
		    <input type="text" name="userid" placeholder="appoint id" /> 
		    <input type="submit" value="Find Now" /> 
		</form> 

	<?php
}

?> 
