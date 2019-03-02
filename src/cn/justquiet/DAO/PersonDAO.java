package cn.justquiet.DAO;

import java.util.List;

import cn.justquiet.Beans.Student;
import cn.justquiet.Beans.Teacher;

public interface PersonDAO {
	public int isStudent(String id,String password);
	public int isTeacher(String id,String password);
	public List<Student> executeQueryStuById(String id);
	public List<Teacher> executeQueryTeaById(String id);
	public List<Student> executeQueryStuByCid(int cid); 
}
