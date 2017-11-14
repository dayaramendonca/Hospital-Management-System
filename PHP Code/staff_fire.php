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

	//initial query
	$query = "UPDATE all_users SET is_fired = 1, current_status='STAFF FIRED BY ADMIN!' WHERE user_id = :s_id";

    //Update query
    $query_params = array(
        ':s_id' => $_POST['userid']
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
        $response["message"] = "Database Error. Couldn't update!";
        die(json_encode($response));
    }

    $response["success"] = 1;
    $response["message"] = "Staff Successfully Fired!";
    echo json_encode($response);
   
} else {
?>
		<h1>Admin_Notice</h1> 
		<form action="add_notice.php" method="post"> 
		    Admin Note:<br /> 
		    <input type="text" name="admin_note" placeholder="" /> 
		    <br /><br /> 
		    <input type="submit" value="Publish" /> 
		</form> 
	<?php
}

?> 
