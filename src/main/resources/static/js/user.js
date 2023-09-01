//var login = document.getElementById('btn-login');
//var join = document.getElementById('btn-join');
//
//login.addEventListener('click',function(){
//    alert('login');
//});
//
//join.addEventListener('click',function(){
//    alert('join');
//});

const token = $("meta[name='_csrf']").attr("content")
const header = $("meta[name='_csrf_header']").attr("content");

$(function() {
    $("#btn-join").click(function() {
        $("#btn-join").unbind('click');

        alert("체크");
        let data = {
            username : $("#username").val(),
            password : $("#password").val(),
            email : $("#email").val()
        }

        $.ajax({
            url : "/auth/joinProc",
            type : "POST",
            contentType: "application/json;charset=utf-8",
            dataType:"json",
            data : JSON.stringify(data),
            //csrf 토큰 헤더에 추가
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            }
        }).done(function (res) {
            if(res == true) {
                alert("회원가입이 완료되었습니다.");
                location.href="/";
                return;
            } else {
                alert("회원가입을 실패하였습니다.");
            }
        }).fail(function (error){
            console.log(error);
            alert("회원가입 통신 실패하였습니다.");
        });
    });

    $("#btn-login").click(function() {
        $("#btn-login").unbind('click');

        var form1 = $("#login-form").serialize();

        $.ajax({
            url : "/auth/loginProc",
            type : "POST",
            data : form1,
//            contentType: "application/json;charset=utf-8",
            processData: false, //프로세스 데이터 설정 : false 값을 해야 form data로 인식합니다
            contentType: false, //헤더의 Content-Type을 설정 : false 값을 해야 form data로 인식합니다
            //csrf 토큰 헤더에 추가
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            }
        }).done(function (res) {
                alert("로그인이 완료되었습니다.");
                location.href="/";
        }).fail(function (error){
            console.log(error);
            alert("로그인 통신 실패하였습니다.");
        });
    });



});