<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
						<c:forEach var='message' items='${messages}' varStatus='status'>
						<div class="alert alert-danger alert-dismissable">
                        	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        	<!-- <h4><i class="fa fa-times-circle"></i> Error</h4>  -->${message}
                        </div>
						</c:forEach>
						<c:if test='${not empty message}'>
						<div class="alert alert-success alert-dismissable">
                        	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        	${message}
                        </div></c:if>