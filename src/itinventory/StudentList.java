package itinventory;

import java.util.ArrayList;

public class StudentList 
{
    private final ArrayList<Student> studentList;
    
    public StudentList()
    {
        studentList = new ArrayList<>();
        addStudentsToList();
    }
    
    private void addStudentsToList()
    {
        getStudentList().add(new Student("MVC5624", "John", "Doe", "2/11/19(10:30)", "2/13/19(11:54)", 5));
        getStudentList().add(new Student("HAO3208", "Jacob", "Rimland", "2/11/19(11:35)", "2/13/19(11:50)", 9));
        getStudentList().add(new Student("DBP5208", "Daniel", "Palmer", "2/11/19(11:30)", "2/13/19(11:15)", 11));
    }
    
    public ArrayList<Student> getStudentList()
    {
        return studentList;
    }
}
