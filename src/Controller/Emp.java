package Controller;

import java.io.DataInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

public class Emp {
    Connection c;
    PreparedStatement p;
     DataInputStream dis=new DataInputStream(System.in);

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
	            }
	            p.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    }
    
}
