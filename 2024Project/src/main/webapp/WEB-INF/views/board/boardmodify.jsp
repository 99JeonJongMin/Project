<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%@include file="../includes/header.jsp"%>

<style>
    body {
        background-color: #f8f9fa; /* 부드러운 배경색 */
    }

    .form-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .form-box {
        background: #fff;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        width: 500px;
    }

    .form-title {
        font-size: 22px;
        font-weight: bold;
        text-align: center;
        margin-bottom: 20px;
        color: #343a40;
    }

    .form-group {
        margin-bottom: 15px;
    }

    .form-control {
        border-radius: 5px;
        padding: 10px;
        font-size: 16px;
    }

    .btn-group {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
    }

    .btn {
        width: 48%;
    }

    .btn-primary {
        background-color: #007bff;
        border: none;
        transition: 0.3s;
    }

    .btn-primary:hover {
        background-color: #0056b3;
    }

    .btn-danger {
        background-color: #dc3545;
        border: none;
        transition: 0.3s;
    }

    .btn-danger:hover {
        background-color: #a71d2a;
    }

    .btn-secondary {
        background-color: #6c757d;
        border: none;
        transition: 0.3s;
    }

    .btn-secondary:hover {
        background-color: #565e64;
    }
</style>

<div class="form-container">
    <div class="form-box">
        <!-- 폼 제목 -->
        <div class="form-title">게시글 수정</div>
        
        <!-- 게시글 수정 폼 -->
        <form role="form" action="/board/boardmodify" method="post">
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

            <div class="btn-group">
                <button type="submit" class="btn btn-primary">수정</button> <!-- 수정 버튼 -->
                <button type="button" class="btn btn-secondary" onclick="window.location.href='/board/boardlist'">목록으로</button>

            </div>
        </form>
    </div>
</div>

<%@include file="../includes/footer.jsp"%>
