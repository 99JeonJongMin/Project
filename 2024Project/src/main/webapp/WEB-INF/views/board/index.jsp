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
    let menuData = [];

    // ✅ AJAX로 DB에서 메뉴 데이터 가져오기
    fetch('/api/menu/list')
        .then(response => response.json())
        .then(data => {
            menuData = data;
        })
        .catch(error => console.error("메뉴 데이터를 불러오는 중 오류 발생:", error));

    // ✅ 버튼 선택 로직 (나라별)
    document.querySelectorAll('.nation-button, .all-button-nation').forEach(button => {
        button.addEventListener('click', function() {
            const category = this.getAttribute('data-category');
            
            // ✅ '전체' 버튼이 눌리면 다른 버튼 해제
            if (category === "전체_나라") {
                document.querySelectorAll('.nation-button').forEach(btn => btn.classList.remove('selected'));
                this.classList.add('selected');
            } else {
                document.querySelector('.all-button-nation').classList.remove('selected'); // 전체 해제
                document.querySelectorAll('.nation-button').forEach(btn => btn.classList.remove('selected'));
                this.classList.add('selected');
            }
        });
    });

    // ✅ 버튼 선택 로직 (시간별)
    document.querySelectorAll('.time-button, .all-button-time').forEach(button => {
        button.addEventListener('click', function() {
            const category = this.getAttribute('data-category');
            
            // ✅ '전체' 버튼이 눌리면 다른 버튼 해제
            if (category === "전체_시간") {
                document.querySelectorAll('.time-button').forEach(btn => btn.classList.remove('selected'));
                this.classList.add('selected');
            } else {
                document.querySelector('.all-button-time').classList.remove('selected'); // 전체 해제
                document.querySelectorAll('.time-button').forEach(btn => btn.classList.remove('selected'));
                this.classList.add('selected');
            }
        });
    });

    // ✅ 메뉴 추천 버튼 클릭 이벤트
    document.getElementById('recommendButton').addEventListener('click', function() {
        if (menuData.length === 0) {
            alert("메뉴 데이터를 불러오는 중입니다. 잠시 후 다시 시도해주세요.");
            return;
        }

        const selectedNation = document.querySelector('.nation-button.selected') 
                               ? document.querySelector('.nation-button.selected').getAttribute('data-category') 
                               : "전체_나라";

        const selectedTime = document.querySelector('.time-button.selected') 
                             ? document.querySelector('.time-button.selected').getAttribute('data-category') 
                             : "전체_시간";

        let filteredMenus = menuData;

        // ✅ 나라별 필터링
        if (selectedNation !== "전체_나라") {
            filteredMenus = filteredMenus.filter(menu => menu.category === selectedNation);
        }

        // ✅ 시간별 필터링
        if (selectedTime !== "전체_시간") {
            filteredMenus = filteredMenus.filter(menu => menu.time === selectedTime);
        }

        if (filteredMenus.length > 0) {
            const randomMenu = filteredMenus[Math.floor(Math.random() * filteredMenus.length)];
            document.getElementById('recommendedMenu').innerText = randomMenu.name;
            document.getElementById('menuContainer').style.display = 'block';

            const iframe = document.createElement('iframe');
            iframe.src = randomMenu.videoUrl;
            iframe.allowFullscreen = true;
            iframe.classList.add('responsive-video');

            document.getElementById('videoContent').innerHTML = '';
            document.getElementById('videoContent').appendChild(iframe);
            document.getElementById('videoContainer').style.display = 'block';
        } else {
            alert('선택한 카테고리에 맞는 메뉴가 없습니다.');
        }
    });
});

</script>



    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>
