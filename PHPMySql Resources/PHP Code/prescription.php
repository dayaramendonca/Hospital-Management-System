<?php

/*
Our "config.inc.php" file connects to database every time we include or require
it within a php script.  Since we want this script to add a new user to our db,
we will be talking with our database, and therefore,
let's require the connection to happen:
*/

require("config.inc.php");

//initial query

$query = "Select * FROM prescription WHERE patient_id = :patient_id";

//$query = "
//SELECT all_users.name, prescription.*
//FROM prescription
//INNER JOIN all_users
//ON prescription.patient_id=all_users.user_id
//AND prescription.doctor_id=all_users.user_id
//";

 $query_params = array(
        ':patient_id' => $_POST['patient_id']
   );


//execute query
try {
    $stmt   = $db->prepare($query);
    $result = $stmt->execute($query_params);
}
catch (PDOException $ex) {
    $response["success"] = 0;
    $response["message"] = "Database Error!";
    die(json_encode($response));
}

// Finally, we can retrieve all of the found rows into an array using fetchAll 
$rows = $stmt->fetchAll();


if ($rows) {

    $response["success"] = 1;
    $response["message"] = "Prescription Available!";
    $response["prescriptions"]   = array();
    
    foreach ($rows as $row) {
    
    
    $p_id  = $row["patient_id"];
    
    $d_id = $row["doctor_id"];

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
    
    
        $post             = array();
        
        $post["prescription_id"]  = $row["prescription_id"];
        $post["patient_id"]  = $row1["name"];
        $post["doctor_id"] = $row2["name"];
        $post["complain_details"] = $row["complain_details"];
        $post["patient_history"]  = $row["patient_history"];
        $post["main_prescription"] = $row["main_prescription"];
        $post["doctors_advice"]  = $row["doctors_advice"];
        $post["general_checkup"] = $row["general_checkup"];
        $post["investigation1"]  = $row["investigation1"];
        $post["investigation2"]  = $row["investigation2"];
        $post["investigation3"] = $row["investigation3"];
        $post["date_time"]  = $row["time_and_date"];
        
        //update our repsonse JSON data

        array_push($response["prescriptions"], $post);
    }
    
    // echoing JSON response
    echo json_encode($response);
    
    
} else {
    $response["success"] = 0;
    $response["message"] = "No Prescription Found!";
    die(json_encode($response));
}

?>