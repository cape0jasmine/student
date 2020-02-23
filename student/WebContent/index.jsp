<%@ page import="dao.TeacherD" %>
<%@ page import="dao.StudentD" %>
<%@ page import="model.Teacher" %>
<%@ page import="model.Student" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    TeacherD teacherD = new TeacherD();
    StudentD studentD = new StudentD();
    Teacher teacher = null;
    Student student = null;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            String cookieName = c.getName();
            if ("name".equals(cookieName)) {
                String user = c.getValue();
                try {
                    teacher = teacherD.findWithId(user);
                    student = studentD.findWithId(user);
                } catch (Exception e) {
                    out.print(e);
                }
                if (teacher != null) {
                    session.setAttribute("info", teacher);
                    response.sendRedirect("one_page_student");
                    return;
                }
                else if(student != null){
                    session.setAttribute("info", student);
                    response.sendRedirect("student/main.jsp");
                    return;
                }
            }
        }
    }
    response.sendRedirect("login.jsp");
%>
</body>
</html>
