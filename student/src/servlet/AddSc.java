package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ScoreD;

/**
 * Servlet implementation class AddSc
 */
@WebServlet("/AddSc")
public class AddSc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        ScoreD scoreD = new ScoreD();
        String student_id = request.getParameter("student_id");
        String subject = request.getParameter("subject");
        String score = request.getParameter("score");
        String name = request.getParameter("name");
        
        try {
        	if (scoreD.findWithStuidAndName(student_id, subject) != null) {
        		 scoreD.insertScore(student_id,subject,score);
        		 out.print("<script>alert(\"添加成功！\");location.href = \"teacher/main.jsp\";</script>");
			}else {
				 out.print("<script>alert(\"添加失败！此学生成绩已存在，您可以修改他的成绩\");location.href = \"teacher/main.jsp\";</script>");
			}
           
        }
        catch (Exception e){
            out.print(e);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
