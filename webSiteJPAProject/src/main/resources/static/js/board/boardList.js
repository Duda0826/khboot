//document.addEventListener('click', (event) => {
//    const target = event.target.closest('.goDetail');
//    const tr = target.closest('tr');
//    const boardNumber = tr.dataset.num;
//    console.log("글 번호: " + boardNumber);
//
//    locationProcess(`/board/${boardNumber}`);
//})
//
//document.getElementById("insertFormBtn").addEventListener("click", () => {
//    locationProcess("/board/insertForm");
//});
//
//const keyword = document.querySelector("#keyword");
//const search = document.querySelector("#search");
//
//let value = "";
//let regex;
//
//const highlightKeyword = function (element, keyword, regex) {
//    const html = element.innerHTML;
//    element.innerHTML = html.replace(regex, `<span class='board-required'>${keyword}</span>`);
//}
//
//const searchKeyword = function () {
//    const word = keyword.value.trim();
//    const searchData = search.value;
//
//    if (word !== "" && searchData !== "b_content") {
//        const value = (searchData === "b_title") ? ".list tr td.goDetail"
//                        : (searchData === "b_name") ? ".list tr td.name"
//                        : "";
//        const elements = document.querySelectorAll(value);
//        console.log(elements);
//
//        // "gi" 플래그 : global(전체 검색) + ignoreCase(대소문자 무시)
//        const regex = new RegExp(word, "gi");
//        elements.forEach((el) => {
//            highlightKeyword(el, word, regex);
//        });
//    }
//};
//
//document.getElementById("keyword").addEventListener("keydown", function(event) {
//    if(event.key === "Enter") {
//        event.preventDefault();
//    }
//});
//
//document.getElementById("searchBtn").addEventListener("click", function() {
//    if(!chkData("#keyword", "검색어를")) return;
//    formSubmit("searchForm", "get", "/board/boardList");
//});
//
//document.getElementById("allSearchBtn").addEventListener("click", function() {
//    locationProcess("/board/boardList");
//});
//
//// 초기 실행
//searchKeyword();

document.querySelectorAll(".goDetail").forEach(function (element) {
    element.addEventListener("click", function () {
        const tr = this.closest("tr");
        const no = tr.dataset.no;
        locationProcess(`/board/${no}`);
    });
});

/* 페이징 처리 */
document.querySelectorAll(".page-item a").forEach(function(anchor) {
    anchor.addEventListener("click", function(e) {
        e.preventDefault();
        const pageNumber = this.dataset.number;
        document.getElementById("page").value = pageNumber;
        formSubmit("searchForm", "get", "/board/boardList");
    });
});

const keywordInput = document.getElementById("keyword");
const searchSelect = document.getElementById("search");
let value = "";
let regex;


const searchKeyword = function () {
    const keyword = keywordInput.value;
    const search = searchSelect.value;

    if ((keyword !== "" || startDate.value !== "") && search !== "content") {
        value = search === 'title' ? ".list tr .goDetail"
              : search === 'name' ? ".list tr td.name"
              : search === 'regDate' ? ".list tr td.date"
              : "";

        const elements = document.querySelectorAll(value);
        elements.forEach(el => {
            if (search === 'regDate') {
                // 날짜 하이라이트만 날짜 부분으로 적용
                const dateText = el.textContent;
                const dateOnly = dateText.slice(0, 10); // yyyy-MM-dd
                const regex = new RegExp(dateOnly, 'g');
                el.innerHTML = dateText.replace(regex, `<span class='board-required'>${dateOnly}</span>`);
            } else {
                const regex = new RegExp(keyword, "gi");
                el.innerHTML = el.innerHTML.replace(regex, match => `<span class='board-required'>${match}</span>`);
            }
        });
    }
};

const dateSearchDisplay = function () {
    const dateAreas = document.querySelectorAll(".dateArea");
    const textArea = document.querySelector(".textArea");

    const isDateSearch = searchSelect.value === "regDate";

    // dateArea 모든 요소에 대해 hidden 토글
    dateAreas.forEach(el => el.hidden = !isDateSearch);
    textArea.hidden = isDateSearch;

    if (!isDateSearch) {
          keywordInput.focus();
    }
};

document.getElementById("searchBtn").addEventListener("click", function () {
    const startDate = document.getElementById("startDate");
    const endDate = document.getElementById("endDate");

    if (searchSelect.value !== "regDate") {
        startDate.value = "";
        endDate.value = "";
        if (!chkData("#keyword", "검색어를")) return;
    } else {
        keywordInput.value = "";
        if (!chkData("#startDate", "시작날자를")) return;
        if (!chkData("#endDate", "종료날자를")) return;

        if (startDate.value > endDate.value) {
            alert("시작날자가 종료날자보다 더 클 수 없습니다.");
            return;
        }
    }

    document.getElementById("page").value = 1;
    formSubmit("searchForm", "get", "/board/boardList");
});

document.getElementById("allSearchBtn").addEventListener("click", function () {
    locationProcess("/board/boardList");
});

searchSelect.addEventListener("change", dateSearchDisplay);

// 초기 설정
dateSearchDisplay();
searchKeyword();