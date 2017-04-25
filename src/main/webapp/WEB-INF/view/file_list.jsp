<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.online.disk.model.OnlineFile"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="./include.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="container" style="position: relative; margin-top: 50px;overflow:'auto'">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<h3>
				家用网盘系统
			</h3>
			<table class="table">
				<thead>
				<tr>
					<th>名称</th>
					<th>大小</th>
					<th>修改时间</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><a href="#" onclick="toPath('${fatherFileId}','','out')"><span class="glyphicon glyphicon-user"></span>返回上一级</a></td>
					<td></td>
					<td></td>
				</tr>
				<c:forEach items="${list}" var="item">
					<tr>
						<td><a href="#" onclick="toPath('${item.id}','${item.fileType}','in')"><span class="glyphicon glyphicon-user"></span><c:out value="${item.fileName}" /></a></td>
						<td><c:out value="${item.fileSize}" /></td>
						<td><fmt:formatDate value="${item.lastModify}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
		</div>
	</div>
	<form action="<%=request.getContextPath()%>"></form>
</div>
<script type="text/javascript">
	$('#lineTable').bootstrapTable({
		striped:true,
		visible:false,
		onClickRow:function(){
			alert("111111111");			
		}
	});
	
	function toPath(id,fileType,pro){
		var url = path + "/file/toPath?id=" + id + "&fileType=" + fileType + "&cmd=" + pro;
		window.location.href=encodeURI(url);
	}
</script>

</body>
</html>