<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<script>
	$(function(){
		var bno = '${boardVO.bno}';
		var page = '${cri.page}';
		var perPageNum = '${cri.perPageNum}';
		  $(".cancel").click(function(){
		  var result = confirm("취소하고 돌아가시겟습니까?");
		    if(result === true){
		      var url = "/board/read?bno="+bno
		    		  +"&page="+page
		    		  +"&perPageNum="+perPageNum;
		      $(location).attr("href", url);
		    }
		  });
		});

	
	</script>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
    <h2>게시물 수정</h2>
    <form class="" action="/board/modify" method="post">

      <p>title</p>
      <p><input type="text" name="title" value="${boardVO.title}"></p>
      <p>content</p>
      <p><input type="text" name="content" value="${boardVO.content}"></p>
      <p>writer</p>
      <p><input type="text" name="writer" value="${boardVO.writer}" readonly="readonly"></p>
      
      <input type="hidden" name="bno" value="${boardVO.bno}">
      <input type="hidden" name="page" value="${cri.page}">
      <input type="hidden" name="perPageNum" value="${cri.perPageNum}">
    
      <input type="submit" name="name" value="수정">
      <input type="button" class="cancel" value="취소">
    </form>
  </body>
</html>
