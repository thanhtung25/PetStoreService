<?php
include "db_connect.php";  // Kết nối cơ sở dữ liệu
// Kiểm tra xem username và password có được gửi không
    $username = $_POST['username'];
    $password = $_POST['password'];
    // Sử dụng Prepared Statement để tránh SQL Injection
    $stmt = $conn->prepare('SELECT * FROM `user` WHERE `username` = ? AND `password` = ?');
    $stmt->bind_param('ss', $username, $password);
    // Thực thi câu lệnh và lấy kết quả
    $stmt->execute();
    $result = $stmt->get_result();
    $userData = array();

    while ($row = $result->fetch_assoc()) {
        $userData[] = $row;
    }
    // Kiểm tra nếu có dữ liệu được trả về
    if (!empty($userData)) {
        $response = [
            'success' => true,
            'message' => 'Войти успешно.',
            'result' => $userData
        ];
    } else {
        $response = [
            'success' => false,
            'message' => 'Ошибка входа.',
            'result' => null
        ];
    }
print_r(json_encode($response, JSON_UNESCAPED_UNICODE));
// Đóng kết nối
$conn->close();
?>
