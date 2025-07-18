package Controller;

import java.io.DataInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Emp {
    Connection c;
    PreparedStatement p;
     DataInputStream dis=new DataInputStream(System.in);
	 Scanner sc=new Scanner(System.in);

	public Emp(Connection c){
		this.c=c;
	}
	    public void InsertEmployee(){
            try {
	            String query = "INSERT INTO Employee(Emp_name,dob,mobile_no,gender,email_id,address,role_id,department_id) VALUES (?, ?, ?, ?, ?,?,?,?)";
	            p = c.prepareStatement(query);
	            System.out.print("Employee name              : ");
	            p.setString(1, dis.readLine());
	            System.out.print("Date of Birth (dd-MM-yyyy) : ");
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	            java.util.Date dob = sdf.parse(dis.readLine());
	            p.setDate(2, new java.sql.Date(dob.getTime()));
                System.out.print("Mobile number              :");
                p.setString(3,dis.readLine());
                System.out.print("Gender                     :");
                p.setString(4,dis.readLine());
                System.out.print("Email id                   :");
                p.setString(5,dis.readLine());
                System.out.print("Address                    :");
                p.setString(6,dis.readLine());
	            System.out.print("Role id(previous id)       : ");
	            p.setInt(7,Integer.parseInt(dis.readLine()));
	            System.out.print("Department ID (previous id): ");
	            p.setInt(8, Integer.parseInt(dis.readLine()));
	            int result = p.executeUpdate();
	            if (result!=0) {
	                System.out.println("Employee details added successfully ✔✔");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
	            }
	            p.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    }
	public void show(){
		try{
			String string="Select emp_id,emp_name,email_id from employee";
			p=c.prepareStatement(string);
			ResultSet rSet=p.executeQuery();

			while (rSet.next()) {
				int emp_id=rSet.getInt("emp_id");
				String emp_name=rSet.getString("emp_name");
				String email=rSet.getString("email_id");

				System.out.println("  Employee id  :"+emp_id);
				System.out.println("  Name of em   :"+emp_name);
				System.out.println("  Email id     :"+email);
				System.out.println("______________________");
			}
			rSet.close();
			p.close();
			c.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void showEmployeeDetails() {
		try {
			System.out.print("Enter the employee id : ");
			int empid = sc.nextInt();
			sc.nextLine();
			if (empid != 0) {
				String query = "SELECT emp_id, emp_name, dob, mobile_no,gender, email_id, address, role_id, department_id FROM employee WHERE emp_id = ?";
				PreparedStatement preparedStatement = c.prepareStatement(query);
				preparedStatement.setInt(1, empid);
				ResultSet rSet = preparedStatement.executeQuery();
				if (rSet.next()) {
					int empId = rSet.getInt("emp_id");
					String empName = rSet.getString("emp_name");
					java.sql.Date dob = rSet.getDate("dob");
					String mobileNo = rSet.getString("mobile_no");
					String gender=rSet.getString("gender");
					String emailId = rSet.getString("email_id");
					String address = rSet.getString("address");
					int roleId = rSet.getInt("role_id");
					int departmentId = rSet.getInt("department_id");
					System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("Employee ID       : " + empId);
					System.out.println("Employee Name     : " + empName);
					System.out.println("Date of Birth     : " + dob);
					System.out.println("Mobile Number     : " + mobileNo);
					System.out.println("DOB               : " + dob);
					System.out.println("address		  : " + address);
					System.out.println("role_id           :"+roleId);
					System.out.println("Department_id     : "+departmentId);
				}

				String string="select department_name from department";
				preparedStatement=c.prepareStatement(string);
				ResultSet rset=preparedStatement.executeQuery();
				if(rset.next()){
					String dp_name=rset.getString("department_name");
					System.out.println("Department name   :"+dp_name);
				}


				String string2="select role_name from roles";
				preparedStatement =c.prepareStatement(string2);
				ResultSet rSet2=preparedStatement.executeQuery();
				if(rSet2.next()){
					String name=rSet2.getString("role_name");

					System.out.println("Role Name         : "+name);
				}
	       }
		   else{
			System.out.println("Please enter a valid employee id.");
		   }
		}
	catch(Exception e) {
			e.printStackTrace();
		}
	}
}
