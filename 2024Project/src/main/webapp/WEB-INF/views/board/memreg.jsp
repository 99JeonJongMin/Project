<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>회원가입</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/resources/css/sign.css">

    <script>
        function validateUserId() {
            const userid = document.getElementById("userid").value.trim();
            const msg = document.getElementById("useridHelp");
            const idPattern = /^[a-z0-9]{4,16}$/;

            if (userid === "") {
                msg.textContent = "아이디를 입력해주세요.";
            } else if (!idPattern.test(userid)) {
                msg.textContent = "아이디는 4~16자의 영문 소문자 및 숫자로만 입력해야 합니다.";
            } else {
                msg.textContent = "";
            }
        }
	
        function validatePassword() {
            const password = document.getElementById("passwd").value.trim();
            const msg = document.getElementById("passwdHelp");
            const pwPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d!@#$%^&*]{8,20}$/;

            if (password === "") {
                msg.textContent = "비밀번호를 입력해주세요.";
            } else if (!pwPattern.test(password)) {
                msg.textContent = "8~20자의 영문과 숫자를 포함해야 합니다.";
            } else {
                msg.textContent = "";
            }
        }

        function validatePasswordConfirm() {
            const password = document.getElementById("passwd").value.trim();
            const confirm = document.getElementById("confirmPasswd").value.trim();
            const msg = document.getElementById("confirmPasswdHelp");

            if (confirm === "") {
                msg.textContent = "비밀번호를 다시 입력해주세요.";
            } else if (password !== confirm) {
                msg.textContent = "비밀번호가 일치하지 않습니다.";
            } else {
                msg.textContent = "";
            }
        }

        function validateName() {
            const name = document.getElementById("name").value.trim();
            const msg = document.getElementById("nameHelp");
            const namePattern = /^[가-힣a-zA-Z]{2,20}$/;

            if (name === "") {
                msg.textContent = "이름을 입력해주세요.";
            } else if (!namePattern.test(name)) {
                msg.textContent = "이름은 2~20자의 한글 또는 영문만 입력 가능합니다.";
            } else {
                msg.textContent = "";
            }
        }

        function validateEmail() {
            const email = document.getElementById("email").value.trim();
            const msg = document.getElementById("emailHelp");
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (email === "") {
                msg.textContent = "이메일을 입력해주세요.";
            } else if (!emailPattern.test(email)) {
                msg.textContent = "올바른 이메일 형식이 아닙니다.";
            } else {
                msg.textContent = "";
            }
        }

        function validateForm() {
            // 기존 정규식 검사
            const userid = document.getElementById("userid").value.trim();
            const password = document.getElementById("passwd").value.trim();
            const confirmPassword = document.getElementById("confirmPasswd").value.trim();
            const name = document.getElementById("name").value.trim();
            const email = document.getElementById("email").value.trim();

            const idPattern = /^[a-z0-9]{4,16}$/;
            const pwPattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d!@#$%^&*]{8,20}$/;
            const namePattern = /^[가-힣a-zA-Z]{2,20}$/;
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (!idPattern.test(userid)) {
                alert("아이디 형식이 올바르지 않습니다.");
                return false;
            }
            if (!pwPattern.test(password)) {
                alert("비밀번호 형식이 올바르지 않습니다.");
                return false;
            }
            if (password !== confirmPassword) {
                alert("비밀번호가 일치하지 않습니다.");
                return false;
            }
            if (!namePattern.test(name)) {
                alert("이름 형식이 올바르지 않습니다.");
                return false;
            }
            if (!emailPattern.test(email)) {
                alert("이메일 형식이 올바르지 않습니다.");
                return false;
            }

            return true;
        }
        function resetUserIdCheckResult() {
            const resultMsg = document.getElementById("useridCheckResult");
            if (resultMsg.textContent !== "") {
                resultMsg.textContent = "";
                resultMsg.className = "form-text";  // 색상도 초기화
            }
        }
        function checkUserId() {
            const userid = document.getElementById("userid").value.trim();
            const resultMsg = document.getElementById("useridCheckResult");

            if (userid === "") {
                resultMsg.textContent = "아이디를 입력해주세요.";
                resultMsg.className = "form-text text-danger";
                return;
            }
            const url = `/board/checkUserId?userid=${userid}`;
            console.log("📌 요청 URL:", url);
            fetch(url)
                .then(response => response.json())
                .then(json => {
                    console.log("서버 응답 전체:", json);
                    console.log("json.available =", json.available);
                    console.log("typeof json.available =", typeof json.available);

                    if (json.available === true || json.available === "true") {
                        resultMsg.textContent = "사용 가능한 아이디입니다.";
                        resultMsg.className = "form-text text-success";
                    } else if (json.available === false || json.available === "false") {
                        resultMsg.textContent = "이미 사용 중인 아이디입니다.";
                        resultMsg.className = "form-text text-danger";
                    } else {
                        resultMsg.textContent = "서버 응답값이 올바르지 않습니다.";
                        resultMsg.className = "form-text text-warning";
                    }
                })
                .catch(error => {
                    console.error("중복 확인 오류:", error);
                    resultMsg.textContent = "중복 확인 중 오류가 발생했습니다.";
                    resultMsg.className = "form-text text-danger";
                });
        }
    </script>
</head>
<body>

<%@ include file="/WEB-INF/views/includes/header.jsp" %>

<div class="signup-container">
    <div class="signup-row">
        <div>
            <img src="https://images.unsplash.com/photo-1574169208507-84376144848b?ixlib=rb-4.0.3&w=700" 
                 alt="회원가입 이미지" class="signup-image">
        </div>

        <div class="signup-form">
            <h2 class="mb-4">회원가입</h2>

            <c:if test="${not empty error}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${error}
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                </div>
            </c:if>

            <form action="/board/memreg" method="post" onsubmit="return validateForm()">
				<div class="mb-3 form-group">
				    <label class="form-label" for="userid">아이디</label>
				    <div class="input-group">
				        <input type="text" id="userid" name="userid" class="form-control"
					       placeholder="아이디 입력 (4~16자 영문 소문자+숫자)"
					       required
					       oninput="validateUserId(); resetUserIdCheckResult();">
				        <button type="button" class="btn btn-secondary" onclick="checkUserId()">중복 확인</button>
				    </div>
				    <div id="useridHelp" class="form-text text-danger"></div> <!-- 정규식 안내 -->
				    <div id="useridCheckResult" class="form-text"></div> <!-- 중복 여부 결과 -->
				</div>


                <div class="mb-3 form-group">
                    <label class="form-label" for="name">이름</label>
                    <input type="text" id="name" name="name" class="form-control" placeholder="이름 입력" required oninput="validateName()">
                    <div id="nameHelp" class="form-text text-danger"></div>
                </div>

                <div class="mb-3 form-group">
                    <label class="form-label" for="email">이메일</label>
                    <input type="email" id="email" name="email" class="form-control" placeholder="이메일 입력" required oninput="validateEmail()">
                    <div id="emailHelp" class="form-text text-danger"></div>
                </div>

                <div class="mb-3 form-group">
                    <label class="form-label" for="passwd">비밀번호</label>
                    <input type="password" id="passwd" name="passwd" class="form-control" placeholder="비밀번호 입력 (8~20자, 영문+숫자)" required oninput="validatePassword()">
                    <div id="passwdHelp" class="form-text text-danger"></div>
                </div>

                <div class="mb-3 form-group">
                    <label class="form-label" for="confirmPasswd">비밀번호 확인</label>
                    <input type="password" id="confirmPasswd" name="confirmPasswd" class="form-control" placeholder="비밀번호 재입력" required oninput="validatePasswordConfirm()">
                    <div id="confirmPasswdHelp" class="form-text text-danger"></div>
                </div>

                <button type="submit" class="btn-submit">회원가입</button>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
