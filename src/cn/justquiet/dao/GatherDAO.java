package cn.justquiet.dao;

import java.util.List;

import cn.justquiet.bean.Check;
import cn.justquiet.bean.Student;
import cn.justquiet.bean.Task;

public interface GatherDAO {
	public List<Task> executeQueryTaskByTid(int tid, int status);//每次查询都要计算是否已过期,Task对象里面有下面方法所需的参数
	public List<Check> executeQueryCheckByTkcodes(String tkcodes, int status);//获得status为1的已提交的同学
	public List<Student> executeQueryCheckNotDone(String tkcodes, int status);//获得status为0的未提交的同学
	public int executeQueryClassMember(int cid);//查询班级总人数
	public String executeQueryCutoff(String tkcodes);//查询显示截止日期（无论有没有人上交作业都可以得到截止日期）
}
