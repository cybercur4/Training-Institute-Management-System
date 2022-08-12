package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;
import DAO.CourseDAO;
import panel.Conn;
import panel.CoursePanel;
public class CourseDAO {
	public static void insert(CoursePanel c) throws SQLException, ClassNotFoundException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="insert into courses1(Course_Name)values(?)";		
		ps=con.prepareStatement(query);
		ps.setString(1, c.Course_Name);
		try {
		ps.executeUpdate();
		}
		catch(SQLIntegrityConstraintViolationException e) {
		}
		catch(SQLSyntaxErrorException e) {
			System.out.println("Table 'courses1' unavailable");
		}
	}
	public static void get() throws ClassNotFoundException,SQLException{
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="select * from courses1 order by cid";
		ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		System.out.println("-------------------------------");
		System.out.println("Course ID || Course Name || ");
		System.out.println("-------------------------------");
		while(rs.next()) {
			System.out.print(rs.getInt(1)+" || ");
			System.out.println(rs.getString(2)+" || ");
		}
		System.out.println("-------------------------------");
	}
	public static void delete(CoursePanel c) throws SQLException, ClassNotFoundException{
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="delete from courses1 where cid=?";
		String query2="delete from employee where course=(select Course_Name from courses1 where cid=?);";
		ps=con.prepareStatement(query2);
		ps.setInt(1,c.cid);
		ps.executeUpdate();
		ps=con.prepareStatement(query);
		ps.setInt(1,c.cid);
		ps.executeUpdate();
	}
	public static void restart() throws SQLException, ClassNotFoundException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="truncate table courses1;";		
		ps=con.prepareStatement(query);
		try {
		ps.executeUpdate();
		//DAO.EmployeeDAO.restart();
		}
		catch(SQLSyntaxErrorException e) {
			String query2="create table courses1(cid int auto_increment,Course_Name varchar(20),Primary key(cid),Unique key(Course_name));";
			ps=con.prepareStatement(query2);
			ps.executeUpdate();
		}
		
	}
}

