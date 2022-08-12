package panel;

import java.sql.SQLException;
import java.util.Scanner;

import DAO.CourseDAO;
import DAO.EmployeeDAO;

public class EmployeePanel {
	public int eid;
	public String name;
	public String email;
	public String password;
	public String Course_Name;
	public int fees;
	public EmployeePanel() throws ClassNotFoundException, SQLException{
		Scanner sc=new Scanner(System.in);
		int d=0;
		while(d==0) {
			System.out.println("1) Insert New Employee Entry \n2) Show Exisiting Employees \n3) Delete EmployeeLogin Record \n4) Table Reset \n0) Exit");
			int choice=sc.nextInt();
		switch(choice) {
		case 0:
			d=1;
			break;
		case 1:
			System.out.println("\n\n-----------------------------------");
			System.out.println("EMPLOYEE REGISTRATION");
			System.out.println("-----------------------------------\n\n");
			System.out.println("Enter Name");
			sc.nextLine();
			String name=sc.nextLine();
			System.out.println("Enter Email");
			String email=sc.nextLine();
			System.out.println("Enter Password");
			String password=sc.nextLine();
			System.out.println("Enter Course");
			String Course_Name=sc.nextLine();
			System.out.println("Enter Fees");
			int fees=sc.nextInt();
			EmployeePanel e=new EmployeePanel(name,email,password,Course_Name,fees);
			EmployeeDAO.insert(e);
			break;
		case 2:
			EmployeeDAO.get();
			break;
		case 3:
			System.out.println("\n\nEnter the Employee ID to be deleted");
			sc.nextLine();
			int eid=sc.nextInt();
			EmployeePanel e2=new EmployeePanel(eid);
			EmployeeDAO.delete(e2);
			System.out.println("Employee Record Deleted!\n\n");
			break;
		case 4:
			EmployeeDAO.restart();
			System.out.println("\n\nTable Resetted!\n\n");
			break;
		default:
			System.out.println("\n\nWRONG CHOICE. TRY AGAIN\n\n");
		}
		}
	}
	EmployeePanel(String name,String email,String password,String Course_Name,int fees){
		this.name=name;
		this.email=email;
		this.password=password;
		this.Course_Name=Course_Name;
		this.fees=fees;
	}
	EmployeePanel(int eid){
		this.eid=eid;
	}
}
