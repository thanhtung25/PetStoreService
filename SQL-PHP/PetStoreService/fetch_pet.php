<?php
include "db_connect.php"; 

$stmt = $conn->prepare('SELECT * FROM `petsprofile`');
$stmt->execute();
$result = $stmt->get_result();
$petData = array();

// Lấy tất cả dữ liệu từ bảng `products` và lưu vào mảng
while ($row = $result->fetch_assoc()) {
    $petData[] = $row;
}

// Kiểm tra nếu có dữ liệu được trả về
if (!empty($petData)) {
    $response = [
        'success' => true,
        'message' => 'Данные о товаре успешно получены.',
        'result' => $petData
    ];
} else {
    $response = [
        'success' => false,
        'message' => 'Данные о продукте отсутствуют.',
        'result' => null
    ];
}

// Trả về dữ liệu dưới dạng JSON
print_r(json_encode($response, JSON_UNESCAPED_UNICODE));

// Đóng kết nối
$conn->close();
?>