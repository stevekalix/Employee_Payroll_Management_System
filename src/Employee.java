    import java.io.DataInputStream;
	import java.sql.*;
import java.util.Scanner;

import Controller.Bankdetails;
import Controller.Emp;
import Controller.Payroll;
import Controller.Rolldepartment;
 public class Employee {
	    	public Connection c;
	    	PreparedStatement p;
			static Scanner sc=new Scanner(System.in);
			DataInputStream dis = new DataInputStream(System.in);
			public Employee()
	    	{
	    		 try{
                         Class.forName("com.mysql.cj.jdbc.Driver");
                         c = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee__database", "root", "mani97888#");
                         System.out.println("Connected !!  "+c.toString());
                       } catch (SQLException e) {
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
			    Rolldepartment rol=new Rolldepartment(employee.getConnection()); // class two
				Bankdetails bankdetails=new Bankdetails(employee.getConnection());  //class three
				Payroll payroll=new Payroll(employee.getConnection()); // class four
			 int choice;
             do{
			 System.out.println("New Employee!!");
			 System.out.println("1.New Employee fill deatils");
			 System.out.println("2.Employee bank details");
			 System.out.println("3.Show employee datails");
			 System.out.println("4.Show particular Employee");
			 System.out.println("5.Employee payment datails");
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
						System.out.println("Enter your choice!!:");
						sc.nextLine();
						choice1=sc.nextInt();
						switch (choice1) {
							case 1:
							 emp.InsertEmployee();
							 break;
							 case 2:
							 System.out.println("Employee  details");
							 emp.show();
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
				case 3:{
					System.out.println("All Employee details");
					System.out.println("_____________________");
					emp.show();
					break;
				}
				case 4:{
					System.out.println("Employee Details!!");
					emp.showEmployeeDetails();
				}
				 case 5:{
							 int cho = 0;
							 do{
								 System.out.println("Employee payment datails");
						      	 System.out.println("1.Fill payment details!!");
						    	 System.out.println("2.Show payment details");
							     System.out.println("0.Exit");
								 
								 System.out.println("Enter your choice:");
								 cho=sc.nextInt();

								 switch (cho) {
									case 1:
									payroll.employeepayrollinsert();
									break;
									case 2:
									payroll.showpayRoll();
						            break;
									default:
									break;
								 }
							 }while(cho!=0);
							 break;
							}
				default:
					break;
			 } 	 
			 }while (choice!=0); 
	    }
	}     
