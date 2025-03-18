<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    
    <style>
        /* ✅ 전체 페이지 스타일 */
        body {
            font-family: 'Noto Sans', sans-serif;
            background-color: #f8f9fa;
        }

        .signup-container {
            max-width: 900px;
            margin: 50px auto;
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
        }

        /* ✅ 반응형 그리드 레이아웃 */
        .signup-row {
            display: flex;
            flex-wrap: wrap;
            align-items: center;
            justify-content: space-between;
        }

        .signup-image {
            width: 100%;
            max-width: 400px;
            border-radius: 10px;
            display: block;
        }

        .signup-form {
            flex: 1;
            padding: 20px;
        }

        /* ✅ 입력 필드 스타일 */
        .form-control {
            border-radius: 5px;
        }

        .btn-submit {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: white;
            font-weight: bold;
            border-radius: 5px;
            border: none;
            transition: background 0.3s ease;
        }

        .btn-submit:hover {
            background-color: #0056b3;
        }

        /* ✅ 모바일에서 세로 정렬 */
        @media (max-width: 768px) {
            .signup-row {
                flex-direction: column;
                text-align: center;
            }

            .signup-image {
                max-width: 100%;
                margin-bottom: 20px;
            }

            .signup-form {
                width: 100%;
            }
        }
    </style>
</head>
<body>

    <!-- ✅ Header Include -->
    <%@ include file="/WEB-INF/views/includes/header.jsp" %>

    <div class="signup-container">
        <div class="signup-row">
            <!-- ✅ 좌측: 이미지 -->
            <div>
                <img src="https://images.unsplash.com/photo-1574169208507-84376144848b?ixid=M3w5MTMyMXwwfDF8c2VhcmNofDN8fGFic3RyYWN0fGVufDB8fHx8MTcxMDg3MDkzMHww&ixlib=rb-4.0.3&w=700" 
                     alt="회원가입 이미지" class="signup-image">
            </div>

            <!-- ✅ 우측: 입력 폼 -->
            <div class="signup-form">
                <h2 class="text-center mb-4">회원가입</h2>

                <form action="/memreg" method="post">
                    <div class="mb-3">
                        <label class="form-label" for="userid">아이디</label>
                        <input type="text" id="userid" name="userid" class="form-control" placeholder="아이디 입력" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label" for="name">이름</label>
                        <input type="text" id="name" name="name" class="form-control" placeholder="이름 입력" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label" for="email">이메일</label>
                        <input type="email" id="email" name="email" class="form-control" placeholder="이메일 입력" required>
                    </div>

                    <div class="mb-3">
                        <label class="form-label" for="passwd">비밀번호</label>
                        <input type="password" id="passwd" name="passwd" class="form-control" placeholder="비밀번호 입력" required>
                    </div>

                    <button type="submit" class="btn-submit">회원가입</button>
                </form>
            </div>
        </div>
    </div>

    <!-- ✅ Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>