<?php

//load and connect to MySQL database stuff
require("config.inc.php");

    //gets user's info based off of a username.
    $query = " 
            SELECT 
               SUM(item_amount) as total
            FROM payment
        ";
    
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

    
    //fetching all the rows from the query
    $row = $stmt->fetch();
    $total_transactions = $row['total'];
    
    
    // 2ND QUERY
  
  
      //gets user's info based off of a username.
    $query2 = " 
            SELECT 
               SUM(item_amount) as amt_paid
            FROM payment
            WHERE is_paid=1
        ";
    
    try {
        $stmt2   = $db->prepare($query2);
        $result2 = $stmt2->execute($query_params2);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
        
    }

    
    //fetching all the rows from the query
    $row2 = $stmt2->fetch();   
    $total_paid = $row2['amt_paid'];
    
    
    
     // 3RD QUERY
  
  
      //gets user's info based off of a username.
    $query3 = " 
            SELECT 
               SUM(item_amount) as amt_npaid
            FROM payment
            WHERE is_paid=0
        ";
    
    try {
        $stmt3   = $db->prepare($query3);
        $result3 = $stmt3->execute($query_params3);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
        
    }

    
    //fetching all the rows from the query
    $row3 = $stmt3->fetch();   
    $total_npaid = $row3['amt_npaid'];
    
    
    
    
   // 4th QUERY
  
  
      //gets user's info based off of a username.
    $query4 = " 
            SELECT 
               SUM(item_amount) as monthly
            FROM payment
            WHERE paid_date BETWEEN DATE_FORMAT(NOW() - INTERVAL 1 MONTH, '%Y-%m-01 00:00:00') AND DATE_FORMAT(LAST_DAY(NOW() - INTERVAL 1 MONTH), '%Y-%m-%d 23:59:59')
        ";
    
    try {
        $stmt4   = $db->prepare($query4);
        $result4 = $stmt4->execute($query_params4);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
        
    }

    
    //fetching all the rows from the query
    $row4 = $stmt4->fetch();   
    $month_paid = $row4['monthly'];
    
    
    
    
    
    
    
    
    //4 types of Payments
    
    
        //gets user's info based off of a username.
    $query5 = " 
            SELECT 
               SUM(item_amount) as total
            FROM payment
            WHERE payment_item_id=1 AND is_paid=1
        ";
    
    try {
        $stmt5   = $db->prepare($query5);
        $result5 = $stmt5->execute($query_params5);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
        
    }

    
    //fetching all the rows from the query
    $row5 = $stmt5->fetch();
    $total_outdoor = $row5['total'];
    
    
    // 2ND QUERY
  
  
      //gets user's info based off of a username.
    $query6 = " 
            SELECT 
               SUM(item_amount) as amt_paid
            FROM payment
            WHERE payment_item_id=2 AND is_paid=1
        ";
    
    try {
        $stmt6  = $db->prepare($query6);
        $result6 = $stmt6->execute($query_params6);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
        
    }

    
    //fetching all the rows from the query
    $row6 = $stmt6->fetch();   
    $total_investigation = $row6['amt_paid'];
    
    
    
     // 3RD QUERY
  
  
      //gets user's info based off of a username.
    $query7 = " 
            SELECT 
               SUM(item_amount) as amt_npaid
            FROM payment
            WHERE payment_item_id=4 OR payment_item_id=5 AND is_paid=1
        ";
    
    try {
        $stmt7   = $db->prepare($query7);
        $result7 = $stmt7->execute($query_params7);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
        
    }

    
    //fetching all the rows from the query
    $row7 = $stmt7->fetch();   
    $total_admission = $row7['amt_npaid'];
    
    
    
    
   // 4th QUERY
  
  
      //gets user's info based off of a username.
    $query8 = " 
            SELECT       
               SUM(item_amount) as amt_paid
            FROM payment
            WHERE payment_item_id=3 AND is_paid=1
        ";
    
    try {
        $stmt8   = $db->prepare($query8);
        $result8 = $stmt8->execute($query_params8);
    }
    catch (PDOException $ex) {
        // For testing, you could use a die and message. 
        //die("Failed to run query: " . $ex->getMessage());
        
        //or just use this use this one to product JSON data:
        $response["success"] = 0;
        $response["message"] = "Database Error1. Please Try Again!";
        die(json_encode($response));
        
    }

    
    //fetching all the rows from the query
    $row8 = $stmt8->fetch();   
    $total_pharmacy = $row8['amt_paid'];
    
    
    if ($row) {
    
        $response["patient_status"] = $row['total'];
    //    $response["patient_status"] = "Total Transactions : $total_transactions \n Total Paid Amount : $total_paid \n Total Not Paid Amount : $total_npaid \n ";
 //       $response["patient_status"] = $row['total']$row4['monthly'];
        $response["success"] = 1;
        $response["message"] = "ALL PAYMENTS & FINANCE SUMMARY:\n\n Total Transactions : BDT. $total_transactions \n Total Paid Amount : BDT. $total_paid \n Total Amount Pending : BDT. $total_npaid \n Previous Month Income : BDT. $month_paid \n\n  Outdoor Income : BDT. $total_outdoor \n Investigations Income : BDT. $total_investigation \n Admissions Income : BDT. $total_admission \n Pharmacy Income : BDT. $total_pharmacy \n";
        die(json_encode($response));
    } else {
        $response["success"] = 0;
        $response["message"] = "Status Not Available";
        die(json_encode($response));
    }


?> 
