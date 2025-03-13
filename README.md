# Project 2024프로젝트 railway로 배포 프로젝트

20250311 

> sts4기준 프로젝트가 local로는 돌아감, railway에 배포하니 오류발생 (mariadb기준)

> mysql과 연동위해 어설프게 따라하다 log4j드라이버 사용 , local,railway 둘다 오류발생<br>
> ㄴ> main으로 빼고, log4j branch로 이후 업데이트<br>
><br>
> gpt한테 물어본 후 mysql로 싹 변경 , 이후 Failed to load driver class net.sf.log4jdbc.sql.jdbcapi.DriverSpy 오류발생, local에서도 db설정탓인지 404오류발생, railway은 여전히 log4jdbc오류<br>
cmd로 프로젝트.jar를 실행할때는 NoClassDefFoundError: ch/qos/logback/core/util/StatusPrinter2오류가 생긴다 (일단 배제)<br>

>20250313 railway의 mysql 환경변수값으로 수정을해서 연동하면서 작동 시키려 했으나 환경변수를 env값으로 넣어줘도 안들어갔음.<br>
>다시 로컬이 동작되는 main버전으로 돌아와서 일단 로컬로 동작시키면서 기능 추가할예정
