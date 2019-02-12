<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="site.yueyiqiu.model.*"%>
<%@ page import="site.yueyiqiu.service.*"%>
<%@ page import="site.yueyiqiu.dao.*"%>

<%

	String allpid=request.getParameter("pidstr");
	String[] pidarray=allpid.split(",");
	boolean RET=false;
	for(int i=0;i<pidarray.length;i++){
		
		RET=PersoninfoDao.getInstance().deletePersoninfoById(Integer.parseInt(pidarray[i]));
		
	}
	if(RET){
%>

<script type="text/javascript">

window.location.href="personlist.jsp";

</script>

<%}%>