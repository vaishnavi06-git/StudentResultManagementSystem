import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StudentResultManagement {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/StudentDB";
        String username = "root"; 
        String password = "";     

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            Scanner sc = new Scanner(System.in);

            System.out.println("1. Insert student");
            System.out.println("2. Display students");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    int marks = sc.nextInt();

                    String insertQuery = "INSERT INTO Students VALUES (" + roll + ", '" + name + "', " + marks + ")";
                    stmt.executeUpdate(insertQuery);
                    System.out.println("Record inserted successfully!");
                    break;

                case 2:
                    String selectQuery = "SELECT * FROM Students";
                    ResultSet rs = stmt.executeQuery(selectQuery);

                    System.out.println("Roll No\tName\tMarks");
                    while (rs.next()) {
                        System.out.println(rs.getInt("roll_no") + "\t" + rs.getString("name") + "\t" + rs.getInt("marks"));
                    }
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
