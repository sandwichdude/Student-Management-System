//Name: Harsh Lalwani
//Student ID: 57051582
//Lab Section: T01/C01

package Assignment3;

import java.util.*;

class StudentEnrollmentManagement {

    Scanner sc = new Scanner(System.in);

    // Method to display the main menu
    void EnrollmentMainMenu() throws InterruptedException {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("**************************************************************************************");
        System.out
                .println("***************** Please select the operation that needs to be performed *****************");
        System.out.println();
        System.out.println("Press 1 to add student course enrollment information");
        System.out.println("Press 2 to delete student course enrollment information");
        System.out.println("Press 3 to inquire about student's course enrollment information");
        System.out.println("Press 4 to display all student course enrollment information");
        System.out.println("Press 5 to return");
        System.out.println("**************************************************************************************");
        System.out.println("Please enter the number corresponding to the module you want to enter:");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------");
        int choice = sc.nextInt();
        // Switch statement to select the corresponding function
        operation(choice);
    }

    // Method to select the corresponding function
    void operation(int choice) throws InterruptedException {
        switch (choice) {
            case 1:
                addEnrollment(sc);
                EnrollmentMainMenu();
                break;
            case 2:
                deleteEnrollment(sc);
                EnrollmentMainMenu();
                break;
            case 3:
                inquireEnrollment(sc);
                EnrollmentMainMenu();
                break;
            case 4:
                displayEnrollment();
                EnrollmentMainMenu();
                break;
            case 5:
                // Return to the main menu
                LoginModule.mainMenu(sc);
                break;
            default:
                System.out.println("Invalid input, please enter again");
                EnrollmentMainMenu();
        }
    }

    // Method to add student course enrollment information
    public void addEnrollment(Scanner sc) throws InterruptedException {
        System.out.println("Please enter the student ID");
        int studentID = sc.nextInt();
        boolean flag = false;
        boolean flag2 = false;
        String timeslot = "";
        System.out.println("Please enter the course ID (0001 - 0007)");
        int courseid = sc.nextInt();
        int StudentIndex = 0;

        // Checking if the Course exists
        for (CourseInfoManagement.Course c : CourseInfoManagement.courseList) {
            if (c.courseID == courseid) {
                // Saving the timeslot of the course
                timeslot = c.coursetime;
                flag = true;
            }
        }

        if (!flag) {
            System.out.println("The course does not exist");
            return;
        }

        flag = false;

        for (StudentInfoManagement.Student s : StudentInfoManagement.studentDatabase) {
            // Checking if the student exists
            if (s.studentID == studentID) {
                flag2 = true;
                // and if the student has already enrolled in any
                // course
                if (s.courseList.size() == 0) {
                    StudentIndex = StudentInfoManagement.studentDatabase.indexOf(s);
                    continue;
                } else if (s.courseList.size() != 0) {
                    for (CourseInfoManagement.Course c : s.courseList) {
                        // Checking if the student has already enrolled in the same course
                        if (c.courseID == courseid) {
                            flag = true;
                            System.out.println(
                                    "This enrollment status information already exists. You cannot enroll the same course repeatedly, please reconfirm the information to be added, and it will return to the main interface");
                            break;
                        }
                        // Checking if the student has already enrolled in another course with the same
                        // timings
                        else if (c.coursetime.equalsIgnoreCase(timeslot) && c.courseID != courseid) {
                            flag = true;
                            System.out.println("The class you selected conflicts with this lesson you have:");
                            System.out
                                    .println(c.courseID + " " + c.coursename + " " + c.coursecredits + " "
                                            + c.coursetime);
                            System.out.println("Please re-select the course and it will return to the main interface");
                            break;
                        }
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }

        }
        if (!flag2) {
            System.out.println("The Student does not exist");
            return;
        }

        if (!flag) {
            // Adding the course to the student's course list
            for (CourseInfoManagement.Course c : CourseInfoManagement.courseList) {
                if (c.courseID == courseid) {
                    StudentInfoManagement.studentDatabase.get(StudentIndex).courseList.add(c);
                    System.out.println("Add successfully, and it will return to the main interface");
                    break;
                }
            }
        }
        System.out.println("*****************************************************************************");
        Thread.sleep(5000);
    }

    // Method to delete student course enrollment information
    public void deleteEnrollment(Scanner sc) throws InterruptedException {
        System.out.println("Please enter the Student ID corresponding to the enrollment status you want to delete");
        int sid = sc.nextInt();
        System.out.println("Please enter the course ID corresponding to the enrollment staus you want to delete");
        int cid = sc.nextInt();
        boolean flag = false;
        // Checking if the student exists
        for (StudentInfoManagement.Student s : StudentInfoManagement.studentDatabase) {
            if (s.studentID == sid) {
                // Checking if the student has enrolled in the course
                for (CourseInfoManagement.Course c : s.courseList) {
                    if (cid == c.courseID) {
                        flag = true;
                        s.courseList.remove(c);
                        System.out.println("successfully Deleted");
                        break;
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }
        // If the student or course does not exist
        if (!flag) {
            System.out.println("Deletion failed, Please check whether the entered information is correct");
        }
        System.out.println("The deletion is over, it will return to main interface in 5 seconds");
        System.out.println("*****************************************************************************");
        Thread.sleep(5000);
    }

    // Method to inquire student course enrollment information
    public void inquireEnrollment(Scanner sc) throws InterruptedException {
        System.out.println("Please enter the Student ID you want to inquire about");
        int sid = sc.nextInt();
        System.out.println("Please enter the Course ID corresponding to the information you want to check result");
        int cid = sc.nextInt();
        boolean flag = false;
        System.out.println("StudentID\tCourseID\tEnrollmentStatus\n");
        for (StudentInfoManagement.Student s : StudentInfoManagement.studentDatabase) {
            if (s.studentID == sid) {
                for (CourseInfoManagement.Course c : CourseInfoManagement.courseList) {
                    if (cid == c.courseID) {
                        flag = true;
                        // Checking if the student has enrolled in the course
                        if (s.courseList.contains(c)) {
                            System.out.println(s.studentID + "\t" + c.courseID + "\t" + "YES");
                            break;
                        } else {
                            System.out.println(s.studentID + "\t" + c.courseID + "\t" + "NO");
                            break;
                        }
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }
        // If the student or course does not exist
        if (!flag) {
            System.out.println("The Student ID or Course ID you entered does not exist");
        }
        System.out.println("Query is complete");
        System.out.println("Will return to the main interface in 5 seconds");
        System.out.println("*****************************************************************************");
        Thread.sleep(5000);
    }

    // Method to display all student course enrollment information
    public void displayEnrollment() throws InterruptedException {
        System.out.println("StudentID\tCourseID\tEnrollmentStatus\n");
        for (StudentInfoManagement.Student s : StudentInfoManagement.studentDatabase) {
            for (CourseInfoManagement.Course c : CourseInfoManagement.courseList) {
                if (s.courseList.contains(c)) {
                    System.out.println(s.studentID + "\t" + c.courseID + "\t" + "YES");
                } else {
                    System.out.println(s.studentID + "\t" + c.courseID + "\t" + "NO");
                }
            }
        }
        System.out.println("Display is complete");
        System.out.println("Will return to the main interface in 5 seconds");
        FileManager.backupStudentEnrollment();
        System.out.println("*****************************************************************************");
        Thread.sleep(5000);
    }
}