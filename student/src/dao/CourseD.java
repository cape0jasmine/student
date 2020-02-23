package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Course;
import model.Student;

public class CourseD {
	private Connection conn = null;

	 public ArrayList<Course> getCourses() throws Exception {
		 ArrayList<Course> colleges = new ArrayList<Course>();
	        initConnection();
	        Statement stat = conn.createStatement();
	        String sql = "SELECT * from course";
	        ResultSet rs = stat.executeQuery(sql);
	        getStu(colleges,rs);
	        closeConnection();
	        return colleges;
	 }
	 
	 private void getStu(ArrayList<Course> al, ResultSet rs) throws SQLException {
	        while (rs.next()){
	        	Course course = new Course();
	        	course.setCid(rs.getString("cid"));
	        	course.setCname(rs.getString("cname"));
	        	course.setCgrade("cgrade");
	            al.add(course);
	        }
	    }
	
	
	// 增
	public int addCourse(String cname, String cgrade) throws Exception {
		initConnection();
		String sql = "insert into course(cname,cgrade) values(?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cname);
		ps.setString(2, cgrade);
		int i = ps.executeUpdate();
		closeConnection();
		return i;
	}

	// 删
	public int deleteCourse(String cid) throws Exception {
		initConnection();
		String sql = "delete course where cid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cid);
		int i = ps.executeUpdate();
		closeConnection();
		return i;
	}

	// 改
	public int updateCourse(String cid, String cname, String cgrade) throws Exception {
		initConnection();
		String sql = "update course set cname=?,cgrade=? where cid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, cname);
		ps.setString(2, cgrade);
		ps.setString(3, cid);
		int i = ps.executeUpdate();
		closeConnection();
		return i;
	}

	// 查
	public ArrayList<Course> selectCourse(String cid, String cname, String cgrade) throws Exception {
		initConnection();
		ArrayList<Course> list = new ArrayList<Course>();
		String sql = "select * from course where 1=1 ";
		if (cid != null && cid != "") {
			sql += " and cid like '%" + cid + "%'";
		}
		if (cname != null && cname != "") {
			sql += " and cname like '%" + cname + "%'";
		}
		if (cgrade != null && cgrade != "") {
			sql += " and cgrade = " + cgrade;
		}
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		while(rs.next()) {
			Course c = new Course();
			c.setCid(rs.getString("cid"));
			c.setCname(rs.getString("cname"));
			c.setCgrade(rs.getString("cgrade"));
			list.add(c);
		}
		closeConnection();
		return list;
	}

	private void initConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/student?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false", "root",
				"123456");
	}

	private void closeConnection() throws Exception {
		conn.close();
	}

}
