<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" 
          crossorigin="anonymous">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메뉴 추천 페이지</title>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light px-3">
        <div class="container">
            <a class="navbar-brand" href="/board/index">MOMOK.GG</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item"><a class="nav-link" href="/board/menulist">메뉴추천게시판</a></li>
                    <li class="nav-item"><a class="nav-link" href="/board/boardlist">자유게시판</a></li>
                    <li class="nav-item"><a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">관리자</a></li>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <% 
                        // 세션에서 로그인 정보 확인
                        String user = (String) session.getAttribute("userid");
                        if (user != null) { 
                    %>
                        <li class="nav-item"><a class="nav-link active" href="/logout">로그아웃</a></li>
                    <% 
                        } else { 
                    %>
                        <li class="nav-item"><a class="nav-link active" href="/board/login">로그인</a></li>
                    <% 
                        } 
                    %>
                </ul>
            </div>
        </div>
    </nav>

    <!-- 로그아웃 처리를 위한 logout.jsp -->
    <%
        if (request.getRequestURI().contains("logout")) {
            session.invalidate();
            response.sendRedirect("index");
        }
    %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYVxA1fOMpY1zFpGxjLYzE2wrKQ1hD3zpGnJlJx04pT3MCqJ6v5PH0t6" 
            crossorigin="anonymous"></script>
</body>
</html>
