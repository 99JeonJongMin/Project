<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<%@ include file="../includes/header.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">메뉴추천게시판</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    추천해주고 싶은 메뉴가 있다면 작성해 주세요!
                </div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>날짜</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="board" items="${list2}">
                                <tr class="odd gradeX">
                                    <td><c:out value="${board.bno}"/></td>
                                    <td><a href="/board/get?bno=<c:out value='${board.bno}'/>"><c:out value="${board.title}"/></a></td>
                                    <td><c:out value="${board.writer}"/></td>
                                    <td><c:out value="${board.regdate}"/></td>
                                </tr> 
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <a class="btn btn-outline-primary" href="/board/modifyBoard" role="button">글쓰기</a>
</div>


<%@ include file="../includes/footer.jsp" %>
