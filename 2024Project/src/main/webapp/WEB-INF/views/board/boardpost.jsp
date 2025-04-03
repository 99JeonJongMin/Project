<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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

<script>
    function validateForm() {
        let title = document.forms["boardForm"]["title"].value.trim();
        let content = document.forms["boardForm"]["content"].value.trim();

        if (title === "" || content === "") {
            alert("제목과 내용을 입력해주세요.");
            return false; // 폼 제출 방지
        }
        return true; // 유효성 검사가 통과되면 제출 허용
    }
</script>

<div class="form-container">
    <div class="form-box">
        <!-- 오류 메시지 출력 -->
        <c:if test="${not empty errorMessage}">
            <p style="color: red; font-weight: bold; margin-bottom: 15px;">${errorMessage}</p>
        </c:if>

        <!-- 폼 제목 -->
        <div class="form-title">게시글 작성</div>
        
        <!-- 폼 시작 -->
        <form name="boardForm" action="/board/boardpost" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label>제목</label>
                <input class="form-control" name="title" placeholder="제목을 입력하세요">
            </div>

            <div class="form-group">
                <label>내용</label>
                <textarea class="form-control" rows="5" name="content" placeholder="내용을 입력하세요"></textarea>
            </div>

            <div class="form-group">
                <label>작성자</label>
                <!-- 세션에서 userid 값을 가져와 value에 설정 -->
                <input class="form-control" name="writer" value="<c:out value='${sessionScope.userid}'/>" readonly />
            </div> 

            <button type="submit" class="btn btn-primary">작성하기</button>
            <button type="button" class="btn btn-secondary" onclick="window.location.href='/board/boardlist'">목록으로</button>
        </form>
        <!-- 폼 종료 -->
    </div>
</div>

<%@include file="../includes/footer.jsp"%>
