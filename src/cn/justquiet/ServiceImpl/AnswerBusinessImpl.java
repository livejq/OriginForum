package cn.justquiet.ServiceImpl;

import java.util.List;

import cn.justquiet.Beans.Answer;
import cn.justquiet.DAOImpl.AnswerDAOImpl;
import cn.justquiet.Service.AnswerBusiness;

public class AnswerBusinessImpl implements AnswerBusiness{

	@Override
	public List<Answer> QueryAnsByQid(int qid) {
		AnswerDAOImpl ad = new AnswerDAOImpl();
		return ad.executeQueryAnsByQid(qid);
	}

	@Override
	public boolean SetAnswer(Answer answer) {
		AnswerDAOImpl ad = new AnswerDAOImpl();
		return ad.executeSetAnswer(answer);
	}

}
