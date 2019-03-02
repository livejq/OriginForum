package cn.justquiet.ServiceImpl;

import java.util.List;

import cn.justquiet.Beans.Check;
import cn.justquiet.Beans.Student;
import cn.justquiet.Beans.Task;
import cn.justquiet.DAOImpl.GatherDAOImpl;
import cn.justquiet.Service.GatherBusiness;

public class GatherBusinessImpl implements GatherBusiness{

	@Override
	public List<Task> QueryTaskByTid(int tid, int status) {
		GatherDAOImpl gdi = new GatherDAOImpl();
		return gdi.executeQueryTaskByTid(tid, status);
	}

	@Override
	public List<Check> QueryCheckByTkcodes(String tkcodes, int status) {
		GatherDAOImpl gdi = new GatherDAOImpl();
		return gdi.executeQueryCheckByTkcodes(tkcodes, status);
	}

	@Override
	public List<Student> QueryCheckNotDone(String tkcodes, int status) {
		GatherDAOImpl gdi = new GatherDAOImpl();
		return gdi.executeQueryCheckNotDone(tkcodes, status);
	}

	@Override
	public int QueryClassMember(int cid) {
		GatherDAOImpl gdi = new GatherDAOImpl();
		return gdi.executeQueryClassMember(cid);
	}

	@Override
	public String QueryCutoff(String tkcodes) {
		GatherDAOImpl gdi = new GatherDAOImpl();
		return gdi.executeQueryCutoff(tkcodes);
	}

}
