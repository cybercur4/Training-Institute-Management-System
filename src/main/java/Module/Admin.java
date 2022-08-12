package Module;

import java.sql.SQLException;
import java.util.Scanner;

import login.AdminLogin;
import panel.CoursePanel;
import panel.EmployeePanel;
import panel.UserPanel;
import main.TIMS;

public class Admin {
	public Admin() throws ClassNotFoundException, SQLException {
		Scanner sc=new Scanner(System.in);
		int d;
		try {
		new AdminLogin();
		d=0;
		System.out.println("ENTER YOUR CHOICE\n");
		}
		catch(SQLException e) {
			System.out.println("Invalid Credentials, Please Try Again!");
			d=1;
		}
	while(d==0) {
		System.out.println("1) Courses Panel \n2) Employee Panel \n3) User Panel\n0) Log out");
		int choice=sc.nextInt();
		switch(choice) {

		case 0:
			d=1;
			System.out.println("Successfully Logged Out!");
			break;
		case 1:
			new CoursePanel();
			break;
		case 2:
			new EmployeePanel();
			break;
		case 3:
			new UserPanel();
			break;
		default:
			System.out.println("WRONG CHOICE. TRY AGAIN");
		}
}
	}
}
