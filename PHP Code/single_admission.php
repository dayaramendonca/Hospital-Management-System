<?php

//load and connect to MySQL database stuff
require("config.inc.php");

if (!empty($_POST)) {
    //gets user's info based off of a username.
    $query = " 
            SELECT 
               *
            FROM admission
            WHERE 
                admission_id = :ad_id 
        ";
    
   $query_params = array(
        ':ad_id' => $_POST['userid']
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
        
        
    $p_id  = $row['patient_id'];
    
    $d_id = $row['consulting_doctor_id'];

    $query1 = "Select name FROM all_users WHERE user_id = $p_id";
    
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
   

    $query2 = "Select name FROM all_users WHERE user_id = $d_id";
    
               try {
              $stmt2   = $db->prepare($query2);
              $result2 = $stmt2->execute($query_params);
              $row2 = $stmt2->fetch();
               }
           catch (PDOException $ex) 
                {
               $response["message"] = "Database Error!";
               die(json_encode($response));
                }
        
    
        $admission_id = $row['admission_id'];
        $patient_name = $row1['name'];
        $doctor_name = $row2['name'];
        $ward_no= $row['ward_no'];
        $cabin_no = $row['cabin_no'];
        $admission_date = $row['admission_date'];
        $discharge_date = $row['discharge_date'];
        $is_discharged = $row['is_discharged'];
        
        if($is_discharged==1){
        
        $discharge_status = "Already Discharged";
        
        }else{
        
        $discharge_status = "Still Admitted";
        
        }
        
        }    
        
        $request_details = "\n Admission Id: $admission_id \n Patient Name: $patient_name \n Consulting Doctor: $doctor_name \n\n Ward No: $ward_no, Cabin No: $cabin_no \n\n Admission Date : $admission_date \n\n Discharged Date : $discharge_date \n\n  Current Status : $discharge_status\n " ;

        $response["success"] = 1;
        $response["message"] = $request_details;
        die(json_encode($response));
    } else {
        $response["success"] = 0;
        $response["message"] = "Admission Info Not Available";
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
