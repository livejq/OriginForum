package cn.justquiet.DAO;

import java.util.List;

import cn.justquiet.Beans.Answer;

public interface AnswerDAO {
	public List<Answer> executeQueryAnsByQid(int qid);
	public boolean executeSetAnswer(Answer answer);
}
