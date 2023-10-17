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

$(function() {
    $("#btn-join").click(function() {
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
            data : JSON.stringify(data)
        }).done(function (res) {
            if(res.httpsCode != 200) {
                alert("회원가입이 실패되었습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href="/";
            }
        }).fail(function (error){
            alert("회원가입이 실패하였습니다.");
        });
    });

    $("#btn-login").click(function() {
        let data = {
            id : $("#username").val(),
            password : $("#password").val()
        }

        $.ajax({
            url : "/auth/login",
            type : "POST",
            contentType: "application/json;charset=utf-8",
            dataType:"json",
            data : JSON.stringify(data)
        }).done(function (res) {
            console.log(res);
            if(res.httpsCode != 200) {
                alert("로그인이 실패되었습니다.");
            } else {
                alert("로그인이 완료되었습니다.");
                location.href="/";
            }
        }).fail(function (error){
            alert("로그인이 실패하였습니다.");
        });
    });


});