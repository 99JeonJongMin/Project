<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>회원가입</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- ✅ Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- ✅ Custom CSS -->
    <link rel="stylesheet" href="/resources/css/sign.css">

    <script>
        function validateForm() {
            let userid = document.getElementById("userid").value.trim();
            let password = document.getElementById("passwd").value.trim();
            let confirmPassword = document.getElementById("confirmPasswd").value.trim();

            // ✅ 아이디 정규식 검사 (영문 소문자+숫자, 4~16자)
            let idPattern = /^[a-z0-9]{4,16}$/;
            if (!idPattern.test(userid)) {
                alert("아이디는 4~16자의 영문 소문자 및 숫자로만 입력해야 합니다.");
                return false;
            }

            // ✅ 비밀번호 정규식 검사 (8~20자, 영문+숫자 필수, 특수문자는 선택 사항)
            let pwPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d!@#$%^&*]{8,20}$/;
            if (!pwPattern.test(password)) {
                alert("비밀번호는 8~20자의 영문과 숫자를 포함해야 합니다. (특수문자는 선택 사항)");
                return false;
            }

            // ✅ 비밀번호 확인 일치 여부 검사
            if (password !== confirmPassword) {
                alert("비밀번호가 일치하지 않습니다.");
                return false;
            }

            return true;
        }

        function checkUserId() {
            let userid = document.getElementById("userid").value.trim();
            if (userid === "") {
                alert("아이디를 입력해주세요.");
                return;
            }

            fetch(`/board/checkUserId?userid=${userid}`)
            .then(response => response.json())
            .then(json => {
            	console.log("서버 응답:", json);  // ✅ 이거 추가해봐야 해
                if (json.available === true) {
                    alert("사용 가능한 아이디입니다.");
                } else {
                    alert("이미 사용 중인 아이디입니다.");
                }
            })
                .catch(error => console.error("Error:", error));
        }
    </script>

</head>
<body>

    <!-- ✅ Header Include -->
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>

    <div class="signup-container">
        <div class="signup-row">
            <!-- ✅ 좌측: 이미지 (모바일에서는 숨김) -->
            <div>
                <img src="https://images.unsplash.com/photo-1574169208507-84376144848b?ixlib=rb-4.0.3&w=700" 
                     alt="회원가입 이미지" class="signup-image">
            </div>

            <!-- ✅ 우측: 입력 폼 -->
            <div class="signup-form">
                <h2 class="mb-4">회원가입</h2>

                <!-- ✅ 오류 메시지 표시 -->
                <c:if test="${not empty error}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        ${error}
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <form action="/board/memreg" method="post" onsubmit="return validateForm()">
                    <!-- ✅ 아이디 입력 및 중복 체크 -->
                    <div class="mb-3 form-group">
                        <label class="form-label" for="userid">아이디</label>
                        <div class="input-group">
                            <input type="text" id="userid" name="userid" class="form-control" placeholder="아이디 입력 (4~16자 영문 소문자+숫자)" required>
                            <button type="button" class="btn btn-secondary" onclick="checkUserId()">중복 확인</button>
                        </div>
                    </div>

                    <!-- ✅ 이름 입력 -->
                    <div class="mb-3 form-group">
                        <label class="form-label" for="name">이름</label>
                        <input type="text" id="name" name="name" class="form-control" placeholder="이름 입력" required>
                    </div>

                    <!-- ✅ 이메일 입력 -->
                    <div class="mb-3 form-group">
                        <label class="form-label" for="email">이메일</label>
                        <input type="email" id="email" name="email" class="form-control" placeholder="이메일 입력" required>
                    </div>

                    <!-- ✅ 비밀번호 입력 -->
                    <div class="mb-3 form-group">
                        <label class="form-label" for="passwd">비밀번호</label>
                        <input type="password" id="passwd" name="passwd" class="form-control" placeholder="비밀번호 입력 (8~20자, 영문+숫자)" required>
                    </div>

                    <!-- ✅ 비밀번호 확인 -->
                    <div class="mb-3 form-group">
                        <label class="form-label" for="confirmPasswd">비밀번호 확인</label>
                        <input type="password" id="confirmPasswd" name="confirmPasswd" class="form-control" placeholder="비밀번호 재입력" required>
                    </div>

                    <!-- ✅ 회원가입 버튼 -->
                    <button type="submit" class="btn-submit">회원가입</button>
                </form>
            </div>
        </div>
    </div>

    <!-- ✅ Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
