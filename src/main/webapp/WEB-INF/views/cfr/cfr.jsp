<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div style="height:1000px;margin:15px;">
    <input type="hidden" id="id" value="${principal.users.id}" />
    <form id="uploadForm" enctype="multipart/form-data">
        <input type="file" id="imageInput" accept="image/jpeg, image/png" required/>
        <button type="button" id="btn-cfrRequest">제출</button>
    </form>
</div>

<script src="/js/cfr.js"></script>

<%@ include file="../layout/footer.jsp" %>