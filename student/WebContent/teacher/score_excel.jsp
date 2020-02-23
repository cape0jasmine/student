<%@ page import="dao.ScoreD" %>
<%@ page import="model.StudentScore" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dao.StudentD" %>

<%@ page contentType="application/msexcel" language="java" pageEncoding="GBK" %>
<!DOCTYPE html>
<html>
<head>
    <title>main</title>
</head>
<body>
<%
    out.clearBuffer();
    response.setHeader("Content-Disposition", "attachment;filename=score.xls");
%>
<table align="center" border="1">
    <tr>
       <th height="35">学号</th>
        <th>姓名</th>
        <th>专业</th>
        <th>科目</th>
        <th>分数</th>
    </tr>
    <%
        try {
            ScoreD scoD = new ScoreD();
            StudentD stuD = new StudentD();
            ArrayList<StudentScore> stus = scoD.getOnePage(1, 10000);
            for (StudentScore stu : stus) {
                String name = stu.getName();
                String major = stu.getMajor();
    %>
    <tr>
            <td align="center"><%=stu.getStudent_id()%></td>
            <td align="center"><%=name%></td>
            <td align="center"><%=major%></td>
            <td align="center"><%=stu.getSubject()%></td>
            <td align="center"><%=stu.getScore()%></td>
    </tr>
    <%
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>
</body>
</html>

