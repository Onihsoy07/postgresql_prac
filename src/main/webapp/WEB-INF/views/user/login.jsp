<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div style="height:1000px;margin:15px;">
    <div class="container">
      <form action="/auth/loginProc" method="POST">
        <div class="form-group">
          <label for="username">Username</label>
          <input type="username" class="form-control" placeholder="Enter username" id="username" name="username">
        </div>

        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
        </div>
        <button id="btn-login" class="btn btn-primary">로그인</button>
      </form>
    </div>
</div>

<%@ include file="../layout/footer.jsp" %>