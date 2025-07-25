package Controller;

import java.io.DataInputStream;
import java.io.IOException;
import java.sql.*;

public class Rolldepartment {
    Connection c;
    PreparedStatement p;
    ResultSet rset = null;

    public Rolldepartment(Connection c) {
        this.c = c;
    }

    DataInputStream dis = new DataInputStream(System.in);

    public void insertdep() {
        try {
        
            String deptSQL = "INSERT INTO department (department_name) VALUES (?)";
            p = c.prepareStatement(deptSQL, new String[] { "DEPARTMENT_ID" });
            System.out.print("New Employee Department     : ");
            String deptName = dis.readLine();
            p.setString(1, deptName);
            p.executeUpdate();
            rset = p.getGeneratedKeys();
            int department_id = -1;
            if (rset.next()) {
                department_id = rset.getInt(1);
            }
            String roleSQL = "INSERT INTO roles (role_name) VALUES (?)";
            p = c.prepareStatement(roleSQL, new String[] { "ROLE_ID" });
            System.out.print("Role of the New Employee     : ");
            String role_name = dis.readLine();
            p.setString(1, role_name);
            p.executeUpdate();
            rset = p.getGeneratedKeys();
            int roleId = -1;
            if (rset.next()) {
                roleId = rset.getInt(1);
            }
            System.out.println("✔ Inserted Successfully!");
            System.out.println("Role ID          : " + roleId);
            System.out.println("Role Name        : " + role_name);
            System.out.println("Department ID    : " + department_id);
            System.out.println("Department Name  : " + deptName);
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
          
        }
    }
}
