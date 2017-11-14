<?php

/*
Our "config.inc.php" file connects to database every time we include or require
it within a php script.  Since we want this script to add a new user to our db,
we will be talking with our database, and therefore,
let's require the connection to happen:
*/
require("config.inc.php");

//initial query
$query = "Select * FROM all_users WHERE role_type_id!=6";

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
    $response["message"] = "Staff List Available!";
    $response["posts"]   = array();
    
    foreach ($rows as $row) {
    
      
       $r_id  = $row["role_type_id"];

       $query1 = "Select role_type_name FROM role_type WHERE role_type_id = $r_id";
    
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
    
    
        $post             = array();
        $post["staff_id"]  = $row["user_id"];
        $post["staff_name"] = $row["name"];
        $post["status_id"] = $row["current_status"];
        $post["designation_id"] = $row1["role_type_name"];
        
        
        //update our repsonse JSON data
        array_push($response["posts"], $post);
    }
    
    // echoing JSON response
    echo json_encode($response);
    
    
} else {
    $response["success"] = 0;
    $response["message"] = "No Staff Available!";
    die(json_encode($response));
}

?>
