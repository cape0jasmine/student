package dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Score;
import model.StudentScore;

public class ScoreD {

    private Connection conn = null;

    public boolean insertScore(String student_id,String subject,String score) throws Exception{
        initConnection();
        Date date = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat("ddHHmmssSSS");  
    	String id = formatter.format(date);
        String sql = "insert into score(id,student_id,subject,score) values(?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, id);
        ps.setString(2, student_id);
        ps.setString(3, subject);
        ps.setString(4, score);
        int i = ps.executeUpdate();
        closeConnection();
        return i == 1;
    }
    
    public ArrayList<Score> findWithStuidAndName(String student_id,String subject) throws Exception{
    	ArrayList<Score> scores = new ArrayList<Score>();
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from score where student_id = '" + student_id + "' and subject = '"+subject+"'";
        ResultSet rs = stat.executeQuery(sql);
        getMoreScore(scores,rs);
        closeConnection();
        return scores;
    }

    public boolean deleteScore(String id,String subject) throws Exception{
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "delete from score where student_id='"+id+"' and subject = '"+subject+"'";
        int i = stat.executeUpdate(sql);
        closeConnection();
        return i==1;
    }

    public void updateScoreInfo(String id, String subject, String score) throws Exception{

        initConnection();
        Statement stat = conn.createStatement();
        String sql = "update score set score='"+score+"' where student_id='"+id+"' and subject ='"+subject+"'";
        int i = stat.executeUpdate(sql);
        System.out.println(sql);
        closeConnection();
    }

    public ArrayList<Score> findWithId(String id) throws Exception{
    	ArrayList<Score> scores = new ArrayList<Score>();
        initConnection();
        Statement stat = conn.createStatement();
        String sql = "select * from score where student_id = '" + id + "'";
        ResultSet rs = stat.executeQuery(sql);
        getMoreScore(scores,rs);
        closeConnection();
        return scores;
    }

    public ArrayList<StudentScore> getOnePage(int page, int size) throws Exception{
        ArrayList<StudentScore> al = new ArrayList<>();
        initConnection();
        String sql = "SELECT sc.id,sc.student_id,st.`name`,st.major,sc.`subject`,sc.score FROM score sc,student st WHERE sc.student_id = st.id limit ?, ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page-1)*size);
        ps.setInt(2, size);
        ResultSet rs =  ps.executeQuery();
        getMoresScore(al, rs);
        closeConnection();
        return al;
    }
    
    private void getMoresScore(ArrayList<StudentScore> al, ResultSet rs) throws SQLException {
        while (rs.next()){
        	StudentScore score = new StudentScore();
            score.setId(rs.getString("id"));
            score.setStudent_id(rs.getString("student_id"));
            score.setSubject(rs.getString("subject"));
            score.setScore(rs.getString("score"));
            score.setName(rs.getString("name"));
            score.setMajor(rs.getString("major"));
            al.add(score);
        }
    }

    public int getScoreCount() throws Exception{
        initConnection();
        String sql = "select count(*) from score";
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(sql);
        rs.next();
        int count = rs.getInt(1);
        closeConnection();
        return count;
    }

    private Score getScore(ResultSet rs) throws SQLException {
        Score stu = null;
        if (rs.next()){
            stu = new Score();
            stu.setId(rs.getString("id"));
            stu.setStudent_id(rs.getString("student_id"));
            stu.setSubject(rs.getString("subject"));
            stu.setScore(rs.getString("score"));
        }
        return stu;
    }

    private void getMoreScore(ArrayList<Score> al, ResultSet rs) throws SQLException {
        while (rs.next()){
            Score score = new Score();
            score.setId(rs.getString("id"));
            score.setStudent_id(rs.getString("student_id"));
            score.setSubject(rs.getString("subject"));
            score.setScore(rs.getString("score"));
            al.add(score);
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
