package cn.justquiet.Service;

import java.util.List;

import cn.justquiet.Beans.Check;
import cn.justquiet.Beans.Task;

public interface TaskBusiness {
	public int QueryCidByClassName(String classname);
	public boolean QueryTkcodes(String tkcodes);
	public boolean SetTask(Task task);
	public List<Task> QueryTaskByCid(int cid, int status);
	public boolean UpdateTaskStatus(int status, int tkid);
	public boolean SetClassTask(Check check);
	public boolean UpdateClassTask(Check check);
}
