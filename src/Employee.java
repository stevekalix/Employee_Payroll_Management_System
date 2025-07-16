    import java.io.DataInputStream;
	import java.sql.*;
import java.util.Scanner;

import Controller.Bankdetails;
import Controller.Emp;
import Controller.Rolldepartmentinsert;
 public class Employee {
	    	public Connection c;
	    	PreparedStatement p;
			static Scanner sc=new Scanner(System.in);
			DataInputStream dis = new DataInputStream(System.in);
			public Employee()
	    	{
	    		try{
	    		Class.forName("oracle.jdbc.driver.OracleDriver");
	    		c = DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-GLS1CGU:1521:XE","mani","mani");
	    		System.out.println(c.toString()+"Connected Sucessfully!!!");
	    		}
	    		catch(ClassNotFoundException e){
	    			e.printStackTrace();
	    		}
	    		catch (Exception e) {
	    			e.printStackTrace();
	    			}
				}
				public Connection getConnection(){
					return c;
				}
				public static void main(String[] args) {
				Employee employee=new Employee();
	            Emp emp=new Emp(employee.getConnection());   //class one
			    Rolldepartmentinsert rol=new Rolldepartmentinsert(employee.getConnection()); // class two
				Bankdetails bankdetails=new Bankdetails(employee.getConnection());

			 int choice;
             do{
			 System.out.println("New Employee!!");
			 System.out.println("1.New Employee fill deatils");
			 System.out.println("2.Employee band details");
			 System.out.println("0.Exit");
             choice=sc.nextInt();
             switch (choice) {
				case 1:{
					  rol.insertdep();
					  int choice1;
					  do{
						System.out.println("EMPLOYEE DETAILS");
						System.out.println("1.Insert Employee details");
						System.out.println("2.Show Employe deatils");
						System.out.println("3.Employee bank details");
						System.out.println("0.Exit");
						System.out.println("Enter your choice!!");
						choice1=sc.nextInt();
						switch (choice1) {
							case 1:
							 emp.InsertEmployee();
							 break;
							 case 2:
							 System.out.println("Fastly update this from!!");
							 break;
							 case 3:
							 System.out.println("EMPLOYEE BANK DETAILS!!");
							 bankdetails.insertBankEmployee();
							 break;
							default:
							break;
						}
					  }while(choice1!=0);
					  break;
				}
				case 2:{
				             System.out.println("EMPLOYEE BANK DETAILS!!");
							 bankdetails.insertBankEmployee();
							 break;
				}
				default:
					break;
			 } 
			 
			 }while (choice!=0); 
	    }
	}     
