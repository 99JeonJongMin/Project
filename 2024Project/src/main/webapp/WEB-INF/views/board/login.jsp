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

            <div class="sign-in9-divider1">
                <div class="sign-in9-divider2"></div>
                <span>OR</span>
                <div class="sign-in9-divider3"></div>
              </div>
              <a href="./register" class="sign-in9-link2">회원가입</a>
              <div class="sign-in9-container7">
                <button class="sign-in9-button2">
                  <svg viewBox="0 0 877.7142857142857 1024">
                    <path d="M713.143 73.143c90.857 0 164.571 73.714 164.571 164.571v548.571c0 90.857-73.714 164.571-164.571 164.571h-107.429v-340h113.714l17.143-132.571h-130.857v-84.571c0-38.286 10.286-64 65.714-64l69.714-0.571v-118.286c-12-1.714-53.714-5.143-101.714-5.143-101.143 0-170.857 61.714-170.857 174.857v97.714h-114.286v132.571h114.286v340h-304c-90.857 0-164.571-73.714-164.571-164.571v-548.571c0-90.857 73.714-164.571 164.571-164.571h548.571z"></path>
                  </svg>
                  페이스북으로 로그인하기
                </button>
                <button class="sign-in9-button3">
                  <svg viewBox="0 0 860.0137142857142 1024">
                    <path d="M438.857 449.143h414.286c4 22.286 6.857 44 6.857 73.143 0 250.286-168 428.571-421.143 428.571-242.857 0-438.857-196-438.857-438.857s196-438.857 438.857-438.857c118.286 0 217.714 43.429 294.286 114.857l-119.429 114.857c-32.571-31.429-89.714-68-174.857-68-149.714 0-272 124-272 277.143s122.286 277.143 272 277.143c173.714 0 238.857-124.571 249.143-189.143h-249.143v-150.857z"></path>
                  </svg>
                  구글계정으로 로그인하기
                </button>
              </div>
            </div>
          </div>
          <script>
    document.addEventListener("DOMContentLoaded", function () {
        var errorMessage = "${error}";

        if (errorMessage) {
            // alert로 표시
            alert(errorMessage);

            // 또는 모달로 표시
            showErrorModal(errorMessage);
        }

        function showErrorModal(message) {
            const modalTitle = "로그인 실패";
            const modalBody = message;

            // 모달 내용 업데이트
            document.getElementById("footerModalLabel").innerText = modalTitle;
            document.querySelector("#footerModal .modal-body").innerText = modalBody;

            // 모달 표시
            const modal = new bootstrap.Modal(document.getElementById("footerModal"));
            modal.show();
        }
    });
</script>
    

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
