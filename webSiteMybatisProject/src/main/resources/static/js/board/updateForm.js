document.getElementById("boardUpdateBtn").addEventListener("click", function () {
    if (!chkData("#boardTitle", "제목을")) return;
    else if (!chkData("#boardContent", "내용을")) return;
    else {
        formSubmit("updateForm", "post", "/board/boardUpdate");
    }
});

document.getElementById("boardCancelBtn").addEventListener("click", function () {
    formReset("updateForm");
});

document.getElementById("boardListBtn").addEventListener("click", function () {
    locationProcess("/board/boardList");
});