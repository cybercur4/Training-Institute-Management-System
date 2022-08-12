package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;

import DAO.CourseDAO;
import panel.Conn;
import panel.CoursePanel;
import panel.EmployeePanel;

public class EmployeeDAO {
	public static void insert(EmployeePanel e) throws SQLException, ClassNotFoundException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="insert into employee(name,email,password,course,fees)values(?,?,?,?,?)";	
		ps=con.prepareStatement(query);
		ps.setString(1, e.name);
		ps.setString(2, e.email);
		ps.setString(3, e.password);
		ps.setString(4, e.Course_Name);
		ps.setInt(5, e.fees);
		CoursePanel c=new CoursePanel(e.Course_Name);
		CourseDAO.insert(c);
		try {
		ps.executeUpdate();
		System.out.println("EmployeeLogin Details INSERTED!\n\n");
		}
		catch(SQLIntegrityConstraintViolationException f) {
			System.out.println("\nUser Record Associated with Email Already Available");
		}
		catch(SQLSyntaxErrorException g) {
			System.out.println("\nTable 'employee' unavailable");
		}
	}
	public static void get() throws ClassNotFoundException,SQLException{
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="select * from employee order by Eid";
		ps=con.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		System.out.println("\n\n--------------------------------------------------------------------");
		System.out.println("EmployeeLogin ID || EmployeeLogin Name || EmployeeLogin Email || Password || Course || Fees");
		System.out.println("--------------------------------------------------------------------");
		while(rs.next()) {
			System.out.print(rs.getInt(1)+" || ");
			System.out.print(rs.getString(2)+" || ");
			System.out.print(rs.getString(3)+" || ");
			System.out.print(rs.getString(4)+" || ");
			System.out.print(rs.getString(5)+" || ");
			System.out.println(rs.getInt(6)+" || ");
		}
		System.out.println("--------------------------------------------------------------------");
	}
	public static void delete(EmployeePanel e) throws SQLException, ClassNotFoundException{
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String course=e.Course_Name;
		String query="delete from employee where Eid=?";
		ps=con.prepareStatement(query);
		ps.setInt(1,e.eid);
		ps.executeUpdate();
		String query2="select exists(select * from employee where course=?);";
		ps=con.prepareStatement(query2);
		ps.setString(1, course);
		ResultSet rs=ps.executeQuery();
		rs.next();
		if(rs.getInt(1)==0) {
			String query3="delete from courses1 where course=?;";
			ps=con.prepareStatement(query3);
			ps.setString(1, course);
		}
	}
	public static void restart() throws SQLException, ClassNotFoundException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="truncate table employee;";		
		ps=con.prepareStatement(query);
		try {
		ps.executeUpdate();
		CourseDAO.restart();
		}
		catch(SQLSyntaxErrorException e) {
			String query2="create table employee(Eid int auto_increment,name varchar(40),email varchar(40),course varchar(20),fees double,unique key(email),primary key(Eid));";
			ps=con.prepareStatement(query2);
			ps.executeUpdate();
		}
		
	}
	public static void profile(int eid) throws ClassNotFoundException, SQLException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="select * from employee where eid=?";
		ps=con.prepareStatement(query);
		ps.setInt(1, eid);
		ResultSet rs=ps.executeQuery();
		rs.next();
		System.out.println("-------------------------------------");
		System.out.println("Name: "+rs.getString(2));
		System.out.println("Employee ID: "+eid);
		System.out.println("Email: "+rs.getString(3));
		System.out.println("Course: "+rs.getString(5));
		System.out.println("-------------------------------------");
	}
	public static void showstudents(String course) throws ClassNotFoundException, SQLException {
		Connection con=Conn.getcon();
		PreparedStatement ps=null;
		String query="select Sid,Name,Email from user where course=? order by Sid ";
		ps=con.prepareStatement(query);
		ps.setString(1, course);
		ResultSet rs=ps.executeQuery();
		System.out.println("\n\n------------------------------------");
		System.out.println("User ID || Name || Email ||");
		System.out.println("------------------------------------");
		while(rs.next()) {
			System.out.print(rs.getInt(1)+" || ");
			System.out.print(rs.getString(2)+" || ");
			System.out.println(rs.getString(3)+" || ");
		}
		System.out.println("--------------------------------------------------------------------");
	}
}
