// logout.js

document.addEventListener('DOMContentLoaded', function () {
    // Lắng nghe sự kiện click trên nút đăng xuất
    document.getElementById('logoutButton').addEventListener('click', function () {
        // Gọi hàm đăng xuất
        logout();
    });

    // Hàm đăng xuất
    function logout() {
        
        // Sau khi đăng xuất, chuyển hướng đến trang đăng nhập (ví dụ)
        window.location.href = 'login.html';
    }
});
