const token = $("meta[name='_csrf']").attr("content")
const header = $("meta[name='_csrf_header']").attr("content");

$(function() {
    $("#aabtn-replySave").click(function() {
        let id = $("#id").text();

        let data = {
            comment : $("#comment").val()
        }

        $.ajax({
            url : "/board/" + id + "/reply",
            type : "POST",
            contentType: "application/json;charset=utf-8",
            dataType:"json",
            data : JSON.stringify(data),
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            }
        }).done(function (res) {
            console.log(res);
            if(res.httpsCode != 200) {
                alert("댓글 쓰기가 실패되었습니다.");
            } else {
                alert("댓글 쓰기가 완료되었습니다.");
                $('#replyWin').load(location.href + ' #replyBox');
            }
        }).fail(function (error){
            console.log(error);
            alert("댓글 쓰기가 실패되었습니다.");
        });
    });

});

function newHello(id) {
    alert("hello");
    $("#replyBtnBox" + id).load(location.href + " #replyBtn" + id);
//    $(`replyButton${id}`).css("display", "none");
//    $('div').remove(`div[name=temp]`);
//    var commentBox = $(`div[name=comment_${id}]`).clone();
////    console.log(this(commentBox));
//    commentBox.css("margin-left", "50px");
//    commentBox.find("span").text("hello~~~");
//    commentBox.find("button").text("바바바바바바바바ㅏ");
//
//    commentBox.attr("name", "temp");
//    $(`div[name=comment_${id}]`).after(commentBox);

}

function writeReply(boardId, replyId, depth) {
        let data = {
            comment : $("#comment").val(),
            replyId : replyId,
            depth : depth
        }

        if(data.comment.length <= 0) {
            data.comment = $("#reComment").val();
        }

        console.log(data);

        $.ajax({
            url : "/board/" + boardId + "/reply",
            type : "POST",
            contentType: "application/json;charset=utf-8",
            dataType:"json",
            data : JSON.stringify(data),
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            }
        }).done(function (res) {
            console.log(res);
            if(res.httpsCode != 200) {
                alert("댓글 쓰기가 실패되었습니다.");
            } else {
                alert("댓글 쓰기가 완료되었습니다.");
                $('#replyWin').load(location.href + ' #replyBox');
            }
        }).fail(function (error){
            console.log(error);
            alert("댓글 쓰기가 실패되었습니다.");
        });
}
