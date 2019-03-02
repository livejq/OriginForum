package cn.justquiet.Service;

import cn.justquiet.Beans.Question;

public interface QuestionBusiness {
	public int QueryQstType(String qvalue);
	public int SetQuestion(Question question);
	public boolean SetStuQstId(String sid, int qid);
	public boolean SetQstAnswer(int qid , boolean hasNews);
	public int QueryQstAnswer(int qid);
}
