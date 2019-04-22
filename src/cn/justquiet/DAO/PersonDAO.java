package cn.justquiet.dao;

import java.util.List;

import cn.justquiet.bean.Student;
import cn.justquiet.bean.Teacher;

public interface PersonDAO {
	public int isStudent(String id,String password);
	public int isTeacher(String id,String password);
	public List<Student> executeQueryStuById(String id);
	public List<Teacher> executeQueryTeaById(String id);
	public List<Student> executeQueryStuByCid(int cid); 
}
