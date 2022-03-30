$(document).ready(function () {
  // localStorage에서 진행중인 투표 정보 얻기.
  let question = localStorage.getItem("question");

  let pollContent;

  if (question) {
    // 진행중인 투표가 있을경우.
    let answers = localStorage.getItem("answers");
    let answerArr = JSON.parse(answers);
    pollContent = `
	  <div class="poll-title">[당신의 선택]</div>
	  <div class="poll-question">${question}</div>
	  <div class="poll-answer">
		<ul>
	  `;
    for (let answer of answerArr) {
      pollContent += `
		  <li><label><input type="radio" name="poll-answer" id="" value="${answer}" /> ${answer}</label></li>
		  `;
    }
    pollContent += `
	  </ul>
	  </div>
	  <div class="poll-btn">
		<button id="btn-poll" class="button btn-primary">투표하기</button>
		<button class="button">이전결과보기</button>
	  </div>
	  <div class="poll-date">투표기간 : 2022.03.01 ~ 2022.03.31</div>
	  `;

    $(".content-left-poll").html(pollContent);
    $(".content-left-poll-btn").css("display", "none");
    $(".content-left-poll").css("display", "");
  } else {
    // 진행중인 투표가 없을경우.
    pollContent = `진행 중인 투표가 없습니다!!!`;
    $(".content-left-poll").html(pollContent);

    $(".content-left-poll-btn").css("display", "");
    $(".content-left-poll").css("text-align", "center").css("color", "red").css("display", "");
  }

  // 투표만들기
  $("#btn-makepoll").click(function () {
    window.open("makepoll.html", "poll", "width=420, height=300, top=300, left=400");
  });

  // 투표하기
  $(document).on("click", "#btn-poll", function () {
    let selVal = $("input[name='poll-answer']:checked").val();
    alert(selVal + "를 선택했습니다.");
  });
});
