<?php
include "db_connect.php"; // Kết nối tới cơ sở dữ liệu

// Nhận dữ liệu từ phương thức POST
$petname = $_POST['petname'];
$petbreed = $_POST['petbreed'];
$petbirthdate = $_POST['petbirthdate'];
$petweight = $_POST['petweight'];
$petgender = $_POST['petgender'];
$petnutrition = $_POST['petnutrition'];
$iduser = $_POST['iduser']; // Khóa ngoại tới bảng user

// Kiểm tra dữ liệu đầu vào
if (empty($petname) || empty($petbreed) || empty($petbirthdate) || empty($petweight) || empty($petgender) || empty($petnutrition) || empty($iduser)) {
    $response = [
        'success' => false,
        'message' => 'Все, что вам нужно сделать, это сделать это.',
        'result' => null
    ];
    echo json_encode($response, JSON_UNESCAPED_UNICODE);
    return;
}

// Chèn dữ liệu vào bảng petsprofile
$sql = "INSERT INTO petsprofile (petname, petbreed, petbirthdate, petweight, petgender, petnutrition, iduser) 
        VALUES (?, ?, ?, ?, ?, ?, ?)";

$stmt = $conn->prepare($sql);

if ($stmt) {
    // Gán tham số vào câu lệnh SQL đã chuẩn bị
    $stmt->bind_param("ssssssi", $petname, $petbreed, $petbirthdate, $petweight, $petgender, $petnutrition, $iduser);

    // Thực thi câu lệnh SQL
    if ($stmt->execute()) {
        $response = [
            'success' => true,
            'message' => 'Информация о домашнем животном успешно добавлена.',
            'result' => null
        ];
    } else {
        $response = [
            'success' => false,
            'message' => 'Добавить неудавшуюся информацию о домашнем животном.',
            'result' => null
        ];
    }

    // Đóng câu lệnh
    $stmt->close();
} else {
    $response = [
        'success' => false,
        'message' => 'Ошибка базы данных.',
        'result' => null
    ];
}

// Trả về phản hồi dưới dạng JSON
echo json_encode($response, JSON_UNESCAPED_UNICODE);

// Đóng kết nối cơ sở dữ liệu
$conn->close();
?>
