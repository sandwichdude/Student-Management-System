//Name: Harsh Lalwani
//Student ID: 57051582
//Lab Section: T01/C01

package Assignment3;

import java.util.*;

public class LoginModule {

    static Scanner sc = new Scanner(System.in);

    // creating method for login
    public static boolean login(Scanner sc) {
        Map<String, String> login = new HashMap<String, String>();
        // adding username and password to the map
        login = FileManager.read();
        System.out.println("Welcome to the student management system, please login to your account");
        System.out.println("------------------------------------------------------------------------------------");
        // giving the use 3 opportunities to login
        for (int i = 1; i <= 3; i++) {
            System.out.println("Please enter your username:");
            String username = sc.nextLine();
            System.out.println("Please enter your password:");
            String password = sc.nextLine();

            // checking if the username and password are correct
            if (login.containsKey(username)) {
                if (login.get(username).equals(password)) {
                    System.out.println(
                            "--------------------------------------------------------------------------------");
                    System.out.println(
                            "--------------------------------------------------------------------------------");
                    return true;
                }
            } else {
                System.out.println("The account entered does not exist");
                System.out.println("There are " + (3 - i) + " opportunities");
                System.out.println("--------------------------------------------------------------------------------");
            }
        }
        System.exit(0);
        return false;
    }

    // Method to display main Menu
    public static void mainMenu(Scanner sc) throws InterruptedException {

        System.out.println(
                "*************************************************************************************************");
        System.out.println(
                "*********************** Please select functional module you want to enter ***********************");
        System.out.println("Press 1 to enter the student personal information management module");
        System.out.println("Press 2 to enter the course information management module");
        System.out.println("Press 3 to enter the Student course enrollment management module");
        System.out.println("Press 4 to perform backup");
        System.out.println("Press 5 to exit the system");
        System.out.println(
                "*************************************************************************************************");
        System.out.println("Please enter the number corresponding to the module you want to enter:");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                StudentInfoManagement s = new StudentInfoManagement();
                s.StudentMainMenu();
                break;
            case 2:
                CourseInfoManagement c = new CourseInfoManagement();
                c.courseMainMenu();
                break;
            case 3:
                StudentEnrollmentManagement se = new StudentEnrollmentManagement();
                se.EnrollmentMainMenu();
                break;
            case 4:
                FileManager.backup();
                Thread.sleep(3000);
                mainMenu(sc);
                break;
            case 5:
                System.out.println("You have exited the system");
                System.exit(0);
            default:
                System.out.println("Invalid input, please enter again");
                break;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (login(sc)) {
            mainMenu(sc);
            System.out.println("End");
        }
    }
}
