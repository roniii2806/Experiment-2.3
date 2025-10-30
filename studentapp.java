import java.util.*;

public class StudentApp {
    public static void main(String[] args) {
        try {
            StudentDAO dao = new StudentDAO();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        System.out.print("Enter Name: ");
                        String name = sc.next();
                        System.out.print("Enter Department: ");
                        String dept = sc.next();
                        System.out.print("Enter Marks: ");
                        double marks = sc.nextDouble();
                        dao.addStudent(new Student(id, name, dept, marks));
                        break;

                    case 2:
                        List<Student> list = dao.getAllStudents();
                        System.out.println("ID\tName\tDept\tMarks");
                        for (Student s : list)
                            System.out.println(s.getStudentID() + "\t" + s.getName() + "\t" + s.getDepartment() + "\t" + s.getMarks());
                        break;

                    case 3:
                        System.out.print("Enter ID to update: ");
                        id = sc.nextInt();
                        System.out.print("Enter New Name: ");
                        name = sc.next();
                        System.out.print("Enter New Dept: ");
                        dept = sc.next();
                        System.out.print("Enter New Marks: ");
                        marks = sc.nextDouble();
                        dao.updateStudent(new Student(id, name, dept, marks));
                        break;

                    case 4:
                        System.out.print("Enter ID to delete: ");
                        dao.deleteStudent(sc.nextInt());
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        System.exit(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
