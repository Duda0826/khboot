$("#subjectNumber").on("change", function(){
	$.ajax({
			url: '/student/studentAutoNumber',
			data: {
				"subjectNumber": $("#subjectNumber").val()
			},
			method: 'GET',
			dataType: 'text'
		}).done(function (data) {
			//console.log(data);
			$("#studentNumber").val(data);
		}).fail(function() {
			alert( "잠시 후에 다시 접속해 주세요." );
		});
});

// 아이디 중복 체크
let idButtonCheck = false;
$("#idCheck").on("click", function(){
	if (!checkData("#studentId","사용할 아이디를")) {
		return;
	} else {
		$.ajax({
			url: '/student/studentIdCheck',
			data: {
				"studentId": $("#studentId").val()
			},
			method: 'POST',
			dataType: 'text'
		}).done(function (data) {
			//console.log(data);
			alert(data);
			if(data.indexOf("없는") >= 0){
				$("#studentId").val("");
				$("#studentId").focus();
			} else {
				idButtonCheck = true;
			}
		}).fail(function() {
			alert( "잠시 후에 다시 접속해 주세요." );
		});
	}
});

// 주소 검색
$('#postCode').on("click", function() {
	new daum.Postcode({
		oncomplete: function (data) {
			const code = data.zonecode;        // 우편번호
			const address = data.roadAddress;  // 주소
			let value = "(" + code +") " + address;
			$("#studentAddress").val(value);
		}
	}).open();
});


$("#insertBtn").on("click", ()=>{
	if($("#subjectNumber > option:selected").index() == 0){
		alert("학과를 선택해 주세요");
		return;
	}
	else if (!checkData("#studentName","학생이름을")) return;
	else if (!checkData("#studentId","아이디를")) return;
	else if (idButtonCheck === false){
		alert("ID 중복체크를 해주세요.");
		return;
	}
	else if (!checkData("#studentPasswd","비밀번호를")) return;
	else if (!checkData("#studentBirth","생년월일을")) return;
	else if (!checkData("#studentPhone","전화번호를")) return;
	else if (!checkData("#studentAddress","주소를")) return;
	else if (!checkData("#studentEmail","이메일을")) return;
	else{
	    actionProcess("#insertForm", "post", "/student/studentInsert");
	}
});

$("#cancelBtn").on("click", ()=>{
    resetProcess("#insertForm");
});