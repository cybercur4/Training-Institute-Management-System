package panel;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.EmployeeDAO;
import DAO.UserDAO;
import Module.User;

public class UserPanel {
	public UserPanel() throws ClassNotFoundException, SQLException{
		Scanner sc=new Scanner(System.in);
		int d=0;
		while(d==0) {
			System.out.println("1) Show Exisiting Users \n2) Delete User Record \n3) Table Reset \n0) Exit");
			int choice=sc.nextInt();
		switch(choice) {
		case 0:
			d=1;
			break;
		case 1:
			UserDAO.get();
			break;
		case 2:
			System.out.println("\n\nEnter the User ID to be deleted");
			sc.nextLine();
			int sid=sc.nextInt();
			User u=new User(sid);
			UserDAO.delete(u);
			System.out.println("User Record Deleted!\n\n");
			break;
		case 3:
			UserDAO.restart();
			System.out.println("\n\nTable Resetted!\n\n");
			break;
		default:
			System.out.println("\n\nWRONG CHOICE. TRY AGAIN\n\n");
		}
		}
	}
}
