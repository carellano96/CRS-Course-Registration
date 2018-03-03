package arellano_HW1;//create an interface for required methods of student
import java.util.ArrayList;
public interface GeneralStudent{
public void ViewAvailableCourses();
public void RegisterToCourse();
public void WithdrawCourse(ArrayList<Course> courses);
public ArrayList<String> GetRegisteredCourses();
}
