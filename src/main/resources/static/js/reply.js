$(function() {
    $("#btn-replySave").click(function() {
        let id = $("#id").text();

        console.log(id);

        let data = {
            comment : $("#comment").val()
        }

        $.ajax({
            url : "/board/" + id + "/reply",
            type : "POST",
            contentType: "application/json;charset=utf-8",
            dataType:"json",
            data : JSON.stringify(data)
        }).done(function (res) {
            console.log(res);
            if(res.httpsCode != 200) {
                alert("댓글 쓰기가 실패되었습니다.");
            } else {
                alert("댓글 쓰기가 완료되었습니다.");
                location.reload();
            }
        }).fail(function (error){
            console.log(error);
            alert("댓글 쓰기가 실패되었습니다.");
        });
    });

});

function newHello(id) {
    $(`replyButton${id}`).css("display", "none");
    $('div').remove(`div[name=temp]`);
    var commentBox = $(`div[name=comment_${id}]`).clone();
//    console.log(this(commentBox));
    commentBox.css("margin-left", "50px");
    commentBox.find("span").text("hello~~~");
    commentBox.find("button").text("바바바바바바바바ㅏ");

    commentBox.attr("name", "temp");
    $(`div[name=comment_${id}]`).after(commentBox);

}