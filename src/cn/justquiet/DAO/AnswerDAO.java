package cn.justquiet.dao;

import java.util.List;

import cn.justquiet.bean.Answer;

public interface AnswerDAO {
	public List<Answer> executeQueryAnsByQid(int qid);
	public boolean executeSetAnswer(Answer answer);
}
