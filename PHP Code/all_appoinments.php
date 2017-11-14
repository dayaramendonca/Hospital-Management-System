<?php

/*
Our "config.inc.php" file connects to database every time we include or require
it within a php script.  Since we want this script to add a new user to our db,
we will be talking with our database, and therefore,
let's require the connection to happen:
*/
require("config.inc.php");

//initial query
$query = "Select * FROM  appointment_requests";

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
    $response["message"] = "Patient List Available!";
    $response["posts"]   = array();
    
    foreach ($rows as $row) {
    
    
    $status = $row["is_responded"];
    
    if ($status==0){
    
    $cstatus = "Not Responded";
    
    }else{
    
    $cstatus = "Responded";
    
    }
    
    
        $post             = array();

        $post["appoint_id"] = $row["request_id"];
        $post["patient_name"] = $row["person_name"];
        $post["request_date"] = $row["request_date"];
        $post["cstatus"] = $cstatus;
        
        
        //update our repsonse JSON data
        array_push($response["posts"], $post);
    }
    
    // echoing JSON response
    echo json_encode($response);
    
    
} else {
    $response["success"] = 0;
    $response["message"] = "No Patient Available!";
    die(json_encode($response));
}

?>
