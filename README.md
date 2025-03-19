# Project 2024프로젝트 railway로 배포 프로젝트

20250311 

> sts4기준 프로젝트가 local로는 돌아감, railway에 배포하니 오류발생 (mariadb기준)
> mysql과 연동위해 어설프게 따라하다 log4j드라이버 사용 , local,railway 둘다 오류발생<br>
> ㄴ> main으로 빼고, log4j branch로 이후 업데이트<br>
><br>
> gpt한테 물어본 후 mysql로 싹 변경 , 이후 Failed to load driver class net.sf.log4jdbc.sql.jdbcapi.DriverSpy 오류발생, local에서도 db설정탓인지 404오류발생, railway은 여전히 log4jdbc오류<br>
cmd로 프로젝트.jar를 실행할때는 NoClassDefFoundError: ch/qos/logback/core/util/StatusPrinter2오류가 생긴다 (일단 배제)<br>

20250313 <br>
>railway의 mysql 환경변수값으로 수정을해서 연동하면서 작동 시키려 했으나 환경변수를 env값으로 넣어줘도 안들어갔음.<br>
>다시 로컬이 동작되는 main버전으로 돌아와서 일단 로컬로 동작시키면서 기능 추가할예정<br>

20250314
>chatGPT결제를 하고 한번 더 도전 해보기로 결심, 환경변수값으로 수정해서 사용할 때 null값으로 들어갔던 문제 > 코드 문제가 아니고 railway에서 환경변수값을 promote하고 사용해야 했음 > LOG에 띄우게 해서 변수값은 들어가는거 확인. <br>
>그러나 Bean중복 문제가 생겼음 > mapper에서 발생한 문제였는데,Mybatis에서도 MapperScan 어노테이션을 사용하고 Mapper에도 @Mapper 어노테이션을 사용해서 중복으로 들어갔던 문제였다. board랑 member mapper를 사용중이였고 xml도 사용중이였는데, xml은 삭제시키고
member와 board mapper의 @mapper 어노테이션을 제거했을때는 똑같이 오류가 생기다 Mybatis.config에서 @MapperScan을 제거한 후에는 오류가 생기지 않았다. 이후에는 mapper 중복 문제는 사라졌지만 mapping에 문제가 생겼다. 기존 프로젝트대로 사용하니 index에 접근을 못해서 Controller의 mapping을 바꿔주고  index에 대한 mapping도 return값을 주었더니 index를 railway에 배포 성공했다 ! <br>
>게시판 글목록, 게시판 작성ui,로그인과 회원가입ui까지는 나오지만 데이터 전달이 안되는듯한 모습이 보였다.
><br>
><h1>수정해야 할 것</h1><br>
><ol><li>Mysql과 제대로 연동해서 기존 프로젝트처럼 정상적으로 작동 시키기. <br> ㄴ> 20250318 기존 mysql삭제 후 새로 작성하여 기존프로젝트처럼 작동 o </li>
>    <li>기존 jsp에 쌩으로 추천음식 데이터를 넣는게 아닌 db로 변경해서 관리보수 용이하게 할 것  <br> ㄴ> 20250318 menu table 생성 후 컨트롤러 맵퍼 서비스 추가시켜 기존처럼 동작 시키면서도 DB로 이전 성공 </li>
>    <li>유튜브 링크 간략화 시키기</li>
>    <li>로딩 속도 증가시킬 방법 찾아내기</li></ol>

20250319 <br>
>Spring MVC Local 방식에서 Spring-boot railway연동 웹배포 방식으로 바꾸는 과정에서 설계없이, 명세서 없이 작업할려니까 죽을 맛이다. 명세서와 설계,디자인,기획이 얼마나 중요한지 절실히 깨닫는 중. <br>
>menu talbe구조를 한차례 바꾸고 메뉴들을 추가했다. AI한테 자동으로 시키고 싶지만 적당한 영상에 적당한 시작타이밍을 맞춰주는게 쉬운 일은 아니여서 수작업으로 하느라 힘이 든다. 지금은 최대한 구조에 대한 생각중 <br>
>아마 이번주까지는 메뉴추가에 힘을 기울이고 다음 주 부터는 실기 공부를 해야 할 것 같다.
