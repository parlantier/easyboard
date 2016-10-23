<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style>
table {
	border-collapse: collapse;
}

th, td {
	border: 1px solid #9932CC;
}


</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script>
	$(function() {
		
		var msg = '${msg}';
		reportMessage(msg);		
		
		$("#write_btn").click(function() {
			$(location).attr("href", "/board/write");
		});
		
	});
	
	function reportMessage(msg){
		if(msg === "SUCCESS"){
			alert("게시물이 추가되었습니다");
		}		
		
		if(msg === "deleted"){
			alert("글이 삭제 되었습니다")
		}
	}
</script>
<meta charset="utf-8">
<title></title>
</head>
<body>
	<div>
		<button id="write_btn">글쓰기</button>
	</div>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<c:forEach items="${list}" var="vo">
				<tr>
					<td>${vo.bno}</td>
					<td><a href="/board/read?bno=${vo.bno}&page=${cri.page}&perPageNum=${cri.perPageNum}">${vo.title}</a></td>
					<td>${vo.writer}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
							value="${vo.regdate}" /></td>
					<td>${vo.viewcnt}</td>
				</tr>
			</c:forEach>
		</table>
	<div class="pagenation">
		<!-- 이전 -->
		<c:if test="${pageMaker.prev}">
			<a href="/board/list?page=${pageMaker.startPage - 1}&perPageNum=${cri.perPageNum}">&lt; &lt;</a>
		</c:if>
		<!-- 페이징처리 -->
		<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage}"
			var="idx">
			<c:if test="${cri.page == idx}">
				${idx}
			</c:if>
			<c:if test="${cri.page != idx}">
				<a href="/board/list?page=${idx}&perPageNum=${cri.perPageNum}">${idx}</a>
			</c:if>
		</c:forEach>

		<!-- 다음 -->
		<c:if test="${pageMaker.next}">
			<a href="/board/list?page=${pageMaker.endPage + 1}&perPageNum=${cri.perPageNum}">&gt;&gt;</a>
		</c:if>

	</div>
</body>
</html>
