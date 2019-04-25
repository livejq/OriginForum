package cn.justquiet.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.justquiet.bean.Student;
import cn.justquiet.bean.Teacher;
import cn.justquiet.dao.PersonDAO;
import cn.justquiet.db.DBConnection;
import cn.justquiet.db.DBUtils;

public class PersonDAOImpl implements PersonDAO{

	/**
	 * 判断学生用户名和密码是否正确，并返回相应准确的错误信息
	 * （用数字表示返回结果，3 用户名和密码正确、2 密码错误、1 用户名错误、0 用户名和密码都错误）
	 * 
	 * @param id
	 * @param password
	 */
	@SuppressWarnings("resource")
	@Override
	public int isStudent(String id, String password) {
		int flag = 0;
		Connection con = null;
		String sql = "select * from tb_student where sid = '"+id+"' and spassword = '"+password+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs != null && rs.next()) {
				flag = 3;
				return flag;
			}else {
				sql = "select * from tb_student where sid = '"+id+"'";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				if(rs != null && rs.next()) {
					flag = 2;
					return flag;
				}else {
					sql = "select * from tb_student where spassword = '"+password+"'";
					stmt = con.prepareStatement(sql);
					rs = stmt.executeQuery(sql);
					if(rs != null && rs.next()) {
						flag = 1;
						return flag;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return flag;
	}
	
	/**
	 * 判断教师用户名和密码是否正确，并返回相应准确的错误信息
	 * （用数字表示返回结果，3 用户名和密码正确、2 密码错误、1 用户名错误、0 用户名和密码都错误）
	 * 
	 * @param id
	 * @param password
	 */
	@SuppressWarnings("resource")
	@Override
	public int isTeacher(String id, String password) {
		int flag = 0;
		Connection con = null;
		String sql = "select * from tb_teacher where taccount = '"+id+"' and tpassword = '"+password+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs!=null&&rs.next()) {
				flag = 3;
				return flag;
			}else {
				sql = "select * from tb_teacher where taccount = '"+id+"'";
				stmt = con.prepareStatement(sql);
				rs = stmt.executeQuery(sql);
				if(rs!=null&&rs.next()) {
					flag = 2;
					return flag;
				}else {
					sql = "select * from tb_teacher where tpassword = '"+password+"'";
					stmt = con.prepareStatement(sql);
					rs = stmt.executeQuery(sql);
					if(rs!=null&&rs.next()) {
						flag = 1;
						return flag;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return flag;
	}
	
	
	/**
	 * 根据学生id查询学生信息
	 * 
	 * @param sql 数据库查询语句--查询指定学生信息
	 * @return List 返回结果--List类型
	 */
	@Override
	public List<Student> executeQueryStuById(String sid) {
		List<Student> liststu = null;
		Connection con = null;
		String sql = "select * from tb_student where sid = '"+sid+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			liststu = new ArrayList<Student>();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				int i = 1;
				stu.setSid(rs.getString(i++));
				stu.setSpassword(rs.getString(i++));
				stu.setSname(rs.getString(i++));
				stu.setSnickname(rs.getString(i++));
				stu.setCid(rs.getInt(i++));
				stu.setSlevel(rs.getInt(i++));
				stu.setSexperience(rs.getString(i++));
				stu.setSactive(rs.getInt(i++));
				liststu.add(stu);// 将结果加入到List中以便返回
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return liststu;
	}

	/**
	 * 根据教师id查询教师信息
	 * 
	 * @param sql 数据库查询语句--查询指定教师信息
	 * @return List 返回结果--List类型
	 */
	@Override
	public List<Teacher> executeQueryTeaById(String tid) {
		List<Teacher> listtea = null;
		Connection con = null;
		String sql = "select * from tb_teacher where taccount = '"+tid+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			listtea = new ArrayList<Teacher>();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Teacher tea = new Teacher();
				int i = 1;
				tea.setTid(rs.getInt(i++));
				tea.setTaccount(rs.getString(i++));
				tea.setTpassword(rs.getString(i++));
				tea.setTname(rs.getString(i++));
				tea.setTnickname(rs.getString(i++));
				tea.setTlevel(rs.getString(i++));
				tea.setTexperience(rs.getString(i++));
				tea.setTactive(rs.getInt(i++));
				listtea.add(tea);// 将结果加入到List中以便返回
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return listtea;
	}

	/**
	 * 根据班级id查询所有学生信息
	 */
	@Override
	public List<Student> executeQueryStuByCid(int cid) {
		List<Student> liststu = null;
		Connection con = null;
		String sql = "select * from tb_student where cid = '"+cid+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			liststu = new ArrayList<Student>();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setSid(rs.getString(1));
				stu.setSname(rs.getString(3));
				liststu.add(stu);// 将结果加入到List中以便返回
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return liststu;
	}



}
