<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%@include file="../includes/header.jsp"%>

<style>
    body {
        background-color: #f8f9fa; /* 배경색을 부드러운 회색 계열로 */
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
        text-align: center;
    }

    .form-title {
        font-size: 22px;
        font-weight: bold;
        margin-bottom: 20px;
        color: #343a40;
    }

    .form-group {
        margin-bottom: 15px;
        text-align: left;
    }

    .form-control {
        border-radius: 5px;
        padding: 10px;
        font-size: 16px;
        width: 100%;
        border: 1px solid #ccc;
        background-color: #f8f9fa;
    }

    .btn-group {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
    }

    .btn {
        width: 48%;
        padding: 10px;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
    }

    .btn-primary {
        background-color: #007bff;
        border: none;
        color: white;
        transition: 0.3s;
    }

    .btn-primary:hover {
        background-color: #0056b3;
    }

    .btn-danger {
        background-color: #dc3545;
        border: none;
        color: white;
        transition: 0.3s;
    }

    .btn-danger:hover {
        background-color: #a71d2a;
    }

    .btn-secondary {
        background-color: #6c757d;
        border: none;
        color: white;
        transition: 0.3s;
    }

    .btn-secondary:hover {
        background-color: #565e64;
    }
</style>

<div class="form-container">
    <div class="form-box">
        <!-- 폼 제목 -->
        <div class="form-title">게시글 보기</div>
        
        <form role="form">
            <div class="form-group">
                <label>제목</label>
                <input class="form-control" name="title" value="<c:out value='${board.title}'/>" readonly>
            </div>

            <div class="form-group">
                <label>내용</label>
                <textarea class="form-control" rows="5" name="content" readonly><c:out value="${board.content}"/></textarea>
            </div>

            <div class="form-group">
                <label>작성자</label>
                <input class="form-control" name="writer" value="<c:out value='${board.writer}'/>" readonly>
            </div>
        </form>

        <!-- 버튼 그룹 -->
        <div class="btn-group">
            <!-- 목록으로 이동 버튼 -->
            <button type="button" class="btn btn-secondary" onclick="window.location.href='/board/boardlist'">목록으로</button>

            <!-- 수정 버튼 (작성자 또는 admin만 가능) -->
            <c:if test="${board.writer == sessionScope.userid'}">
                <button type="button" class="btn btn-primary" onclick="location.href='/board/modify?bno=<c:out value='${board.bno}'/>'">수정</button>
            </c:if>
        </div>

        <!-- 삭제 버튼 (작성자 또는 admin만 가능) -->
        <c:if test="${board.writer == sessionScope.userid || sessionScope.userid == 'admin'}">
            <form action="/board/remove" method="post" style="margin-top: 15px;">
                <input type="hidden" name="bno" value="<c:out value='${board.bno}'/>">
                <button type="submit" class="btn btn-danger btn-block">삭제</button>
            </form>
        </c:if>
    </div>
</div>

<%@include file="../includes/footer.jsp"%>
