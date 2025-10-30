import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static final String URL = "jdbc:mysql://localhost:3306/companydb";
    static final String USER = "root";
    static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con.setAutoCommit(false); // Start transaction

            while (true) {
                System.out.println("\n=== PRODUCT MANAGEMENT ===");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        addProduct(con, sc);
                        break;
                    case 2:
                        viewProducts(con);
                        break;
                    case 3:
                        updateProduct(con, sc);
                        break;
                    case 4:
                        deleteProduct(con, sc);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        con.close();
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addProduct(Connection con, Scanner sc) throws SQLException {
        String query = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);

        System.out.print("Enter Product ID: ");
        ps.setInt(1, sc.nextInt());
        System.out.print("Enter Product Name: ");
        ps.setString(2, sc.next());
        System.out.print("Enter Price: ");
        ps.setDouble(3, sc.nextDouble());
        System.out.print("Enter Quantity: ");
        ps.setInt(4, sc.nextInt());

        ps.executeUpdate();
        con.commit();
        System.out.println(" Product added successfully!");
    }

    static void viewProducts(Connection con) throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
        System.out.println("ProductID\tName\tPrice\tQuantity");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
                    rs.getDouble(3) + "\t" + rs.getInt(4));
        }
    }

    static void updateProduct(Connection con, Scanner sc) throws SQLException {
        String query = "UPDATE Product SET Price=?, Quantity=? WHERE ProductID=?";
        PreparedStatement ps = con.prepareStatement(query);

        System.out.print("Enter Product ID to update: ");
        ps.setInt(3, sc.nextInt());
        System.out.print("Enter new Price: ");
        ps.setDouble(1, sc.nextDouble());
        System.out.print("Enter new Quantity: ");
        ps.setInt(2, sc.nextInt());

        ps.executeUpdate();
        con.commit();
        System.out.println(" Product updated successfully!");
    }

    static void deleteProduct(Connection con, Scanner sc) throws SQLException {
        String query = "DELETE FROM Product WHERE ProductID=?";
        PreparedStatement ps = con.prepareStatement(query);

        System.out.print("Enter Product ID to delete: ");
        ps.setInt(1, sc.nextInt());

        ps.executeUpdate();
        con.commit();
        System.out.println(" Product deleted successfully!");
    }
}
