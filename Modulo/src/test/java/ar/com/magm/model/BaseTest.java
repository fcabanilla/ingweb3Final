package ar.com.magm.model;

import org.hibernate.SessionFactory;

public class BaseTest {
	public BaseTest() {
	
	}

	public SessionFactory sessionFactory() {
		
		return HibernateUtil.getSessionFactory();		
		
	}
	
	

}
