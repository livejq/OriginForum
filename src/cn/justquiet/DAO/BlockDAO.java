package cn.justquiet.dao;

import java.util.List;

import cn.justquiet.bean.Question;

public interface BlockDAO {
	public List<Question> executeQueryQuestion(int qtype);
}
