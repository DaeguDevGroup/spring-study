<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>게시판</title>

    <!-- Bootstrap -->
    <link href="../resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    
    <div class="container">
    
                    <%@ include file="../inc/message_block.jsp" %>
    
					<div class="block">
						<div class="block-title">
                            <div class="block-options pull-right">
                                <a href="./form" class="btn btn-default" title="등록">등록</a>
                            </div>
							<h2>
								<strong>게시판</strong>
								<small>목록</small>
							</h2>
						</div>
                        <div class="table-responsive">
                            <table id="general-table" class="table table-striped table-vcenter">
								<!-- <caption>게시판 목록</caption> -->
                                <thead>
                                    <tr>
										<th style="width:60%">제목</th>
										<th>작성자</th>
										<th>등록일</th>
                                    </tr>
                                </thead>
                                <tbody>
								<c:forEach var="item" items="${items}" varStatus="status">
									<tr>
										<td class="text-left"><a href="./${item.id}" title="상세보기">${item.subject}</a></td>
										<td>${item.writer}</td>
										<td>${item.createDate}</td>
									</tr>
								</c:forEach>
								<c:if test='${empty items}'>
									<tr>
										<td colspan="3">검색된 내용이 없습니다.</td>
									</tr>
								</c:if>
								
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td colspan="3">
                                        </td>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../resources/js/bootstrap.min.js"></script>
  </body>
</html>