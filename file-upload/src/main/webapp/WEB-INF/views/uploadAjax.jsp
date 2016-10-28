<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
.fileDrop {
	width: 100%;
	height: 200px;
	border: 1px dotted blue;
}

small {
	margin-left: 3px;
	font-weight: bold;
	color: gray;
}
</style>
</head>
<body>

	<h3>Ajax File Upload</h3>
	<div class='fileDrop'></div>

	<div class='uploadedList'></div>

	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	 
	<script>
	$(".fileDrop").on("dragover dragenter", function(event){
		
		event.preventDefault();
	});
	
	
	$(".fileDrop").on("drop", function(event){
		event.preventDefault();
		
		var files = event.originalEvent.dataTransfer.files;
		
		var file = files[0];

		var formData = new FormData();		
		
		formData.append("file", file);
		
		$.ajax({
			url: '/file/uploadAjax',
			type: 'post',
			processData : false,
			contentType : false,
			data:formData,
			dataType:'text',
			success : function(data){
				alert(data);
				var str = "";
				
				 if(checkImageType(data)){
					  str ="<div><a href='/file/displayFile?fileName="+getImageLink(data)+"'>"
							  +"<img src='/file/displayFile?fileName="+data+"'/>"
							  +"</a><small data-src="+data+">X</small></div>";
				  }else{
					  str = "<div><a href='/file/displayFile?fileName="+data+"'>" 
							  + getOriginalName(data)+"</a>"
							  +"<small data-src="+data+">X</small></div></div>";
				  }
				
				$(".uploadedList").append(str);
			}
			
		});
		
	
	});
	
	// x를 클릭햇을때 실제파일이 삭제되고 프론트에잇는 이미지파일도 삭제
	$(".uploadedList").on("click", "small", function(event){
		
		var that = $(this);
		
		$.ajax({
			url:'/file/deleteFile',
			type:'post',
			data: {fileName:$(this).attr("data-src")},
			dataType:"text",
			success:function(result){
				if(result == 'deleted'){
					alert("삭제 되었습니다");
					that.parent("div").remove();
				}
			}
		
		});
		
	});
	
	
	</script>
<script>

// 확장자가 미디어타입인지 true/false로 리턴하는 메서드
function checkImageType(fileName){
	
	// i의 의미 대,소문자 구분없음
	var pattern = /jpg|gif|png|jpeg/i;
	
	return fileName.match(pattern);
}

// 랜덤id_파일이름 _을 이용해 추출하는 메서드
function getOriginalName(fileName){
	
	if(checkImageType(fileName)){
		return;
	}
	
	var idx = fileName.indexOf("_") + 1;
	return fileName.substr(idx);
}

// 원본파일 링크
// 년/월/일 추출 + s_제외 나머지추출해서 서로합침
function getImageLink(fileName){
	
	if(!checkImageType(fileName)){
		return;
	}
	
	var front = fileName.substr(0, 12);
	var end = fileName.substr(14);
	return front + end;
}

</script>
</body>
</html>