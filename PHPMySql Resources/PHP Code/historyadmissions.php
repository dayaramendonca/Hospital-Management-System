<?php

/*
Our "config.inc.php" file connects to database every time we include or require
it within a php script.  Since we want this script to add a new user to our db,
we will be talking with our database, and therefore,
let's require the connection to happen:
*/
require("config.inc.php");

//initial query
$query = "Select * FROM admission WHERE is_discharged=1";

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
    
          $p_id  = $row["patient_id"];
        
          $query2 = "Select name FROM all_users WHERE user_id = $p_id";
    
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
        
        $post["patient_id"]  = $row["patient_id"];
        $post["patient_name"] = $row2["name"];
        $post["admission_id"] = $row["admission_id"];
        
        
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
