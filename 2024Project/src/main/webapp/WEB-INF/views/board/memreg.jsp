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
</head>
<body>
<%@ include file="/WEB-INF/views/includes/header.jsp" %>
<div class="signup-container">
    <div class="signup-row">
        <div>
            <img src="https://images.unsplash.com/photo-1574169208507-84376144848b?ixlib=rb-4.0.3&w=700" alt="회원가입 이미지" class="signup-image">
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
                        <input type="text" id="userid" name="userid" class="form-control" placeholder="아이디 입력 (4~16자 영문+숫자)" required oninput="validateUserId(); resetUserIdCheckResult();">
                        <button type="button" class="btn btn-secondary" onclick="checkUserId()">중복 확인</button>
                    </div>
                    <div id="useridHelp" class="form-text text-danger"></div>
                    <div id="useridCheckResult" class="form-text"></div>
                </div>
                <div class="mb-3 form-group">
                    <label class="form-label" for="name">이름</label>
                    <input type="text" id="name" name="name" class="form-control" placeholder="이름 입력" required oninput="validateName()">
                    <div id="nameHelp" class="form-text text-danger"></div>
                </div>
                <div class="mb-3 form-group">
                    <label class="form-label" for="email">이메일</label>
                    <input type="email" id="email" name="email" class="form-control" placeholder="이메일 입력" required>
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

<script>
document.addEventListener("DOMContentLoaded", () => {
    let isUserIdChecked = false;

    window.checkUserId = function () {
        const useridInput = document.getElementById("userid");
        const resultMsg = document.getElementById("useridCheckResult");

        console.log("🚀 checkUserId 실행됨");
        console.log("📦 useridInput DOM 객체:", useridInput);

        if (!useridInput) {
            alert("❌ userid input 요소를 찾을 수 없습니다.");
            return;
        }

        const userid = useridInput.value.trim();
        console.log("📨 가져온 userid 값:", `'${userid}'`);
        console.log("🛠 요청 URL:", `/board/checkUserId?userid=${userid}`);

        if (userid === "") {
            resultMsg.textContent = "아이디를 입력해주세요.";
            resultMsg.className = "form-text text-danger";
            return;
        }

        fetch(`/board/checkUserId?userid=${userid}`)
            .then(response => response.json())
            .then(json => {
                console.log("✅ 서버 응답:", json);
                if (json.available === true) {
                    resultMsg.textContent = "사용 가능한 아이디입니다.";
                    resultMsg.className = "form-text text-success";
                    isUserIdChecked = true;
                } else {
                    resultMsg.textContent = "이미 사용 중인 아이디입니다.";
                    resultMsg.className = "form-text text-danger";
                    isUserIdChecked = false;
                }
            })
            .catch(error => {
                console.error("❌ 중복 확인 오류:", error);
                resultMsg.textContent = "중복 확인 중 오류가 발생했습니다.";
                resultMsg.className = "form-text text-danger";
                isUserIdChecked = false;
            });
    };
});
</script>


<!-- Bootstrap JS (no integrity) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
