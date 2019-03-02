package cn.justquiet.Service;

import java.util.List;

import cn.justquiet.Beans.Question;

public interface BlockBusiness {
	public List<Question> QueryQuestion(int qtype);
}
