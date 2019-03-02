package cn.justquiet.DAO;

import cn.justquiet.Beans.Question;

public interface QuestionDAO {
	public int executeQueryQstType(String qvalue);
	public int executeSetQuestion(Question question);
	public boolean executeSetQstAnswer(int qid,  boolean hasNews);
	public int executeQueryQstAnswer(int qid);
}
