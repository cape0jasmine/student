package servlet;

import dao.ScoreD;
import dao.StudentD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/update_score")
public class update_score extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        ScoreD scoreD = new ScoreD();

        String[] id = request.getParameterValues("id");
        String[] subject = request.getParameterValues("subject");
        String[] score = request.getParameterValues("score");

        try {
            for (int i=0; i<id.length; i++) {
                scoreD.updateScoreInfo(id[i], subject[i], score[i]);
            }
            response.sendRedirect("one_page_score");
        }
        catch (Exception e){
            out.print(e);
        }
    }
}
