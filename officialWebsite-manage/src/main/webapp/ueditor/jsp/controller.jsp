<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.rongdu.ow.core.common.util.ueditor.*"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%

    request.setCharacterEncoding( "utf-8" );
	response.setHeader("Content-Type" , "text/html");
	String rootPath = request.getSession().getServletContext().getRealPath("/");
	out.write( new UeditorActionEnter( request, rootPath ).exec() );
	
%>