<?php


if($_SERVER["REQUEST_METHOD"]=="POST")
{
	require 'connection.php';
	
	createEmploye();
	
}

function createEmploye()
{
		global $connect;
		
$name=  $_POST["name"];
$email= $_POST["email"];
$phone= $_POST["phone"];

$query = "INSERT into employee(name,email,phone) VALUES ('$name','$email','$phone');";


mysqli_query($connect,$query) or die(mysqli_error($connect));

mysqli_close($connect);


		
		 
}