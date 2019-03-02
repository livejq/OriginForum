package cn.justquiet.ServiceImpl;

import cn.justquiet.Beans.Question;
import cn.justquiet.DAOImpl.QuestionDAOImpl;
import cn.justquiet.Service.QuestionBusiness;

public class QuestionBusinessImpl implements QuestionBusiness {

	@Override
	public int QueryQstType(String qvalue) {
		QuestionDAOImpl qdi = new QuestionDAOImpl();
		return qdi.executeQueryQstType(qvalue);
	}

	@Override
	public int SetQuestion(Question question) {
		QuestionDAOImpl qdi = new QuestionDAOImpl();
		return qdi.executeSetQuestion(question);
		
	}

	@Override
	public boolean SetStuQstId(String sid, int qid) {
		QuestionDAOImpl qdi = new QuestionDAOImpl();
		return qdi.executeSetStuQstId(sid, qid);
		
	}

	@Override
	public boolean SetQstAnswer(int qid , boolean hasNews) {
		QuestionDAOImpl qdi = new QuestionDAOImpl();
		return qdi.executeSetQstAnswer(qid , hasNews);
	}

	@Override
	public int QueryQstAnswer(int qid) {
		QuestionDAOImpl qdi = new QuestionDAOImpl();
		return qdi.executeQueryQstAnswer(qid);
	}

}
