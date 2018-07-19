<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul>
				<li><a href="#">&lsaquo;</a></li>
				<c:forEach begin="1" end="${soluong/9+1 }" varStatus="i">
				<c:choose>
				<c:when test="${paging==i.index }">
						<li class="active"><a href="filter?page=${i.index }">${i.index }</a></li>
				</c:when>
				<c:otherwise>
						<li><a href="filter?page=${i.index }&scsize=${scsize}&os=${os}&pricelv=${pricelv}">${i.index }</a></li>
				</c:otherwise>
			</c:choose>
				</c:forEach>
				<li><a href="#">&rsaquo;</a></li>
			</ul>