package site.yueyiqiu.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import site.yueyiqiu.model.PersonInfo;
import site.yueyiqiu.model.PersonType;
import site.yueyiqiu.model.User;

public class PersoninfoDao {
	
	private static PersoninfoDao instance=null;
	
	public static PersoninfoDao getInstance(){
		
		if(instance==null)
			instance=new PersoninfoDao();
		return instance;
	}
	
public boolean savePersoninfo(PersonInfo person){
		
		boolean result=false;
		Session session=null;
		Transaction trans=null;
		
		try{
		session=SessionFactoryProvider.getSession();
		trans=session.beginTransaction();
		
		session.save(person);
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
	
	public boolean updatePersoninfo(PersonInfo person){
		
		boolean result=false;
		Session session=null;
		Transaction trans=null;
		
		try{
		session=SessionFactoryProvider.getSession();
		trans=session.beginTransaction();
		
		session.update(person);
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
	
	public PersonInfo selectPersoninfoById(int id){
		
		PersonInfo person=null;
		Session session=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			Query query=session.createQuery("from PersonInfo where pId=:id");
			
			query.setInteger("id", id);
			person= (PersonInfo) query.list().get(0);
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
		}
		finally{
			
			session.close();
			
		}
		
		return person;
		
	}
	
	
	public int getInfoCount(User user){
		
		
		int count=0;
		
		Session session=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			
			StringBuilder sb=new StringBuilder();
			sb.append("select count(*) from PersonInfo where user=:user");
			
			Query query=session.createQuery(sb.toString());
			
			query.setEntity("user", user);
			
			
			if(query.list()!=null&&query.list().size()>0)
				count=Integer.parseInt(query.list().get(0).toString());
				
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
		}
		finally{
			
			session.close();
			
		}
		
		return count;
		
	}
	
	public int selectPersonInfoCount(User user,String name,PersonType type){
		
		
		int count=0;
		
		Session session=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			
			StringBuilder sb=new StringBuilder();
			sb.append("select count(*) from PersonInfo where user=:user");
			
			if(!name.equals(""))
				sb.append(" and pName like :name");
			if(type!=null)
				sb.append(" and type=:type");
			
			Query query=session.createQuery(sb.toString());
			
			query.setEntity("user", user);
			
			if(!name.equals(""))
			query.setString("name", "%"+name+"%");
			if(type!=null)
			query.setEntity("type", type);
			
			if(query.list()!=null&&query.list().size()>0)
				count=Integer.parseInt(query.list().get(0).toString());
				
		}
		catch(RuntimeException e){
			
			e.printStackTrace();
		}
		finally{
			
			session.close();
			
		}
		
		return count;
		
	}
	
	public List<PersonInfo> getPersonInfoList(User user){
		
		List<PersonInfo> list=null;

		Session session=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			
			StringBuilder sb=new StringBuilder();
			sb.append("from PersonInfo where user=:user");
			
			Query query=session.createQuery(sb.toString());
			query.setEntity("user", user);
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
	
public List<PersonInfo> getPersonInfoList(User user,int first,int maxcount,String flagorder){
		
		List<PersonInfo> list=null;

		Session session=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			
			StringBuilder sb=new StringBuilder();
			sb.append("from PersonInfo where user=:user");
			
			if(flagorder.equals("0"))
				sb.append(" order by pName desc");
			else
				sb.append(" order by pName asc");
				
			
			Query query=session.createQuery(sb.toString());
			query.setEntity("user", user);
			query.setFirstResult(first);
			query.setMaxResults(maxcount);
			
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
public List<PersonInfo> getPersonInfoListByProperty(User user,String name,PersonType type,int first,int maxcount,String orderflag){
		
		List<PersonInfo> list=null;

		Session session=null;
		
		try{
			session=SessionFactoryProvider.getSession();
			
			StringBuilder sb=new StringBuilder();
			sb.append("from PersonInfo where user=:user");
			
			if(!name.equals(""))
			sb.append(" and pName like :name");
			
			if(type!=null)
			sb.append(" and type=:type");
			
			if(orderflag.equals("0"))
				sb.append(" order by pName desc");
			else
				sb.append(" order by pName asc");
			
			Query query=session.createQuery(sb.toString());
			
			query.setFirstResult(first);
			query.setMaxResults(maxcount);
			query.setEntity("user", user);
			
			if(!name.equals(""))
				query.setString("name", "%"+name+"%");
				
			if(type!=null)
				query.setEntity("type", type);
			
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
	
	
public boolean deletePersoninfoById(int id){
		
		
		Session session=null;
		Transaction trans=null;
		
		try{
		session=SessionFactoryProvider.getSession();
		trans=session.beginTransaction();
		
		Query query=session.createQuery("delete from PersonInfo where pId=:id");
		query.setInteger("id", id);
		int ptr=query.executeUpdate();
		trans.commit();
		if(ptr==1)
			return true;
		
		}
		catch(RuntimeException e){
			
			if(trans!=null&&trans.isActive())
				trans.rollback();
			
			e.printStackTrace();
		}
		finally{session.close();}
		
		
		return false;
		
	}
	
}
