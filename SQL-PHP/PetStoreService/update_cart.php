<?php
include "db_connect.php"; // Kết nối tới cơ sở dữ liệu

// Nhận dữ liệu từ phương thức POST
$idcart = $_POST['idcart'];
$quantity = $_POST['quantity'];
// Chỉ cập nhật số lượng trong bảng `cart` theo `idcart`
$sql_update = "UPDATE cart SET quantity = ? WHERE idcart = ?";
$stmt_update = $conn->prepare($sql_update);

if ($stmt_update) {
    $stmt_update->bind_param("ii", $quantity, $idcart);

    if ($stmt_update->execute()) {
        $response = [
            'success' => true,
            'message' => 'Обновлено количество товаров в корзине.',
            'result' => null
        ];
    } else {
        $response = [
            'success' => false,
            'message' => 'Обновите количество неисправных продуктов.',
            'result' => null
        ];
    }

    $stmt_update->close();
} else {
    $response = [
        'success' => false,
        'message' => 'Ошибка базы данных при обновлении.',
        'result' => null
    ];
}

// Trả về phản hồi dưới dạng JSON
echo json_encode($response, JSON_UNESCAPED_UNICODE);

// Đóng kết nối cơ sở dữ liệu
$conn->close();
?>
