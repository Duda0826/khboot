document.getElementById("insertBtn").addEventListener("click", () => {
    if(!chkData("#title", "첵제목을")) return;
    else if(!chkData("#author" , "책저자를")) return;
    else if(!chkData("#publisher" , "출판사를")) return;
    else if(!chkData("#publishDate" , "출간연도를")) return;
    else if(!chkData("#price" , "책가격을")) return;
    else{
        const isNew = document.getElementById("bookId").value === "0";
        const actionUrl = isNew ? "/book/bookInsert" : "/book/bookUpdate";
        formSubmit("insertForm", "post", actionUrl);
    }
});

document.getElementById("cancelBtn").addEventListener("click", () => {
    formReset("insertForm");
});

document.addEventListener('click', (event) => {
    if(event.target.classList.contains('deleteBtn')){
    event.preventDefault();

    const row = event.target.closest('tr');
    const number =row.querySelector('th[scope="row"]').textContent.trim();

    //console.log(`선택한 번호: ${number}`);
    if(confirm("책정보를 삭제하시겠습니까?")){
        locationProcess(`/book/bookDelete?bookId=${number}`);
     }
    }
});

document.addEventListener("click", function(event) {
    if(event.target.classList.contains("updateFormBtn")) {
    event.preventDefault();

    const row = event.target.closest("tr");
    const cells = row.querySelectorAll("td");
    const id = row.querySelector("th[scope='row']").textContent.trim();

    document.getElementById("bookId").value = id;
    document.getElementById("title").value = cells[0].textContent.trim();
    document.getElementById("author").value = cells[1].textContent.trim();
    document.getElementById("publisher").value = cells[2].textContent.trim();
    document.getElementById("publishDate").value = cells[3].textContent.trim();
    document.getElementById("price").value = cells[4].textContent.trim();

    //입력완료 -> 수정완료
    document.getElementById("insertBtn").textContent = "수정완료";

    //수정 취소 버튼 중복 생성 방지
    if(!document.querySelector(".upResetBtn")) {
        const cancelBtn = document.createElement("button");
        cancelBtn.type = "button";
        cancelBtn.className = "upResetBtn btn btn-secondary btn-sm ms-2";
        cancelBtn.textContent = "수정 취소";

        const insertBtn = document.getElementById("insertBtn");
        insertBtn.insertAdjacentElement("afterend", cancelBtn);
        document.getElementById("cancelBtn").disabled = true;
    }
    }
});

document.addEventListener("click", function(event) {
    if(event.target.classList.contains("upResetBtn")) {
        document.querySelectorAll(".upResetBtn").forEach(btn => btn.remove());

        document.getElementById("insertBtn").textContent = "입력완료";
        document.getElementById("bookId").value = 0;
        document.getElementById("cancelBtn").disabled = false;
        formReset("insertForm");
    }
});
