<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>로그인</title>
    <meta property="og:title" content="Euphoric Live Albatross" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta charset="utf-8" />
    <meta property="twitter:card" content="summary_large_image" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <style>
      html {
        font-family: 'Inter', sans-serif;
        font-size: 16px;
      }
      body {
        font-weight: 400;
        line-height: 1.15;
        background: #f8f9fa;
      }
      .navbar {
        padding-left: 150px;
        padding-right: 150px;
      }
      .navbar-brand {
        font-weight: bold;
        font-size: 1.5rem;
      }
      .navbar-nav .nav-link {
        font-size: 1.2rem;
        padding-left: 20px;
        padding-right: 20px;
      }
      .navbar-nav .ms-auto .nav-link {
        font-size: 1.2rem;
        color: #007bff;
      }
      .navbar-nav .ms-auto .nav-link.active {
        font-weight: bold;
      }
      .sign-in9-form {
        margin-top: 20px;
      }
      .sign-in9-form input {
        margin-bottom: 15px;
        padding: 10px;
        width: 100%;
        border: 1px solid #ced4da;
        border-radius: 5px;
      }
      .sign-in9-form button {
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 1.1rem;
        cursor: pointer;
      }
      .sign-in9-form button:hover {
        background-color: #0056b3;
      }
      .sign-in9-divider1 {
        display: flex;
        justify-content: center;
        align-items: center;
        margin: 20px 0;
      }
      .sign-in9-divider2, .sign-in9-divider3 {
        flex-grow: 1;
        height: 1px;
        background-color: #ced4da;
      }
      .sign-in9-link2 {
        text-align: center;
        font-size: 1rem;
        color: #007bff;
        text-decoration: none;
      }
      .sign-in9-link2:hover {
        text-decoration: underline;
      }
      .sign-in9-container7 {
        display: flex;
        justify-content: space-between;
        margin-top: 20px;
      }
      .sign-in9-button2, .sign-in9-button3 {
        width: 48%;
        padding: 10px;
        background-color: transparent;
        border: 1px solid #ced4da;
        border-radius: 5px;
        cursor: pointer;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 1rem;
      }
      .sign-in9-button2:hover, .sign-in9-button3:hover {
        background-color: #f1f1f1;
      }
      .sign-in9-button2 svg, .sign-in9-button3 svg {
        width: 20px;
        margin-right: 10px;
      }
    </style>
  </head>
  <body>
   <%@include file="/WEB-INF/views/includes/header.jsp"%>
   <c:if test="${not empty success}">
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        ${success}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</c:if>

    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card shadow-sm">
            <div class="card-body">
              <h2 class="text-center mb-4">로그인</h2>
                <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
              <form class="sign-in9-form" action="/member/login" method="post">
		        <div class="mb-3">
		            <label for="userid" class="form-label">아이디</label>
		            <input type="text" id="userid" name="userid" required placeholder="아이디" class="form-control" />
		        </div>
		        <div class="mb-3">
		            <label for="passwd" class="form-label">비밀번호</label>
		            <input type="password" id="passwd" name="passwd" required placeholder="비밀번호" class="form-control" />
		            <a href="#" class="form-text text-primary" data-bs-toggle="modal" data-bs-target="#findPwModal">비밀번호를 잊으셨나요?</a>
		        </div>
		        <button type="submit" class="btn btn-primary">로그인</button>
		      </form>

              <div class="sign-in9-divider1">
                <div class="sign-in9-divider2"></div>
                <span>OR</span>
                <div class="sign-in9-divider3"></div>
              </div>
              <a href="<c:url value='/member/memreg' />" class="sign-in9-link2">회원가입</a>
              <div class="sign-in9-container7">
                <button class="sign-in9-button2">
                  <svg viewBox="0 0 877.7142857142857 1024">
                    <path d="..."/>
                  </svg>
                  페이스북으로 로그인하기
                </button>
                <button class="sign-in9-button3">
                  <svg viewBox="0 0 860.0137142857142 1024">
                    <path d="..."/>
                  </svg>
                  구글계정으로 로그인하기
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

<!-- 🔐 비밀번호 찾기 모달 (꾸민 버전) -->
<div class="modal fade" id="findPwModal" tabindex="-1" aria-labelledby="findPwModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <form action="/member/findpw" method="post">
        <div class="modal-header custom-bg text-white">
          <h5 class="modal-title" id="findPwModalLabel">비밀번호 찾기 🔑</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <div class="mb-3">
            <label class="form-label">아이디</label>
            <input type="text" name="userid" class="form-control" placeholder="아이디를 입력하세요" required>
          </div>
          <div class="mb-3">
            <label class="form-label">이름</label>
            <input type="text" name="name" class="form-control" placeholder="이름을 입력하세요" required>
          </div>
          <div class="mb-3">
            <label class="form-label">이메일</label>
            <input type="email" name="email" class="form-control" placeholder="example@email.com" required>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-primary w-100">임시 비밀번호 발급</button>
        </div>
      </form>
    </div>
  </div>
</div>

<!-- ✅ 임시 비밀번호 안내 모달 -->
<div class="modal fade" id="tempPwModal" tabindex="-1" aria-labelledby="tempPwModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content rounded-3">
      <div class="modal-header bg-success text-white">
        <h5 class="modal-title" id="tempPwModalLabel">임시 비밀번호 안내</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body text-center">
        <p class="fs-5">📢 발급된 임시 비밀번호:</p>
        <p class="fw-bold fs-4 text-success">${tempPassword}</p>
        <p class="text-muted">로그인 후 반드시 비밀번호를 변경해주세요.</p>
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

<c:if test="${not empty tempPassword}">
<script>
  document.addEventListener("DOMContentLoaded", function () {
    var modal = new bootstrap.Modal(document.getElementById('tempPwModal'));
    modal.show();
  });
</script>
</c:if>

<script>
  window.addEventListener("DOMContentLoaded", () => {
    const alertEl = document.querySelector(".alert-dismissible");
    if (alertEl) {
      setTimeout(() => {
        alertEl.classList.remove("show");
        alertEl.classList.add("fade");
        alertEl.style.opacity = "0";
        setTimeout(() => {
          alertEl.remove();
        }, 500);
      }, 3000);
    }
  });
</script>
  </body>
</html>
