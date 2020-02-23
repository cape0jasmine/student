<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page import="model.Teacher" %>
<%@ page import="model.College" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.CollegeD" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>学院管理</title>
  <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<%
    Teacher teacher = (Teacher) session.getAttribute("info");
    ArrayList<College> colleges = (ArrayList<College>) session.getAttribute("college");
    Integer sumIndex = (Integer) session.getAttribute("sumScoreIndex");
%>
<body>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="../userImg/<%=teacher.getId()%>.jpeg"/>
            <h1><%=teacher.getId()%>
            </h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="per.jsp">个人信息</a></li>
                <li class="current_page_item"><a href="../one_page_student">学生管理</a></li>
                <li class="current_page_item"><a href="../one_page_score">成绩管理</a></li>
                <li class="current_page_item"><a href="../CollegePage">学院管理</a></li>
                <li class="current_page_item"><a href="../MajorPage">专业管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>学院管理</h2>
            <hr/>
        </div>
        <form method="post" action="../update_score" style="height: 525px; margin-top: 20px">
            <input type="button" class="btn-add" style="float: right;margin-bottom: 30px" onclick="addColl()" value="添加">
            <div class="table" style="margin-top: 20px; height: 525px">
                <table id="table" width="845" frame="box" align="center">
                    <tr>
                        <th height="35">编号</th>
                        <th>学院名称</th>
                    </tr>
                   <%
                    for (College college : colleges) {
                %>
                        <tr>
                            <form method="post" action="../update_score">
                                <td><input value="<%=college.getId()%>" name="stuacademy" class="table-input" style="width: 90%"></td>
                                <td><input value="<%=college.getName()%>" name="stumajor" class="table-input" style="width: 40%"></td>
                                <td>
                                <span>
                               		<input type="button" id="addScore" onclick="updaterScore('<%=college.getId()%>','<%=college.getName()%>')" value="修改"></input>
                               		<input type="button" id="delScore" onclick="deleScore('<%=college.getId()%>')" value="删除"></input>
                                </span>
                                </td>
                            </form>
                        </tr>
                <%
                    }
                %>
                </table>

            </div>
        </form>

        <%
            if (sumIndex != null){
        %>
                <div id="index">
                    <a href="../CollegePage?index=1">首页</a>
                    <%
                        for (int i = 1; i <= sumIndex.intValue(); i++) {
                    %>
                    <a href="../CollegePage?index=<%=i%>">第<%=i%>页</a>
                    <%
                        }
                    %>
                    <a href="../CollegePage?index=<%=sumIndex%>">尾页</a>
                </div>
        <%
            }
        %>
    </div>
    <%--修改对话框--%>
	<div id="updateCo" title="修改学院">
		<form id="add-form" method="post" onsubmit="return check(this)">
			编号:<span id="cid"></span><input id="co_id" name="id" type="hidden" style="width: 200px"><br>
			学院名称:<input id="co_name" name="name" type="text" style="width: 200px"><br>
			<hr>
			<input style="float: left; margin-right: 25px" type="submit"
				value="确定" onclick="this.form.action='../UpdateCo'">
		</form>
		<input style="float: right" type="submit" value="取消" onclick="window.location.href='college.jsp'">
	</div>
	 <%--添加对话框--%>
	<div id="addCo" title="添加学院">
		<form id="add-form" method="post" onsubmit="return check(this)">
			学院名称:<input name="name" type="text" style="width: 200px"><br>
			<hr>
			<input style="float: left; margin-right: 25px" type="submit"
				value="确定" onclick="this.form.action='../AddCollServlet'">
		</form>
		<input style="float: right" type="submit" value="取消" onclick="window.location.href='college.jsp'">
	</div>
	
   <script type="text/javascript">
    $('#updateCo').dialog({
		width : 310,
		autoOpen : false,
		draggable : false,
		modal : true,
		resizable : false
	});
    $('#addCo').dialog({
		width : 310,
		autoOpen : false,
		draggable : false,
		modal : true,
		resizable : false
	});
    function updaterScore(id,name){
    	$("#co_id").val(id);
    	$("#co_name").val(name);
    	$("#cid").text(id);
		$('#updateCo').dialog('open');
    }
    function deleScore(id){
    	if(confirm('确定要删除吗?')){
    		window.location.href = "../DeleCoServlet?id="+id;
    	}
    }
    function addColl(){
    	$('#addCo').dialog('open');
    }
    </script>
</div>
</body>
</html>