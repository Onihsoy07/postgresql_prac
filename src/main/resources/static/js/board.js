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
    $("#btn-save").click(function() {
        let data = {
            title : $("#title").val(),
            content : $("#content").val()
        }

        $.ajax({
            url : "/board",
            type : "POST",
            contentType: "application/json;charset=utf-8",
            dataType:"json",
            data : JSON.stringify(data)
        }).done(function (res) {
            console.log(res);
            if(res.httpsCode != 200) {
                alert("글쓰기가 실패되었습니다.");
            } else {
                alert("글쓰기가 완료되었습니다.");
                location.href="/";
            }
        }).fail(function (error){
            console.log(error);
            alert("글쓰기가 실패되었습니다.");
        });
    });

});

function boardSearch() {
    //select option 확인
    let target = $('#searchKeyword option:selected').val();
    let sd = $('#searchInput').val();
    location.href = `/search?target=${target}&keyword=${sd}`;
}



//function cfrBoard(let id) {
//    $.ajax({
//        url : "/board/" + id,
//        type : "get",
//        contentType: "application/json;charset=utf-8",
//        dataType:"json",
//        data : JSON.stringify(data)
//    }).done(function (res) {
//        console.log(res);
//        if(res.httpsCode != 200) {
//            alert("글쓰기가 실패되었습니다.");
//        } else {
//            alert("글쓰기가 완료되었습니다.");
//            location.href="/";
//        }
//    }).fail(function (error) {
//        console.log(error);
//        alert("글쓰기가 실패되었습니다.");
//    })
//}