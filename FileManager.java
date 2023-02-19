//Name: Harsh Lalwani
//Student ID: 57051582
//Lab Section: T01/C01

package Assignment3;

import java.util.*;

import Assignment3.CourseInfoManagement.Course;
import Assignment3.StudentInfoManagement.Student;

import java.io.*;

public class FileManager {
	// create a hashmap method read to store username and password
	public static Map<String, String> read() {
		Map<String, String> login = new HashMap<String, String>();
		try {
			FileInputStream fstream = new FileInputStream("C:\\Users\\DELL\\Desktop\\CS2360\\Assignment3\\users.txt");

			// create a bufferedreader
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

			String strLine;

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				String[] splited = strLine.split(" ");
				String name = splited[0];
				String password = splited[1];
				// add username and password to the map
				login.put(name, password);
			}

			// Close the input stream
			fstream.close();
		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
		}
		return login;
	}

	// Method to write the student information to the file
	public static void backupStudentData(List<Student> studentDatabase) {
		try {
			// creating a buffered writer
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("C:\\Users\\DELL\\Desktop\\CS2360\\Assignment3\\backupStudentData.txt"));
			String data = "StudentID\tStudentName\tStudentGender\n";
			// writing the data to the file
			bw.write(data);
			// looping through the student database
			for (Student s : studentDatabase) {
				// writing the relevant data to the file
				data = s.getSID() + "\t" + s.getName() + "\t" + s.getGender() + "\n";
				bw.write(data);
			}
			bw.close();
			System.out.println("Student information data Backup completed");
		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
		}
	}

	// Method to write the course information to the file
	public static void backupCourseData(List<Course> courseDatabase) {
		try {
			// creating a buffered writer
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("C:\\Users\\DELL\\Desktop\\CS2360\\Assignment3\\backupCourseData.txt"));
			String data = "CourseID\tCourseName\tCourseCredit\tCourseTime\n";
			bw.write(data);
			// looping through the course database
			for (Course c : courseDatabase) {
				// writing the relevant data to the file
				data = c.getCourseID() + "\t" + c.getCourseName() + "\t" + c.getCourseCredit() + "\t"
						+ c.getCourseTime() + "\n";
				bw.write(data);
			}
			bw.close();
			System.out.println("Course Information data Backup completed");
		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
		}
	}

	// Method to write the student enrollment information to the file
	public static void backupStudentEnrollment() {
		try {
			// creating a buffered writer
			BufferedWriter bw = new BufferedWriter(
					new FileWriter("C:\\Users\\DELL\\Desktop\\CS2360\\Assignment3\\backupStudentEnrollment.txt"));
			String data = "StudentID\tCourseID\tEnrollmentStatus\n";
			bw.write(data);

			// looping through the student database
			for (Student s : StudentInfoManagement.studentDatabase) {
				// looping through the course database
				for (Course c : CourseInfoManagement.courseList) {
					// checking if the student is enrolled in the course
					if (s.courseList.contains(c)) {
						data = s.studentID + "\t" + c.courseID + "\t" + "YES" + "\n";
					} else {
						data = s.studentID + "\t" + c.courseID + "\t" + "NO" + "\n";
					}
					// writing the relevant data to the file
					bw.write(data);

				}
			}
			bw.close();
			System.out.println("Student Enrollment data Backup completed");
		} catch (Exception e) {
			System.err.println("Error:" + e.getMessage());
		}
	}

	public static void backup() {
		backupStudentData(StudentInfoManagement.studentDatabase);
		backupCourseData(CourseInfoManagement.courseList);
		backupStudentEnrollment();

	}
}