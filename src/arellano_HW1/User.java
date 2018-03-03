package arellano_HW1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayList;

public class User implements java.io.Serializable{
//implements serializable and creates parameters for user_name and password as well as first and last name
private String user_name="";
private String password="";
protected String first_name;
protected String last_name;
ArrayList<Course> courses = new ArrayList<Course>();
//creates a courses arraylist where course objects will be stored
public User(){};
public User(ArrayList<Course> courses){
this.courses = courses;
}//getters and setters for password and username
public String GetUsername(){
	return user_name;
}
public String GetPassword(){
	return password;}
protected void SetPassword(String password){
	this.password = password;
}
protected void SetUsername(String username){
	this.user_name = username;
}//views basic info for courses (overrided in admin class)
protected void ViewACourse(String course_name,int course_section){
	for (int i=0;i<courses.size();i++){
			if (courses.get(i).course_name.equals(course_name)&&courses.get(i).section_number==course_section){
					System.out.println("Course Name: "+courses.get(i).course_name);
					System.out.println("Course ID: "+courses.get(i).course_id);
					System.out.println("Course Instructor: "+courses.get(i).instructor);
					System.out.println("Course Section Number: "+courses.get(i).section_number);
					System.out.println("Course Location: "+courses.get(i).location);
					System.out.println("\n");
			}
	}
}
//iterates through courses using ViewACourse method
protected void ViewCourses(){
	for (int i=0; i<courses.size();i++){
		ViewACourse(courses.get(i).course_name,courses.get(i).section_number);
		
}
	
}//returns Course object based on the name and section number of course
protected Course GetCourse(String course_name,int section){
	Course temp = new Course();
	for (int i=0; i<courses.size();i++){
		if (courses.get(i).course_name.equals(course_name)&&courses.get(i).section_number==section){
			temp = courses.get(i);
	}
}
	return temp;
}

boolean contains(ArrayList<Course> courses, Course course){
	for (Course course1:courses){
		if (course.course_name.equals(course1.course_name)&&course.section_number==course1.section_number){
			return true;
		}
	}
return false;
}
//exits out of the program using System.exit method
protected void Exit(){
	System.out.println("\nAll changes have been saved.\nThank you for using the Course Registration System (CRS).");
	System.exit(0);
}

}


	

