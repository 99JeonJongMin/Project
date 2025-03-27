<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>회원정보 수정</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
      body {
        background-color: #f8f9fa;
      }
      .card {
        border: none;
        border-radius: 10px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.1);
      }
      .btn-primary {
        background-color: #007bff;
        border: none;
      }
      .btn-primary:hover {
        background-color: #0056b3;
      }
    </style>
  </head>
  <body>
    <%@include file="/WEB-INF/views/includes/header.jsp"%>

    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-6">
          <div class="card p-4">
            <h2 class="text-center mb-4">회원정보 수정</h2>

            <c:if test="${not empty error}">
              <div class="alert alert-danger">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
              <div class="alert alert-success">${msg}</div>
            </c:if>

            <form action="/member/edit" method="post">
              <input type="hidden" name="userid" value="${member.userid}" />
              ${member.userid}

              <div class="mb-3">
                <label class="form-label">이름</label>
                <input type="text" name="name" class="form-control" value="${member.name}" required>
              </div>

              <div class="mb-3">
                <label class="form-label">이메일</label>
                <input type="email" name="email" class="form-control" value="${member.email}" required>
              </div>

              <div class="mb-3">
                <label class="form-label">현재 비밀번호</label>
                <input type="password" name="currentPassword" class="form-control" placeholder="본인 확인용 비밀번호 입력" required>
              </div>

              <div class="mb-3">
                <label class="form-label">새 비밀번호</label>
                <input type="password" name="passwd" class="form-control" placeholder="변경하지 않으려면 비워두세요">
              </div>

              <button type="submit" class="btn btn-primary w-100">정보 수정</button>
            </form>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
