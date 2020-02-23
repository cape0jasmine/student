package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TeacherD;
import model.Teacher;

/**
 * Servlet implementation class Modifpwd
 */
@WebServlet("/Modifpwd")
public class Modifpwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modifpwd() {
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
        String id = request.getParameter("mid");
        String name = request.getParameter("mname");
        String password = request.getParameter("mpwd");
        String newpwd = request.getParameter("mnewpwd");
        
        TeacherD te = new TeacherD();
        try {
			Teacher teacher = te.findWithId(id);
			if(password.equals(teacher.getPassword())) {
				te.updateTeacherPwd(id, newpwd);
				 out.print("<script>alert(\"修改成功！\");location.href = \"../student/login.jsp\";</script>");
			}else {
				 out.print("<script>alert(\"修改失败！\");location.href = \"teacher/main.jsp\";</script>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
