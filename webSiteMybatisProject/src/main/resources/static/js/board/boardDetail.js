let buttonCheck = "" // 수정버튼과 삭제버튼을 구별하기 위한 변수

// 비밀번호 입력 영역 표시/숨김 처리
const pwdInit = function(value) {
    const pwdFormArea = document.querySelector("#pwdFormArea");
    pwdFormArea.style.visibility = value;
};

// 메시지 초기화
const btnInit = function () {
    const msg = document.querySelector("#message");
    msg.classList.remove("board-msg-error-color");
    msg.classList.add("board-msg-default-color");
    msg.textContent = "작성시 입력한 비밀번호를 입력해 주세요."
}

pwdInit("hidden");

document.getElementById("updateFormBtn").addEventListener("click", function () {
    //formSubmit("dataForm", "get", "/board/updateForm");
    pwdInit("visible");
    btnInit();
    buttonCheck = "updateButton";
});

document.getElementById("boardDeleteBtn").addEventListener("click", function() {
    /*if(confirm("정말 삭제하시겠습니까?")) {
        formSubmit("dataForm", "post", "/board/boardDelete");
    }*/
    pwdInit("visible");
    btnInit();
    buttonCheck = "deleteButton";
});

document.getElementById("boardPasswd").addEventListener("keyup", function() {
    btnInit();
});

document.getElementById("passwdBtn").addEventListener("click", function(){
    const passwdInput = document.querySelector("#boardPasswd");
    const messageEl = document.querySelector("#message");
    const pwdForm = document.querySelector("#pwdForm");

    if(!dataCheck("#boardPasswd", "message", "비밀번호를")) return;
    /*FormData는 해당 폼 내의 모든 입력 필드(name 속성이 있는 것들)를 자동으로 수집하여 key-value 형태로 저장.*/
    const formData = new FormData(pwdForm);

    fetch("/board/pwdConfirm", {
        method: "POST",
        body: formData
    })
    .then(response => response.text())
    .then(resultData => {
        if(resultData === '불일치') {
            messageEl.textContext = '작성시 입력한 비밀번호가 일치하지 않습니다.';
            messageEl.classList.remove("board-msg-default-color");
            messageEl.classList.add("board-msg-error-color");
            passwdInput.select();
        } else if(resultData === "일치") {
            messageEl.textContent = "";
            if (buttonCheck === "updateButton") {
                formSubmit("dataForm", "get", "/board/updateForm");
            } else if(buttonCheck === "deleteButton") {
                if(confirm("정말 삭제하시겠습니까?")) {
                    formSubmit("dataForm", "post", "/board/boardDelete");
                }
            }
        }
    })
    .catch(() => {
        alert("시스템 오류입니다. 관리자에게 문의하세요");
    })
});

document.getElementById("passwdCancelBtn").addEventListener("click", function () {
    const passwdInput = document.querySelector("#boardPasswd");
    passwdInput.value = "";
    pwdInit("hidden");
    buttonCheck = "";
})

document.getElementById("insertFormBtn").addEventListener("click", function() {
    locationProcess("/board/insertForm");
});

document.getElementById("boardListBtn").addEventListener("click", function() {
    locationProcess("/board/boardList");
})