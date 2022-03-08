// 기능 1 로그인
let login = document.getElementById("logIn");
login.addEventListener("click", function () {
    let login_id = prompt("아이디입력", "ssafy");
    let login_pass = prompt("비밀번호입력", "1234");
    if (login_id != "ssafy" || login_pass != "1234") {
        alert("로그인 실패!!!");
    } else if (login_id == "ssafy" && login_pass == "1234") {
        alert("로그인 성공!!!");
        // 기능 2 로그인 여부에 따른 메뉴 표시 여부 설정
        document.getElementById("display_before_login").style.display = "none";
        document.getElementById("display_after_login").style.display = "inline-block";
        // 기능 3 프로필 이미지 변경
        document.getElementById("profile_img").src = "./media/profile.png";
    }
});

// 기능 4 지역 메뉴 선택 시 펼치기/접기
let menu_op_fd = document.getElementsByClassName("store_display");
let store_item_op_fd = document.getElementsByClassName("store_item_sub");
let store_area_item_op_fd = document.getElementsByClassName("store_area");
menu_op_fd[0].addEventListener("click", function () {
    menu_op_fd[0].style.display = "none";
    menu_op_fd[1].style.display = "block";
    for (const store_item_sub of store_item_op_fd) {
        store_item_sub.style.display = "block";
    }
});
menu_op_fd[1].addEventListener("click", function () {
    menu_op_fd[0].style.display = "block";
    menu_op_fd[1].style.display = "none";
    for (const store_item_sub of store_item_op_fd) {
        store_item_sub.style.display = "none";
    }
});
var flag_sw = true;
store_area_item_op_fd[0].addEventListener("click", function () {
    if (flag_sw) {
        store_item_op_fd[0].style.display = "block";
        flag_sw = !flag_sw;
    } else {
        store_item_op_fd[0].style.display = "none";
        flag_sw = !flag_sw;
    }
});
var flag_dj = true;
store_area_item_op_fd[1].addEventListener("click", function () {
    if (flag_dj) {
        store_item_op_fd[1].style.display = "block";
        flag_dj = !flag_dj;
    } else {
        store_item_op_fd[1].style.display = "none";
        flag_dj = !flag_dj;
    }
});
var flag_gm = true;
store_area_item_op_fd[2].addEventListener("click", function () {
    if (flag_gm) {
        store_item_op_fd[2].style.display = "block";
        flag_gm = !flag_gm;
    } else {
        store_item_op_fd[2].style.display = "none";
        flag_gm = !flag_gm;
    }
});
var flag_gj = true;
store_area_item_op_fd[3].addEventListener("click", function () {
    if (flag_gj) {
        store_item_op_fd[3].style.display = "block";
        flag_gj = !flag_gj;
    } else {
        store_item_op_fd[3].style.display = "none";
        flag_gj = !flag_gj;
    }
});

// 기능 5 : 투표 만들기
document.getElementsByClassName("header_nav_menuitem")[4].addEventListener("click", function () {
    window.open("./Make_Vote.html", "Make a Vote", "width = 460, height = 300, top = 200, left = 550");
});
