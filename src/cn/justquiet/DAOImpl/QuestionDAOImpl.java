package cn.justquiet.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.justquiet.bean.Question;
import cn.justquiet.dao.QuestionDAO;
import cn.justquiet.db.DBConnection;
import cn.justquiet.db.DBUtils;

public class QuestionDAOImpl implements QuestionDAO {

	public int executeQueryQstType(String qvalue) {
		Connection con = null;
		String sql = "select * from tb_qtype where qvalue = '"+qvalue+"'";
		Statement stmt = null;
		ResultSet rs = null;
		int qtype = 0;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				qtype = rs.getInt(1);
				return qtype;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return qtype;
	}

	//设置为同步方法，一旦学生发布问题便立即获得对应的qid问题编号（同步方法确保数据库中相应的多个表使用的qid相同）
	public synchronized int executeSetQuestion(Question question) {
		Connection con = null;
		String sql = "insert into tb_qst values(default,?,?,?,?,?,?,?,default,default)";
		PreparedStatement ptmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		int qid = 0;
		try {
			con = DBConnection.getConnection();
			ptmt = con.prepareStatement(sql);
			int i = 1;
			ptmt.setInt(i++, question.getQtype());
			ptmt.setString(i++, question.getNickname());
			ptmt.setInt(i++, question.getSlevel());
			ptmt.setString(i++, question.getDate());
			ptmt.setString(i++, question.getTitle());
			ptmt.setString(i++, question.getDetails());
			ptmt.setString(i ,question.getCodes());
			if(ptmt.executeUpdate()==1){
				sql = "select * from tb_qst";
				stmt = con.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.last()) {
					qid = rs.getInt(1);
					return qid;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, ptmt, con);
		}
		return qid;
		
	}
	
	public boolean executeSetStuQstId(String sid, int qid) {
		Connection con = null;
		String sql = "insert into tb_sqst values(?,?,default)";
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			ptmt = con.prepareStatement(sql);
			int i = 1;
			ptmt.setString(i++, sid);
			ptmt.setInt(i, qid);
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
	public boolean executeSetQstAnswer(int qid, boolean hasNews) {
		Connection con = null;
		int count = 0;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			count = executeQueryQstAnswer(qid);
			if(hasNews)
				count++;
			String sql = "update tb_qst set answer = '"+count+"' where qid = '"+qid+"'";
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
	public int executeQueryQstAnswer(int qid) {
		Connection con = null;
		String sql = "select * from tb_qst where qid = '"+qid+"'";
		Statement stmt = null;
		ResultSet rs = null;
		int answer = -1;
		try {
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				answer = rs.getInt(10);
				return answer;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return answer;
	}

}
