package site.yueyiqiu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import site.yueyiqiu.model.PersonInfo;
import site.yueyiqiu.model.PersonType;

public class PersontypeDao {
	
	private static PersontypeDao instance=null;
	
	public static PersontypeDao getInstance(){
		
		if(instance==null)
			instance=new PersontypeDao();
		return instance;
		
	}
	
	public List<PersonType> getAllType(){
		
		Session session=null;
		List<PersonType> list=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			Query query=session.createQuery("from PersonType");
			
			list=query.list();		
			
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
		}
		finally{
			
			session.close();
			
		}
		
		return list;
		
	}
	
	public PersonType getPersonTypeById(int id){
		
		Session session=null;
		PersonType type=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			Query query=session.createQuery("from PersonType where typeId=:id");
			query.setInteger("id", id);
			
			if(query.list()!=null&&query.list().size()>0)
				type=(PersonType)query.list().get(0);
			
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
		}
		finally{
			
			session.close();
			
		}
		
		return type;
		
		
	}
	
	public boolean saveOrUpdateType(PersonType type){
		
		boolean result=false;
		Session session=null;
		Transaction trans=null;
		
		try{
		session=SessionFactoryProvider.getSession();
		trans=session.beginTransaction();
		
		session.saveOrUpdate(type);
		trans.commit();
		result=true;
		}
		catch(Exception e){
			e.printStackTrace();
			trans.rollback();
		}
		finally{
			session.close();
		}
		
		return result;		
	}
	
	
	public boolean deleteType(PersonType type){
		
		boolean result=false;
		Session session=null;
		Transaction trans=null;
		
		try{
		session=SessionFactoryProvider.getSession();
		trans=session.beginTransaction();
		
		session.delete(type);
		trans.commit();
		result=true;
		}
		catch(Exception e){
			e.printStackTrace();
			trans.rollback();
		}
		finally{
			session.close();
		}
		
		return result;		
		
	}
	
	public int getTypeCount(){
		
		int count = 0;
		Session session=null;
		Transaction trans=null;
		
		try{
		session=SessionFactoryProvider.getSession();
		Query query=session.createQuery("select count(*) from PersonType");
		if(query.list()!=null&&query.list().size()>0){
			count=Integer.parseInt(query.list().get(0).toString());
		}
		
		}
		catch(Exception e){
			e.printStackTrace();
			trans.rollback();
		}
		finally{
			session.close();
		}
		
		return count;			
	}
	
}
