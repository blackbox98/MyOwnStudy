window.onload = function () {
    document.getElementById("btn-add").addEventListener("click", function () {
        let voteAnswerList = document.getElementById("vote-answer-list");

        let divEl = document.createElement("div");
        divEl.setAttribute("class", "vote-answer-item");

        let inputEl = document.createElement("input");
        inputEl.setAttribute("type", "text");
        inputEl.setAttribute("name", "answer");

        let buttonEl = document.createElement("button");
        buttonEl.setAttribute("class", "button");
        buttonEl.setAttribute("type", "button");
        buttonEl.appendChild(document.createTextNode("삭제"));
        buttonEl.addEventListener("click", function () {
            voteAnswerList.removeChild(buttonEl.parentElement);
        });

        divEl.appendChild(inputEl);
        divEl.appendChild(buttonEl);

        voteAnswerList.appendChild(divEl);
    });

    document.getElementById("btn-make").addEventListener("click", function () {
        let question = document.getElementById("question").value;
        if (!question) {
            alert("질문 내용을 입력!!!");
            return;
        }

        let answers = document.getElementsByName("answer");
        for (let answer of answers) {
            if (!answer.value) {
                alert("답변 항목 입력!!!");
                return;
            }
        }

        let answerArr = [];
        for (let answer of answers) {
            answerArr.push(answer.value);
        }

        if (confirm("투표 생성?")) {
            localStorage.setItem("question", question);
            localStorage.setItem("answer", JSON.stringify(answerArr));

            opener.location.reload();
            self.close();
        }
    });
};
