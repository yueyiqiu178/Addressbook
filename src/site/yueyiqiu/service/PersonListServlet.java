package site.yueyiqiu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import site.yueyiqiu.dao.*;
import site.yueyiqiu.model.*;

/**
 * Servlet implementation class PersonListServlet
 */
@WebServlet("/PersonListServlet")
public class PersonListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method=request.getParameter("action");
		
		if(method!=null){
			
			if(method.equalsIgnoreCase("add"))
				this.add(request, response);
			if(method.equalsIgnoreCase("update"))
				this.update(request, response);
			
			
			
		}
		else{
			
			
			
		}
		
		
	}
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("enter add");
		response.setContentType("text/xml");
		System.out.println("request="+request.getCharacterEncoding());
		//request.setCharacterEncoding("utf-8");
		//response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		//response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		//response.addHeader( "Expires", "0" );
		//response.addHeader( "Pragma", "no-cache" );
		
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		
//		name=new String(name.getBytes("ISO-8859-1"),"utf-8");
//		address=new String(address.getBytes("ISO-8859-1"),"utf-8");
		
//		name=URLDecoder.decode(name, "utf-8");
//		address=URLDecoder.decode(address, "utf-8");
//		
		//request.setCharacterEncoding("utf-8");
		
//		name=URLDecoder.decode(name, "utf-8");
//		address=URLDecoder.decode(address, "utf-8");
		
		System.out.println("encoding="+request.getCharacterEncoding());
		
		System.out.println("name="+name);
		System.out.println("address="+address);
		
		int typeid=Integer.parseInt(request.getParameter("typeid"));
		
		int age;
		if(request.getParameter("age")!=null&&!request.getParameter("age").equals(""))
			age=Integer.parseInt(request.getParameter("age"));
		else
			age=0;
		
		PersonInfo userinfo=new PersonInfo();
		User sessionUser=null;
		sessionUser=(User) request.getSession().getAttribute("enteruser");
		
		PersonType persontype=PersontypeDao.getInstance().getPersonTypeById(typeid);
		
		userinfo.setpAge(age);
		userinfo.setpName(name);
		userinfo.setpEmail(email);
		userinfo.setpMobile(mobile);
		userinfo.setpSex(sex);
		userinfo.setpAddress(address);
		userinfo.setUser(sessionUser);
		userinfo.setType(persontype);
		
		
		boolean RET=PersoninfoDao.getInstance().savePersoninfo(userinfo);
		
		PrintWriter out=response.getWriter();
		out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		out.println("<savePerson>");
		if(RET==true)
			out.println("<success id=\"ok\"/>");
		else
			out.println("<error id=\"error\">");
		out.println( "</savePerson>" );
		
	}
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("begin update!!");
		
		response.setContentType("text/xml");
		//response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		//response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		//response.addHeader( "Expires", "0" );
		//response.addHeader( "Pragma", "no-cache" );
		
		String name=request.getParameter("name");
		String sex=request.getParameter("sex");
		String mobile=request.getParameter("mobile");
		String address=request.getParameter("address");
		String email=request.getParameter("email");
		int typeid=Integer.parseInt(request.getParameter("typeid"));
		int pid=Integer.parseInt(request.getParameter("pid"));
		
		System.out.println("address="+address);
		
		//name=new String(name.getBytes("ISO-8859-1"),"utf-8");
		//address=new String(address.getBytes("ISO-8859-1"),"utf-8");
		
		
		System.out.println("address="+address);
		
		int age;
		if(request.getParameter("age")!=null&&!request.getParameter("age").equals(""))
			age=Integer.parseInt(request.getParameter("age"));
		else
			age=0;
		
		PersonInfo userinfo=new PersonInfo();
		User sessionUser=null;
		sessionUser=(User) request.getSession().getAttribute("enteruser");
		System.out.println("username="+sessionUser.getUserName());
		System.out.println("sex="+sex);
		PersonType persontype=PersontypeDao.getInstance().getPersonTypeById(typeid);
		
		userinfo.setpId(pid);
		userinfo.setpAge(age);
		userinfo.setpName(name);
		userinfo.setpEmail(email);
		userinfo.setpMobile(mobile);
		userinfo.setpSex(sex);
		userinfo.setpAddress(address);
		userinfo.setUser(sessionUser);
		userinfo.setType(persontype);
		
		
		boolean RET=PersoninfoDao.getInstance().updatePersoninfo(userinfo);
		
		PrintWriter out=response.getWriter();
		out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		out.println("<updatePerson>");
		if(RET==true)
			out.println("<success id=\"ok\"/>");
		else
			out.println("<error id=\"error\">");
		
		
			out.println( "</updatePerson>" );
		
	}
	
}
