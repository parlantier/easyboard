<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script>
$(function(){

  var formObj = $(".keep_value");
  var page = '${cri.page}';
  var perPageNum = '${cri.perPageNum}';
  var msg = '${msg}';
  console.log("c: "+msg);
  console.log(page);
	if(msg === "SUCCESS"){
		alert("수정 하였습니다");
	}
  
  $(".modify_btn").on("click",function(){
      formObj.attr("action", "/board/modify");
      formObj.attr("method", "get");
      formObj.submit();
  });

  $(".remove_btn").on("click", function(){
        formObj.attr("action", "/board/remove");
        formObj.attr("method", "post");
        formObj.submit();
  });

  $(".goList_btn").on("click", function(){
    
    var url = "/board/list?page="+page+"&perPageNum="+perPageNum;
    $(location).attr("href", url);
  });

});
    </script>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
    <h2>게시물</h2>
      <p>title</p>
      <p><input type="text" name="title" value="${boardVO.title}" readonly="readonly"></p>
      <p>content</p>
      <p><input type="text" name="content" value="${boardVO.content}" readonly="readonly"></p>
      <p>writer</p>
      <p><input type="text" name="writer" value="${boardVO.writer}" readonly="readonly"></p>
      <input type="button"  class="modify_btn" value="수정">
      <input type="button" class="remove_btn" value="삭제">
      <input type="button" class="goList_btn" value="목록">
      <form class="keep_value" action="/board/read" method="post">
          <input type="hidden" name="bno" value="${boardVO.bno}">
          <input type="hidden" name="page" value="${cri.page}">
          <input type="hidden" name="perPageNum" value="${cri.perPageNum}">
      </form>
  </body>
</html>
