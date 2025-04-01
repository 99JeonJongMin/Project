<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@include file="/WEB-INF/views/includes/header.jsp"%>

<!-- CSS 연결 -->
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
                <button class="category-button nation-button" data-category="기타">기타</button>
            </div>

            <!-- 시간별 카테고리 -->
            <div class="category-group">
                <button class="category-button all-button-time" data-category="전체_시간">전체</button>
                <button class="category-button time-button" data-category="아침">아침</button>
                <button class="category-button time-button" data-category="점심">점심</button>
                <button class="category-button time-button" data-category="저녁">저녁</button>
                <button class="category-button time-button" data-category="야식">야식</button>
                <button class="category-button time-button" data-category="간식">간식</button>
            </div>
        </div>
    </div>

    <button id="recommendButton">오늘은이거다!</button>

    <div id="menuContainer" style="display: none;">
        <h2 id="recommendedMenu"></h2>
    </div>

    <!-- 영상 및 채널명 출력 컨테이너 -->
    <div id="videoContainer" style="display: none;">
        <div class="video-wrapper" id="videoContent">
            <!-- iframe + 채널명 JS에서 append -->
        </div>
    </div>
</main>

<!-- ✅ 스크립트 -->
<script>
document.addEventListener("DOMContentLoaded", function () {
    let menuData = [];

    fetch('/api/menu/list')
        .then(response => response.json())
        .then(data => {
            menuData = data;
            console.log(menuData);
        })
        .catch(error => console.error("메뉴 데이터를 불러오는 중 오류 발생:", error));

    document.querySelectorAll('.nation-button, .all-button-nation').forEach(button => {
        button.addEventListener('click', function () {
            document.querySelectorAll('.nation-button').forEach(btn => btn.classList.remove('selected'));
            document.querySelector('.all-button-nation').classList.remove('selected');
            this.classList.add('selected');
        });
    });

    document.querySelectorAll('.time-button, .all-button-time').forEach(button => {
        button.addEventListener('click', function () {
            document.querySelectorAll('.time-button').forEach(btn => btn.classList.remove('selected'));
            document.querySelector('.all-button-time').classList.remove('selected');
            this.classList.add('selected');
        });
    });

    document.getElementById('recommendButton').addEventListener('click', function () {
        if (menuData.length === 0) {
            alert("메뉴 데이터를 불러오는 중입니다. 잠시 후 다시 시도해주세요.");
            return;
        }

        const selectedNation = document.querySelector('.nation-button.selected')?.getAttribute('data-category') || "전체_나라";
        const selectedTime = document.querySelector('.time-button.selected')?.getAttribute('data-category') || "전체_시간";

        let filteredMenus = menuData;
        if (selectedNation !== "전체_나라") {
            filteredMenus = filteredMenus.filter(menu => menu.category === selectedNation);
        }
        if (selectedTime !== "전체_시간") {
            filteredMenus = filteredMenus.filter(menu => menu.times.includes(selectedTime));
        }

        if (filteredMenus.length > 0) {
            const randomMenu = filteredMenus[Math.floor(Math.random() * filteredMenus.length)];

            // 메뉴명 출력
            const menuTitle = document.getElementById('recommendedMenu');
            menuTitle.innerText = randomMenu.name;
            document.getElementById('menuContainer').style.display = 'block';

            // 영상 iframe 생성
            const iframe = document.createElement('iframe');
            iframe.src = decodeURIComponent(randomMenu.videoUrl.replace(/&amp;/g, "&"));
            iframe.allowFullscreen = true;
            iframe.classList.add('responsive-video');

            // 채널명 출력
            const channelInfo = document.createElement('p');
            channelInfo.textContent = `📺 채널명: ${randomMenu.channelName.trim()}`;
            channelInfo.classList.add('channel-info');

            // 콘텐츠 삽입
            const videoContent = document.getElementById('videoContent');
            videoContent.innerHTML = '';
            videoContent.appendChild(iframe);
            videoContent.appendChild(channelInfo);

            document.getElementById('videoContainer').style.display = 'block';

            // 로그
            console.log(`[${randomMenu.channelName}]`);
        } else {
            alert("선택한 조건에 맞는 메뉴가 없습니다.");
        }
    });
});
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
