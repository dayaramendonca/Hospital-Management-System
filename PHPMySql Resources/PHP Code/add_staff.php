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
	//initial quer
	$query = "INSERT INTO all_users (username, role_type_id, name, sex, address, phone, speciality, age, password) VALUES (:un, :rt, :nm, :sx, :add, :ph, :sp, :ag, :pw)";

    //Update query
    $query_params = array(
        ':un' => $_POST['un'],
        ':rt' => $_POST['rt'],
        ':nm' => $_POST['nm'],
	':sx' => $_POST['sx'],
	':add' => $_POST['add'],
        ':ph' => $_POST['ph'],
        ':sp' => $_POST['sp'],
	':ag' => $_POST['ag'],
	':pw' => $_POST['pw']
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
    $response["message"] = "New Staff Created";
    echo json_encode($response);
   
} else {

?>
		<h1>New_Staff</h1> 
		<form action="add_staff.php" method="post"> 
		    un:<br /> 
		    <input type="text" name="un" placeholder="" /> 
		    <br /><br /> 
		    rt:<br /> 
		    <input type="text" name="rt" placeholder="" /> 
		    <br /><br />
		    nm:<br /> 
		    <input type="text" name="nm" placeholder="" /> 
		    <br /><br />
	            sx:<br /> 
		    <input type="text" name="sx" placeholder="" /> 
		    <br /><br />
		    add:<br /> 
		    <input type="text" name="add" placeholder="" /> 
		    <br /><br />
		    Ph:<br /> 
		    <input type="text" name="ph" placeholder="" /> 
		    <br /><br /> 
		    Sp:<br /> 
		    <input type="text" name="sp" placeholder="" /> 
		    <br /><br /> 
		    ag:<br /> 
		    <input type="text" name="ag" placeholder="" /> 
		    <br /><br />
	            pw:<br /> 
		    <input type="text" name="pw" placeholder=""/> 
		    <br /><br />
		    <input type="submit" value="Add Staff" /> 
		</form> 
	<?php
}

?> 
