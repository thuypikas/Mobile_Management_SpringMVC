<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul>
				<li><a href="#">&lsaquo;</a></li>
				<c:forEach begin="1" end="${soluong/9+1 }" varStatus="i">
				<c:choose>
				<c:when test="${paging==i.index }">
					<c:if test="${supid==null }">
						<li class="active"><a href="products?page=${i.index }">${i.index }</a></li>
					</c:if>
					<c:if test="${supid!=null }">
						<li class="active"><a
							href="products?supid=${supid }&page=${i.index }">${i.index }</a></li>
					</c:if>
				</c:when>
				<c:otherwise>
					<c:if test="${supid==null }">
						<li><a href="products?page=${i.index }">${i.index }</a></li>
					</c:if>
					<c:if test="${supid!=null }">
						<li><a
							href="products?supid=${supid }&page=${i.index }">${i.index }</a></li>
					</c:if>
				</c:otherwise>
			</c:choose>
				</c:forEach>
				<li><a href="#">&rsaquo;</a></li>
			</ul>