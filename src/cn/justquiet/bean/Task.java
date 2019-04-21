package cn.justquiet.bean;

public class Task {
	private int tkid;
	private int tid;
	private String tname;
	private int cid;
	private String tkcodes;
	private String tktitle;
	private String tkcontent;
	private String attach = "";//需要检验若没有上传文件时是否会出错
	private String path = "";
	private int status;
	private String date;
	private int deadline;
	private String cutoff = "";

	public int getTkid() {
		return tkid;
	}
	public void setTkid(int tkid) {
		this.tkid = tkid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getTkcodes() {
		return tkcodes;
	}
	public void setTkcodes(String tkcodes) {
		this.tkcodes = tkcodes;
	}
	public String getTktitle() {
		return tktitle;
	}
	public void setTktitle(String tktitle) {
		this.tktitle = tktitle;
	}
	public String getTkcontent() {
		return tkcontent;
	}
	public void setTkcontent(String tkcontent) {
		this.tkcontent = tkcontent;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDeadline() {
		return deadline;
	}
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}
	public String getCutoff() {
		return cutoff;
	}
	public void setCutoff(String cutoff) {
		this.cutoff = cutoff;
	}
}
