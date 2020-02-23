package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ScoreD;
import dao.StudentD;
import model.Student;

/**
 * Servlet implementation class AddScoreServlet
 */
@WebServlet("/AddScoreServlet")
public class AddScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddScoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String id = request.getParameter("aid");
        String name = request.getParameter("aname");
        String major = request.getParameter("amajor");
        String subject = request.getParameter("asubject");
        String score = request.getParameter("ascore");
        StudentD studentD = new StudentD();
        ScoreD scoreD = new ScoreD();
        try {
        	Student s = studentD.findWithId(id);
        	if (s == null) {
        		 out.print("<script>alert(\"该学生不存在！\");location.href = \"teacher/main.jsp\";</script>");
			}
        	scoreD.insertScore(id, subject, score);
        	 out.print("<script>alert(\"添加成功！\");location.href = \"teacher/main.jsp\";</script>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
