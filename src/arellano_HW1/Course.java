package arellano_HW1;
import java.util.ArrayList;
import java.util.Comparator;
public class Course implements java.io.Serializable{
	ArrayList<Student> StudentsInCourse=new ArrayList<Student>();
String course_name;
String course_id;
int maximum=0;
int current=0;
String names;
String instructor;
int section_number=0;
String location;
	Course(){};
	//creates parameters that holds info as well as creates a StudentsInCourse arraylist
	public Course(String course_name, String course_id, int maximum, int current, String names, String instructor, int section_number,String location){
		this.course_name = course_name;
		this.course_id = course_id;
		this.maximum = maximum;
		this.current = current;
		this.names = names;
		this.instructor = instructor;
		this.section_number = section_number;
		this.location = location;
	}//create an addstudentocourse by adding a student object to course arraylist
	public void AddStudentToCourse(Student student){
		StudentsInCourse.add(student);
	}


}//facilitates sorting of courses based on the current amount of students in course
class CourseStudentRegister implements Comparator<Course>{
	public int compare(Course course1, Course course2){
		return course1.current - course2.current;
	}
	
}
