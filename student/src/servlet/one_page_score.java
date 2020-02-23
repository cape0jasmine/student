package servlet;

import dao.ScoreD;
import dao.StudentD;
import model.Score;
import model.Student;
import model.StudentScore;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;

@WebServlet("/one_page_score")
public class one_page_score extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String key = request.getParameter("student_id");
        if (key == null) { // 教师端
            int currentIndex, count, size = 6;
            String index = request.getParameter("index");
            if (index == null)
                index = "1";
            currentIndex = Integer.parseInt(index);

            try {
                ScoreD scoD = new ScoreD();
                count = scoD.getScoreCount();
                ArrayList<StudentScore> stus = scoD.getOnePage(currentIndex, size);
                int sumIndex = count % size == 0 ? count / size : count / size + 1;
                session.setAttribute("onePageScore", stus);
                session.setAttribute("sumScoreIndex", sumIndex);
                response.sendRedirect("teacher/score.jsp");
            } catch (Exception e) {
                out.print(e);
            }
        }else {
            ScoreD scoreD = new ScoreD();
                try {
                    ArrayList<Score> scores = scoreD.findWithId(key);
                    session.setAttribute("onePageScore", scores);
                    session.setAttribute("sumScoreIndex", 1);
                    response.sendRedirect("teacher/score.jsp");
                } catch (Exception e) {
                    out.print(e);
                }
            }
        }
}
