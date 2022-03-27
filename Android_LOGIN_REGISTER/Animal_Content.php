<?php  
	// $servername = "localhost";
	// $username = "root";
	// $password = "";
	// $database = "users";

	// // create content
	// $conn = new mysqli($servername,$username,$password,$database);

	// //check content
	// // kiểm tra cái này có lỗi trong database không ?
	// if ($conn->connect_error) {
	// 	die("Connection failed: ". $conn->connect_error);
	// }

	require_once 'connect.php';

	$sql = "SELECT * FROM animal";
	$result = $conn->query($sql);

	$mang = array();
	// Lấy hàng số lượng trong kết quả tập tin
	$row_cnt = $result->num_rows;
	if ($row_cnt > 0) {
		while ($row = $result->fetch_assoc()) {
			array_push($mang, $row);
		}
		echo json_encode($mang);
	}else{
		echo "0 result";
	}
	$conn->close();

?>