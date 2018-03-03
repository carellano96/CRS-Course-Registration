package arellano_HW1;
import java.util.ArrayList;
import java.util.Scanner;
public class Student extends User implements GeneralStudent{
//Create a CoursesRegistered arraylist to hold course objects in student arraylist
ArrayList<Course> CoursesRegistered = new ArrayList<Course>();
//set up a constructor to set the student's name
	Student(String first, String last){
		super.first_name = first;
		super.last_name = last;
	}
//view all avaiable courses that are not full (maximum doesn't equal current)
	public void ViewAvailableCourses(){
		for (int i=0; i<courses.size();i++){
			int maximum1 = courses.get(i).maximum;
			int current1 = courses.get(i).current;
			if (maximum1==current1){
				continue;
			}
			else{
				ViewACourse(courses.get(i).course_name,courses.get(i).section_number);
			}
		}
	}
//Add the current object to the arraylist of StudentsinCourse of the course desired unless they are already in that course
	public void RegisterToCourse(){
		while (true){
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the name of the course:");
		String answer = input.nextLine();
		System.out.println("Enter the section number of the course: ");
		int section = input.nextInt();
		Course desired_course = GetCourse(answer,section);
		int max = GetCourse(answer,section).maximum;
		int current = GetCourse(answer,section).current;
		
		if ((current==max)&&!(courses.contains(desired_course))){
			System.out.println("This course does not exist!\n");
			continue;
			}
		else if ((contains(CoursesRegistered,desired_course))==true){
			System.out.println("You are already registered in this course!");
			continue;
		}
		
		else if ((current==max)){
			System.out.println("Registration unsuccessful. This course is full!\n");
			continue;
		}
		else if (answer.equalsIgnoreCase(desired_course.course_name)&&section==desired_course.section_number){
		desired_course.StudentsInCourse.add(this);
		desired_course.current = desired_course.StudentsInCourse.size();
		CoursesRegistered.add(desired_course);
		System.out.println("You have been successfully registered!\n");
		break;
	}
	

}
	}
	//iterates through StudentsinCourse arraylist and updates the current course size based on the arraylist size
	public void WithdrawCourse(ArrayList<Course> Courses){
		while (true){
			Scanner input_w = new Scanner(System.in);
			System.out.println("Enter the name of the course: ");
			String course = input_w.nextLine();
			System.out.println("Enter the section number: ");
			int section = input_w.nextInt();
			Course temp_course = GetCourse(course,section);
			if (!(Courses.contains(temp_course))){
				System.out.println("This course does not exist!");
				continue;
			}
			
			else if (!(temp_course.StudentsInCourse.contains(this))){
				System.out.println("You are not registered in this course!");
				continue;
			}
			
			for (Student student:temp_course.StudentsInCourse){
			if (student.first_name.equals(this.first_name)&&student.last_name.equals(this.last_name)){
				CoursesRegistered.remove(temp_course);
				temp_course.StudentsInCourse.remove(this);
				temp_course.current= temp_course.StudentsInCourse.size();
				System.out.println("You have successfully been withdrawn from:\n"+temp_course.course_name+"\n");
				break;
			}
			
			
			}
			
			break;
		}
	}//iterates through CoursesRegistered arraylist of student object
	public ArrayList<String> GetRegisteredCourses(){
		ArrayList<String> registered= new ArrayList<String>();
		for (int i=0;i<CoursesRegistered.size();i++){
			registered.add(CoursesRegistered.get(i).course_name);
		}
		return registered;
	}
	

	
}
