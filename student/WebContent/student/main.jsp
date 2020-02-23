<%@ page import="dao.StudentD" %>
<%@ page import="model.Student" %>
<%@ page import="dao.ScoreD" %>
<%@ page import="model.Score" %>
<%@ page import="java.util.*"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>main</title>
    <link href="../resources/css/default.css" rel="stylesheet"/>
</head>
<body>
<%
    Student student = (Student) session.getAttribute("info");
%>
<div id="page" class="container">
    <div id="header">
        <div id="logo">
            <img src="../userImg/<%=student.getId()%>.jpeg"/>
            <h1><%=student.getName()%></h1>
        </div>
        <div id="menu">
            <ul>
                <li><a href="personal.jsp">个人信息</a></li>
                <li class="current_page_item"><a href="main.jsp">成绩信息</a></li>
                <li><a onclick="return confirm('确认退出?');" href="../exit">退出登录</a></li>
            </ul>
        </div>
    </div>
    <div id="main">
        <div class="top">
            <h2>成绩信息</h2>
            <hr/>
        </div>
        <div class="table">
            <table width="800" frame="box" align="center">
                <tr>
                    <th height="35">学号</th>
                    <th>姓名</th>
                    <th>专业</th>
                    <th>科目</th>
                    <th>成绩</th>
                    <th>打印</th>
                </tr>
                <%
                    try {
                        ScoreD scoD = new ScoreD();
                        StudentD stuD = new StudentD();
                        List<Score> scores = scoD.findWithId(student.getId());
                        String name = stuD.findWithId(student.getId()).getName();
                        String major = stuD.findWithId(student.getId()).getMajor();
                        session.setAttribute("scores",scores);     
                %>
                <c:forEach items="${scores}" var="s">
                	<tr>
                    	<td height="35">${s.student_id}</td>
                    	<td><%=name%></td>
                    	<td><%=major%></td>
                    	<td>${s.subject}</td>
                    	<td>${s.score}</td>
                    	<td><a href="pdf.jsp?id=${s.student_id}&name=<%=name%>&major=<%=major%>&subject=${s.subject}&score=${s.score}">PDF</a></td>
                	</tr>
                </c:forEach>
                <%
                    }
                    catch (Exception e){
                        out.print(e);
                    }
                %>
            </table>
        </div>
    </div>
</div>
</body>
</html>

