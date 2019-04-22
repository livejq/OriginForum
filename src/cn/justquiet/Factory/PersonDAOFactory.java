package cn.justquiet.factory;

import cn.justquiet.dao.PersonDAO;
import cn.justquiet.daoimpl.PersonDAOImpl;

public class PersonDAOFactory {
	public static PersonDAO getPersonDAOInstance(){
		return new PersonDAOImpl();
	} 
}
