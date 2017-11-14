<?php

//load and connect to MySQL database stuff
require("config.inc.php");

if (!empty($_POST)) {

    //gets user's info based off of a username.
    
    $query = "INSERT INTO payment (item_amount, patient_id, payment_item_id) VALUES (:amount, :patient_id, :payment_item_id) ";

    
   $query_params = array(
        ':patient_id' => $_POST['userid'], ':payment_item_id' => $_POST['itemid'], ':amount' => $_POST['amount']
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

        $response["success"] = 1;
        $response["message"] = "Invoice Created";
        die(json_encode($response));
   
} else {
?>
		<h1>PAYMENT STATUS</h1> 
		<form action="set_amount.php" method="post"> 
		    Patient ID:<br /> 
		    <input type="text" name="userid" placeholder="patient id" /> 
		    <br /><br /> 		    
		    
		    Item ID:<br /> 
		    <input type="text" name="itemid" placeholder="item id" /> 
		    <br /><br /> 
		    
		    Item ID:<br /> 
		    <input type="text" name="amount" placeholder="amount" /> 
		    <br /><br /> 
		    
		    <input type="submit" value="Find Now" /> 
		</form> 

	<?php
}

?> 
