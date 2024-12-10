<?php
include "db_connect.php";
$iduser = $_POST['iduser'];
$idproduct = $_POST['idproduct'];
$quantity = $_POST['quantity'];

// Kiểm tra dữ liệu đầu vào
if (empty($iduser) || empty($idproduct)|| empty($quantity)) {
    $response = [
        'success' => false,
        'message' => 'Все, что вам нужно сделать, это сделать это.',
        'result' => null
    ];
    echo json_encode($response, JSON_UNESCAPED_UNICODE);
    return;
}
// Kiểm tra xem sản phẩm đã có trong giỏ hàng của người dùng chưa
$sql_check = "SELECT * FROM warehouse WHERE iduser = ? AND idproduct = ?";
$stmt_check = $conn->prepare($sql_check);

if ($stmt_check) {
    // Gán tham số vào câu lệnh SQL đã chuẩn bị
    $stmt_check->bind_param("ii", $iduser, $idproduct);
    $stmt_check->execute();
    $result = $stmt_check->get_result();

    if ($result->num_rows > 0) {
        // Nếu sản phẩm đã có trong giỏ hàng, tăng số lượng
        $row = $result->fetch_assoc();
        $new_quantity = $row['quantity'] + $quantity;

        $sql_update = "UPDATE warehouse SET quantity = ? WHERE iduser = ? AND idproduct = ?";
        $stmt_update = $conn->prepare($sql_update);

        if ($stmt_update) {
            $stmt_update->bind_param("iii", $new_quantity, $iduser, $idproduct);

            if ($stmt_update->execute()) {
                $response = [
                    'success' => true,
                    'message' => 'Обновлено количество товара на складе.',
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
    } else {
        // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới sản phẩm vào giỏ hàng
        $sql_insert = "INSERT INTO warehouse (iduser, idproduct, quantity) VALUES (?, ?, ?)";
        $stmt_insert = $conn->prepare($sql_insert);

        if ($stmt_insert) {
            $stmt_insert->bind_param("iii", $iduser, $idproduct, $quantity);

            if ($stmt_insert->execute()) {
                $response = [
                    'success' => true,
                    'message' => 'Товар добавлен в инвентарь.',
                    'result' => null
                ];
            } else {
                $response = [
                    'success' => false,
                    'message' => 'Не удалось добавить товар в инвентарь.',
                    'result' => null
                ];
            }

            $stmt_insert->close();
        } else {
            $response = [
                'success' => false,
                'message' => 'Ошибка базы данных при добавлении товаров.',
                'result' => null
            ];
        }
    }

    // Đóng câu lệnh kiểm tra
    $stmt_check->close();
} else {
    $response = [
        'success' => false,
        'message' => 'Ошибка базы данных при проверке товаров.',
        'result' => null
    ];
}

// Trả về phản hồi dưới dạng JSON
echo json_encode($response, JSON_UNESCAPED_UNICODE);

// Đóng kết nối cơ sở dữ liệu
$conn->close();


?>