<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<%@include file="../includes/header.jsp"%>

<style>
    /* 화면 중앙 정렬 스타일 */
    .form-container {
        display: flex;
        justify-content: center;
        align-items: flex-start;
        height: 90vh;
        padding-top: 50px;
    }

    .form-box {
        width: 400px;
        padding: 20px;
        background-color: #f9f9f9;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        text-align: center;
    }

    .form-title {
        font-size: 24px;
        font-weight: bold;
        margin-bottom: 20px;
        color: #333;
    }

    .form-box .form-group {
        margin-bottom: 15px;
    }

    .form-box button {
        width: 100%;
    }
</style>

<div class="form-container">
    <div class="form-box">
        <!-- 폼 제목 -->
        <div class="form-title">게시글 보기</div>
        
        <form role="form">
            <div class="form-group">
                <label>제목</label>
                <input class="form-control" name="title" value="<c:out value='${mboard.title}'/>" readonly>
            </div>
            <div class="form-group">
                <label>내용</label>
                <textarea class="form-control" rows="5" name="content" readonly><c:out value="${mboard.content}"/></textarea>
            </div>
            <div class="form-group">
                <label>링크</label>
                <textarea class="form-control" rows="5" name="link" readonly><c:out value="${mboard.link}"/></textarea>
            </div>
            <div class="form-group">
                <label>작성자</label>
                <input class="form-control" name="writer" value="<c:out value='${mboard.writer}'/>" readonly>
            </div>
        </form>

        <!-- 목록으로 이동 버튼 -->
        <button type="button" class="btn btn-secondary" onclick="window.location.href='/board/menulist'">목록으로</button>

        <!-- 삭제 버튼 (작성자 또는 admin만 가능) -->
        <c:if test="${mboard.writer == sessionScope.userid || sessionScope.userid == 'admin'}">
            <form action="/board/menuremove" method="post" style="margin-top: 15px;">
                <input type="hidden" name="bno" value="<c:out value='${mboard.bno}'/>">
                <button type="submit" class="btn btn-danger btn-block">삭제</button>
            </form>
        </c:if>
    </div>
</div>

<%@include file="../includes/footer.jsp"%>
