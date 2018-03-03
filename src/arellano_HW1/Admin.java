package arellano_HW1;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Admin extends User implements java.io.Serializable {
//Create a new arraylist of type student
	ArrayList<Student> students = new ArrayList<Student>();
public Admin(){};//empty constructor
public Admin(ArrayList<Course>Courses){
	super(Courses);
	SetUsername("Admin");
	SetPassword("Admin001");//constructor setting up default username and password
	//constructor also inserting the arraylist created in program into the admin object
}
//Getstudent method to return student object
protected Student GetStudent(String first, String last ){
	Student desired = new Student("","");
	for (int i=0;i<students.size();i++){
		if (students.get(i).first_name.equals(first)&& students.get(i).last_name.equals(last)){
			desired = students.get(i);
		}
	}
	return desired;
}
//view courses shows all courses
protected void ViewCourses(){
	for (int i=0; i<courses.size();i++){
		ViewACourse(courses.get(i).course_name,courses.get(i).section_number);
}
}//overrided method from User parent class to show more information for admin
	public void ViewACourse(String course, int section){
		for (int i=0;i<courses.size();i++){
				if (courses.get(i).course_name.equals(course)&&courses.get(i).section_number==section){
					System.out.println("Course Name: "+courses.get(i).course_name);
					System.out.println("Course ID: "+courses.get(i).course_id);
					System.out.println("Maximum Students to Enroll: "+courses.get(i).maximum);
					System.out.println("Current Students: "+courses.get(i).current);
					System.out.println("Course Instructor: "+courses.get(i).instructor);
					System.out.println("Course Section Number: "+courses.get(i).section_number);
					System.out.println("Course Location: "+courses.get(i).location);
					System.out.println("\n");

					}
				}
			}//create a new course and inserting into course arraylist
	protected void CreateCourse(){
		Scanner input = new Scanner(System.in);
		System.out.println("What is the name of the course?");
		String temp_course_name = input.nextLine();
		System.out.println("What is the course ID?");
		String temp_id = input.nextLine();
		int temp_max=0;
		while (true){
		System.out.println("What is the maximum amount of students you would like the class to have?");
		if (input.hasNextInt()){
			break;
		}
		else{
		System.out.println("That is not a number! Please try again.");
		input.next();
		
		}
		}
		System.out.println("What is the instructor's first name?");	
		Scanner input_f = new Scanner(System.in);
		String temp_firstname = input_f.next();
		System.out.println("What is the instructor's last name?");
		String temp_lastname = input_f.next();
		String temp_name= temp_firstname+" "+temp_lastname;
		System.out.println("What is the course section number?");
		int temp_section = input_f.nextInt();
		System.out.println("Where is the course location?");
		Scanner location = new Scanner(System.in);
		String temp_location = location.nextLine();
		Course temp_course = new Course(temp_course_name, temp_id,temp_max,0,"NULL",temp_name,temp_section, temp_location);
		courses.add(temp_course);
		System.out.println("\nCourse successfully added!\n");
	}//remove course by getting name and section number of course
	protected void DeleteCourse(){
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the course name: ");
		String answer = input.nextLine();
		System.out.println("Please enter course section: ");
		int section = input.nextInt();
		while (true){
		if (GetCourse(answer,section).course_name.equalsIgnoreCase(answer)&&GetCourse(answer,section).section_number==section){
			this.courses.remove(GetCourse(answer,section));
			System.out.println("Course successfully removed!\n");
			break;
		}
		else{
			System.out.println("Invalid course name or section number");
			
		}
		}
	}//edit a course by asking what the user would like to edit about the course
	protected void EditCourse(){

		while (true){
			Scanner input = new Scanner(System.in);
			System.out.println("Enter name of the course:");
			String answer = input.nextLine();
			System.out.println("Enter section number: ");
			int section = input.nextInt();
			Course temp_cours = GetCourse(answer,section);
			if (GetCourse(answer,section).course_name.equalsIgnoreCase(answer)&&GetCourse(answer,section).section_number==section){
				while (true){
				System.out.println("What would you like to edit? (Please choose by number)\n(1) Course Name\n(2) Maximum Students\n(3) Course Instructor\n(4) Course Location");
				String answer2 = input.next();
				if (answer2.equalsIgnoreCase("1")){
					System.out.println("Enter New Course name:");
					Scanner input_name = new Scanner(System.in);
					String name_change = input_name.nextLine();
					temp_cours.course_name = name_change;
					System.out.println("Course name change successful.\n");
					break;
				}
				else if (answer2.equalsIgnoreCase("2")){
					System.out.println("What is the maximum amount of students you would like to have?");
					int max_change = input.nextInt();
					temp_cours.maximum = max_change;
					System.out.println("Maximum Students amount successful.\n");
					break;
				}
				else if (answer2.equalsIgnoreCase("3")){
					System.out.println("Enter the new Instructor name: ");
					Scanner input_ins = new Scanner(System.in);
					String instructor_name = input_ins.nextLine();
					temp_cours.instructor = instructor_name;
					System.out.println("Instructor name change successful!\n");
					break;
				}
				else if (answer2.equalsIgnoreCase("4")){
					System.out.println("Enter new Location name: ");
					Scanner input_loc = new Scanner(System.in);
					String location_change = input_loc.nextLine();
					temp_cours.location = location_change;
					System.out.println("Location name change successful!\n");
					break;
				}

				else{
					System.out.println("Invalid answer!");
				}
			}
			}
			else{
				System.out.println("Invalid Course Name or Section Number!");
				continue;
				
			}
			break;
		}
	}//creating a new student using new Student constructor, giving student only a name 
protected Student CreateStudent(){	
	Scanner input = new Scanner(System.in);
	System.out.println("Please enter full name of student: ");
	String first = input.next();
	String last = input.next();
	Student student = new Student(first,last);
	return student;
}//register a student by calling create a student and then adding them to the student arraylist
//username and password of student created based on the number of students in the arraylist
protected void RegisterStudent(){
	Student student_temp = CreateStudent();
	//username and password of student created based on the number of students in the arraylist
	students.add(student_temp);
	student_temp.SetUsername("Student"+Integer.toString(students.size()));
	student_temp.SetPassword("NewStudent"+Integer.toString(students.size()));
	System.out.println("Student username is: "+student_temp.GetUsername());
	System.out.println("Student password is: "+student_temp.GetPassword());
	students.add(student_temp);
	System.out.println("Registration successful!\n");
	}
//iterates through all courses and checks which ones have matching current size and max size number
protected void ViewFullCourses(){
	int counter=0;
	for (int i=0;i<courses.size();i++){
		int maximum1 = courses.get(i).maximum;
		int current1 = courses.get(i).current;
		if (maximum1==current1){
			counter+=1;
			System.out.println("Course Name: "+courses.get(i).course_name);
			System.out.println("Course ID: "+courses.get(i).course_id);
			System.out.println("Maximum Students to Enroll: "+courses.get(i).maximum);
			System.out.println("Current Students: "+courses.get(i).current);
			System.out.println("Course Instructor: "+courses.get(i).instructor);
			System.out.println("Course Section Number: "+courses.get(i).section_number);
			System.out.println("Course Location: "+courses.get(i).location);
			System.out.println("\n");;
		}
	}
	if (counter==0){
		System.out.println("\nThere are no full courses!\n");
	}
	}
//Creates a full courses file based on current student size in course number
protected void CreateFullCoursesFile() throws InterruptedException{
	System.out.println("Creating FullCourses file...");
	Thread.sleep(1500);
	try{
		PrintWriter writer = new PrintWriter("FullCourses.txt");
		for (int i=0; i<courses.size();i++){
			int maximum1 = courses.get(i).maximum;
			int current1 = courses.get(i).current;

			if (maximum1==current1){
				writer.println("Course Name: "+courses.get(i).course_name);
				writer.println("Course ID: "+courses.get(i).course_id);
				writer.println("Maximum Students to Enroll: "+courses.get(i).maximum);
				writer.println("Current Students: "+courses.get(i).current);
				writer.println("Course Instructor: "+courses.get(i).instructor);
				writer.println("Course Section Number: "+courses.get(i).section_number);
				writer.println("Course Location: "+courses.get(i).location);
				writer.println("\n");;
			}
		}
		System.out.println("Full Course File created!\nFile name: FullCourses.txt\n");
		writer.close();
		Thread.sleep(1000);

	}
		catch(Exception e){
			System.out.println("Error!");
		}

		}
//Shows the student names of students object in StudentsInCourse arraylsit
protected void StudentsInCourse(){
	ArrayList<Student> students = null;
	while (true){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the course name: ");
		String course_name = input.nextLine();
		System.out.println("Enter the section number of the course: ");
		int section = input.nextInt();
		if (courses.contains(GetCourse(course_name,section))){
		students = GetCourse(course_name,section).StudentsInCourse;
		System.out.println("Students enrolled in: \nCourse Name: "+course_name+"\nSection number: "+section+"\n____________________");
		int counter=1;
		if (students.size()==0){
			System.out.println("No students currently registered!\n");
			break;
		}
		for (Student student:students){
			System.out.println(counter+". "+student.first_name+" "+student.last_name);
			counter+=1;
		}
		System.out.println("");
		break;
		}
		else{
			System.out.println("Course does not exist! Please try again!");
		}
		}

}//iterates CoursesofStudent arraylist
protected void CoursesOfStudent(){
	while (true){
	Scanner input = new Scanner(System.in);
	System.out.println("Enter the student's full name: ");
	String first = input.next();
	String last = input.next();
	if ((students.contains(GetStudent(first,last)))){
	ArrayList<Course> courses = GetStudent(first,last).CoursesRegistered;
	if (courses.isEmpty()){
		System.out.println("The student is currently not registered in any course!");
		break;
	}
	else{
	System.out.println("\nThe student is currently enrolled in:\n");
	for (Course course:courses){
		System.out.println("Course Name: "+course.course_name);
		System.out.println("Section Number: "+course.section_number+"\n");
	}
	break;
	}
	}
	else{
		System.out.println("This student is not registered in the system!");
	}
	break;
	}
}//Sorts courses using the Collections function
protected void SortCourses(ArrayList<Course> Courses){
	Collections.sort(this.courses, new Comparator<Course>(){
		@Override 
		public int compare(Course c1,Course c2){
			return c2.current - c1.current;
		}
	});
}
}


	
			
	

