<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/header.jsp" %>

<div style="height:700px;margin:15px;">
    <div class="container">

        <input type="hidden" id="id" value="${principal.users.id}">

        <div class="form-group">
          <label for="title">Title</label>
          <input type="text" class="form-control" placeholder="Enter Title" id="title" name="title">
        </div>

        <div class="form-group">
          <label for="content">Content</label>
          <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>

      <button id="btn-save" class="btn btn-primary">글쓰기</button>

    </div>
</div>

<script>
  $('.summernote').summernote({
    placeholder: 'content',
    tabsize: 2,
    height: 300
  });
</script>

<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp" %>