package arellano_HW1;
import java.io.File;


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Program {
//Create an arraylist that will hold objects of type Course
	static ArrayList<Course> Courses = new ArrayList<Course>();
	@SuppressWarnings("unchecked")

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InterruptedException {
		//Read from the MyUniversityCourses.csv using a while loop
		File file = new File("MyUniversityCourses.csv");
			Scanner input = new Scanner(file); 
			input.nextLine();
			while (input.hasNext()){	
				String data = input.nextLine();
				String[] CourseData = data.split(",");//create a temporary array that will store each line (course info)
				//create course object for each course using CourseData array
				Course course = new Course(CourseData[0],CourseData[1],Integer.parseInt(CourseData[2]),Integer.parseInt(CourseData[3]),CourseData[4],CourseData[5],Integer.parseInt(CourseData[6]),CourseData[7]);
				//add each Course object to the course array
				Courses.add(course);
			}
			//Create a temporary courses arraylist and students arraylist that will be used for serialization
			ArrayList<Course> courses=null;
			ArrayList<Student>students =null;
			Admin admin=new Admin(Courses);
			//Instantiate an admin object
			try{//deserialize from courses.ser. have ArrayList courses variable point to this object
				FileInputStream fis = new FileInputStream("courses.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				courses = (ArrayList<Course>)ois.readObject();
				ois.close();
				fis.close();
			}catch(IOException ioe){
				courses = Courses;//for the first run, courses.ser will not exist in memory. 
									//therefore, point courses to the new Course file created
			}catch(ClassNotFoundException cnfe) {
			       return;
			}
			admin.courses = courses;//have the courses arraylist in Admin object equal the courses object deserialized 
			
			try{//similarly to the last deserialization of courses, but this time, students arraylist is deserialized
				FileInputStream fis = new FileInputStream("students.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);
				students = (ArrayList<Student>)ois.readObject();
				ois.close();
				fis.close();
			}catch(IOException ioe){
				students = new ArrayList<Student>();//for the first run, if there is no student.ser, create an empty student arraylist
			}catch(ClassNotFoundException cnfe) {
			       return;
			}
			admin.students = students;//ensure students arraylist in admin references the students arraylist created in this class 

			//Create a scanner and ensure that the user inputs from given options with while loop
			Scanner input1 = new Scanner(System.in);
			System.out.println("Welcome to the Course Registration System (CRS). \nAre you an (Please choose by letter):\n(a) Admin\n(s) Student");
			String answer1 = input1.next();

			while(true){
				if ((answer1.equals("a"))||(answer1.equals("s"))){
					break;
				}
				else{
				System.out.println("Invalid input!");
				System.out.println("Welcome to the Course Registration System (CRS). Are you a(n) (Please choose by letter):\n(a) Admin\n(s) Student");
				answer1 = input1.next();
				}
			}
			//create an admin menu using an if statement
			if (answer1.equalsIgnoreCase("a")){	
				//ensure username and password are correct using getters and String.equals
			System.out.println("Please enter your username:");
			String username1 = input1.next();
			System.out.println("Please enter your password:");
			String password1 = input1.next();
			while(true){
				if ((username1.equals(admin.GetUsername()))&&(password1.equals(admin.GetPassword()))){
					break;
				}
				else{
					System.out.println("Invalid username or password! Please try again.");
					System.out.println("Please enter your username:");
					username1 = input1.next();
					System.out.println("Please enter your password:");
					password1 = input1.next();
				}
			}//After verification, create while loop to return to main menu after user completes desired task
			//ensure input from given options
				System.out.println("\nWelcome, Admin!\n");
				while (true){
				System.out.println("What would you like to do? \n(Please choose by number or press E to Exit)");
				System.out.println("\nCourses Management\n--------------------\n(1) Create a new course\n(2) Delete a course\n(3) Edit a course\n(4) Display Course Information\n(5) Register a student\n\nReports\n--------------------\n(6) View all courses\n(7) View full courses\n(8) Create FullCourses File\n(9) View students in course\n(10) View courses of student\n(11) Sort courses\n--------------------\n(E) Exit Program \n"); 
				String answer2 = input1.next();
				//All methods explained in Admin and User classes
				if (answer2.equals("1")){
					admin.CreateCourse();;
				}
				else if (answer2.equals("2")){
					admin.DeleteCourse();
				}
				else if (answer2.equals("3")){
					admin.EditCourse();
				}
				else if (answer2.equals("4")){
					while (true){//ensure the name of the course and section number user inputs are valid using try catch
					Scanner input3 = new Scanner(System.in);
					System.out.println("\nEnter the name of the course:");
					String temp_co = input3.nextLine();
					System.out.println("Enter the section number: ");
					int temp_sec=0;
					try{
					temp_sec = input3.nextInt();}
					catch(Exception e){
					}
					if (courses.contains(admin.GetCourse(temp_co, temp_sec))){
					admin.ViewACourse(temp_co, temp_sec);
					break;
					}
					else{
						System.out.println("Invalid course name or course section!");
					}
					}
				}
				else if (answer2.equals("5")){
					admin.RegisterStudent();
					Thread.sleep(1000);
				}
				else if (answer2.equals("6")){
					admin.ViewCourses();;
				}
				else if (answer2.equals("7")){
					admin.ViewFullCourses();
				}
				else if (answer2.equals("8")){
					admin.CreateFullCoursesFile();
				}
				else if (answer2.equals("9")){
					admin.StudentsInCourse();
				}
				else if (answer2.equals("10")){
					admin.CoursesOfStudent();
				}
				else if (answer2.equals("11")){
					System.out.println("Sorting courses...");
					Thread.sleep(1500);
					admin.SortCourses(admin.courses);
					System.out.println("Sorting complete!");
					Thread.sleep(700);
					System.out.println("Courses have been sorted based on the number of student registers!\n");
				}
				else if (answer2.equalsIgnoreCase("E")){
					try{
						//serialize both courses and students arraylist and all changes that have been made during the program
						
						FileOutputStream fos = new FileOutputStream("courses.ser");
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(admin.courses);
						oos.close();
						fos.close();
					}catch(IOException ioe){
						ioe.printStackTrace();}
						try{
							FileOutputStream fos = new FileOutputStream("students.ser");
							ObjectOutputStream oos = new ObjectOutputStream(fos);
							oos.writeObject(admin.students);
							oos.close();
							fos.close();
						}catch(IOException ioe2){
							ioe2.printStackTrace();
					}
					admin.Exit();
					
				}
				
				else{
					System.out.println("Invalid response! Please try again.");
				}
			}
			
			
			
	}//Case created if user is student through else if statement
			else if (answer1.equalsIgnoreCase("s")){
				String username1="";
				String password1="";
				while (true){//ensure verification of student 
				System.out.println("Please enter your username: ");
				username1 = input1.next();
				System.out.println("Please enter your password: ");
				password1 = input1.next();
				for (Student student1: students){
					//iterates through the students ArrayList to look for student based on username and password
					if ((student1.GetUsername().equals(username1)&&student1.GetPassword().equals(password1))){
						student1.courses=courses;
		
						//Welcome student using their respective first name
						System.out.println("\nWelcome "+student1.first_name+"!\n");
						while(true){
							//allow students to read through menu
							//all Student and User class methods are explained in their class
						System.out.println("What would you like to do? \n(Please choose by number or press E to Exit)");
						System.out.println("\nReports\n--------------------\n(1) View all courses\n(2) View available courses\n(3) Register to course\n(4) Withdraw from course\n(5) View my courses\n--------------------\n(E) Exit");
						String answer_s = input1.next();
						if (answer_s.equals("1")){
							student1.ViewCourses();
						}
						else if (answer_s.equals("2")){
							student1.ViewAvailableCourses();
						}
						else if (answer_s.equals("3")){
							student1.RegisterToCourse();
						}
						else if (answer_s.equals("4")){
							student1.WithdrawCourse(admin.courses);
						}
						else if (answer_s.equals("5")){ 
							//reference student's arraylist of courses registered (created in student class)
							ArrayList<Course> MyCourses=student1.CoursesRegistered;
							if (MyCourses.size()==0){
								System.out.println("No registered courses to view!");
								}
							for (Course course:MyCourses){
								//loops through courses and prints course info to console
								student1.ViewACourse(course.course_name, course.section_number);
							}
							
						}//similar to exiting in admin option, serializes all courses and students arraylists upon exiting
						else if (answer_s.equalsIgnoreCase("E")){
							try{
								FileOutputStream fos = new FileOutputStream("courses.ser");
								ObjectOutputStream oos = new ObjectOutputStream(fos);
								oos.writeObject(admin.courses);
								oos.close();
								fos.close();
							}catch(IOException ioe){
								ioe.printStackTrace();}
								try{
									FileOutputStream fos = new FileOutputStream("students.ser");
									ObjectOutputStream oos = new ObjectOutputStream(fos);
									oos.writeObject(admin.students);
									oos.close();
									fos.close();
								}catch(IOException ioe2){
									ioe2.printStackTrace();
							}
							student1.Exit();
							
						}
						else{//response to not choosing correctly from the student menu
							System.out.println("Invalid response! Please try again.");
						}
					}
					
					}

				}//response to not entering correct information
				System.out.println("Invalid username or password! Please try again.");
				}
				
				
				}
			}
			
	}

			
			

			
		




