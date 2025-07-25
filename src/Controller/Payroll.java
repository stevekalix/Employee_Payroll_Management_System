package Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Payroll {
      Connection connection;
      Connection c;
      Scanner sc=new Scanner(System.in);
      PreparedStatement p;

      public Payroll(Connection c){
        this.connection=c;
     }

public void employeepayrollinsert() {
    
    try {
        System.out.print("Employee id     :");
        int employeeid = sc.nextInt();

        if (employeeid != 0) {
            String checkquery = "SELECT COUNT(*) FROM employee WHERE emp_id = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(checkquery);
            prepareStatement.setInt(1, employeeid);
            ResultSet rSet = prepareStatement.executeQuery();
            if (rSet.next()) {
                int count = rSet.getInt(1);
                if (count > 0) {
                    System.out.print("Basic Salary     :");
                    long basic_salary = sc.nextLong();
                    System.out.print("Bonus Amount     :");
                    long bonus = sc.nextLong();
                    System.out.print("Tax, Insurance   :");
                    long Tax = sc.nextLong();
                    long netpay = basic_salary + bonus - Tax;
                    String payId = "PAY" + new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date());
                    Timestamp payTimestamp=new Timestamp(System.currentTimeMillis());
                    String sql = "INSERT INTO paymentinfo (pay_id, emp_id, basic_salary, bonus,Net_pay, detuction, created_At)VALUES (?, ?, ?, ?, ?, ?, ?)";
                    p = connection.prepareStatement(sql);
                    p.setString(1, payId);
                    p.setInt(2, employeeid);
                    p.setLong(3, basic_salary);
                    p.setLong(4, bonus);
                    p.setLong(5, netpay);
                    p.setLong(6, Tax);
                    p.setTimestamp(7, payTimestamp);
                    int n = p.executeUpdate();
                    if (n != 0) {
                        System.out.println("Payment details successfully filed.");
                        sc.nextLine();
                        System.out.println("         Pay id--------------------Current date time--------");
                        System.out.print("         "+payId);
                        System.out.println("          "+payTimestamp);
                        System.out.println("---------------------------------------------------------------------------------------------------------------------");
                    } else {
                        System.out.println("Please enter the data correctly.");
                    }
                } else {
                    System.out.println("Employee not found.");
                }
            }
        } else {
            System.out.println("Please enter a valid employee ID.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public void showpayRoll() {
    try {
        System.out.print("Enter the employee id  :");
        int empid = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Payment ID             :");
        String pay_id = sc.nextLine();

        if (empid != 0 && !pay_id.isEmpty()) {
            String cheString = "SELECT COUNT(*) FROM paymentinfo WHERE emp_id = ? AND pay_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(cheString);
            preparedStatement.setInt(1, empid);
            preparedStatement.setString(2, pay_id);
            ResultSet rSet = preparedStatement.executeQuery();
            if (rSet.next() && rSet.getInt(1) > 0) {
                String query = "SELECT emp_id, pay_id, basic_salary, bonus, detuction, created_at, net_pay FROM paymentinfo WHERE emp_id = ? AND pay_id = ?";
                PreparedStatement p1 = connection.prepareStatement(query);
                p1.setInt(1, empid);
                p1.setString(2, pay_id);
                ResultSet rSet2 = p1.executeQuery();

                String string="select acc_no from Bankdetails";
                PreparedStatement p2=connection.prepareStatement(string);
                ResultSet rSet3=p2.executeQuery();

                
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Emp_ID  | Pay_ID            | Salary | Bonus | Deduction | Date & Time           | Net Pay");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                while (rSet2.next()) {
                    int emp_id = rSet2.getInt("emp_id");
                    String pay = rSet2.getString("pay_id");
                    long salary = rSet2.getLong("basic_salary");
                    long bonus = rSet2.getLong("bonus");
                    long deduction = rSet2.getLong("detuction");
                    Timestamp created_at = rSet2.getTimestamp("created_at");
                    long net_pay = rSet2.getLong("net_pay");


                    System.out.printf("%7d | %-8s | %6d | %5d | %9d | %s | %7d%n",
                            emp_id, pay, salary, bonus, deduction, created_at.toString(), net_pay);
                            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------");
                }

            } else {
                System.out.println("Please enter correct employee ID and pay ID.");
            }
        } else {
            System.out.println("Missing employee ID or pay ID.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
