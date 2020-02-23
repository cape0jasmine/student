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

@WebServlet("/add_score")
public class add_score extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	

}
