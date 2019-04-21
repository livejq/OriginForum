package cn.justquiet.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.justquiet.bean.Question;
import cn.justquiet.db.DBConnection;
import cn.justquiet.db.DBUtils;

public class BlockDAOImpl {
	public List<Question> executeQueryQuestion(int qtype) {
		Connection con = null;
		String sql = "select * from tb_qst where qtype = '"+qtype+"'";
		Statement stmt = null;
		ResultSet rs = null;
		List<Question> listqst = null;
		try {
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			listqst = new ArrayList<Question>();
			con = DBConnection.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int i = 1;
				Question qst = new Question();
				qst.setQid(rs.getInt(i++));
				qst.setQtype(rs.getInt(i++));
				qst.setNickname(rs.getString(i++));
				qst.setSlevel(rs.getInt(i++));
				Date time=new Date(rs.getTimestamp(i++).getTime());//java.util.Date
				String date = simpleDateFormat.format(time);
				qst.setDate(date);
				qst.setTitle(rs.getString(i++));
				qst.setDetails(rs.getString(i++));
				qst.setCodes(rs.getString(i++));
				qst.setBrowse(rs.getInt(i++));
				qst.setAnswer(rs.getInt(i++));
				listqst.add(qst);
			}
			return listqst;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeObject(rs, stmt, con);
		}
		return listqst;
	}
}
