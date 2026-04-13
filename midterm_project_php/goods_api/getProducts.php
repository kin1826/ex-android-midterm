<?php
global $conn;
include "db.php";

$sql = "SELECT * FROM product";
$result = $conn->query($sql);

$data = array();

while($row = $result->fetch_assoc()) {
    $data[] = $row;
}

echo json_encode($data);
?>