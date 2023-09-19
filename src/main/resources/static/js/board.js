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
                alert(res.data);
            } else {
                alert("글쓰기가 완료되었습니다.");
                location.href="/";
            }
        }).fail(function (error){
            console.log(error);
            alert("글쓰기가 실패되었습니다.");
        });
    });

    $("#btn-delete").click(function() {
        alert("삭제 진행");
        let boardId = $("#id").text();

        $.ajax({
            url : "/board/"+boardId,
            type : "DELETE",
            contentType: "application/json;charset=utf-8",
//            dataType:"json"
        }).done(function (res) {
            console.log(res);
            if(res.httpsCode != 200) {
                alert(res.data);
            } else {
                alert("글 삭제를 완료했습니다.");
                location.href="/";
            }
        }).fail(function (error){
            console.log(error);
            alert("글 삭제를 실패했습니다.");
        });
    });

    $("#select-board").on('change', function(){
        let val = $("#select-board option:selected").attr('value');

        if(!val){
            alert("선택된 항목이 없습니다.");
            return false;
        }
        window.open(val);
    });


});

function boardSearch() {
    //select option 확인
    let target = $('#searchKeyword option:selected').val();
    let sd = $('#searchInput').val();
    location.href = `/search?target=${target}&keyword=${sd}`;
}

function rereplyOpen(boardId, replyId, depth) {
    $("div").remove("#reReplyBox");
    $("div").remove("#areplyBox");

    let html = "";

    html += "<div id='reReplyBox'>";
    html += "<div class='card-body'>";
    html += "<div class='row'>";
    html += "<div class='form-group col-sm-8'>";
    html += "<input class='form-control input-sm' id='reComment' type='text' placeholder='댓글 입력...'>";
    html += "</div>";
    html += "<div class='form-group col-sm-2'>";
    html += "<button type='button' class='btn btn-primary' onclick='writeReply(" + boardId + ", " + replyId + ", " + depth + ")'>저장</button>";
    html += "</div>";
    html += "</div>";
    html += "</div>";

    $("#replybox" + replyId).after(html);

}

//    <div id="replyBox">
//        <div class="card-body" th:if="${principal}!=anonymousUser">
//            <div class="row">
//                <div class="form-group col-sm-8">
//                    <input class="form-control input-sm" id="comment" type="text" placeholder="댓글 입력...">
//                </div>
//                <div class='form-group col-sm-2'>
//                    <button type='button' class='btn btn-primary' id='btn-replySave111' onclick='writeReply()'>
//                        저장
//                    </button>
//                </div>
//            </div>
//        </div>



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