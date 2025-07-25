package Controller;

import java.io.DataInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Bankdetails {
    PreparedStatement p;
    Connection c;
    DataInputStream dis = new DataInputStream(System.in);
    Scanner sc=new Scanner(System.in);
    public Bankdetails(Connection c) {
        this.c = c;
    }
    public void insertBankEmployee() {
        try {
            System.out.println("Enter the employee id:");
            int employeeid = sc.nextInt();
            if (employeeid != 0) {
                String checkQuery = "SELECT COUNT(*) FROM employee WHERE emp_id = ?";
                PreparedStatement p= c.prepareStatement(checkQuery);
                p.setInt(1, employeeid);
                ResultSet rs = p.executeQuery();
                if (rs.next()) {
                    int count = rs.getInt(1);
                    if (count > 0) {
                        System.out.println("Bank name      : ");
                        String bankName = dis.readLine();
                      
                        System.out.println("Account number : ");
                        String accNo = "";
                        long bnumber = sc.nextLong();
                        sc.nextLine();
                         if(bnumber==11) {
                               accNo =Long.toString(bnumber);
                        }
                        else{
                            System.out.println("Please enter a valid 11-digit account number.");
                            return;
                        }
                        System.out.println("IFSC code      : ");
                        String ifsc = dis.readLine();
                        String insertQuery = "INSERT INTO Bankdetails(emp_id, Bank_name, acc_no, IFSC_code) VALUES (?, ?, ?, ?)";
                        PreparedStatement insertStmt = c.prepareStatement(insertQuery);
                        insertStmt.setInt(1, employeeid);
                        insertStmt.setString(2, bankName);
                        insertStmt.setString(3, accNo);
                        insertStmt.setString(4, ifsc);
                        int n = insertStmt.executeUpdate();
                        if (n != 0) {
                            System.out.println("Bank details uploaded successfully!");
                            System.out.println("---------------------------------------------------------------------------------------------------------------");
                        }
                    } else {
                        System.out.println("Required employee ID not present in our company!");
                    }
                }
            } else {
                System.out.println("PLEASE ENTER EMPLOYEE ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
