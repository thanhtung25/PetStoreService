<?php
include "db_connect.php";

$idcart = $_POST['idcart'];

// Câu lệnh SQL để xóa mục giỏ hàng theo idcart
$sql_delete = "DELETE FROM cart WHERE idcart = ?";
$stmt_delete = $conn->prepare($sql_delete);

if ($stmt_delete) {
    $stmt_delete->bind_param("i", $idcart);

    if ($stmt_delete->execute()) {
        // Kiểm tra xem có bản ghi nào bị ảnh hưởng
        if ($stmt_delete->affected_rows > 0) {
            $response = [
                'success' => true,
                'message' => 'Товар удален из корзины.',
                'result' => null
            ];
        } else {
            $response = [
                'success' => false,
                'message' => 'Товар не существует в корзине.',
                'result' => null
            ];
        }
    } else {
        $response = [
            'success' => false,
            'message' => 'Удалить неисправный продукт.',
            'result' => null
        ];
    }

    $stmt_delete->close();
} else {
    $response = [
        'success' => false,
        'message' => 'Ошибка базы данных при удалении товаров.',
        'result' => null
    ];
}
// Trả về phản hồi dưới dạng JSON
echo json_encode($response, JSON_UNESCAPED_UNICODE);

// Đóng kết nối cơ sở dữ liệu
$conn->close();

?>