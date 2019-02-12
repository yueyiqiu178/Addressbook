package site.yueyiqiu.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import site.yueyiqiu.dao.PersontypeDao;
import site.yueyiqiu.model.PersonType;

/**
 * Servlet implementation class PersonTypeServlet
 */
@WebServlet("/PersonTypeServlet")
public class PersonTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonTypeServlet() {
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
		if(action!=null){
			
		if(action.equals("add"))
			this.add(request, response);
			
			
			
		}
	}
	
	
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String typename=request.getParameter("type");
		PersonType type=new PersonType();
		type.setTypeName(typename);
		
		boolean isOK=PersontypeDao.getInstance().saveOrUpdateType(type);
		
		if(isOK){
			response.sendRedirect("personlist.jsp");
		}
		
	}
	
}
