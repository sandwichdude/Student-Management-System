//Name: Harsh Lalwani
//Student ID: 57051582
//Lab Section: T01/C01

package Assignment3;

import java.util.*;

import Assignment3.CourseInfoManagement.Course;

public class StudentInfoManagement {
    // creating a class Student with SID, Name, Gender, and CourseList as attributes
    class Student {
        int studentID = 0;
        String name = "";
        String gender = "";
        // Student.courseList is a list of all courses taken by the student
        ArrayList<Course> courseList = new ArrayList<Course>();

        // constructor to initialize the attributes
        public Student(int sid, String Name, String Gender, ArrayList<Course> courses) {
            this.studentID = sid;
            this.name = Name;
            this.gender = Gender;
            this.courseList = new ArrayList<Course>();
        }

        // getters for the attributes
        public int getSID() {
            return studentID;
        }

        public String getName() {
            return name;
        }

        public String getGender() {
            return gender;
        }
    }

    // creating an arraylist of type Student to store all the students
    static List<Student> studentDatabase = new ArrayList<Student>();
    int choice = 0;
    // creating a scanner object to take input from the user
    static Scanner sc = new Scanner(System.in);

    // method to display the student information management main menu
    public void StudentMainMenu() throws InterruptedException {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Welcome to the student personal information management module");
        System.out.println("**************************************************************************************");
        System.out
                .println("***************** Please select the operation that needs to be performed *****************");
        System.out.println();
        System.out.println("Press 1 to add student personal information");
        System.out.println("Press 2 to delete student personal information");
        System.out.println("Press 3 to modify student personal information");
        System.out.println("Press 4 to inquire about student's personal information");
        System.out.println("Press 5 to display all student personal information");
        System.out.println("Press 6 to return");
        System.out.println("**************************************************************************************");
        System.out.println("Please enter the number corresponding to the module you want to enter:");
        choice = sc.nextInt();
        System.out
                .println("------------------------------------------------------------------------------------------");
        System.out
                .println("------------------------------------------------------------------------------------------");
        operation(choice);
    }

    // method to compare student s1 and student s2
    public boolean compareStudent(Student s1, Student s2) {
        if (s1.studentID != s2.studentID) {
            return false;
        }
        if (s1.name != s2.name) {
            return false;
        }
        if (s1.gender != s2.gender) {
            return false;
        }
        return true;

    }

    // method to add a student to the student database
    public void addStudentInfo() throws InterruptedException {
        System.out.println("Please enter the student ID:");
        int tempsid = sc.nextInt();
        System.out.println("Please enter the student name:");
        String tempname = sc.next();
        System.out.println("Please enter the gender of the student (Male/Female)");
        String tempgender = sc.next();
        boolean flag = false;
        Student addstudent = new Student(tempsid, tempname, tempgender, new ArrayList<Course>());
        for (Student s : studentDatabase) {
            // checking if student already exists in the database
            if (s.studentID == addstudent.studentID) {
                System.out.println(
                        "The student already exists, please reconfirm the student information that needs to be added and return to the main interface");
                flag = true;
                break;
            } else {
                continue;
            }
        }
        if (!flag) {
            studentDatabase.add(addstudent);
            System.out.println("Add successfully, you will return to main interface");
        }
        System.out.println(
                "******************************************************************************************************************************");
        Thread.sleep(3000);
    }

    // method to delete a student from the student database
    public void deleteStudentInfo() throws InterruptedException {
        System.out.println("Please enter the student ID corresponding to the student you want to delete:");
        int checkSID = sc.nextInt();
        boolean flag = false;
        System.out.println("Please enter the name of the student you want to delete");
        String checkName = sc.next();
        System.out.println("Please enter the gender of the student you want to delete (male/female)");
        String checkGender = sc.next();
        for (Student s : studentDatabase) {
            if (s.studentID == checkSID && s.name.equalsIgnoreCase(checkName)
                    && s.gender.equalsIgnoreCase(checkGender)) {
                studentDatabase.remove(s);
                flag = true;
                System.out.println("sucessfully deleted");
                System.out.println("Data has been syncronized to the grade information table");
                break;
            } else {
                continue;
            }
        }
        if (!flag) {
            System.out.println("Deletion failed, please check whether the entered information is correct");
        }
        System.out.println("The deletion is over, It will return to the main interface");
        System.out.println(
                "******************************************************************************************************************************");
        Thread.sleep(3000);
    }

    // method to modify a student's information
    public void modifyStudentInfo() throws InterruptedException {
        int checkSID;
        boolean flag = false;
        System.out.println(
                "Please enter the student ID corresponding to the student whose infomation needs to be modified:");
        checkSID = sc.nextInt();
        for (Student student : studentDatabase) {
            // checking if the student exists in the database
            if (student.studentID == checkSID) {
                System.out.println("Please enter the revised student name");
                String newName = sc.next();
                student.name = newName;
                System.out.println("Please enter the revised gender of the student (Male/Female)");
                String newGender = sc.next();
                student.gender = newGender;
                flag = true;
                System.out.println("The modification is successful, it will return to the main interface");
                break;
            } else {
                continue;
            }
        }
        if (!flag) {
            System.out.println("Modification failed, please check whether the SID is correct");
        }
        System.out.println(
                "******************************************************************************************************************************");
        Thread.sleep(3000);
    }

    // method to inquire about a student's information
    public void inquireStudentInfo() throws InterruptedException {
        int checkSID;
        boolean flag = false;
        System.out.println("Please enter the student ID corresponding to the student's information you want to query");
        checkSID = sc.nextInt();
        for (Student student : studentDatabase) {
            // checking if the student exists in the database
            if (student.studentID == checkSID) {
                // Display all info
                System.out.println("StudentID\tName\tGender");
                System.out.println(student.studentID + "\t" + student.name + "\t" + student.gender);
                System.out.println("Query is complete");
                flag = true;
                break;
            } else {
                continue;
            }
        }

        if (!flag) {
            System.out.println("The student ID you entered does not exist");
        }

        System.out.println("Return to the main interface after 5 seconds");
        System.out.println(
                "******************************************************************************************************************************");
        Thread.sleep(5000);
    }

    // method to display the student database
    public void displayStudentInfo() throws InterruptedException {
        System.out.println("StudentID\tName\tGender");

        for (Student student : studentDatabase) {
            System.out.println(student.studentID + "\t" + student.name + "\t" + student.gender);
        }

        System.out.println("Press enter to continue");
        // String inputString = sc.next();
        // System.out.println(inputString);
        sc.nextLine();
        sc.nextLine();
        // backing up the student database
        FileManager.backupStudentData(studentDatabase);
        System.out.println("*****************************************************************************************");
        Thread.sleep(5000);
    }

    // method to choose which operation to perform
    void operation(int choice) throws InterruptedException {
        // switch case to perform different operations
        switch (choice) {
            case 1:
                addStudentInfo();
                StudentMainMenu();
                break;
            case 2:
                deleteStudentInfo();
                StudentMainMenu();
                break;
            case 3:
                modifyStudentInfo();
                StudentMainMenu();
                break;
            case 4:
                inquireStudentInfo();
                StudentMainMenu();
                break;
            case 5:
                displayStudentInfo();
                StudentMainMenu();
                break;
            case 6:
                LoginModule.mainMenu(sc);
                break;
            default:
                System.out.println("Please enter the correct number!");
                StudentMainMenu();
        }
    }
}