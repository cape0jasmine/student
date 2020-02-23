package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Major;

public class MajorD {
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
	        String sql = "select count(*) from major";
	        Statement stat = conn.createStatement();
	        ResultSet rs = stat.executeQuery(sql);
	        rs.next();
	        int count = rs.getInt(1);
	        closeConnection();
	        return count;
	    }
	    
	    public void addMajor(String mid,String name,String mname)throws Exception {
	    	 initConnection();
	    	
	         String sql = "INSERT INTO major(`name`,academy_id) VALUES (?,?)";
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ps.setString(1, mname);
	         ps.setString(2, mid);
	         ps.executeUpdate();
	        
	         closeConnection();
	    }
	    
	    public ArrayList<Major> getMajorAndColl(String name, String mname)  throws Exception{
	    	ArrayList<Major> colleges = new ArrayList<Major>();
	    	 initConnection();
		        Statement stat = conn.createStatement();
		        String sql = "SELECT co.id,co.name,m.id mid,m.`name` mname from college co,major m where co.`name` = '"+name+"' and m.`name` = '"+mname+"'";
		        ResultSet rs = stat.executeQuery(sql);
		        getCollege(colleges,rs);
		        closeConnection();
		        return colleges;
	    }
	    
	    public void updateMajor(String id,String name,String mid)  throws Exception{
	    	 initConnection();
		        String sql = "UPDATE major SET `name` = ?, academy_id = ? WHERE id = ?";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setString(1, name);
		        ps.setString(2, mid);
		        ps.setString(3, id);
		        ps.executeUpdate();
		        closeConnection();
	    }
	    
	   
	    public Major getID(String name)  throws Exception{
	    	 initConnection();
		     Statement stat = conn.createStatement();
	         String sql = "SELECT * from college where name = '"+name+"' ORDER BY name";
	         ResultSet rs = stat.executeQuery(sql);
	         Major major = getCollegeId(rs);
	          closeConnection();
	         return major;
	    }
	    
	    
	    private Major getCollegeId(ResultSet rs) throws SQLException {
	    	Major major = null;
	        if (rs.next()){
	        	major = new Major();
	        	major.setId(rs.getString("id"));
	        }
	        return major;
	    }
	    
	    
	    public ArrayList<Major> getAlls(int page, int size) throws Exception{
	    	ArrayList<Major> colleges = new ArrayList<Major>();
	        initConnection();
	        String sql = "select co.id,co.name,m.id mid,m.`name` mname from college co,major m WHERE co.id = m.academy_id limit ?,?";
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setInt(1, (page-1)*size);
	        ps.setInt(2, size);
	        ResultSet rs =  ps.executeQuery();
	        getCollege(colleges,rs);
	        closeConnection();
	        return colleges;
	    }
	    
	    public ArrayList<Major> getAll() throws Exception{
	    	ArrayList<Major> colleges = new ArrayList<Major>();
	        initConnection();
	        Statement stat = conn.createStatement();
	        String sql = "select co.id,co.name,m.id mid,m.`name` mname from college co,major m WHERE co.id = m.academy_id";
	        ResultSet rs = stat.executeQuery(sql);
	       /* getCollegec(colleges,rs);*/
	        getCollege(colleges,rs);
	        closeConnection();
	        return colleges;
	    }
	    
	    public ArrayList<Major> getAllc() throws Exception{
	    	ArrayList<Major> colleges = new ArrayList<Major>();
	        initConnection();
	        Statement stat = conn.createStatement();
	        String sql = "SELECT * from college ORDER BY name";
	        ResultSet rs = stat.executeQuery(sql);
	        getCollegec(colleges,rs);
	        closeConnection();
	        return colleges;
	    }
	    
	    private void getCollege(ArrayList<Major> al, ResultSet rs) throws SQLException {
	        while (rs.next()){
	        	Major major = new Major();
	        	major.setId(rs.getString("id"));
	        	major.setName(rs.getString("name"));
	        	major.setMid(rs.getString("mid"));
	        	major.setMname(rs.getString("mname"));
	            al.add(major);
	        }
	    }
	    
	    private void getCollegec(ArrayList<Major> al, ResultSet rs) throws SQLException {
	        while (rs.next()){
	        	Major major = new Major();
	        	major.setId(rs.getString("id"));
	        	major.setName(rs.getString("name"));
	            al.add(major);
	        }
	    }
}
