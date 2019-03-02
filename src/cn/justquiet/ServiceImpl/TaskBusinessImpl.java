package cn.justquiet.ServiceImpl;

import java.util.List;

import cn.justquiet.Beans.Check;
import cn.justquiet.Beans.Task;
import cn.justquiet.DAOImpl.TaskDAOImpl;
import cn.justquiet.Service.TaskBusiness;

public class TaskBusinessImpl implements TaskBusiness{

	@Override
	public int QueryCidByClassName(String classname) {
		TaskDAOImpl tdi = new TaskDAOImpl();
		return tdi.executeQueryCidByClassname(classname);
	}

	@Override
	public boolean QueryTkcodes(String tkcodes) {
		TaskDAOImpl tdi = new TaskDAOImpl();
		return tdi.executeQueryTkcodes(tkcodes);
	}

	@Override
	public boolean SetTask(Task task) {
		TaskDAOImpl tdi = new TaskDAOImpl();
		return tdi.executeSetTask(task);
	}

	@Override
	public List<Task> QueryTaskByCid(int cid, int status) {
		TaskDAOImpl tdi = new TaskDAOImpl();
		return tdi.executeQueryTaskByCid(cid, status);
	}

	@Override
	public boolean UpdateTaskStatus(int status, int tkid) {
		TaskDAOImpl tdi = new TaskDAOImpl();
		return tdi.executeUpdateTaskStatus(status, tkid);
	}

	@Override
	public boolean SetClassTask(Check check) {
		TaskDAOImpl tdi = new TaskDAOImpl();
		return tdi.executeSetClassTask(check);
	}

	@Override
	public boolean UpdateClassTask(Check check) {
		TaskDAOImpl tdi = new TaskDAOImpl();
		return tdi.executeUpdateClassTask(check);
	}

}
