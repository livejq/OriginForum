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

	@SuppressWarnings("resource")
	@Override
	public int isStudent(String id, String password) {
		int flag = 0;
		Connection con = null;
		String sql = "select * from tb_student where sid = '"+id+"' and spassword = '"+password+"'";
//		PreparedStatement ptmt;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
//			ptmt = con.prepareStatement(sql);
//			ptmt.setString(1, id);
//			ptmt.setString(2, password);
			rs = stmt.executeQuery(sql);
			if(rs!=null&&rs.next()) {
				flag = 3;
				return flag;
			}else {
				sql = "select * from tb_student where sid = '"+id+"'";
				stmt = con.prepareStatement(sql);
//				ptmt.setString(1, id);
				rs = stmt.executeQuery(sql);
				if(rs!=null&&rs.next()) {
					flag = 2;
					return flag;
				}else {
					sql = "select * from tb_student where spassword = '"+password+"'";
					stmt = con.prepareStatement(sql);
//					ptmt.setString(1, password);
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

	@SuppressWarnings("resource")
	@Override
	public int isTeacher(String id, String password) {
		int flag = 0;
		Connection con = null;
		String sql = "select * from tb_teacher where taccount = '"+id+"' and tpassword = '"+password+"'";
//		PreparedStatement ptmt;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
//			ptmt = con.prepareStatement(sql);
//			ptmt.setString(1, id);
//			ptmt.setString(2, password);
			rs = stmt.executeQuery(sql);
			if(rs!=null&&rs.next()) {
				flag = 3;
				return flag;
			}else {
				sql = "select * from tb_teacher where taccount = '"+id+"'";
				stmt = con.prepareStatement(sql);
//				ptmt.setString(1, id);
				rs = stmt.executeQuery(sql);
				if(rs!=null&&rs.next()) {
					flag = 2;
					return flag;
				}else {
					sql = "select * from tb_teacher where tpassword = '"+password+"'";
					stmt = con.prepareStatement(sql);
//					ptmt.setString(1, password);
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
	
	
	/***************************************************************************
	 * 查询学生信息
	 * 
	 * @param sql
	 *            数据库查询语句--查询指定学生信息
	 * @return List 返回结果--List类型
	 */
	@Override
	public List<Student> executeQueryStuById(String id) {
		List<Student> liststu = null;
		Connection con = null;
		String sql = "select * from tb_student where sid = '"+id+"'";
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

	/***************************************************************************
	 * 查询教师信息
	 * 
	 * @param sql
	 *            数据库查询语句--查询指定教师信息
	 * @return List 返回结果--List类型
	 */
	@Override
	public List<Teacher> executeQueryTeaById(String id) {
		List<Teacher> listtea = null;
		Connection con = null;
		String sql = "select * from tb_teacher where taccount = '"+id+"'";
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
