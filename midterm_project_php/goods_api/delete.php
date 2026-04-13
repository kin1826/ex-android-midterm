<?php

global $conn;
include "db.php";

$id = $_POST['id'];

$sql = "DELETE FROM product WHERE id='$id'";

if ($conn->query($sql)) {
    echo "Deleted";
} else {
    echo "Error";
}
