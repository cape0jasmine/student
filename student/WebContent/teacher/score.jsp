<%@page import="com.itextpdf.text.log.SysoCounter"%>
<%@ page import="model.Teacher" %>
<%@ page import="model.StudentScore" %>
<%@ page import="model.Student" %>
<%@ page import="model.Course" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.StudentD" %>
<%@ page import="dao.CourseD" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>成绩管理</title>
     <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    Teacher teacher = (Teacher) session.getAttribute("info");
    ArrayList<StudentScore> stus = (ArrayList<StudentScore>) session.getAttribute("onePageScore");
    Integer sumIndex = (Integer)session.getAttribute("sumScoreIndex");
%>
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
            <h2>学生成绩管理</h2>
            <hr/>
        </div>
        <form method="post" action="../update_score" style="height: 525px; margin-top: 20px">
            <input type="button" class="btn-add" onclick="location.href='score_excel.jsp';" value="导出EXCEL">
            <input type="button" class="btn-add" style="float: right;margin-bottom: 30px" onclick="addScores()" value="添加">
            <div class="table" style="margin-top: 20px; height: 525px">
                <table id="table" width="845" frame="box" align="center">
                    <tr>
                        <th height="35">学号</th>
                        <th>姓名</th>
                        <th>专业</th>
                        <th>科目</th>
                        <th>分数</th>
                        <th>操作</th>
                    </tr>
                   <%
                    for (StudentScore stu : stus) {
                %>
                        <tr>
                            <form method="post" action="../update_score">
                                <td><input value="<%=stu.getStudent_id()%>" name="stuacademy" class="table-input" style="width: 90%"></td>
                                <td><input value="<%=stu.getName()%>" name="stumajor" class="table-input" style="width: 40%"></td>
                                <td><input value="<%=stu.getMajor()%>" name="stuname" class="table-input" style="width: 90%"></td>
                                <td><input value="<%=stu.getSubject()%>" name="stusex" class="table-input" style="width: 50%"></td>                                                       
                                <td><input value="<%=stu.getScore()%>" name="stusex" class="table-input" style="width: 50%"></td>       
                                <td>
                                <span>
                               		<input type="button" id="addScore" onclick="updaterScore('<%=stu.getStudent_id()%>','<%=stu.getSubject()%>','<%=stu.getScore()%>')" value="修改"></input>
                               		<input type="button" id="delScore" onclick="deleScore('<%=stu.getStudent_id()%>','<%=stu.getSubject()%>')" value="删除"></input>
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
                    <a href="../one_page_score?index=1">首页</a>
                    <%
                        for (int i = 1; i <= sumIndex.intValue(); i++) {
                    %>
                    <a href="../one_page_score?index=<%=i%>">第<%=i%>页</a>
                    <%
                        }
                    %>
                    <a href="../one_page_score?index=<%=sumIndex%>">尾页</a>
                </div>
        <%
            }
        %>
    </div>
    <%--修改成绩对话框--%>
	<div id="updateSc" title="修改成绩">
		<form id="add-form" method="post" onsubmit="return check(this)">
			学号:<span id="sid"></span><input id="stu_id" name="student_id" type="hidden" style="width: 200px"><br>
			学号:<span id="ssuj"></span><input id="stu_suj" name="subj" type="hidden" style="width: 200px"><br>
			分数:<input id="std_sc" name="score" type="text" style="width: 200px"><br>
			<hr>
			<input style="float: left; margin-right: 25px" type="submit"
				value="确定" onclick="this.form.action='../UpdateSc'">
		</form>
		<input style="float: right" type="submit" value="取消" onclick="window.location.href='score.jsp'">
	</div>
	
	 <%--添加成绩对话框--%>
	<div id="addSc" title="添加成绩">
		<form id="add-form" method="post" onsubmit="return check(this)">
			学号:<input id="xh" name="student_id" type="text" style="width: 200px"><br>
			
			姓名:<input id="xm" name="name" type="text" style="width: 200px"><br>
		
			科目:<select name="subject" id="xy" style="width: 200px">
					<option value="">---请选择---</option>
					<%
					CourseD cd = new  CourseD();
						ArrayList<Course> list2 = cd.getCourses();
						if(list2 != null && list2.size() != 0){
							for(Course c:list2){
								%>
								<option value="<%=c.getCname()%>"><%=c.getCname()%></option>
								<%
							}
						}
					%>
				</select><br>
			分数:<input id="std_sc" name="score" type="text" style="width: 200px"><br>
			<hr>
			<input style="float: left; margin-right: 25px" type="submit"
				value="确定" onclick="this.form.action='../AddSc'">
		</form>
		<input style="float: right" type="submit" value="取消" onclick="window.location.href='score.jsp'">
	</div>
	
    <script type="text/javascript">
    $('#updateSc').dialog({
		width : 310,
		autoOpen : false,
		draggable : false,
		modal : true,
		resizable : false
	});
    $('#addSc').dialog({
		width : 310,
		autoOpen : false,
		draggable : false,
		modal : true,
		resizable : false
	});
    function updaterScore(stuid,subject,score){
    	$("#stu_id").val(stuid);
    	$("#stu_suj").val(subject);
    	$("#sid").text(stuid);
    	$("#ssuj").text(subject);
		$("#std_sc").val(score);
		$('#updateSc').dialog('open');
    }
    function deleScore(id,subject){
    	if(confirm('确定要删除吗?')){
    		window.location.href = "../DeleScoreServlet?id="+id+"&subject="+subject;
    	}
    }
    
    function addScores(){
    	$('#addSc').dialog('open');
    }
    </script>
</div>
</body>
</html>

