package servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import dao.StudentD;
import model.Student;
import util.ExcelUtil;

/**
 * Servlet implementation class ExportServlet
 */
@WebServlet("/ExportServlet")
public class ExportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportServlet() {
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
        StudentD studentD = new StudentD();
		List<Map<String, String>> result = new ArrayList<>();// 最终返回结果集合
		List<Student> stuInfos = null;
		try {
			stuInfos = studentD.getAllStu();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int i = 1;// 定义表序号
		for (Student stuInfo : stuInfos) {
			Map<String, String> rmap = new LinkedHashMap<>();
			rmap.put("序号", "" + i);
			// 1
			if (stuInfo.getId() == null) {
				rmap.put("学号", "无数据");
			} else {
				String id = stuInfo.getId();
				rmap.put("学号", id);
			}
			// 2
			if (stuInfo.getName() == null) {
				rmap.put("姓名", "无数据");
			} else {
				String name = stuInfo.getName();
				rmap.put("姓名", name);
			}
			// 3
			if (stuInfo.getSex() == null) {
				rmap.put("性别", "无数据");
			} else {
				String sex = stuInfo.getSex();
				rmap.put("性别", sex);
			}
			// 4
			if (stuInfo.getAcademy() == null) {
				rmap.put("学院", "无数据");
			} else {
				String xy = stuInfo.getAcademy();
				rmap.put("学院", xy);
			}
			// 5
			if (stuInfo.getMajor() == null) {
				rmap.put("专业", "无数据");
			} else {
				String zy = stuInfo.getMajor();
				rmap.put("专业", zy);
			}
			// 6
			if (stuInfo.getEmail() == null) {
				rmap.put("邮箱", "无数据");
			} else {
				String yx = stuInfo.getEmail();
				rmap.put("邮箱", yx);
			}

			// 7
			if (stuInfo.getAddress() == null) {
				rmap.put("地址", "无数据");
			} else {
				String dz = stuInfo.getAddress();
				rmap.put("地址", dz);
			}
			i++;// 表序号增加
			result.add(rmap);
		}
		Date d = new Date();
		DateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = fm.format(d);
		DateFormat fm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time2 = fm2.format(d);
		// 设置excel表属性
		String fileName = "学生信息表(" + time + ").xls"; // 定义文件名
		String headString = "学生信息表(创建时间" + time2 + ")"; // 定义表格标题
		String sheetName = "学生信息表一"; // 定义工作表表名
		String filePath = "D:\\"; // 文件本地保存路径
		String[] thead = { "序号", "学号", "姓名", "性别", "学院", "专业", "邮箱", "地址"};// 定义表头内容
		int[] sheetWidth = { 4000, 5000, 5000, 4000, 5000, 5000, 4000, 4000}; // 定义每一列宽度
		// 创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建sheet页,并命名
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 创建表格的标题
		ExcelUtil.createHeadTittle(wb, sheet, headString, result.get(0).size() - 1);// （result.get(0).size() - // 1）为表格占用列数，从0开始
		// 创建表头
		ExcelUtil.createThead(wb, sheet, thead, sheetWidth);
		// 填入数据
		ExcelUtil.createTable(wb, sheet, result);

		try {
			// 创建输出流，将数据输出到文件
			FileOutputStream fos = new FileOutputStream(new File(filePath + fileName));// filePath,fileName是如上定义的文件保存路径及文件名
			wb.write(fos);// 写入数据
			fos.close();// 关闭输出流
		} catch (IOException e) {
			e.printStackTrace();
		}
		response.sendRedirect("student/exportsuccess.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
