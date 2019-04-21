package cn.justquiet.exception;

public class RuntimeException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2159112736698605408L;

	public RuntimeException(String e) {
		System.err.println(e);
	}
}
