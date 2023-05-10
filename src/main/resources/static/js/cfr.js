document.getElementById("btn-cfrRequest").addEventListener('click',cfrRequest);

function cfrRequest(){

    var imageInput = $("#imageInput")[0];
    var formData = new FormData();
    formData.append("image", imageInput.files[0]);

    if(imageInput.files.length === 0){
        alert("파일은 선택해주세요");
        return;
      }

    console.log(formData);

    $.ajax({
        url : '/test/cfr',
        type : 'POST',
        data : formData,
        dataType: "json",
        processData: false, //프로세스 데이터 설정 : false 값을 해야 form data로 인식합니다
        contentType: false //헤더의 Content-Type을 설정 : false 값을 해야 form data로 인식합니다
    }).done(function(data){
        alert("성공");
        console.log(data);
    }).fail(function(error){
        alert("실패");
        console.log(error);
    });
}

