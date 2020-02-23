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

@WebServlet("/update_student")
public class update_student extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();
        StudentD studentD = new StudentD();

        String stuno = request.getParameter("stuno");
        String stuacademy = request.getParameter("stuacademy");
        String stumajor = request.getParameter("stumajor");
        String stuname = request.getParameter("stuname");
        String stusex = request.getParameter("stusex");
        

        try {
            studentD.updateStudentInfo(stuno, stuacademy, stumajor,stuname, stusex);
        }
        catch (Exception e){
            out.print(e);
        }
        response.sendRedirect("one_page_student");
    }
}
