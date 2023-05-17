<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div style="height:1000px;margin:15px;">
    <div class="container">
      <form>
        <div class="form-group">
          <label for="username">Username</label>
          <input type="username" class="form-control" placeholder="Enter username" id="username" name="username" value="${principal.users.username}" readonly>
        </div>

        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" class="form-control" placeholder="Email null" id="email" name="email" value="${principal.users.email}" readonly>
        </div>
      </form>
      <a href="/user/modify" class="btn btn-primary">회원 수정</a>
      <button id="btn-delete" class="btn btn-primary">회원 탈퇴</button>
    </div>
</div>


<%@ include file="../layout/footer.jsp" %>