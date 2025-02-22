document.addEventListener("DOMContentLoaded", function () {
    // ✅ ログアウトボタンの処理
    const logoutButton = document.getElementById("confirmLogout");
    if (logoutButton) {
        logoutButton.addEventListener("click", function () {
            document.getElementById("logout-form").submit();
        });
    } else {
        console.error("confirmLogout ボタンが見つかりません！");
    }

    // ✅ ハンバーガーメニューの開閉処理（jQuery なし）
    const menuButton = document.querySelector(".navbar-toggler");
    const menuContent = document.getElementById("navbarSupportedContent");

    if (menuButton && menuContent) {
        menuButton.addEventListener("click", function () {
            menuContent.classList.toggle("show");
        });
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const logoutButton = document.getElementById("confirmLogout");
    if (logoutButton) {
        logoutButton.addEventListener("click", function () {
            document.getElementById("logout-form").submit();
        });
    } else {
        console.error("confirmLogout ボタンが見つかりません！");
    }
});