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
	$query = "INSERT INTO payment (patient_id, payment_item_id, item_amount, billing_note) VALUES (:ptid, 3, :phbill, :phrep)";

    //Update query
    $query_params = array(
        ':ptid' => $_POST['ptid'],
        ':phrep' => $_POST['phrep'],
	':phbill' => $_POST['phbill']
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
    $response["message"] = "Pharmacy Report Generated";
    echo json_encode($response);
   
} else {

?>
		<h1>New_Pharmacy_Invoice</h1> 
		<form action="new_admission.php" method="post"> 
		    Patient_id:<br /> 
		    <input type="text" name="patient_id" placeholder="Patient_id" /> 
		    <br /><br /> 
		    Payment_Item_id:<br /> 
		    <input type="text" name="payment_item_id" placeholder="payment_item_id" /> 
		    <br /><br />
	            Item_amount:<br /> 
		    <input type="text" name="item_amount" placeholder="item_amount" /> 
		    <br /><br />
		    Billing_note:<br /> 
		    <input type="text" name="billing_note" placeholder="billing_note" /> 
		    <br /><br />
		    <input type="submit" value="Add Bill" /> 
		</form> 
	<?php
}

?> 
