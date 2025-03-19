<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>


<%@include file="../includes/header.jsp"%>


<div class="form-container">
    <div class="form-box">
        <!-- 폼 제목 -->
        <div class="form-title">게시글 작성</div>
        
        <!-- 게시글 수정 폼 -->
        <form role="form" action="/board/modify" method="post">
            <div class="form-group">
                <label>제목</label>
                <input class="form-control" name="title" value="<c:out value='${board.title}'/>">
            </div>

            <div class="form-group">
                <label>내용</label>
                <textarea class="form-control" rows="5" name="content"><c:out value="${board.content}"/></textarea>
            </div>

            <div class="form-group">
                <label>작성자</label>
                <input class="form-control" name="writer" value="<c:out value='${board.writer}'/>" readonly>
            </div>

            <input type="hidden" name="bno" value="<c:out value='${board.bno}'/>"> <!-- 게시글 번호를 hidden 필드로 전달 -->

            <button type="submit" class="btn btn-primary">수정</button> <!-- 수정 버튼 -->
        </form>
        
        <!-- 게시글 삭제 폼 -->
        <form action="/board/remove" method="post" style="display:inline;">
            <input type="hidden" name="bno" value="<c:out value='${board.bno}'/>"> <!-- 게시글 번호를 hidden 필드로 전달 -->
            <button type="submit" class="btn btn-danger">삭제</button> <!-- 삭제 버튼 -->
        </form>

         <button type="button" class="btn btn-secondary" onclick="window.location.href='list2'">목록으로</button>
    </div>
</div>

<%@include file="../includes/footer.jsp"%>
