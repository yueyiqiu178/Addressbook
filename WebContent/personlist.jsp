<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*,site.yueyiqiu.model.*,site.yueyiqiu.dao.*" %>

<%

User user=null;

if((User)session.getAttribute("enteruser")!=null)
	user=(User)session.getAttribute("enteruser");

String ordName=request.getParameter("ordName");
String flagOrder="";

if(ordName==null)
	ordName="0";

	if(ordName.equals("0"))
	flagOrder="↓";
	else if(ordName.equals("1"))
	flagOrder="↑";	
	
String currentP=request.getParameter("currentP");
String preOrnext=request.getParameter("pageAction");



int currentPage=1;
int totalRows=0;
int totalPages=0;
int pageSize=5;
int firstResult=0;

if(currentP!=null)
	currentPage=Integer.parseInt(currentP);

Page mypage=new Page();

String namestr=request.getParameter("name");
String flag=request.getParameter("flag");
int typeid;

try{
typeid=Integer.parseInt(request.getParameter("typeid"));
}
catch(Exception e){
	typeid=0;
}


if(flag!=null&&flag.equals("select")){
	
	PersonType querytype=PersontypeDao.getInstance().getPersonTypeById(typeid);
	totalRows=PersoninfoDao.getInstance().selectPersonInfoCount(user, namestr, querytype);		
}
else{
	
	totalRows=PersoninfoDao.getInstance().getInfoCount(user);
	
}

mypage.init(pageSize, totalRows);
mypage.setCurrentPage(currentPage);
firstResult=(currentPage-1)*pageSize;
mypage.setFirstResult(firstResult);

List<PersonInfo> list=null;

if(flag!=null&&flag.equals("select")){
	
	PersonType seltype=PersontypeDao.getInstance().getPersonTypeById(typeid);
	list=PersoninfoDao.getInstance().getPersonInfoListByProperty(user, namestr, seltype, firstResult, pageSize, ordName);
}
else{
	
	list=PersoninfoDao.getInstance().getPersonInfoList(user,firstResult, pageSize, ordName);
}

	
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<script type="text/javascript">

	function add(){
		
		
		var url="<%=request.getContextPath()%>/addpersoninfo.jsp?flag=add&pid=";
		
		
		window.open(url);
		
	

		
		
		}

	function addtype(){

		alert("addtype");
		var url="<%=request.getContextPath()%>/addpersontype.jsp";
		
		window.open(url);
		
		//var ret=window.showModalDialog(url,"window",'status=no;dialogWidth=590px;dialogHeight=450px;center=yes;help=no;location=no;');
        //
		//if(typeof(ret)==="string"){
		//	window.location.href="personlist.jsp";
		//	}	
		
		}
		

	function ordName(){

		var order=document.getElementById("ordName").value;
		if(order=="0"){
			document.getElementById("ordName").value=1;
			}
		else if(order=="1"){
			document.getElementById("ordName").value=0;
		}

		
		var flag=document.getElementById("flag").value;
		//alert("flag="+flag);
		if(flag=="select"){
				selectPerson();
			}
		else{
			document.forms.listform.action="personlist.jsp";
			document.forms.listform.method="post";
			document.forms.listform.submit();
				
			}

		}

	function selectAllCheckBox(){

		
		var checkall=document.getElementById("checkAll");
		var checktotal=document.getElementsByName("checkone");

		if(checkall.checked==true){
			
			for(var i=0;i<checktotal.length;i++){

				checktotal[i].checked=true;
				
				}	

			}
		else{

			for(var i=0;i<checktotal.length;i++){
				checktotal[i].checked=false;
				}	
			}
		
		}

	function selectPerson(){
		//alert("hello");
		var type=document.getElementById("type");
		document.getElementById("typeid").value=type.value;
		document.getElementById("flag").value="select";
		
		document.forms.listform.action="personlist.jsp";
		document.forms.listform.method="post";
		document.forms.listform.submit();

		}

	function getAllSelectedPid(){

		var pidstr="";
		var allobject=document.getElementsByName("checkone");

		for(var i=0;i<allobject.length;i++){

			if(allobject[i].checked==true)
				pidstr=pidstr+allobject[i].value+",";

			}
		return pidstr;

		}

	function deleteInfo(){

		var delstr=getAllSelectedPid();

		if(delstr==""){
			alert("請至少選擇一個選項");
			}
		else{
			if(window.confirm("您確定要刪除嗎?")==true){

				document.forms.listform.action="<%=request.getContextPath()%>"+"/delpersoninfo.jsp?pidstr="+delstr;
				document.forms.listform.method="post";
				document.forms.listform.submit();
				}
			else{
				return;
				}
			}

		}

	function editInfo(){

		var pid;
		var allitems=document.getElementsByName("checkone");
		
		if(!checkselect()){
			alert("編輯最多選一筆資料!!");
			for(var i=0;i<allitems.length;i++){
				allitems[i].checked=false;				}
			return;
			}
					
		if(!checknoselect()){

			alert("編輯請選一筆資料!!");
			return;
			}

		for(var i=0;i<allitems.length;i++){
			if(allitems[i].checked==true){
				pid=allitems[i].value;
				break;
				}
			}

		alert("edit");
		var url="<%=request.getContextPath()%>/addpersoninfo.jsp?flag=update&pid="+pid;
		
		window.open(url);
		
		//if(window.showModalDialog(url,"window",'status=no;dialogWidth=590px;dialogHeight=450px;center=yes;help=no;location=no;')){
		//	alert("編輯成功");
		//	window.location.href="personlist.jsp";
		//	}
		//else{
		//	//alert("編輯聯絡人出錯了!!");
		//	}	
		
		
		}

	function checkselect(){

		var allitems=document.getElementsByName("checkone");
		var ptr=0;

		for(var i=0;i<allitems.length;i++){
			if(allitems[i].checked==true){
				ptr++;
				}
			}

		if(ptr>1)
			return false;
		else
			return true;
		
		}


	function checknoselect(){

		var ptr=false;
		var allitems=document.getElementsByName("checkone");

		for(var i=0;i<allitems.length;i++){
			if(allitems[i].checked==true){
				ptr=true;
				}
			}
		
		return ptr;
		
		}

	function findAll(){

		
		window.location.href="personlist.jsp";
		

		}

	
</script>


</head>
<body bgcolor="#FFFFFF">

<form id="listform" name="listform">
	
	<input type="hidden" id="ordName" name="ordName" value="<%=ordName%>"/>
	<input type="hidden" id="typeid" name="typeid" value="<%=typeid%>"/>
	<input type="hidden" id="flag" name="flag" value="<%=flag %>"/>
	
	<br/>
	類別代號為:<%=typeid %><br/>
	flag為:<%=flag%>
	name為:<%=namestr %>
	ordName為:<%=ordName%>
	<table align="center" width="1000px" cellpadding="0" cellspacing="0" border="0">
	
		<tr>
			<td width="1000px" height="124px" colspan="4"><img alt="" src="images/zhuce_01.gif" /></td>
		</tr>
		
		<tr>
			<td width="12px" height="431px" valign="top" align="left" background="images/zuoshuxian.gif" bgcolor="#FFFFFF"></td>
			<td width="191px" height="431px" align="center" valign="top" bgcolor="#FFFFFF">
				<table border="1" width="188px" height="380px" cellpadding="0" cellspacing="0" bordercolor="#b2b2b2">
					<tr>
						<td width="184px" height="37px"><img alt="" src="images/index01.gif"></td>
					</tr>
					<tr>
						<td width="184px" height="311px" valign="top" align="center" bgcolor="#f2f1f1" >
							<table border="0" width="180" height="388" cellpadding="0" cellspacing="0">
								<tr>
									<td><img alt="" src="images/frie1.jpg"></td>
								</tr>
								<tr>
									<td><img alt="" src="images/frie2.jpg"></td>
								</tr>
								<tr>
									<td><img alt="" src="images/frie3.jpg"></td>
								</tr>
								<tr>
									<td><img alt="" src="images/frie4.jpg"></td>
								</tr>
								<tr>
									<td></td>
								</tr>
								
							</table>
						</td>
					</tr>
					
				
				</table>
			
			</td>
			<td width="787px" height="431px" align="center" valign="top" bgcolor="#FFFFFF">
				<table width="787px"  border="1" cellpadding="0" cellspacing="0" bordercolor="#b2b2b2">
					<tr height="35px">
						<td colspan="8" align="left" valign="middle" style="background-image: url('images/indextable.gif');">
						<input type="button" value="新增好友" onclick="add()" style="cursor: pointer;"/>&nbsp;&nbsp;
						<input type="button" value="刪除好友" onclick="deleteInfo()" style="cursor: pointer;"/>&nbsp;&nbsp;
						<input type="button" value="增加類別" onclick="addtype()" style="cursor: pointer;"/>&nbsp;&nbsp;
						<input type="button" value="編輯好友" onclick="editInfo()" style="cursor: pointer;"/>&nbsp;&nbsp;
						<input type="button" value="所有好友" onclick="findAll()" style="cursor: pointer;"/>
						</td> 
					</tr>
					
					<tr height="35px">
						<td colspan="8" align="left" valign="middle" style="background-image: url('images/indextable.gif');" class="indextable">
							姓名:<input type="text" name="name" id="name" value="<%if(namestr!=null)out.println(namestr);else out.println("");%>"/>
							類別:<select name="type" id="type">
							<option value="">請選擇</option>
							<%
							
							List<PersonType> typelist=PersontypeDao.getInstance().getAllType();
							
							if(typelist!=null&&typelist.size()>0){
								
								for(int i=0;i<typelist.size();i++){
									
									PersonType persontype=(PersonType)typelist.get(i);%>
									
									<option value="<%=persontype.getTypeId()%>" <%if(persontype.getTypeId()==typeid){out.println("selected='selected'");}%>><%=persontype.getTypeName() %></option>
									
							<% 	
								}
								
								
								
							}
							
							%>	
							
							
							
							</select>
							<input type="button" value="查詢" onclick="selectPerson()" />
						</td>
					</tr>
					
					<tr height="35px">
						<td width="30px"><input type="checkbox" name="checkAll" id="checkAll" onclick="selectAllCheckBox()"/></td>
						<td width="60px"><a href="javascript:ordName()">姓名<%=flagOrder %></a></td>
						<td width="60px">類別</td>
						<td width="50px">性別</td>
						<td width="40px">年齡</td>
						<td width="100px">手機</td>
						<td width="100px">E-Mail</td>
						<td width="150px">地址</td>
					</tr>
					
					<%
					
					
					if(list!=null&&list.size()>0){
					for(int i=0;i<list.size();i++){
					
						PersonInfo personinfo=(PersonInfo)list.get(i);
						
						PersonType persontype=PersontypeDao.getInstance().getPersonTypeById(personinfo.getType().getTypeId());
		
					%>
					
					<tr height="40px" bgcolor="#f2f1f1">
						<td><input type="checkbox" name="checkone" id="checkone" value="<%=personinfo.getpId()%>"/></td>
						<td><a href=""><%=personinfo.getpName() %></a></td>
						<td><%=persontype.getTypeName() %></td>
						<td><%
						if(personinfo.getpSex().trim().equals("m"))
						out.println("男");
						else
						out.println("女");
						%></td>
						<td>
						<% 
						if((personinfo.getpAge()==0))
						out.println("");
						else
						out.println(personinfo.getpAge());
						%>
						</td>
						<td>
						<% 
						
						if(personinfo.getpMobile()==null)
						out.println("");
						else
						out.println(personinfo.getpMobile());%>
						</td>
						<td>
						<% 
						
						if(personinfo.getpEmail()==null)
						out.println("");
						else
						out.println(personinfo.getpEmail());%>
						</td>
						<td>
						<% 
						if(personinfo.getpAddress()==null)
						out.println("");
						else
						out.println(personinfo.getpAddress());%>
						</td>
					</tr>
					
					<% }}%>
					
					<tr height="35px">
						<td colspan="8" align="center" bgcolor="#E4E4CA">
						共[<%=mypage.getTotalRows() %>]條紀錄
						<%
						if(mypage.isHasPrevious()){%>
						<a href="personlist.jsp?currentP=1&flag=<%=flag%>&typeid=<%=typeid%>&name=<%=namestr%>">首頁</a>
						<a href="personlist.jsp?currentP=<%=mypage.getCurrentPage()-1 %>&pageAction=before&flag=<%=flag%>&typeid=<%=typeid%>&name=<%=namestr%>">上一頁</a>
						<%} if(mypage.isHasNext()){%>
						<a href="personlist.jsp?currentP=<%=mypage.getCurrentPage()+1%>&pageAction=next&flag=<%=flag%>&typeid=<%=typeid%>&name=<%=namestr%>">下一頁</a>
						<a href="personlist.jsp?currentP=<%=mypage.getTotalPages()%>&flag=<%=flag%>&typeid=<%=typeid%>&name=<%=namestr%>">頁尾</a>
						<%}else{ %>
						<a href="">下一頁</a>
						<a href="">頁尾</a>
						<%} %>
						當前為[<%=mypage.getCurrentPage() %>/<%=mypage.getTotalPages() %>]頁
						</td>
					</tr>
				</table>
			
			</td>
			<td width="10px" height="431px" background="images/youshuxian.gif"></td>
		</tr>
		
		<tr>
			<td background="images/zhuce_04.gif"><img alt="" src="images/zhuce_03.gif"></td>
			<td align="right" valign="top" width="374px" height="45px" colspan="2" style="background-image:url('images/zhuce_04.gif'); "><img alt="" src="images/zhuce_08.gif"></td>
			
			<td width="10px"><img alt="" src="images/zhuce_06.gif"></td>
		</tr>
	
	
	</table>
	

</form>

</body>
</html>