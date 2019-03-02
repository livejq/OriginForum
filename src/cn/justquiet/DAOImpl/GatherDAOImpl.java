package cn.justquiet.DAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.justquiet.Beans.Check;
import cn.justquiet.Beans.Student;
import cn.justquiet.Beans.Task;
import cn.justquiet.DAO.GatherDAO;
import cn.justquiet.DB.DBConnection;
import cn.justquiet.DB.DBUtils;
import cn.justquiet.Utils.ConvertUtils;

public class GatherDAOImpl implements GatherDAO{

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
				if(ConvertUtils.isTaskOk(date)) {//判定是否过期
					if(status==0) {
						TaskDAOImpl ti = new TaskDAOImpl();
						ti.executeUpdateTaskStatus(1, rs.getInt(1));
						tk.setStatus(1);
					}
				}else {
					tk.setCutoff(date);
					tk.setStatus(0);
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
