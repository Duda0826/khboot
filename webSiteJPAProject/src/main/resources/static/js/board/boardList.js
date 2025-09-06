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