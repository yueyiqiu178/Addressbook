package site.yueyiqiu.service;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import site.yueyiqiu.dao.UserDao;
import site.yueyiqiu.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		
		String action=request.getParameter("action");
		System.out.println("123");
		System.out.println(action);
		if(action!=null){
			
			
			if(action.equals("enter")){
				this.enter(request, response);
			}
			else if(action.equals("checkEnter")){
				this.checkEnter(request, response);
			}
			else if(action.equals("checkRegister")){
				this.checkRegister(request, response);
			}
			else if(action.equals("register")){
				
				this.register(request, response);
			}
			
		}
		
	}
	
	protected void enter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("userid");
		String pwd=request.getParameter("userpwd");
		
		User user=UserDao.getInstance().getUserByIdAndPwd(id, pwd);
		
		if(user!=null){
			System.out.println("user!=null");
			HttpSession session=request.getSession();
			session.setAttribute("enteruser", user);
			response.sendRedirect("personlist.jsp");
		}
		else{
			System.out.println("user==null");
			response.sendRedirect("index.jsp");
		}
		
		
	}
	
	protected void checkEnter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/xml;charset=UTF-8");
		//response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		//response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		//response.addHeader( "Expires", "0" );
		//response.addHeader( "Pragma", "no-cache" );
		System.out.println("checkEnter");
		PrintWriter out=response.getWriter();
		
		out.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>" );
		out.println( "<checkEnter>" );
		
		String id=request.getParameter("name");
		
		boolean isOK=UserDao.getInstance().getUserById(id);
		
		if(isOK==true){
			System.out.println("enter true");
			out.println("<hasUser id=\"OK\" />");
		}
		else{
			System.out.println("enter false id="+id);
			out.println("<noUser>"+id+"</noUser>");
		}
		out.println( "</checkEnter>" );	
	}
	
	
	protected void checkRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("checkregister");
		response.setContentType("text/xml;charset=UTF-8");
		response.addHeader( "Cache-Control", "no-store,no-cache,must-revalidate" );
		response.addHeader( "Cache-Control", "post-check=0,pre-check=0" );
		response.addHeader( "Expires", "0" );
		response.addHeader( "Pragma", "no-cache" );
		
		PrintWriter out=response.getWriter();
		
		out.println( "<?xml version=\"1.0\" encoding=\"utf-8\"?>" );
		out.println( "<checkEnter>" );
		
		String id=request.getParameter("id");
		
		
		boolean isOK=UserDao.getInstance().getUserById(id);
		System.out.println("isOK="+isOK);
		if(isOK==false){
			System.out.println("enter false");
			out.println("<hasUser id=\"OK\" />");
		}
		else{
			System.out.println("enter true");
			out.println("<noUser>"+id+"</noUser>");
		}
		out.println( "</checkEnter>" );	
		
	}
	
	
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("register");
		String userid=request.getParameter("userid");
		String userpwd=request.getParameter("userpwd");
		String username=request.getParameter("username");
		String sex=request.getParameter("sex");
		int userage=Integer.parseInt(request.getParameter("userage"));
		
		Calendar cal=Calendar.getInstance();
		
		User user=new User();
		user.setUserName(username.trim());
		user.setUserId(userid.trim());
		user.setUserPwd(userpwd.trim());
		user.setUserSex(sex.trim());
		user.setUserAge(userage);
		user.setUserLogintime(cal);
		
		boolean flag=UserDao.getInstance().saveUser(user);
		
		if(flag==true){
			
			User loginuser=UserDao.getInstance().selectUserById(user.getUserId());
			
			HttpSession session=request.getSession();
			session.setAttribute("enteruser", loginuser);
			response.sendRedirect("personlist.jsp");
			
		}
		else{
			
			response.sendRedirect("registerError.jsp");
		}
			
		
	}
	
	
}
