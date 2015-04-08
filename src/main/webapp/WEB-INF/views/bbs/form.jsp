<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
					<form:errors path="form">
						<%@ include file="../inc/message_block.jsp" %>
					</form:errors>
                    
                    <div class="block">
						<div class="block-title">
                            <div class="block-options pull-right">
                                <a href="./" class="btn btn-default" title="목록" id="listButton">목록</a>
                            </div>
							<h2>
								<strong>게시판</strong>
								<small>${not empty form.id ? "수정" : "등록"}</small>
							</h2>
						</div>
						<form:form commandName="form" action="./form" cssClass="form-horizontal">
						<fieldset>
                            <div class="form-group<form:errors path="subject"> has-error</form:errors>">
								<label class="col-md-1 control-label" for="subject">제목</label>
                                <div class="col-md-11">
									<form:input path="subject" cssClass="form-control" maxlength="500" />
									<form:errors path="subject" cssClass="help-block" element="div" delimiter=" "  />
								</div>
							</div>
                            <div class="form-group<form:errors path="content"> has-error</form:errors>">
								<label class="col-md-1 control-label" for="content">내용</label>
                                <div class="col-md-11">
									<form:textarea path="content" htmlEscape="false" cssClass="form-control" rows="10" />
									<form:errors path="content" cssClass="help-block" element="div" delimiter=" "  />
								</div>
							</div>
                            <div class="form-group<form:errors path="writer"> has-error</form:errors>">
								<label class="col-md-1 control-label" for="writer">작성자</label>
                                <div class="col-md-11">
									<form:input path="writer" cssClass="form-control" maxlength="500" />
									<form:errors path="writer" cssClass="help-block" element="div" delimiter=" "  />
								</div>
							</div>
                            <div class="form-group form-actions">
                                <div class="col-md-11 col-md-offset-1">
                                	<button type="submit" class="btn btn-default">저장</button>
								<c:if test='${not empty form.id}'>
									<button type="button" id="btn-delete" class="btn btn-danger">삭제</button>
								</c:if>
                                </div>
                            </div>
                        </fieldset>
                        </form:form>
                    </div>
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../resources/js/bootstrap.min.js"></script>
  </body>
</html>