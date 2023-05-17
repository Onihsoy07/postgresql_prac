<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="layout/header.jsp" %>

<div class="container-fluid" style="height:1000px;margin:15px;">
    <form id="uploadForm" enctype="multipart/form-data">
        <input type="file" id="imageInput" />
        <button type="button" id="btn-cfrRequest">제출</button>
    </form>

    <div class="container mt-3">
      <ul class="list-group">
        <li class="list-group-item d-flex justify-content-between align-items-center">
          Inbox
          <span class="badge badge-primary badge-pill">12</span>
        </li>
        <li class="list-group-item d-flex justify-content-between align-items-center">
          Ads
          <span class="badge badge-primary badge-pill">50</span>
        </li>
        <li class="list-group-item d-flex justify-content-between align-items-center">
          Junk
          <span class="badge badge-primary badge-pill">99</span>
        </li>
      </ul>
    </div>

    <ul class="pagination justify-content-center" style="margin:20px 0">
      <li class="page-item"><a class="page-link" href="#">Previous</a></li>
      <li class="page-item"><a class="page-link" href="#">1</a></li>
      <li class="page-item"><a class="page-link" href="#">2</a></li>
      <li class="page-item"><a class="page-link" href="#">3</a></li>
      <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>

</div>


<script src="/js/cfr.js"></script>

<%@ include file="layout/footer.jsp" %>