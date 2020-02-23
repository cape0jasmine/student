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

import dao.MajorD;
import model.Major;

/**
 * Servlet implementation class MajorPage
 */
@WebServlet("/MajorPage")
public class MajorPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MajorPage() {
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
	        HttpSession session = request.getSession();
	        MajorD majord = new MajorD();
	        int currentIndex, count, size = 10;
      	    String index = request.getParameter("index");
      	    if (index == null)
                index = "1";
      	    currentIndex = Integer.parseInt(index);
	        try {
	        	 count = majord.getCount();
	        ArrayList<Major> major = majord.getAlls(currentIndex, size);
	        	/* ArrayList<Major> major = majord.getAll();*/
	            int sumIndex = count % size == 0 ? count / size : count / size + 1;
	             session.setAttribute("sumIndex", sumIndex);
	        	 session.setAttribute("major", major);
	        	 response.sendRedirect("teacher/major.jsp");
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
