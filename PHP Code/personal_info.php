<?php

//load and connect to MySQL database stuff
require("config.inc.php");

if (!empty($_POST)) {
    //gets user's info based off of a username.
    $query = " 
            SELECT 
               *
            FROM all_users
            WHERE 
                user_id = :u_id 
        ";
    
   $query_params = array(
        ':u_id' => $_POST['userid']
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

        $uid = $row['user_id'];
        $uname = $row['username'];
        $name = $row['name'];
        $age = $row['age'];
        $sex = $row['sex'];
        $phn_no= $row['phone'];
        $address = $row['address'];
        $current_status = $row['current_status'];
        $speciality = $row['speciality'];

        }    
        
        $user_details = "$name \n\n Identity : $speciality, Id Number: $uid, Username: $uname\n\n Age: $age, Sex: $sex, Phone No: $phn_no \n Address : $address \n\n Current Status : $current_status" ;

        $response["success"] = 1;
        $response["message"] = $user_details;
        die(json_encode($response));
    } else {
        $response["success"] = 0;
        $response["message"] = "User Info Not Available";
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
