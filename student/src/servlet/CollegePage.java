package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CollegeD;
import model.College;

/**
 * Servlet implementation class CollegePage
 */
@WebServlet("/CollegePage")
public class CollegePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CollegePage() {
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
	        HttpSession session = request.getSession();
	        CollegeD collegeD = new CollegeD();
	        int currentIndex, count, size = 10;
      	    String index = request.getParameter("index");
      	    if (index == null)
                index = "1";
      	    currentIndex = Integer.parseInt(index);
	        try {
	        	 count = collegeD.getCount();
	        	ArrayList<College> college = collegeD.getAll(currentIndex, size);
	        	 int sumIndex = count % size == 0 ? count / size : count / size + 1;
	        	 session.setAttribute("college", college);
                 session.setAttribute("sumScoreIndex",sumIndex);
                 response.sendRedirect("teacher/college.jsp");
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
