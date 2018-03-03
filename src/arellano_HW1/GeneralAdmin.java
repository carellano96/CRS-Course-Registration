package arellano_HW1;
import java.util.ArrayList;//create an interface of required methods for admin
public interface GeneralAdmin{
abstract void CreateCourse();
public void DeleteCourse();
public void EditCourse();
public void ViewACourse(String course_id, int section);
public void RegisterStudent(ArrayList<Course> Courses);
public void ViewFullCourses();
public void CreateFullCoursesFile();
public void StudentsInCourse();
public void CoursesOfStudent();
public void SortCourses(ArrayList<Course> courses);
public void Exit();

}
