package login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DAO.UserDAO;
import Module.Admin;
import Module.User;
import panel.Conn;
public class UserLogin {
	public UserLogin() throws ClassNotFoundException, SQLException{
		Scanner sc=new Scanner(System.in);
		Connection con=Conn.getcon();
		System.out.println("LOGIN\n");
		System.out.println("Enter your email");
		String email=sc.nextLine();
		System.out.println("Enter your password");
		String password=sc.nextLine();
		String query="select Sid,name from user where email=? and password=?";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setString(1,email);
		ps.setString(2, password);
		ResultSet rs=ps.executeQuery();
		rs.next();
		int e;
		try {
		System.out.println("\nWelcome "+rs.getString(2));
		System.out.println("---------------------------\n\n");
		e=0;
		}
		catch(SQLException f) {
			System.out.println("Invalid Credentials, Please Try again!\n");
			e=1;
		}
		while(e==0) {
			System.out.println("1) Profile \n2)Check Your Status  \n0) Logout");
			int choice1=sc.nextInt();
		switch(choice1) {
		
		case 0:
			e=1;
			System.out.println("Successfully Logged Out!\n");
			break;
		case 1:
			UserDAO.profile(rs.getInt(1));
			break;
		case 2:
			new Admin();
		break;
	}
}
	}
}
