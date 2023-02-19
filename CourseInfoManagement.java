//Name: Harsh Lalwani
//Student ID: 57051582
//Lab Section: T01/C01

package Assignment3;

import java.util.*;

public class CourseInfoManagement {
    // Create a class Course with the following attributes
    class Course {

        int courseID = 0;
        String coursename = "";
        int coursecredits = 0;
        String coursetime = "";

        // Create a constructor for the class Course
        public Course(int courseid, String coursename, int creds, String coursetime) {
            this.courseID = courseid;
            this.coursename = coursename;
            this.coursecredits = creds;
            this.coursetime = coursetime;
        }

        // getters for Course class
        public int getCourseID() {
            return courseID;
        }

        public String getCourseName() {
            return coursename;
        }

        public int getCourseCredit() {
            return coursecredits;
        }

        public String getCourseTime() {
            return coursetime;
        }
    }

    int choice = 0;

    // Create an ArrayList called courseList of type Course
    static ArrayList<Course> courseList = new ArrayList<Course>();
    static Scanner sc = new Scanner(System.in);

    // method to display the courseMainMenu and call the respective operation
    public void courseMainMenu() throws InterruptedException {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("**************************************************************************************");
        System.out
                .println("***************** Please select the operation that needs to be performed *****************");
        System.out.println();
        System.out.println("Press 1 to add course information");
        System.out.println("Press 2 to delete course information");
        System.out.println("Press 3 to modify course information");
        System.out.println("Press 4 for course information");
        System.out.println("Press 5 to display all course information");
        System.out.println("Press 6 to return");
        System.out.println("**************************************************************************************");
        System.out.println("Please enter the number corresponding to the module you want to enter:");
        System.out
                .println("------------------------------------------------------------------------------------------");
        System.out
                .println("------------------------------------------------------------------------------------------");
        choice = sc.nextInt();
        operation(choice);
    }

    void operation(int choice) throws InterruptedException {
        switch (choice) {
            case 1:
                addCourse();
                courseMainMenu();
                break;
            case 2:
                deleteCourse();
                courseMainMenu();
                break;
            case 3:
                modifyCourse();
                courseMainMenu();
                break;
            case 4:
                inquireCourse();
                courseMainMenu();
                break;
            case 5:
                displayCourse();
                courseMainMenu();
                break;
            case 6:
                LoginModule.mainMenu(sc);
                break;
            default:
                System.out.println("Invalid input, please try again.");
                courseMainMenu();
        }
    }

    // method to add a course to the courseList
    public void addCourse() throws InterruptedException {
        System.out.println("Please enter the course ID");
        int courseid = sc.nextInt();
        System.out.println("Please enter the course name");
        String coursename = sc.next();
        System.out.println("Please enter the course credits");
        int creds = sc.nextInt();
        System.out.println("Please enter the course time");
        String coursetime = sc.next();
        Course course = new Course(courseid, coursename, creds, coursetime);
        boolean flag = false;
        // check if the course already exists in the courseList
        for (Course c : courseList) {
            if (c.courseID == courseid) {
                flag = true;
                System.out.println(
                        "Course already exists, please reconfirm the course information that needs to be added, and return to the main interface after 5 seconds");
                break;
            } else {
                continue;
            }
        }
        // if the course does not exist, add it to the courseList
        if (!flag) {
            courseList.add(course);
            System.out.println("Course added successfully, you will return to the main interface");
        }
        System.out.println("***************************************************************************************");
        Thread.sleep(5000);
    }

    // method to delete a course from the courseList
    public void deleteCourse() throws InterruptedException {
        System.out.println("Please enter the course ID");
        int courseid = sc.nextInt();
        System.out.println("Please enter the course name");
        String coursename = sc.next();
        System.out.println("Please enter the course credits");
        int creds = sc.nextInt();
        System.out.println("Please enter the course time");
        String coursetime = sc.next();
        boolean flag = false;
        // check if the course exists in the courseList
        for (Course course : courseList) {
            if ((course.courseID == courseid) && (course.coursename.equalsIgnoreCase(coursename))
                    && (course.coursecredits == creds) && (course.coursetime.equalsIgnoreCase(coursetime))) {
                flag = true;
                courseList.remove(course);
                System.out.println("successfully deleted \n Data has been syncronized to the grade infomation table");
                break;
            } else {
                continue;
            }
        }
        if (!flag) {
            System.out.println("Deletion failed, please check whether the course information is correct");
        }
        System.out.println("You will return to the main interface after 5 seconds");
        System.out.println("**************************************************************************************");
        Thread.sleep(5000);
    }

    public void modifyCourse() throws InterruptedException {
        boolean flag = false;
        System.out.println("Please enter the course ID whose information needs to be modified");
        int courseid = sc.nextInt();
        // check if the course exists in the courseList
        for (Course course : courseList) {
            if (course.courseID == courseid) {
                System.out.println("Please enter the revised course name");
                String coursename = sc.next();
                System.out.println("Please enter the revised course credit");
                int creds = sc.nextInt();
                System.out.println("Please enter the revised course time");
                String coursetime = sc.next();
                course.coursename = coursename;
                course.coursecredits = creds;
                course.coursetime = coursetime;
                flag = true;
                System.out.println("The modification is successful, return to the main interface after 5 seconds");
                break;
            } else {
                continue;
            }
        }
        // if the course does not exist, print the error message
        if (!flag) {
            System.out.println("The modification failed, please check whether the course ID is correct");
        }
        System.out.println("**************************************************************************************");
        Thread.sleep(5000);
    }

    // method to inquire a course in the courseList
    public void inquireCourse() throws InterruptedException {
        System.out.println(
                "Please enter the course ID corresponding to the information you want to inquire about the course");
        int checkid = sc.nextInt();
        boolean flag = false;
        // check if the course exists in the courseList
        for (Course course : courseList) {
            if (course.courseID == checkid) {
                System.out.println("CourseID\tCourseName\tCourseCredits\tCourseTime");
                System.out.println(course.courseID + "\t" + course.coursename + "\t" + course.coursecredits + "\t"
                        + course.coursetime);
                flag = true;
                System.out.println("Query is successful");
                break;
            } else {
                continue;
            }
        }
        // if the course does not exist, print the error message
        if (!flag) {
            System.out.println("The courseID you entered does not exist");
        }
        System.out.println("Will return to main interface after 5 seconds");
        System.out.println("**************************************************************************************");
        Thread.sleep(5000);
    }

    // method to display all the courses in the courseList
    public void displayCourse() throws InterruptedException {
        System.out.println("CourseID\tCourseName\tCourseCredits\tCourseTime");
        for (Course course : courseList) {
            System.out.println(course.courseID + "\t" + course.coursename + "\t" + course.coursecredits + "\t"
                    + course.coursetime);
        }
        // Backing up CourseData in .txt file
        FileManager.backupCourseData(courseList);
        System.out.println("**********************************************************************************");
        Thread.sleep(5000);
    }
}