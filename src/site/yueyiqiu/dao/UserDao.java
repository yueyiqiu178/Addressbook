package site.yueyiqiu.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import site.yueyiqiu.model.User;

public class UserDao {
	
	private static UserDao instance=null;
	
	
	public static UserDao getInstance(){
		if(instance==null)
			instance=new UserDao();
		return instance;
	}
	
	public boolean saveUser(User user){
		
		boolean result=false;
		Session session=null;
		Transaction trans=null;
		
		try{
		session=SessionFactoryProvider.getSession();
		trans=session.beginTransaction();
		
		session.save(user);
		trans.commit();
		result=true;
		}
		catch(RuntimeException e){
			
			if(trans!=null&&trans.isActive())
				trans.rollback();
			
			e.printStackTrace();
		}
		finally{session.close();}
		
		
		return result;
		
	}
	
	public boolean updateUser(User user){
		
		boolean result=false;
		Session session=null;
		Transaction trans=null;
		
		try{
		session=SessionFactoryProvider.getSession();
		trans=session.beginTransaction();
		trans.begin();
		session.update(user);
		trans.commit();
		result=true;
		}
		catch(RuntimeException e){
			
			if(trans!=null&&trans.isActive())
				trans.rollback();
			
			e.printStackTrace();
		}
		finally{session.close();}
		
		
		return result;
		
	}
	
	public User selectUserById(String id){
		
		User user=null;
		Session session=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			Query query=session.createQuery("from User where userId=:id");
			
			query.setString("id", id);
			user=(User) query.list().get(0);
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
		}
		finally{
			
			session.close();
			
		}
		
		return user;
		
	}
	
	public boolean getUserById(String id){
		
		boolean flag=false;
		Session session=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			Query query=session.createQuery("from User where userId=:id");
			
			query.setString("id", id);
			
			if(query.list()!=null&&query.list().size()>0)
				flag=true;
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
		}
		finally{
			
			session.close();
			
		}
		
		return flag;
		
	}
	
public User getUserByIdAndPwd(String id,String pwd){
		
		User user=null;
		Session session=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			Query query=session.createQuery("from User where userId=:id and userPwd=:pwd");
			
			query.setString("id", id);
			query.setString("pwd", pwd);
			
			if(query.list()!=null&&query.list().size()>0)
			user=(User) query.list().get(0);
			
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
		}
		finally{
			
			session.close();
			
		}
		
		return user;
		
	}
	
	
public boolean getUserByName(String name){
		
		
		Session session=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			Query query=session.createQuery("from User where userName=:name");
			
			query.setString("name", name);
			if(query.list()!=null&&query.list().size()>0)
				return true;
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
		}
		finally{
			
			session.close();
			
		}
		
		return false;
		
	}
	
	
	
	public boolean enter(String id,String pwd){
		
		boolean result=false;
		Session session=null;
		
		try{
			
			session=SessionFactoryProvider.getSession();
			
			String hql="select count(*) from user where userId=:id and userPwd=:pwd";
			Query query=session.createQuery(hql);
			query.setString("id", id);
			query.setString("pwd", pwd);
			
			int usercount=Integer.parseInt(query.list().get(0).toString());
			if(usercount==1)
				return true;
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
			
		}
		finally{
			
			session.close();
		}
		
		return false;
	}
	
	
	
	
}
