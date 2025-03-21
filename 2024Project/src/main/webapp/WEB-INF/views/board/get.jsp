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
        align-items: flex-start; /* 수직 정렬을 위쪽으로 */
        height: 90vh; /* 화면의 90% 높이 */
        padding-top: 50px; /* 상단 여백 추가 */
    }

    .form-box {
        width: 400px; /* 폼 너비 */
        padding: 20px; /* 내부 여백 */
        background-color: #f9f9f9; /* 배경 색상 */
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
        border-radius: 8px; /* 모서리 둥글게 */
        text-align: center; /* 폼 제목 중앙 정렬 */
    }

    .form-title {
        font-size: 24px; /* 제목 크기 */
        font-weight: bold; /* 제목 두껍게 */
        margin-bottom: 20px; /* 제목과 폼 필드 간격 */
        color: #333; /* 제목 색상 */
    }

    .form-box .form-group {
        margin-bottom: 15px; /* 각 필드 간격 */
    }

    .btn-group {
        display: flex;
        justify-content: space-between;
        gap: 10px;
    }

    .btn {
        flex: 1;
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
            
            <!-- 버튼 그룹 -->
            <div class="btn-group">
                <!-- 목록으로 이동 버튼 -->
                <button type="button" class="btn btn-secondary" onclick="window.location.href='/board/boardlist'">목록으로</button>

                <!-- 수정 버튼 (작성자 또는 admin만 가능) -->
                <c:if test="${board.writer == sessionScope.userid}">
                    <button type="button" class="btn btn-primary" onclick="location.href='/board/modify?bno=<c:out value='${board.bno}'/>'">수정</button>
                </c:if>
            </div>

            <!-- 삭제 버튼 (작성자 또는 admin만 가능) -->
            <c:if test="${board.writer == sessionScope.userid || sessionScope.userid == 'admin'}">
                <form action="/board/remove" method="post" style="margin-top: 10px;">
                    <input type="hidden" name="bno" value="<c:out value='${board.bno}'/>">
                    <button type="submit" class="btn btn-danger btn-block">삭제</button>
                </form>
            </c:if>
        </form>
    </div>
</div>

<%@include file="../includes/footer.jsp"%>
