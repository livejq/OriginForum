package cn.justquiet.ServiceImpl;

import java.util.List;

import cn.justquiet.Beans.Question;
import cn.justquiet.DAOImpl.BlockDAOImpl;
import cn.justquiet.Service.BlockBusiness;

public class BlockBusinessImpl implements BlockBusiness{

	@Override
	public List<Question> QueryQuestion(int qtype) {
		BlockDAOImpl bd = new BlockDAOImpl();
		return bd.executeQueryQuestion(qtype);
	}

}
