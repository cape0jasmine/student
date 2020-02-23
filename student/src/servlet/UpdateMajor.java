package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MajorD;
import model.Major;

/**
 * Servlet implementation class UpdateMajor
 */
@WebServlet("/UpdateMajor")
public class UpdateMajor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMajor() {
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
        String id = request.getParameter("id");
        String mid = request.getParameter("mid");
        String name = request.getParameter("name");
        String mname = request.getParameter("mname");
        MajorD majorD = new MajorD();
        try {
        	ArrayList<Major> list = majorD.getMajorAndColl(name, mname);
        	if (list == null) {
        		 out.print("<script>alert(\"该学院下没有这个专业！\");location.href = \"teacher/major.jsp\";</script>");
			}else {
				majorD.updateMajor(id, mname, mid);
				 out.print("<script>alert(\"修改成功！\");location.href = \"teacher/main.jsp\";</script>");
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
