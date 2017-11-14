<?php

/*
Our "config.inc.php" file connects to database every time we include or require
it within a php script.  Since we want this script to add a new user to our db,
we will be talking with our database, and therefore,
let's require the connection to happen:
*/
require("config.inc.php");

//initial query
$query = "Select * FROM payment";

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
    $response["message"] = "Transaction List Available!";
    $response["posts"]   = array();
    
    foreach ($rows as $row) {
    
    
    $status = $row["is_paid"];
    
    if ($status==0){
    
    $cstatus = "Not Paid";
    
    }else{
    
    $cstatus = "Already Paid";
    
    }
    
    
        $post             = array();

        $post["pay_id"] = $row["payment_id"];
        $post["amount"] = $row["item_amount"]." Tk.";
        $post["pstatus"] = $cstatus;
        
        
        //update our repsonse JSON data
        array_push($response["posts"], $post);
    }
    
    // echoing JSON response
    echo json_encode($response);
    
    
} else {
    $response["success"] = 0;
    $response["message"] = "List Not Available!";
    die(json_encode($response));
}

?>
