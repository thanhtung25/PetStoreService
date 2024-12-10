<?php
include "db_connect.php";  // Kết nối cơ sở dữ liệu

// Truy vấn để lấy toàn bộ dữ liệu từ bảng `products`
$stmt = $conn->prepare('SELECT * FROM `products`');
$stmt->execute();
$result = $stmt->get_result();
$productData = array();

// Lấy tất cả dữ liệu từ bảng `products` và lưu vào mảng
while ($row = $result->fetch_assoc()) {
    $productData[] = $row;
}

// Kiểm tra nếu có dữ liệu được trả về
if (!empty($productData)) {
    $response = [
        'success' => true,
        'message' => 'Данные о товаре успешно получены.',
        'result' => $productData
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
