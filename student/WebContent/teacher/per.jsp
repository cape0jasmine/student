<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Student" %>
<%@ page import="model.Teacher" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="../resources/css/jquery-ui-1.10.4.custom.min.css">
    <script src="../resources/js/jquery-1.10.2.js"></script>
    <script src="../resources/js/jquery-ui-1.10.4.custom.min.js"></script>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    Teacher teacher = (Teacher) session.getAttribute("info");
    ArrayList<Student> stus = (ArrayList<Student>) session.getAttribute("onePageStudent");
    int sumIndex = (int) session.getAttribute("sumIndex");
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
                <li><a href="personal.jsp">个人信息</a></li>
               
                <li class="current_page_item"><a href="../one_page_student">学生管理</a></li>
                <li><a href="../one_page_score">成绩管理</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>学生信息管理</h2>
            <hr/>
            <button class="btn-add">添加教师</button>
            <div class="find">
                <form action="../one_page_student" method="post">
                    <input id="find-text" type="text" name="key" placeholder="输入工号或姓名搜索">
                    <input class="find-btn" type="submit" value="搜索">
                </form>
            </div>
        </div>
        <div class="table">
            <table id="table" width="800" frame="box" align="center">
                <tr>
                    
                    <th>工号</th>
                    <th>姓名</th>
                    <th>密码</th>
                 
                    <th>操作</th>
                </tr>
               <%--  <%
                    for (Student stu : stus) {
                %> --%>
                       <%--  <tr>
                            <form method="post" action="../update_student">
                               
                                <td><input value="<%=stu.getAcademy()%>" name="stuacademy" class="table-input" style="width: 90%"></td>
                                <td><input value="<%=stu.getMajor()%>" name="stumajor" class="table-input" style="width: 90%"></td>
                                <td height="35"><%=stu.getId()%></td>
                                <td><input value="<%=stu.getName()%>" name="stuname" class="table-input" style="width: 50%"></td>
                                <td><input value="<%=stu.getSex()%>" name="stusex" class="table-input" style="width: 50%"></td>                                                       
                                <input value="<%=stu.getId()%>" name="stuno" type="hidden">
                                <td><input type="submit" class="update-btn" value="修改" onclick="return confirm('确定要修改吗?')">&nbsp;
                                <a class="btn-delete" onclick="return confirm('确定要删除吗?');"
                                 href=<%="'../delete_student?id=" + stu.getId() + "'"%>>删除</a>&nbsp;&nbsp;<a href="../one_page_score?id=<%=stu.getId()%>">查看成绩</a>
                                </td>
                            </form>
                        </tr>
                <%
                    }
                %> --%>
            </table>
        </div>
        <%
            if (sumIndex > 1){
        %>
                <div id="index">
                    <a href="../one_page_student?index=1">首页</a>
                    <%
                        for (int i=1; i<=sumIndex; i++){
                    %>
                            <a href="../one_page_student?index=<%=i%>">第<%=i%>页</a>
                    <%
                        }
                    %>
                    <a href="../one_page_student?index=<%=sumIndex%>">尾页</a>
                </div>
        <%
            }
        %>
    </div>
</div>

<%--添加教师信息对话框--%>
<div id="add-dialog" title="添加教师信息">
    <form id="add-form" method="post" onsubmit="return check(this)">
        
        学院:<select name="academy" id="academy" style="width: 200px">
						<option selected="selected">---请选择---</option>
			</select><br>
        专业:<select name="major" id="major" style="width: 200px">
						<option selected="selected">---请选择---</option>
			</select><br>
		工号:<input name="id" type="text" style="width: 200px"><br>
        姓名:<input name="name" type="text" style="width: 200px"><br>
        性别:<input name="sex" type="text" style="width: 200px"><br>
        
        
        <hr>
        <input style="float: left; margin-right: 25px" type="submit" value="确定"
               onclick="this.form.action='../add_student'">
    </form>
        <input style="float: right" type="submit" value="取消" onclick="window.location.href='main.jsp'">
</div>

<style>
    .ui-dialog-titlebar-close {
        display: none
    }
</style>

<script>
    $('#add-dialog').dialog({
        width: 310,
        autoOpen: false,
        draggable: false,
        modal: true,
        resizable: false
    });
    $('.btn-add').click(function () {
        $('#add-dialog').dialog('open');
    });
</script>
</body>
<%--添加学生信息对话框--%>
<script>
	function check(form){
		if (form.academy.value == "") {
			alert("学院不能为空");
			return false;
		}
		if (form.major.value == "") {
			alert("专业不能为空");
			return false;
		}
		if (form.id.value == "") {
			alert("学号不能为空");
			return false;
		}
		if (form.name.value == "") {
			alert("姓名不能为空");
			return false;
		}
		if (form.sex.value == "") {
			alert("性别不能为空");
			return false;
		}
		
		//验证数字的正则表达式 
		if (form.id.value == "") {
			alert("学号不能为空");
			return false;
		}
		else
			{
				var reg=/^\d+$/;
				var number=form.id.value;
				if(number.match(reg)){
					
				}
				else
					{
						alert("学号必须为纯数字");
						return false;
					}
			}
		return true;
	}
</script>
<script type="text/javascript">
		var librarians = ['机电工程学院', '电子信息学院', '工商管理学院', '财经管理学院', '汽车工程学院'];
		var choice = ['---请选择---'];
		var electromeChanical = ['数控技术', '模具设计与制造', '机械制造与自动化', '机电设备维修与管理', '工业机器人', '电气自动化', '机械设计与制造'];
		var electronicInformation = ['工业设计', '艺术设计', '光伏工程技术', '光伏发电技术与应用', '物联网应用技术', '计算机应用技术', '通讯技术', '电子信息工程技术'];
		var businessCircles = ['物流管理', '电子商务', '市场营销', '国际贸易实务', '工商企业管理', '商务管理'];
		var financialManagement = ['会计', '金融管理与实务', '财务管理', '酒店管理', '旅游管理'];
		var automobileEngineering = ['食品营养与检测', '汽车技术服务与营销', '汽车检测与维修技术'];

		var professional = new Array;
		professional[0] = choice;
		professional[1] = electromeChanical;
		professional[2] = financialManagement;
		professional[3] = businessCircles;
		professional[4] = electronicInformation;
		professional[5] = automobileEngineering;

		function add_option(select, option) {
			var target = document.getElementById(select);
			for(var i = option.length - 1; i >= 0; i--) {
				var add_option = document.createElement("option");
				add_option.text = option[i];
				target.add(add_option, null);
				target.lastChild.setAttribute("value", option[i]);
			}

		}
		add_option("academy", librarians);

		document.getElementById("academy").addEventListener("change", function() {

			var selevted_college = document.getElementById("academy");
			var selected_major = document.getElementById("major");

			for(var i = selected_major.length - 1; i >= 0; i--) {
				selected_major.remove(i);
			}
			var selected = selevted_college.selectedIndex;
			if(selected == 0) {
				add_option("major", professional[0]);
			} else {
				add_option("major", professional[selected]);
			}
		})
	</script>
</html>

