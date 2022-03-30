$(document).ready(function () {
  // 답변 항목 추가 버튼 클릭.
  $("#btn-add").click(function () {
    var div = $('<div class="poll-answer-item">')
      .append('<input type="text" name="answer">')
      .append('<button type="button" class="button btn-remove">삭제</button>');
    $("#poll-answer-list").append(div);
  });

  // 항목 삭제
  $(document).on("click", ".button.btn-remove", function () {
    $(this).parent(".poll-answer-item").remove();
  });

  // 투표 생성 버튼 클릭
  $("#btn-make").click(function () {
    if (!$("#question").val()) {
      alert("질문 내용 입력!!!");
      return;
    }

    var flag = true;
    $("input[name='answer']").each(function (i, element) {
      if (!$(this).val()) {
        alert("답변 항목 입력!!!");
        flag = false;
      }
    });

    if (flag && confirm("투표를 생성하시겠습니까?")) {
      var answerArr = [];
      $("input[name='answer']").each(function (i, element) {
        answerArr.push($(this).val());
      });

      //   localStorage에 저장.
      localStorage.setItem("question", $("#question").val());
      localStorage.setItem("answers", JSON.stringify(answerArr));

      alert("투표를 생성합니다.");
      opener.location.reload();
      self.close();
    }
  });
});
