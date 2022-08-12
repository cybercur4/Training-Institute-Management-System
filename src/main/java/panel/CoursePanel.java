package panel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

import DAO.CourseDAO;

public class CoursePanel{
	public String Course_Name;
	public int cid;
	public CoursePanel(String Course_Name) throws ClassNotFoundException, SQLException{
		this.Course_Name=Course_Name;
	}
	public CoursePanel(int cid){
		this.cid=cid;
	}

	public CoursePanel() throws ClassNotFoundException, SQLException{
		Scanner sc=new Scanner(System.in);
		int d=0;
		while(d==0) {
			System.out.println("1) Show Available Courses\n2) Delete a course \n3) Table Reset \n0) Exit");
			int choice=sc.nextInt();
		switch(choice) {
		case 0:
			d=1;
			break;
		case 1:
			CourseDAO.get();
			break;
		case 2:
			System.out.println("Enter the course ID to be deleted");
			sc.nextLine();
			int cid=sc.nextInt();
			CoursePanel c2=new CoursePanel(cid);
			CourseDAO.delete(c2);
			System.out.println("Course Deleted!\n\n");
			break;
		case 3:
			CourseDAO.restart();
			System.out.println("Table Resetted!\n\n");
			break;
		default:
			System.out.println("WRONG CHOICE. TRY AGAIN\n\n");
		}
		}
	}


	

}