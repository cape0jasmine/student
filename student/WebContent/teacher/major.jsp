<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page import="model.Teacher" %>
<%@ page import="model.Major" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.MajorD" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>专业管理</title>
  <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<%
    Teacher teacher = (Teacher) session.getAttribute("info");
    ArrayList<Major> majors = (ArrayList<Major>) session.getAttribute("major");
    Integer sumIndex = (Integer) session.getAttribute("sumIndex");
%>
<body>
qertyuio
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
    <div style="background: #f9f9f9;float: right;text-align: center;overflow: hidden;border-top: 6px solid #2980b9;padding: 2em 50px 1em 50px; height: 90%;width: 800px;">
        <div class="top" style="height: 100%">
            <h2>专业管理</h2>
            <hr/>
        </div>
        <form method="post" action="../update_score" style="height: 525px; margin-top: 20px">
            <input type="button" class="btn-add" style="float: right;margin-bottom: 30px" onclick="addColl()" value="添加">
            <div class="table" style="margin-top: 20px; height: 525px">
                <table id="table" width="845" frame="box" align="center">
                    <tr>
                        <th height="35">专业编号</th>
                        <th>学院名称</th>
                        <th>专业名称</th>
                    </tr>
                   <%
                    for (Major major : majors) {
                %>
                        <tr>
                            <form method="post" action="../update_score">
                                <td><input value="<%=major.getId()%>" name="stuacademy" class="table-input" style="width: 40%"></td>
                                <td><input value="<%=major.getName()%>" name="stumajor" class="table-input" style="width: 90%"></td>
                                <%-- <td><input value="<%=major.getMid()%>" name="stuacademy" class="table-input" style="width: 90%"></td> --%>
                                <td><input value="<%=major.getMname()%>" name="stumajor" class="table-input" style="width: 90%"></td>
                                <td>
                                <span>
                               		<input type="button" id="addScore" onclick="updaterScore('<%=major.getId()%>','<%=major.getName()%>','<%=major.getMid()%>')" value="修改"></input>
                               		<input type="button" id="delScore" onclick="deleScore('<%=major.getId()%>')" value="删除"></input>
                                </span>
                                </td>
                            </form>
                        </tr>
                <%
                    }
                %>
                
                </table>
  <%
            if (sumIndex != null){
        %>
                <div id="index">
                    <a href="../MajorPage?index=1">首页</a>
                    <%
                        for (int i = 1; i <= sumIndex.intValue(); i++) {
                    %>
                    <a href="../MajorPage?index=<%=i%>">第<%=i%>页</a>
                    <%
                        }
                    %>
                    <a href="../MajorPage?index=<%=sumIndex%>">尾页</a>
                </div>
        <%
            }
        %>
            </div>
        </form>

      
    </div>
    <%--修改对话框--%>
   <div id="updateMajor" title="修改专业">
		<form id="add-form" method="post" onsubmit="return check(this)">
			学院编号:<span id="cid"></span><input id="co_id" name="id" type="hidden" style="width: 200px"><br>
			学院:<select name="name" id="xy" style="width: 200px">
					<option value="">---请选择---</option>
					<%
					MajorD cd = new  MajorD();
						ArrayList<Major> list = cd.getAllc();
						if(list != null && list.size() != 0){
							for(Major c:list){
								%>
								<option value="<%=c.getName()%>"><%=c.getName()%></option>
								<%
							}
						}
					%>
				</select><br>
			专业编号:<span id="mid"></span><input id="m_id" name="mid" type="hidden" style="width: 200px"><br>
			专业:<select name="mname" id="zy" style="width: 200px">
					<option value="">---请选择---</option>
					<%
					MajorD cd1 = new  MajorD();
					ArrayList<Major> list1 = cd1.getAll();
					if(list1 != null && list1.size() != 0){
						for(Major c:list1){
							%>
							<option value="<%=c.getMname()%>"><%=c.getMname()%></option>
							<%
						}
					}
					%>
				</select><br>
			<hr>
			<input style="float: left; margin-right: 25px" type="submit"
				value="确定" onclick="this.form.action='../UpdateMajor'">
		</form>
		<input style="float: right" type="submit" value="取消" onclick="window.location.href='college.jsp'">
	</div>
	 <%--添加对话框--%>
	<div id="addMajor" title="添加专业">
		<form id="add-form" method="post" onsubmit="return check(this)">
			学院名称:<select name="xname" id="axy" style="width: 200px">
					<option value="">---请选择---</option>
					<%
					MajorD cd2 = new  MajorD();
						ArrayList<Major> list2 = cd2.getAllc();
						if(list2 != null && list2.size() != 0){
							for(Major c:list2){
								%>
								<option value="<%=c.getName()%>"><%=c.getName()%></option>
								<%
							}
						}
					%>
				</select><br>
			专业:<select name="xmname" id="xzy" style="width: 200px">
					<option value="">---请选择---</option>
					<%
					MajorD cd3 = new  MajorD();
					ArrayList<Major> list3 = cd3.getAll();
					if(list3 != null && list3.size() != 0){
						for(Major c:list3){
							%>
							<option value="<%=c.getMname()%>"><%=c.getMname()%></option>
							<%
						}
					}
					%>
				</select><br>
			<hr>
			<input style="float: left; margin-right: 25px" type="submit"
				value="确定" onclick="this.form.action='../AddMajorServlet'">
		</form>
		<input style="float: right" type="submit" value="取消" onclick="window.location.href='college.jsp'">
	</div>
	
 <script type="text/javascript">
    $('#updateMajor').dialog({
		width : 310,
		autoOpen : false,
		draggable : false,
		modal : true,
		resizable : false
	});
    $('#addMajor').dialog({
		width : 310,
		autoOpen : false,
		draggable : false,
		modal : true,
		resizable : false
	});
    function updaterScore(id,name,mid){
    	$("#co_id").val(id);
    	$("#co_name").val(name);
    	$("#m_id").val(mid);
    	$("#cid").text(id);
    	$("#mid").text(mid);
		$('#updateMajor').dialog('open');
    }
    function deleScore(id){
    	if(confirm('确定要删除吗?')){
    		window.location.href = "../DeleMajorServlet?id="+id;
    	}
    }
    function addColl(){
    	$('#addMajor').dialog('open');
    }
    </script>
</div>
</body>
</html>