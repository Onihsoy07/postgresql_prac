$(function() {

    $("#btn-cfrRequest").click(function() {
        let id = $("#id").val();

//        alert(id);

        var imageInput = $("#imageInput")[0];
        console.log(imageInput.files[0]);
        var preImage = $("#preview");
        console.log(resizingFile);

        alert('aaaa');

        var formData = new FormData();
        formData.append("image", resizingFile);

        if(imageInput.files.length === 0){
            alert("파일을 선택해주세요");
            return;
        }

        $.ajax({
            url : '/test/cfr',
            type : 'POST',
            data : formData,
            dataType: "json",
            processData: false, //프로세스 데이터 설정 : false 값을 해야 form data로 인식합니다
            contentType: false //헤더의 Content-Type을 설정 : false 값을 해야 form data로 인식합니다
        }).done(function(data){
            alert("성공");
            location.href = "/cfr/" + id;
        }).fail(function(error){
            alert("실패");
        });
    });


});

//function resize() {
//    alert("안녛");
//
//    const fileSrc = $("#imageInput")[0].files[0];
//    console.log(fileSrc);
//
//    const actionImgCompress = async (fileSrc) => { //이미지 압축
//        console.log("시작");
//        const options = { //압축 옵션
//            maxSizeMB: 1.9,
//            maxWidthOrHeight: 1920,
//            useWebWorker: true
//        }
//        try {
//            const compressedFile = await imageCompression(fileSrc, options);
//            console.log(compressedFile);
//            console.log("완료");
//            return compressedFile;
//        } catch (error) {
//            console.log(error);
//        }
//    }
//
////    const getImgUpload = async (image: File) => {
////      const resizingBlob = await imageCompression(image, { maxSizeMB: 0.5 });
////      const resizingFile = new File([resizingBlob], image.name, { type: image.type });
////      return resizingFile;
////    };
//
//
//
//
//}


//이미지 압축
var resizingFile;

async function handleImageUpload(event) {

  const imageFile = event.target.files[0];
  console.log(imageFile);
  console.log('originalFile instanceof Blob', imageFile instanceof Blob); // true
  console.log(`originalFile size ${imageFile.size / 1024 / 1024} MB`);

  const options = {
    maxSizeMB: 0.1,
    maxWidthOrHeight: 1920,
    useWebWorker: true,
    onProgress: async function(c) {
        await console.log(c);
    }
  }
  try {
    const compressedFile = await imageCompression(imageFile, options);
    console.log('compressedFile instanceof Blob', compressedFile instanceof Blob); // true
    console.log(`compressedFile size ${compressedFile.size / 1024 / 1024} MB`); // smaller than maxSizeMB

    resizingFile = new File([compressedFile], imageFile.name, { type: imageFile.type });
    console.log(resizingFile);
//    await uploadToServer(compressedFile); // write your own logic
    readURL(resizingFile);

  } catch (error) {
    console.log(error);
  }

}


//이미지 미리보기
function readURL(file) {
//  if (input.files && input.files[0]) {
    console.log("readURL 시작");
    var reader = new FileReader();
    reader.onload = function(e) {
      document.getElementById('preview').src = e.target.result;
    };
    reader.readAsDataURL(file);
//  } else {
//    document.getElementById('preview').src = "";
//  }
}

//document.getElementById("btn-cfrRequest").addEventListener('click',cfrRequest);

const item = document.querySelector(".drop_item");
//item.addEventListener("dragover", (e) => {
//  console.log(e);
//  console.log("드래그를 시작하면 발생하는 이벤트");
//});

item.addEventListener('dragover', (event) => {
    console.log("dragover");
  event.preventDefault();
});

item.addEventListener('dragleave', (event) => {
    console.log("dragleave");
  event.preventDefault();
});

item.addEventListener('drop', () => {
  console.log('drop 이벤트');
});
