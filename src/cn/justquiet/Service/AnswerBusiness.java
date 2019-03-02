package cn.justquiet.Service;

import java.util.List;

import cn.justquiet.Beans.Answer;

public interface AnswerBusiness {
	public List<Answer> QueryAnsByQid(int qid);
	public boolean SetAnswer(Answer answer);
}
