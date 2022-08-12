package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Module.Admin;
import login.EmployeeLogin;
import Module.User;


public class TIMS {
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		Scanner sc=new Scanner(System.in);
		int e=0;
		while(e==0) {
			System.out.println("1) User \n2 Employee \n3) Admin \n0) Exit");
			int choice1=sc.nextInt();
		switch(choice1) {
		
		case 0:
			e=1;
			System.out.println("\n\nSYSTEM SHUTTING DOWN!");
			break;
		case 1:
			new User();
			break;
		case 2: 
			new EmployeeLogin();
			break;
		case 3:
			new Admin();
		break;
		default:
			System.out.println("Wrong Choice! Try Again\n");
	}
}
	}
}
