package cn.justquiet.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.justquiet.bean.Check;
import cn.justquiet.bean.Task;
import cn.justquiet.dao.TaskDAO;
import cn.justquiet.db.DBConnection;
import cn.justquiet.db.DBUtils;
import cn.justquiet.util.ConvertUtils;

public class TaskDAOImpl implements TaskDAO{

	@Override
	public int executeQueryCidByClassname(String classname) {
		Connection con = null;
		String sql = "select * from tb_class where cname = '"+classname+"'";
		Statement stmt = null;
		ResultSet rs = null;
		int cid = 0;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				cid = rs.getInt(1);
				return cid;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return cid;
	}

	@Override
	public boolean executeQueryTkcodes(String tkcodes) {
		Connection con = null;
		String sql = "select * from tb_task where tkcodes = '"+tkcodes+"'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return false;
	}

	@Override
	public boolean executeSetTask(Task task) {
		Connection con = null;
		String sql = "insert into tb_task values(default,?,?,?,?,?,?,?,?,?,?,default)";
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			ptmt = con.prepareStatement(sql);
			int i = 1;
			ptmt.setInt(i++, task.getTid());
			ptmt.setString(i++, task.getTname());
			ptmt.setInt(i++, task.getCid());
			ptmt.setString(i++, task.getTkcodes());
			ptmt.setString(i++, task.getTktitle());
			ptmt.setString(i++, task.getTkcontent());
			ptmt.setString(i++, task.getAttach());
			ptmt.setString(i++, task.getPath());
			ptmt.setString(i++, task.getDate());
			ptmt.setInt(i, task.getDeadline());
			if(ptmt.executeUpdate()==1){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, ptmt, con);
		}
		return false;
	}

	@Override
	public List<Task> executeQueryTaskByCid(int cid, int status) {
		List<Task> listtask = null;
		Connection con = null;
		String sql = "select * from tb_task where cid = '"+cid+"' and status = '"+status+"'";
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
					if(rs.getInt(12)==0) {
//						rs.updateInt(i, 1);//??????????????
						executeUpdateTaskStatus(1, rs.getInt(1));
						tk.setStatus(1);
						listtask.add(tk);// 将结果加入到List中以便返回
					}else {
						tk.setStatus(1);
						listtask.add(tk);
					}
				}else {
					tk.setCutoff(date);
					tk.setStatus(0);
					listtask.add(tk);// 将结果加入到List中以便返回
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return listtask;
	}

	@Override
	public boolean executeUpdateTaskStatus(int status, int tkid) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "update tb_task set status = '"+status+"' where tkid = '"+tkid+"'";
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			if(stmt.executeUpdate(sql)==1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return false;
	}

	@Override
	public boolean executeSetClassTask(Check check) {
		Connection con = null;
		String sql = "insert into tb_check(tid,tkcodes,cname,sid,sname,cutoff) values(?,?,?,?,?,?)";
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			ptmt = con.prepareStatement(sql);
			int i = 1;
			ptmt.setInt(i++, check.getTid());
			ptmt.setString(i++ ,check.getTkcodes());
			ptmt.setString(i++, check.getCname());
			ptmt.setString(i++, check.getSid());
			ptmt.setString(i++, check.getSname());
			ptmt.setString(i++, check.getCutoff());
			if(ptmt.executeUpdate()==1){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, ptmt, con);
		}
		return false;
	}

	@Override
	public boolean executeUpdateClassTask(Check check) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "update tb_check set date = '"+check.getDate()+"' where tkcodes = '"+check.getTkcodes()+"' and sid = '"+check.getSid()+"'";
		String sql2 = "update tb_check set stkname = '"+check.getStkname()+"' where tkcodes = '"+check.getTkcodes()+"' and sid = '"+check.getSid()+"'";
		String sql3 = "update tb_check set path = '"+check.getPath()+"' where tkcodes = '"+check.getTkcodes()+"' and sid = '"+check.getSid()+"'";
		String sql4 = "update tb_check set status = 1 where tkcodes = '"+check.getTkcodes()+"' and sid = '"+check.getSid()+"'";
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			if(stmt.executeUpdate(sql)==1&&stmt.executeUpdate(sql2)==1&&stmt.executeUpdate(sql3)==1&&stmt.executeUpdate(sql4)==1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return false;
	}
	
}
