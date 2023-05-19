<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="layout/header.jsp" %>

<div class="main_wrap">
    <aside>
        <div class="container-fluid">
          <ul class="list-group">
            <c:forEach var="cfr" items="${topRateCfr}">
                <li class="list-group-item d-flex justify-content-between align-items-center">
                  <a href="#">${cfr.value}</a>
                  <p>${cfr.confidence}</p>
                  <span class="badge badge-primary badge-pill">${cfr.users.username}</span>
                </li>
            </c:forEach>
          </ul>
        </div>
    </aside>

    <c:choose>
            <c:when test="${not empty boardView}">
                <div class="container-fluid" style="margin: 15px 40px;">
                  <button class="btn btn-secondary" onclick="location.href='/'">목록</button>
                  <c:if test="${boardView.users.id == principal.users.id}">
                    <a href="/board/${boardView.id}/updateForm" class="btn btn-warning">수정</a>
                    <button id="btn-delete" class="btn btn-danger">삭제</button>
                  </c:if>
                  <br/><br/>
                  <div>
                    작성자:<span><i>${boardView.users.username}</i>&nbsp;&nbsp;&nbsp;</span>
                    글 번호:<span id="id"><i>${boardView.id}</i></span>
                  </div>
                  <br/>
                  <div>
                    <h3>${boardView.title}</h3>
                  </div>
                  <hr>
                  <div>
                    <div>${boardView.content}</div>
                  </div>
                    <div class="card-body">
                        <div class="row">
                          <div class="form-group col-sm-8">
                            <input class="form-control input-sm" id="comment" type="text" placeholder="댓글 입력...">
                          </div>
                          <div class="form-group col-sm-2">
                            <button type="button" class="btn btn-primary" id="btn-replySave">
                                저장
                            </button>
                          </div>
                        </div>
                    </div>
                </div>
            </c:when>
    </c:choose>

    <section class="content">
        <div class="container-fluid" style="height:800px;margin:15px;">

            <div class="container-fluid" style="margin:30px 0px;">
              <ul class="list-group">
                <c:forEach var="board" items="${boards.content}">
                    <li class="list-group-item d-flex justify-content-between align-items-center">
                      <a href="/${board.id}?page=${boards.number}">${board.title}</a>
                      <span class="badge badge-primary badge-pill">${board.users.username}</span>
                    </li>
                </c:forEach>
              </ul>
            </div>

            <ul class="pagination justify-content-center">
              <c:choose>
                <c:when test="${boards.first}">
                    <li class="page-item disabled"><a class="page-link" href="/?page=${boards.number-1}">Previous</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/?page=${boards.number-1}">Previous</a></li>
                </c:otherwise>
              </c:choose>
              <c:forEach var="cnt" begin="1" end="${boards.totalPages}">
                <c:choose>
                  <c:when test="${boards.number == cnt-1}">
                    <li class="page-item active"><a class="page-link" href="/?page=${cnt-1}">${cnt}</a></li>
                  </c:when>
                  <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/?page=${cnt-1}">${cnt}</a></li>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
              <c:choose>
                <c:when test="${boards.last}">
                    <li class="page-item disabled"><a class="page-link" href="/?page=${boards.number+1}">Previous</a></li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/?page=${boards.number+1}">Previous</a></li>
                </c:otherwise>
              </c:choose>
            </ul>

        </div>
    </section>
</div>

<script src="/js/reply.js"></script>

<%@ include file="layout/footer.jsp" %>