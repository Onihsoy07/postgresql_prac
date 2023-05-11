<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <title>My Page</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <style>
  .fakeimg {
    height: 200px;
    background: #aaa;
  }
  </style>
</head>
<body>

<div class="jumbotron text-center" style="margin-bottom:0">
  <h1>My Page</h1>
</div>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
  <a class="navbar-brand" href="/">HOME</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" href="/auth/login">로그인</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/auth/join">회원가입</a>
      </li>
    </ul>
  </div>
</nav>

<div style="height:1000px;margin:15px;">
    <div class="container">
      <form>
        <div class="form-group">
          <label for="username">Username</label>
          <input type="username" class="form-control" placeholder="Enter username" id="username" name="username">
        </div>

        <div class="form-group">
          <label for="password">Password</label>
          <input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
        </div>

        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" class="form-control" placeholder="Enter email" id="email" name="email">
        </div>
      </form>
      <button id="btn-join" class="btn btn-primary">회원가입</button>
    </div>
</div>



<div class="jumbotron text-center" style="margin-bottom:0">
  <p>Footer</p>
</div>

<script src="/js/user.js"></script>

</body>
</html>