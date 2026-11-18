import java.util.Scanner;

public class StudentRegistrationList {

    // Node representing a single student record
    static class Student {
        int rollNo;
        String name;
        Student next;

        Student(int rollNo, String name) {
            this.rollNo = rollNo;
            this.name = name;
            this.next = null;
        }
    }

    static Student head = null;

    // 1. Insert a new student at the end (Enroll)
    static void insert(int rollNo, String name) {
        Student newNode = new Student(rollNo, name);
        if (head == null) {
            head = newNode;
        } else {
            Student temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println("Enrolled: Roll No " + rollNo + " (" + name + ")");
    }

    // 2. Delete a student by Roll No (Withdraw)
    static void delete(int rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.rollNo == rollNo) {
            head = head.next;
            System.out.println("Withdrawn: Roll No " + rollNo);
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("Student with Roll No " + rollNo + " not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Withdrawn: Roll No " + rollNo);
        }
    }

    // 3. Search for a student by Roll No
    static void search(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Found -> Roll No: " + temp.rollNo + ", Name: " + temp.name);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
    }

    // 4. Update a student's name by Roll No
    static void update(int rollNo, String newName) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.name = newName;
                System.out.println("Updated Roll No " + rollNo + " to: " + newName);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll No " + rollNo + " not found.");
    }

    // 5. Display all registered students
    static void display() {
        if (head == null) {
            System.out.println("No students registered.");
            return;
        }
        System.out.print("Registered Students: ");
        Student temp = head;
        while (temp != null) {
            System.out.print("[" + temp.rollNo + " : " + temp.name + "] -> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- STUDENT REGISTRATION MENU ---");
            System.out.println("1. Enroll (Insert)");
            System.out.println("2. Withdraw (Delete)");
            System.out.println("3. Search Student");
            System.out.println("4. Update Record");
            System.out.println("5. Display All");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            if (choice == 6) {
                System.out.println("Exiting...");
                break;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No: ");
                    int r1 = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    insert(r1, name);
                    break;
                case 2:
                    System.out.print("Enter Roll No to withdraw: ");
                    delete(sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter Roll No to search: ");
                    search(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter Roll No to update: ");
                    int r4 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    update(r4, sc.nextLine());
                    break;
                case 5:
                    display();
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
        sc.close();
    }
}