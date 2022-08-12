package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Module.User;
import panel.Conn;

public class UserDAO {
	public static void insert(User u) throws ClassNotFoundException, SQLException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query1="select name from employee where course=? and fees=(select max(fees) from employee where course=? and fees<=?)";
		ps=con.prepareStatement(query1);
		ps.setString(1, u.Course_Name);
		ps.setString(2,u.Course_Name);
		ps.setInt(3, u.budget);
		ResultSet rs=ps.executeQuery();
		rs.next();
		String trainer=rs.getString(1);
		String query2="insert into user(name,email,course,password,trainer)values(?,?,?,?,?)";
		ps=con.prepareStatement(query2);
		ps.setString(1, u.name);
		ps.setString(2, u.email);
		ps.setString(3, u.Course_Name);
		ps.setString(4, u.password);
		ps.setString(5, trainer);
		ps.executeUpdate();
		System.out.println("\nCongratulations! Youre now a registered user!");
		System.out.println("\nYour Login Credentials");
		System.out.println("\n--------------------------------");
		System.out.println("Email: "+u.email);
		System.out.println("Password: "+u.password);
		System.out.println("\n--------------------------------");
		System.out.println("\nAllocated Trainer: "+trainer+"\n");
	}
	public static void get() throws ClassNotFoundException, SQLException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="select * from user order by Sid";
		ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		System.out.println("\n\n--------------------------------------------------------------------");
		System.out.println("User ID || Name || Email || Course || Password || Trainer");
		System.out.println("--------------------------------------------------------------------");
		while(rs.next()) {
			System.out.print(rs.getInt(1)+" || ");
			System.out.print(rs.getString(2)+" || ");
			System.out.print(rs.getString(3)+" || ");
			System.out.print(rs.getString(4)+" || ");
			System.out.print(rs.getString(5)+" || ");
			System.out.println(rs.getString(6));
		}
		System.out.println("--------------------------------------------------------------------");
	}
	public static void delete(User u) throws ClassNotFoundException, SQLException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="delete from user where Sid=?";
		ps=con.prepareStatement(query);
		ps.setInt(1,u.sid);
		ps.executeUpdate();
	}
	public static void restart() throws ClassNotFoundException, SQLException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="truncate table user;";		
		ps=con.prepareStatement(query);
		ps.executeUpdate();

}
	public static void profile(int sid) throws ClassNotFoundException, SQLException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="select * from user where sid=?";
		ps=con.prepareStatement(query);
		ps.setInt(1, sid);
		ResultSet rs=ps.executeQuery();
		rs.next();
		System.out.println("-------------------------------------");
		System.out.println("Name: "+rs.getString(2));
		System.out.println("Enrollment ID: "+sid);
		System.out.println("Email: "+rs.getString(3));
		System.out.println("Course: "+rs.getString(4));
		System.out.println("Trainer Allocated: "+rs.getString(6));
		System.out.println("-------------------------------------");
	}
}
