<?php

global $conn;
include "db.php";

$id = $_POST['id'];
$name = $_POST['name'];
$desc = $_POST['description'];
$price = $_POST['price'];
$image = $_POST['image'];

$sql = "UPDATE product SET
        productName='$name',
        description='$desc',
        price='$price',
        image='$image'
        WHERE id='$id'";

if ($conn->query($sql)) {
    echo "Updated";
} else {
    echo "Error";
}
