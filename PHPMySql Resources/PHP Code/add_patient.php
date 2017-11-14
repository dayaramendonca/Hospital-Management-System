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
	$query = "INSERT INTO all_users (username, role_type_id, name, sex, address, phone, speciality, age, password, current_status) VALUES (:un, 6, :nm, :sx, :add, :ph, 'patient', :ag, :pw, :cs) ";

    //Update query
    $query_params = array(
        ':un' => $_POST['un'],
        ':nm' => $_POST['nm'],
	':sx' => $_POST['sx'],
	':add' => $_POST['add'],
        ':ph' => $_POST['ph'],
	':ag' => $_POST['ag'],
	':pw' => $_POST['pw'],
	':cs' => $_POST['cs']
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
    $response["message"] = "New Patient Created";
    echo json_encode($response);
   
} else {

?>
		<h1>New_Patient</h1> 
		<form action="add_patient.php" method="post"> 
		    un:<br /> 
		    <input type="text" name="un" placeholder="" /> 
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
		    ag:<br /> 
		    <input type="text" name="ag" placeholder="" /> 
		    <br /><br />
	            pw:<br /> 
		    <input type="text" name="pw" placeholder=""/> 
		    <br /><br />
		    cs:<br /> 
		    <input type="text" name="cs" placeholder="" /> 
		    <br /><br />
		    <input type="submit" value="Add Patient" /> 
		</form> 
	<?php
}

?> 
