<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String view = request.getParameter("view");
if(view.startsWith("admin/"))
pageContext.forward("adminlayout.jsp");
else
pageContext.forward("userlayout.jsp");
%>
