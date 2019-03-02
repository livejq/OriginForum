package cn.justquiet.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.justquiet.Beans.Answer;
import cn.justquiet.DAO.AnswerDAO;
import cn.justquiet.DB.DBConnection;
import cn.justquiet.DB.DBUtils;

public class AnswerDAOImpl implements AnswerDAO{

	@Override
	public List<Answer> executeQueryAnsByQid(int qid) {
		Connection con = null;
		String sql = "select * from tb_ans where qid = '"+qid+"'";
		Statement stmt = null;
		ResultSet rs = null;
		List<Answer> listans = null;
		try {
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			listans = new ArrayList<Answer>();
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int i = 2;
				Answer ans = new Answer();
				ans.setQid(rs.getInt(i++));
				ans.setNickname(rs.getString(i++));
				ans.setLevel(rs.getString(i++));
				Date time=new Date(rs.getTimestamp(i++).getTime());//java.util.Date
				String date = simpleDateFormat.format(time);
				ans.setDate(date);
				ans.setAnswer(rs.getString(i++));
				ans.setSupport(rs.getInt(i++));
				ans.setAgainst(rs.getInt(i));
				listans.add(ans);
			}
			return listans;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return listans;
	}

	@Override
	public boolean executeSetAnswer(Answer answer) {
		Connection con = null;
		String sql = "insert into tb_ans values(default,?,?,?,?,?,default,default)";
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getConnection();
			ptmt = con.prepareStatement(sql);
			int i = 1;
			ptmt.setInt(i++, answer.getQid());
			ptmt.setString(i++, answer.getNickname());
			ptmt.setString(i++, answer.getLevel());
			ptmt.setString(i++, answer.getDate());
			ptmt.setString(i++, answer.getAnswer());
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

}
