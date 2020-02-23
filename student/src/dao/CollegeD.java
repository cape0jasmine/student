package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.College;

public class CollegeD {
	  private Connection conn = null;

	  private void initConnection() throws Exception {
	        Class.forName("com.mysql.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?characterEncoding=utf-8&serverTimezone=UTC&useSSL=false", "root", "123456");
	    }

	    private void closeConnection() throws Exception{
	        conn.close();
	    }
	    
	    
	    public int getCount() throws Exception{
	        initConnection();
	        String sql = "select count(*) from college";
	        Statement stat = conn.createStatement();
	        ResultSet rs = stat.executeQuery(sql);
	        rs.next();
	        int count = rs.getInt(1);
	        closeConnection();
	        return count;
	    }
	    
	    public ArrayList<College> getAll(int page, int size) throws Exception{
	    	ArrayList<College> colleges = new ArrayList<College>();
	        initConnection();
	        
	        String sql = "select * from college  limit ?,?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, (page-1)*size);
	        ps.setInt(2, size);
	        ResultSet rs =  ps.executeQuery();
	        getCollege(colleges,rs);
	        closeConnection();
	        return colleges;
	    }
	    
	    private void getCollege(ArrayList<College> al, ResultSet rs) throws SQLException {
	        while (rs.next()){
	        	College college = new College();
	        	college.setId(rs.getString("id"));
	        	college.setName(rs.getString("name"));
	            al.add(college);
	        }
	    }
	    
	    public void deleteColl(String id) throws Exception {
	    	 initConnection();
		     Statement stat = conn.createStatement();
		     String sql1 = "DELETE FROM major WHERE academy_id='"+id+"'";
		     stat.executeUpdate(sql1);
		     String sql = "delete from college where id='"+id+"'";
		     stat.executeUpdate(sql);
		     closeConnection();
	    }
	    
	    public void updateCollInfo(String id, String name) throws Exception{
	        initConnection();
	        String sql = "update college set name=? where id=?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, name);
	        ps.setString(2, id);
	        ps.executeUpdate();
	        closeConnection();
	    }
	    
	    public void insertColl(String name) throws Exception{
	        initConnection();
	        String sql = "insert into college(name) values(?)";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, name);
	        ps.executeUpdate();
	        closeConnection();
	    }
}
