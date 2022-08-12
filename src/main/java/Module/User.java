package Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import DAO.CourseDAO;
import DAO.EmployeeDAO;
import DAO.UserDAO;
import login.UserLogin;
import main.TIMS;
import panel.Conn;
import panel.CoursePanel;
import panel.EmployeePanel;
import main.TIMS;

public class User {
	public String name;
	public String email;
	public String Course_Name;
	public int budget;
	public int sid;
	public String password;
	public User(int sid) {
		this.sid=sid;
	}
	public User(String name,String email,String Course_Name,String password,int budget) {
		this.name=name;
		this.email=email;
		this.Course_Name=Course_Name;
		this.budget=budget;
		this.password=password;
	}
	public User() throws ClassNotFoundException, SQLException{
		Scanner sc=new Scanner(System.in);
		Connection con=Conn.getcon();
		System.out.println("::::: WELCOME TO SOFTECH COMPUTERS :::::\n\n");
		System.out.println("Enter Your Choice!");
		int d=0;
		while(d==0) {
			System.out.println("1) Login\n2) Registration\n0) Exit");
			int choice=sc.nextInt();
		switch(choice) {
		case 0:
			d=1;
			break;
		case 1:
			new UserLogin();
			break;
		case 2:
			System.out.println("\n\n-----------------------------------");
			System.out.println("REGISTRATION");
			System.out.println("-----------------------------------\n\n");
			System.out.println("Enter Name");
			sc.nextLine();
			String name=sc.nextLine();
			System.out.println("Enter Email");
			String email=sc.nextLine();
			System.out.println("Select a course ID from available courses!\n");
			CourseDAO.get();
			int cid=sc.nextInt();
			String query="select Course_Name from courses1 where cid=?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, cid);
			ResultSet rs=ps.executeQuery();
			rs.next();
			String Course_Name=rs.getString(1);
			sc.nextLine();
			System.out.println("Enter your password");
			String password=sc.nextLine();
			System.out.println("Enter Budget");
			int budget=sc.nextInt();
			User u=new User(name,email,Course_Name,password,budget);
			UserDAO.insert(u);
			break;
		default:
			System.out.println("WRONG CHOICE. TRY AGAIN\n\n");
		}
		}
	}
}
