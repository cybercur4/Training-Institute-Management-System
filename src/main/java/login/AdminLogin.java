package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import panel.Conn;

public class AdminLogin {
	public AdminLogin() throws ClassNotFoundException, SQLException{
		Scanner sc=new Scanner(System.in);
		Connection con=Conn.getcon();
	System.out.println("LOGIN\n");
	System.out.println("Enter your email");
	String email=sc.nextLine();
	System.out.println("Enter your password");
	String password=sc.nextLine();
	String query="select aid,name from admin where email=? and password=?";
	PreparedStatement ps=con.prepareStatement(query);
	ps.setString(1,email);
	ps.setString(2, password);
	ResultSet rs=ps.executeQuery();
	rs.next();
	System.out.println("\nWelcome "+rs.getString(2));
	System.out.println("---------------------------\n\n");
}
}
