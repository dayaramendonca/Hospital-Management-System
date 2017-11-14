<?php

//load and connect to MySQL database stuff
require("config.inc.php");

if (!empty($_POST)) {
    //gets user's info based off of a username.
    $query = " 
            SELECT 
               admin_notice
            FROM all_users 
            WHERE 
                user_id = :patient_id 
        ";
    
   $query_params = array(
        ':patient_id' => $_POST['userid']
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
    
    //This will be the variable to determine whether or not the user's information is correct.
    //we initialize it as false.
    $validated_info = false;
    
    //fetching all the rows from the query
    $row = $stmt->fetch();
    if ($row) {
    
        $response["patient_status"] = $row['admin_notice'];
        $response["success"] = 1;
        $response["message"] = $row['admin_notice'];
        die(json_encode($response));
    } else {
        $response["success"] = 0;
        $response["message"] = "Notice Not Found";
        die(json_encode($response));
    }
} else {
?>
		<h1>ADMIN NOTICE</h1> 
		<form action="get_admin_notice.php" method="post"> 
		    Patient ID:<br /> 
		    <input type="text" name="userid" placeholder="Staff id" /> 
		    <br /><br /> 
		    <input type="submit" value="Find Now" /> 
		</form> 

	<?php
}

?> 
