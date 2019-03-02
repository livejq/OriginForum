package cn.justquiet.Factory;

import cn.justquiet.DAO.PersonDAO;
import cn.justquiet.DAOImpl.PersonDAOImpl;

public class PersonDAOFactory {
	public static PersonDAO getPersonDAOInstance(){
		return new PersonDAOImpl();
	} 
}
