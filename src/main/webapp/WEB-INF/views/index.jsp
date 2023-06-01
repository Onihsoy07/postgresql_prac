<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ include file="layout/header.jsp" %>

<div class="main_wrap" style="margin-bottom:20px;">

    <c:if test="${not empty boardView}">
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
            <c:forEach var="reply" items="${replyList}">
                <div style="display:flex;margin-top:10px;margin-left:${reply.id*10}px" name="comment_${reply.id}">
                    <span style="width:130px;">${reply.comment} &nbsp;</span>
                    <button type="button" onclick="newHello(${reply.id})" class="replyButton${reply.id}">버튼</button>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${not empty boards}">
        <section class="content">
            <div class="container-fluid">

                <div class="container-fluid" style="margin:30px 0px;color:gray;">
                  <div class="list-group cus-list">
                    <div class="list-group-item align-items-center list-head">
                        <div class="row-top">
                            <div class="col-title">제목</div>
                        </div>
                        <div class="d-flex justify-content-between row-bottom">
                            <div class="col-author">작성자</div>
                            <div class="col-time">작성일</div>
                            <div class="col-view">조회수</div>
                            <div class="col-good">추천</div>
                        </div>
                    </div>
                    <c:forEach var="board" items="${boards.content}">
                        <div class="cusHov list-body" style="padding:0px;">
                          <a class="list-bodyInner" href="/${board.id}?page=${boards.number+1}">
                            <div class="row-top">
                                <div class="col-title">${board.title}</div>
                            </div>
                            <div class="row-bottom">
                                <div class="col-author">${board.users.username}</div>
                                <div class="row-bottom-rightInner">
                                    <div class="col-time">${board.createDate.substring(11, 16)}</div>
                                    <div class="col-view">0</div>
                                    <div class="col-good">0</div>
                                </div>
                            </div>
                          </a>
                        </div>
                    </c:forEach>

                  </div>
                </div>

                <ul class="pagination justify-content-center">
                  <c:choose>
                    <c:when test="${boards.first}">
                        <li class="page-item disabled"><a class="page-link" href="${state}page=${boards.number}">Previous</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="${state}page=${boards.number}">Previous</a></li>
                    </c:otherwise>
                  </c:choose>
                  <c:forEach var="cnt" begin="1" end="${boards.totalPages}">
                    <c:choose>
                      <c:when test="${boards.number == cnt-1}">
                        <li class="page-item active"><a class="page-link" href="${state}page=${cnt}">${cnt}</a></li>
                      </c:when>
                      <c:otherwise>
                        <li class="page-item"><a class="page-link" href="${state}page=${cnt}">${cnt}</a></li>
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>
                  <c:choose>
                    <c:when test="${boards.last}">
                        <li class="page-item disabled"><a class="page-link" href="${state}page=${boards.number+2}">Previous</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href="${state}page=${boards.number+2}">Previous</a></li>
                    </c:otherwise>
                  </c:choose>
                </ul>

            </div>

            <div>
                <div class="searchBox">
                    <select id="searchKeyword">
                        <option value="title">제목</option>
                        <option value="writer">작성자</option>
                    </select>
                    <input class="searchInput" id="searchInput" type="text" />
                    <button class="searchButton" type="button" onclick="boardSearch()">
                        <img class="image-thumb" src="https://media.istockphoto.com/id/1167683205/ko/%EB%B2%A1%ED%84%B0/%EA%B2%80%EC%83%89-%EC%95%84%EC%9D%B4%EC%BD%98.jpg?s=612x612&w=0&k=20&c=zFYCQOhkQmFkjFOz8B0SnZpwnDv_M_n1D2jgrfzkLfk=">
                    </button>

                </div>

            </div>
        </section>
    </c:if>

        <aside class="main_aside">
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


</div>




<script src="/js/reply.js"></script>
<script src="/js/board.js"></script>
<link rel="stylesheet" href="/css/main.css" />

<%@ include file="layout/footer.jsp" %>