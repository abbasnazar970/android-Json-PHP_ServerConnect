<?php

if($_SERVER["REQUEST_METHOD"]=="POST")
{
	include 'connection.php';
	showemployee();
}

function showemployee()
{
	global $connect;

	$query= " Select * FROM employee ; ";

	$result=mysqli_query($connect,$query);
	$number_of_rows=mysqli_num_rows($result);

	$temp_array=array();




	if($number_of_rows > 0)
	{
		while ( $row= mysqli_fetch_assoc($result)) {

			
			$temp_array[]=$row;

		}
	}

	header('Content-Types:aplication/json');

	echo json_encode(array("employees" =>$temp_array));



	mysql_close($connect);



}

?>