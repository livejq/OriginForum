package cn.justquiet.DAO;

import java.util.List;

import cn.justquiet.Beans.Question;

public interface BlockDAO {
	public List<Question> executeQueryQuestion(int qtype);
}
