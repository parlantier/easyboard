<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="/resources/button_control.js"></script>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
    <h2>글작성</h2>
    <form class="" action="/board/write" method="post">

      <p>title</p>
      <p><input type="text" name="title" value=""></p>
      <p>content</p>
      <p><input type="text" name="content" value=""></p>
      <p>writer</p>
      <p><input type="text" name="writer" value=""></p>
      <input type="submit" name="name" value="작성">
      <input type="button" class="cancel" value="취소">
    </form>
  </body>
</html>
