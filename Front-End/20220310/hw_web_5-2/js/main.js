window.onload = function () {
    if (!document.querySelector(".content-user-list-ul")) return;
    // 비동기 통신을 위해 새로운 xmlhttp 요청 생성
    const xhr = new XMLHttpRequest();
    // 요청 method
    const method = "GET";
    // 파일 위치
    const url = "./data/user.json";
    // 위의 method 와 url 로 비동기 요청 초기화
    xhr.open(method, url, true);
    // 요청 헤더 설정
    xhr.setRequestHeader("Content-Type", "application/text");
    // 요청 동작 설정
    xhr.onreadystatechange = function () {
        if (xhr.readyState === xhr.DONE) {
            // 요청 상태가 OK 이면
            if (xhr.status === 200) {
                // Json 객체 형태로 응답 받기
                const resJson = JSON.parse(xhr.responseText);
                const userData = resJson.users;
                // 자동차 data 삽입할 html 요소 찾기
                let userList = document.querySelector(".content-user-list-ul");
                for (let i = 0; i < userData.length; i++) {
                    let carItem = `
              <li>
                <div class="list-item">
                  <div>
                    <img src="${userData[i]["img"]}" alt="" />
                  </div>
                  <div class="user-info">
                    <div>
                      <div>${userData[i]["id"]}</div>
                      <div>${userData[i]["name"]}</div>
                      <div>${userData[i]["email"]}</div>
                      <div>${userData[i]["age"]} 세</div>
                    </div>
                  </div>
                </div>
              </li>
              `;
                    userList.innerHTML += carItem;
                }
            }
        }
    };

    // 요청 보내기
    xhr.send(method == "POST" ? httpParams : null);
};

function regist() {
    // 문서에서 id 로 input data 가져오기
    let id = document.getElementById("id").value;
    let password = document.getElementById("password").value;
    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let age = document.getElementById("age").value;

    // 입력값 검증
    if (id == "" || password == "" || name == "" || email == "" || age == "") {
        alert("빈칸이 없도록 입력해주세요.");
        return;
    } else {
        // input data로 user 만들기
        const user = {
            id: id,
            password: password,
            name: name,
            email: email,
            age: age,
        };

        // user 객체 문자열로 바꿔서 로컬스토리지에 저장
        localStorage.setItem("user", JSON.stringify(user));
        alert("사용자 등록 성공!");
        // 로그인 화면으로 돌아가기
        location.replace("login.html");
    }
}

function login() {
    // 문서에서 id로 input data 가져오기
    let id = document.getElementById("id").value;
    let password = document.getElementById("password").value;

    // 로컬스토리지에 "user" 키로 저장된 item 가져와서 json 객체로 만들기
    const user = JSON.parse(localStorage.getItem("user"));

    // 입력값 검증
    if (id == user.id && password == user.password) {
        alert("로그인 성공 !");
        // 로그인 성공하면 index 페이지로 이동.
        location.replace("index.html");
    } else {
        alert("로그인 실패 !");
    }
}
