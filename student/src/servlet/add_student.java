package servlet;

import dao.ScoreD;
import dao.StudentD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DatabaseMetaData;
import java.sql.Date;

@WebServlet("/add_student")
public class add_student extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        StudentD studentD = new StudentD();
        ScoreD scoreD = new ScoreD();

        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String major = request.getParameter("major");
        String academy = request.getParameter("academy");

        String student_id = request.getParameter("student_id");
        String subject = request.getParameter("subject");
        String score = request.getParameter("score");
        
        try {
            studentD.insertStudent(academy, major, student_id, name, sex);
            scoreD.insertScore(student_id,subject,score);
        }
        catch (Exception e){
            out.print(e);
        }
        response.sendRedirect("one_page_student");
    }
}
