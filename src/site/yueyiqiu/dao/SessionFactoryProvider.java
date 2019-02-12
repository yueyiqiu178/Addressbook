package site.yueyiqiu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryProvider {
	
	public static SessionFactory factory;
	
	
	private SessionFactoryProvider(){}
	
	static{
		
		try{
			
			Configuration config=new Configuration().configure();
			factory=config.buildSessionFactory();
			
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
			
		}
		
	}
	
	public static Session getSession(){
		
		Session session;
		
		if(factory==null)
		rebuildSessionFactory();
		
		session=factory.openSession();
			
		return session;
		
	}
	
	
	public static void rebuildSessionFactory(){
		
		try{
			
			Configuration config=new Configuration().configure();
			factory=config.buildSessionFactory();
			
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
			
		}
		
		
	}
	
	
	public static void main(String[] args){
		
		SessionFactoryProvider ptr=new SessionFactoryProvider();
		
		System.out.println("asd");
	}
	
	
}
