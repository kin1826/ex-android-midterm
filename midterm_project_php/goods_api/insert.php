<?php
global $conn;
include "db.php";

$name = $_POST['name'];
$desc = $_POST['description'];
$price = $_POST['price'];
$image = $_POST['image']; // link ảnh

$sql = "INSERT INTO product(productName, description, price, image)
        VALUES('$name', '$desc', '$price', '$image')";

if ($conn->query($sql)) {
    echo "Success";
} else {
    echo "Error";
}
?>