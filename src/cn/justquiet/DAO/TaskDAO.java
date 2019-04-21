package cn.justquiet.dao;

import java.util.List;

import cn.justquiet.bean.Check;
import cn.justquiet.bean.Task;

public interface TaskDAO {
	public int executeQueryCidByClassname(String classname);
	public boolean executeQueryTkcodes(String tkcodes);
	public boolean executeSetTask(Task task);
	public List<Task> executeQueryTaskByCid(int cid, int status);
	public boolean executeUpdateTaskStatus(int status, int tkid);
	public boolean executeSetClassTask(Check check);
	public boolean executeUpdateClassTask(Check check);
}
