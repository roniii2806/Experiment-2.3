import java.sql.*;

public class FetchEmployeeData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/companydb"; // Replace with your DB name
        String user = "root";
        String password = "your_password"; // Replace with your MySQL password

        try {
            // 1. Load and register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish the connection
            Connection con = DriverManager.getConnection(url, user, password);

            // 3. Create a Statement object
            Statement stmt = con.createStatement();

            // 4. Execute SQL query
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            // 5. Display the results
            System.out.println("EmpID\tName\tSalary");
            System.out.println("-------------------------");
            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");

                System.out.println(id + "\t" + name + "\t" + salary);
            }

            // 6. Close resources
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
