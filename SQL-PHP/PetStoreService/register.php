<?php
include "db_connect.php";
$username = $_POST['username'];
$telephone = $_POST['telephone'];
$address = $_POST['address'];
$email = $_POST['email'];
$password = $_POST['password'];

    // Kiểm tra cơ bản (bạn có thể thêm nhiều kiểm tra hơn tùy theo yêu cầu của bạn)
    if (empty($username) || empty($telephone) || empty($address) || empty($email) || empty($password)) {
        //echo json_encode(["status" => "error", "message" => "Tất cả các trường đều bắt buộc."]);
        $response = [
            'success' => false,
            'message' => 'Все, что вам нужно сделать, это сделать это.',
            'result' => null
        ];
        exit();
    }

    // Kiểm tra xem username đã tồn tại chưa
$username_check_query = "SELECT * FROM user WHERE username = ?";
$stmt_username_check = $conn->prepare($username_check_query);
$stmt_username_check->bind_param("s", $username);
$stmt_username_check->execute();
$result_username_check = $stmt_username_check->get_result();

if ($result_username_check->num_rows > 0) {
    $response = [
        'success' => false,
        'message' => 'Аккаунт уже существует.',
        'result' => null
    ];
    print_r(json_encode($response, JSON_UNESCAPED_UNICODE));
    exit();
}

// Kiểm tra xem số điện thoại đã tồn tại chưa
$telephone_check_query = "SELECT * FROM user WHERE telephone = ?";
$stmt_telephone_check = $conn->prepare($telephone_check_query);
$stmt_telephone_check->bind_param("s", $telephone);
$stmt_telephone_check->execute();
$result_telephone_check = $stmt_telephone_check->get_result();

if ($result_telephone_check->num_rows > 0) {
    $response = [
        'success' => false,
        'message' => 'Номер телефона уже существует.',
        'result' => null
    ];
    print_r(json_encode($response, JSON_UNESCAPED_UNICODE));
    exit();
}

// Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
//$hashed_password = password_hash($password, PASSWORD_BCRYPT);

// Chèn người dùng mới nếu không có trùng lặp
$sql = "INSERT INTO user (username, telephone, address, email, password) VALUES (?, ?, ?, ?, ?)";
$stmt = $conn->prepare($sql);

if ($stmt) {
    // Gán tham số vào câu lệnh đã chuẩn bị
    $stmt->bind_param("sssss", $username, $telephone, $address, $email, $password);

    // Thực thi câu lệnh
    if ($stmt->execute()) {
        $response = [
            'success' => true,
            'message' => 'Регистрация пользователя прошла успешно.',
            'result' => null
        ];
    } else {
        $response = [
            'success' => false,
            'message' => 'Регистрация пользователя не удалась.',
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
print_r(json_encode($response, JSON_UNESCAPED_UNICODE));

// Đóng kết nối cơ sở dữ liệu
$conn->close();

?>