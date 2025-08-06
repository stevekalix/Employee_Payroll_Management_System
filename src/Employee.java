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
                         c = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_database", "root", "root123");
                         System.out.println("Connected   "+c.toString());
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
			System.out.println("======EMPLOYEE DETAILS====================================================================================================================================");
            System.out.println("1. NEW EMPLOYEE OFFICE DETAILS");
			System.out.println("2. EMPLOYEE PERSONAL DETAILS");
            System.out.println("3. EMPLOYEE BANK DETAILS");
            System.out.println("4. SHOW EMPLOYEE DETAILS");
            System.out.println("5. SHOW PARTICULAR EMPLOYEE INFORMATION");
            System.out.println("6. EMPLOYEE PAYMENT DETAILS");
            System.out.println("0. EXIT");
			System.out.println(" ");
            System.out.println("Enter your choice:");
             choice=sc.nextInt();
             switch (choice) {
				case 1:{
					  rol.insertdep();
					  break;
				}
				case 2:{
					int choice1;
					do{
					System.out.println("EMPLOYEE DETAILS");
                    System.out.println("1. INSERT EMPLOYEE DETAILS");
                    System.out.println("2. SHOW EMPLOYEE DETAILS");
                    System.out.println("3. EMPLOYEE BANK DETAILS");
                    System.out.println("0. EXIT");
					System.out.println(" ");
						System.out.println("Enter your choice!!:");
						sc.nextLine();
						choice1=sc.nextInt();
						switch (choice1) {
							case 1:
							 emp.InsertEmployee();
							 break;
							 case 2:
							 System.out.println("ALL EMPLOYEE DETAILS!!");
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
				case 3:{
				     System.out.println("EMPLOYEE BANK DETAILS!!");
					 bankdetails.insertBankEmployee();
					 break;
				}
				case 4:{
					System.out.println("ALL EMPLOYEE DETAILS!!");
					System.out.println("_____________________");
					emp.show();
					break;
				}
				case 5:{
					System.out.println("EMPLOYEE DETAILS!!");
					emp.showEmployeeDetails();
					break;
				}
				 case 6:{
							 int cho = 0;
							 do{
								 System.out.println("Employee payment datails");
						      	 System.out.println("1.INSERT PAYMENT DETAILS");
						    	 System.out.println("2.SHOW PAYMENT DETAILS");
							     System.out.println("0.EXIT");

								 System.out.println("");
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
