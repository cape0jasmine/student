package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Field;
import com.mysql.jdbc.ResultSetMetaData;

import model.Major;
import model.Student;
import util.DBUtil;

public class StudentD {

    Connection conn = null;

    public Student checkAccount(String user, String password) throws Exception {
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from student where id = '" + user + "' and password = '" + password + "'";
        ResultSet rs = stat.executeQuery(sql);
        Student stu = getStudent(rs);
        closeConnection();
        return stu;
    }
    
    public List<Student> getAllStu()throws Exception{
    	  initConnection();
          Statement stat = conn.createStatement();
          String sql = "select * from student";
          ResultSet rs = stat.executeQuery(sql);
          List<Student> stu = getStuList(conn,sql);
          closeConnection();
          return stu;
    }
    
    public List getStuList(Connection conn,String sql) throws SQLException{
    	 try {  
             List<Object> list=DBUtil.executeQuery(conn, sql, Student.class);  
             return list;
         } catch (SQLException e) {  
             e.printStackTrace();  
         } 
    	 return null;
    }

    public Student findWithId(String id) throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from student where id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        Student stu = getStudent(rs);
        closeConnection();
        return stu;
    }

    public ArrayList<Student> findWithName(String name) throws Exception{
        ArrayList<Student> al = new ArrayList<>();
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from student where name = '" + name + "'";
        ResultSet rs = stat.executeQuery(sql);
        getMoreStudent(al, rs);
        closeConnection();
        return al;
    }

    public boolean insertStudent(String academy, String major, String id, String name, String sex) throws Exception{
        initConnection();
        String sql = "insert into student(academy, major, id, name, sex) values(?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, academy);
        ps.setString(2, major);
        ps.setString(3, id);
        ps.setString(4, name);
        ps.setString(5, sex);
        int i = ps.executeUpdate();
        closeConnection();
        return i == 1;
    }

    
    public ArrayList<Student> getAllStudent() throws Exception {
    	ArrayList<Student> colleges = new ArrayList<Student>();
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "SELECT * from student";
        ResultSet rs = stat.executeQuery(sql);
        getStu(colleges,rs);
        closeConnection();
        return colleges;
    }
    
    private void getStu(ArrayList<Student> al, ResultSet rs) throws SQLException {
        while (rs.next()){
        	Student student = new Student();
        	student.setId(rs.getString("id"));
        	student.setName(rs.getString("name"));
            al.add(student);
        }
    }
    
    public boolean deleteStudent(String id) throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from student where id='"+id+"'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i==1;
    }

    public ArrayList<Student> getOnePage(int page, int size) throws Exception{
        ArrayList<Student> al = new ArrayList<>();
        initConnection();
        String sql = "SELECT * FROM student limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page-1)*size);
        ps.setInt(2, size);
        ResultSet rs =  ps.executeQuery();
        getMoreStudent(al, rs);
        closeConnection();
        return al;
    }

    public int getStudentCount() throws Exception{
        initConnection();
        String sql = "select count(*) from student";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    public void updateStudentInfo(String id, String academy,String major,String name, String sex) throws Exception{

        initConnection();
        String sql = "update student set academy=?,major=?,name=?, sex=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, academy);
        ps.setString(2, major);
        ps.setString(3, name);
        ps.setString(4, sex);
        ps.setString(5, id);
        ps.executeUpdate();
        closeConnection();
    }

    public void updateStudentSecurity(String id, String email, String password) throws Exception{

        initConnection();
        String sql = "update student set password=?, email=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, password);
        ps.setString(2, email);
        ps.setString(3, id);
        ps.executeUpdate();
        closeConnection();
    }

    private Student getStudent(ResultSet rs) throws SQLException {
        Student stu = null;
        if (rs.next()){
            stu = new Student();
            stu.setId(rs.getString("id"));
            stu.setAcademy(rs.getString("academy"));
            stu.setMajor(rs.getString("major"));           
            stu.setName(rs.getString("name"));
            stu.setSex(rs.getString("sex"));
            stu.setPassword(rs.getString("password"));            
            stu.setEmail(rs.getString("email"));
        }
        return stu;
    }

    private void getMoreStudent(ArrayList<Student> al, ResultSet rs) throws SQLException {
        while (rs.next()){
            Student stu = new Student();
            stu.setId(rs.getString("id"));
            stu.setAcademy(rs.getString("academy"));
            stu.setMajor(rs.getString("major"));         
            stu.setName(rs.getString("name"));
            stu.setSex(rs.getString("sex"));
            stu.setPassword(rs.getString("password"));           
            stu.setEmail(rs.getString("email"));      
            al.add(stu);
        }
    }

    private void initConnection() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false", "root", "123456");
    }

    private void closeConnection() throws Exception{
        conn.close();
    }
}
