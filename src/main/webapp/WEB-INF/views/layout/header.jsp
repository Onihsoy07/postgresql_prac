<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAuthenticated()">
  <sec:authentication property="principal" var="principal"/>
</sec:authorize>

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

  <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

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
        <c:choose>
            <c:when test="${empty principal}">
                <li class="nav-item">
                    <a class="nav-link" href="/auth/login">로그인</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/auth/join">회원가입</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item dropdown">
                    <li class="nav-item">
                        <a class="nav-link" href="/cfr">연예인 얼굴</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/board">글쓰기</a>
                    </li>
                  <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    ${principal.users.username}
                  </a>
                  <div class="dropdown-menu">
                    <a class="dropdown-item" href="/user/detail">회원 정보</a>
                    <a class="dropdown-item" href="/cfr/${principal.users.id}">cfr 리스트</a>
                    <a class="dropdown-item" href="/logout">로그아웃</a>
                  </div>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
  </div>
</nav>