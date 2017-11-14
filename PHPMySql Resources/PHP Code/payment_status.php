<?php

//load and connect to MySQL database stuff
require("config.inc.php");

if (!empty($_POST)) {
    //gets user's info based off of a username.
    $query = " 
            SELECT 
               *
            FROM payment 
            WHERE 
                patient_id = :patient_id 
            AND 
                payment_item_id = :payment_item_id 
        ";
    
   $query_params = array(
        ':patient_id' => $_POST['userid'], ':payment_item_id' => $_POST['itemid']
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
    $rows = $stmt->fetchAll();
    if ($rows) {
    
        foreach ($rows as $row) {
        
        $pay_id = $row['payment_id'];
        $pid = $row['patient_id'];
        $is_paid = $row['is_paid'];
        $item_amount = $row['item_amount'];
        $due_date = $row['due_date'];
        $paid_date = $row['paid_date'];
        $item_id = $row['payment_item_id'];
        
        }
        
        $query1 = " 
            SELECT 
               payment_item
            FROM payment_items 
            WHERE 
                payment_item_id = $item_id 
        "; 
        
           try {
              $stmt1   = $db->prepare($query1);
              $result1 = $stmt1->execute($query_params);
              $row1 = $stmt1->fetch();
               }
           catch (PDOException $ex) 
                {
               $response["message"] = "Database Error!";
               die(json_encode($response));
                }
                
           $payment_item_name = $row1['payment_item'];      
        
        
        
        if ($is_paid != 0 ) {
        
        $payment_status = "Patient with Token No:$pid has already paid $payment_item_name BDT $item_amount on $paid_date, Transaction Id: $pay_id ";
        
        } else {
        
        $payment_status = "Patient with Token No:$pid is required to pay $payment_item_name BDT $item_amount due on $due_date, Transaction Id: $pay_id";
        
        }
        
        $response["success"] = 1;
        $response["message"] = $payment_status;
        die(json_encode($response));
    } else {
        $response["success"] = 0;
        $response["message"] = "Payment Invoice Not Available";
        die(json_encode($response));
    }
} else {
?>
		<h1>PAYMENT STATUS</h1> 
		<form action="payment_status.php" method="post"> 
		    Patient ID:<br /> 
		    <input type="text" name="userid" placeholder="patient id" /> 
		    <br /><br /> 		    
		    
		    Item ID:<br /> 
		    <input type="text" name="itemid" placeholder="item id" /> 
		    <br /><br /> 
		    
		    
		    <input type="submit" value="Find Now" /> 
		</form> 

	<?php
}

?> 
