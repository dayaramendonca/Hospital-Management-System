<?php

//load and connect to MySQL database stuff
require("config.inc.php");

if (!empty($_POST)) {
    //gets user's info based off of a username.
    $query = " 
    
         UPDATE appointment_requests SET is_responded = 1 WHERE request_id = :r_id
         
        ";
    
   $query_params = array(
        ':r_id' => $_POST['rid']
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
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
        
    }

} else {
?>
		<h1>Update Staff Status</h1> 
		<form action="update_staff_status.php" method="post"> 
		    Patient ID:<br /> 
		    <input type="text" name="userid" placeholder="Staff id" /> 
		    <br /><br /> 
		    Updated Status:<br /> 
		    <input type="text" name="status_updated" placeholder="Status" /> 
		    <br /><br /> 
		    <input type="submit" value="Find Now" /> 
		</form> 

	<?php
}

?> 
