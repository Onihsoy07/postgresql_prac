<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div style="height:1000px;margin:15px;">
    <div class="container">
      <h2>Basic Table</h2>
      <table class="table">
        <thead>
          <tr>
            <th>이름</th>
            <th>률</th>
            <th>생성시간</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
            <c:forEach var="cfrData" items="${cfrDataSet}">
              <tr>
                <td>${cfrData.value}</td>
                <td>${cfrData.confidence}</td>
                <td>${cfrData.createDate}</td>
                <td><a type="button" class="badge" href="/board/${cfrData.id}">글쓰기</a></td>
              </tr>

            </c:forEach>
        </tbody>
      </table>
    </div>
</div>

<script src="/js/cfr.js"></script>
<script src="/js/board.js"></script>


<%@ include file="../layout/footer.jsp" %>