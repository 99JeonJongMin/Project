<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>로그인</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- ✅ Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- ✅ Custom CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/login.css' />">
</head>
<body>

    <!-- ✅ 네비게이션 바 (header.jsp에서 포함) -->
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>

    <!-- ✅ 로그인 폼 -->
    <div class="container">
        <div class="card">
            <h2 class="text-center mb-4">로그인</h2>

            <!-- ✅ 오류 메시지 출력 -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>

            <form action="/login" method="post">
                <div class="mb-3">
                    <label for="userid" class="form-label">아이디</label>
                    <input type="text" id="userid" name="userid" class="form-control" required placeholder="아이디 입력">
                </div>

                <div class="mb-3">
                    <label for="passwd" class="form-label">비밀번호</label>
                    <input type="password" id="passwd" name="passwd" class="form-control" required placeholder="비밀번호 입력">
                    <a href="#" class="form-text">비밀번호를 잊으셨나요?</a>
                </div>

                <button type="submit" class="btn btn-primary">로그인</button>
            </form>

            <div class="sign-in-divider">
                <div class="divider-line"></div>
                <span class="divider-text">OR</span>
                <div class="divider-line"></div>
            </div>

            <a href="./memreg" class="btn btn-outline-primary">회원가입</a>

            <!-- ✅ 소셜 로그인 -->
            <button class="social-login-btn">
                <img src="https://upload.wikimedia.org/wikipedia/commons/c/c7/Facebook_logo_%28square%29.png" alt="페이스북">
                페이스북으로 로그인하기
            </button>

            <button class="social-login-btn">
                <img src="https://upload.wikimedia.org/wikipedia/commons/5/53/Google_%22G%22_Logo.svg" alt="구글">
                구글 계정으로 로그인하기
            </button>
        </div>
    </div>

    <!-- ✅ Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
