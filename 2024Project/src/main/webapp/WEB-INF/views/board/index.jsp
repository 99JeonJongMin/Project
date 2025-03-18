<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@include file="/WEB-INF/views/includes/header.jsp"%>

<!-- CSS 파일 불러오기 -->
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/index.css' />">
	 	 	 
<main style="margin-top: 100px;">
    <div class="category-container">
        <div class="category-section">
            <!-- 나라별 카테고리 -->
            <div class="category-group">
                <button class="category-button all-button-nation" data-category="전체_나라">전체</button>
                <button class="category-button nation-button" data-category="한식">한식</button>
                <button class="category-button nation-button" data-category="일식">일식</button>
                <button class="category-button nation-button" data-category="중식">중식</button>
                <button class="category-button nation-button" data-category="양식">양식</button>
            </div>

            <!-- 시간별 카테고리 -->
            <div class="category-group">
                <button class="category-button all-button-time" data-category="전체_시간">전체</button>
                <button class="category-button time-button" data-category="아침">아침</button>
                <button class="category-button time-button" data-category="점심">점심</button>
                <button class="category-button time-button" data-category="저녁">저녁</button>
                <button class="category-button time-button" data-category="야식">야식</button>
            </div>
        </div>
    </div>

    <button id="recommendButton" style="margin-bottom: 70px;">오늘은이거다!</button>

    <div id="menuContainer" style="display: none;">
        <h2 id="recommendedMenu"></h2>
    </div>

    <!-- ✅ 반응형 YouTube 영상 -->
    <div id="videoContainer" style="display: none;">
        <div class="video-wrapper">
            <div id="videoContent"></div>
        </div>
    </div>
</main>

<script>
document.addEventListener("DOMContentLoaded", function() {
    const menuData = [
        { name: '짜장면', categories: ['중식', '점심', '저녁', '야식'], video: 'https://www.youtube.com/embed/v8Y_oHBFotE?start=179' },
        { name: '탕수육', categories: ['중식', '점심', '저녁', '야식'], video: 'https://www.youtube.com/embed/xx6HdrulgPM?start=44' },
        { name: '김치찌개', categories: ['한식', '점심', '저녁'], video: 'https://www.youtube.com/embed/fOuPsNp94hA?start=93' },
        { name: '비빔밥', categories: ['한식', '점심', '저녁'], video: 'https://www.youtube.com/embed/Nx5vi905knk?start=123' },
        { name: '라면', categories: ['한식', '점심', '저녁', '야식'], video: 'https://www.youtube.com/embed/x94_2x_-p0Q?start=45' },
        { name: '라멘', categories: ['일식', '점심', '저녁'], video: 'https://www.youtube.com/embed/DbkXaZTQK_k?start=168' },
        { name: '토스트', categories: ['양식', '아침'], video: 'https://www.youtube.com/embed/TCDPXisHsfA?start=204' },
        { name: '팬케이크', categories: ['양식', '아침'], video: 'https://www.youtube.com/embed/hNfcXgYNS84?start=182' },
        { name: '스테이크', categories: ['양식', '점심', '저녁'], video: 'https://www.youtube.com/embed/XX7h3z0me1s?start=265' },
    ];

    document.getElementById('recommendButton').addEventListener('click', function() {
        let filteredMenus = menuData;
        const randomMenu = filteredMenus[Math.floor(Math.random() * filteredMenus.length)];

        document.getElementById('recommendedMenu').innerText = randomMenu.name;
        document.getElementById('menuContainer').style.display = 'block';

        const iframe = document.createElement('iframe');
        iframe.src = randomMenu.video;
        iframe.allowFullscreen = true;
        iframe.classList.add('responsive-video');

        document.getElementById('videoContent').innerHTML = '';
        document.getElementById('videoContent').appendChild(iframe);
        document.getElementById('videoContainer').style.display = 'block';
    });
});
</script>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
