package cn.justquiet.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.justquiet.bean.Check;
import cn.justquiet.bean.Student;
import cn.justquiet.bean.Task;
import cn.justquiet.dao.GatherDAO;
import cn.justquiet.db.DBConnection;
import cn.justquiet.db.DBUtils;
import cn.justquiet.util.ConvertUtils;

public class GatherDAOImpl implements GatherDAO{

	/**
	 * 根据教师id和完成状态来查找其布置过的所有任务，status值：1 完成, 0 未完成
	 * 
	 * @param tid 教师编号
	 * @param status 状态码
	 */
	@Override
	public List<Task> executeQueryTaskByTid(int tid, int status) {
		List<Task> listtask = null;
		Connection con = null;
		String sql = "select * from tb_task where tid = '"+tid+"' and status = '"+status+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			listtask = new ArrayList<Task>();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Task tk = new Task();
				int i = 2;
				tk.setTid(rs.getInt(i++));
				tk.setTname(rs.getString(i++));
				tk.setCid(rs.getInt(i++));
				tk.setTkcodes(rs.getString(i++));
				tk.setTktitle(rs.getString(i++));
				tk.setTkcontent(rs.getString(i++));
				tk.setAttach(rs.getString(i++));
				tk.setPath(rs.getString(i++));
				String date = ConvertUtils.getCutoffTime(rs.getTimestamp(i++), rs.getInt(i++));
				if(ConvertUtils.isTaskOk(date)) {// 判定是否过期
					if(status == 0) {
						TaskDAOImpl ti = new TaskDAOImpl();
						ti.executeUpdateTaskStatus(1, rs.getInt(1));// 设置数据表中对应的任务状态为1
						tk.setStatus(1);
					}
				}else {// 设置截止日期和状态
					tk.setCutoff(date);
					tk.setStatus(0);// 0表示未截止
				}
				listtask.add(tk);// 将结果加入到List中以便返回
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return listtask;
	}

	/**
	 * 根据任务码（教师每布置一个任务，对应随机生成（生成前会监测是否已存在）一个唯一编号）和状态码（）来返回一个
	 * 
	 * @param tkcodes 作业码
	 * @param status 完成状态
	 */
	@Override
	public List<Check> executeQueryCheckByTkcodes(String tkcodes, int status) {
		List<Check> listcheck = null;
		Connection con = null;
		String sql = "select * from tb_check where tkcodes = '"+tkcodes+"' and status = '"+status+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			listcheck = new ArrayList<Check>();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Check ck = new Check();
				int i = 2;
				ck.setTid(rs.getInt(i++));
				ck.setTkcodes(rs.getString(i++));
				ck.setCname(rs.getString(i++));
				ck.setSid(rs.getString(i++));
				ck.setSname(rs.getString(i++));
				ck.setCutoff(ConvertUtils.getJQTime(rs.getTimestamp(i++)));
				ck.setDate(ConvertUtils.getTime(new Date(rs.getTimestamp(i++).getTime())));
				ck.setStkname(rs.getString(i++));
				ck.setPath(rs.getString(i++));
				ck.setStatus(rs.getInt(i++));
				ck.setScore(rs.getInt(i));
				listcheck.add(ck);// 将结果加入到List中以便返回
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return listcheck;
	}

	/**
	 * 根据作业码和完成状态查找学生完成进度，返回没有完成的学生姓名
	 * 
	 * @param tkcodes 作业码
	 * @param status 完成状态
	 */
	@Override
	public List<Student> executeQueryCheckNotDone(String tkcodes, int status) {
		List<Student> liststu = null;
		Connection con = null;
		String sql = "select * from tb_check where tkcodes = '"+tkcodes+"' and status = '"+status+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			liststu = new ArrayList<Student>();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Student stu = new Student();
				stu.setSname(rs.getString(6));
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
	 * 根据班级编号返回班级人数
	 * 
	 * @param cid 班级编号
	 */
	@Override
	public int executeQueryClassMember(int cid) {
		Connection con = null;
		String sql = "select * from tb_class where cid = '"+cid+"'";
		Statement stmt = null;
		ResultSet rs = null;
		int all = -1;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return rs.getInt(4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return all;
	}

	/**
	 * 返回任务截止日期
	 * 
	 * @param tkcodes 任务码
	 */
	@Override
	public String executeQueryCutoff(String tkcodes) {
		Connection con = null;
		String sql = "select * from tb_check where tkcodes = '"+tkcodes+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return ConvertUtils.getTime(new Date(rs.getTimestamp(7).getTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return null;
	}

}
