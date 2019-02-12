<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="BIG5"%>
<%@ page import="java.util.*,site.yueyiqiu.dao.*,site.yueyiqiu.model.*"%> 
<%
	
	String flag=request.getParameter("flag").trim();
	String pid=request.getParameter("pid").trim();
	
	PersonInfo personinfo=null;
	
	if(pid!=null&&!flag.equalsIgnoreCase("add"))
		personinfo=PersoninfoDao.getInstance().selectPersoninfoById(Integer.parseInt(pid));

	System.out.println("pid="+pid);
	//System.out.println("flag="+flag);
	//System.out.println("name="+personinfo.getpName());
	//System.out.println("phone="+personinfo.getpMobile());
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>新增聯絡人</title>
<link type="text/css" rel="stylesheet" href="css/style.css"/>
<script type="text/javascript">

	var reg;

	
	function request(reqType,url,sync,callback){

		
		alert("begin request");
		if(window.XMLHttpRequest)
			reg=new XMLHttpRequest();
		else if(window.ActiveXObject)
			reg=new ActiveXObject("Microsoft.XMLHTTP");

		reg.open(reqType,url,true);
		//ptr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		//ptr.setRequestHeader("Cache-Control","no-cache"); 
		//ptr.setRequestHeader("If-Modified-Since","0"); 
		reg.onreadystatechange=callback;
		reg.send(null);
		
		alert("request");
		
		
		
	} 

	
	function add(){

		
		var name=document.getElementById("name").value;
		var man=document.getElementById("man");
		var woman=document.getElementById("woman");
		var age=document.getElementById("age").value;
		var address=document.getElementById("address").value;
		var email=document.getElementById("email").value;
		var mobile=document.getElementById("mobile").value;
		var type=document.getElementById("type").value;
		var sex;


		if(man.checked)
			sex=man.value;
		if(woman.checked)
			sex=woman.value;

		

		
		
		
		if(checkInput()){
			
			var path=document.getElementById("path").value;
			alert(path);
			var url=path+"/PersonListServlet?action=add&typeid="+type+"&name="+name+"&age="+age+"&sex="+sex+"&mobile="+mobile+"&address="+address+"&email="+email;
			//var param="action=add&typeid="+type+"&name="+name+"&age="+age+"&sex="+sex+"&mobile="+mobile+"&address="+address+"&email="+email;
			request("post",url,true,callbackFunc);
			}

		
		alert("function end!!");
		window.close();	
		
		
		}
	
	function callbackFunc(){
		
		
		
		alert(reg.readyState);
		alert(reg.status);
		if(reg.readyState==4){
			if(reg.status==200){
				
				var success=reg.responseXML.getElementsByTagName("success");
				var error=reg.responseXML.getElementsByTagName("error");
				
				if(typeof(success)!="undefined"&&success.length>0){

					alert("新增聯絡人成功!");
					
					window.opener.location.href = "personlist.jsp";					
            		
					}
				
				if(typeof(error)!="undefined"&&error.length>0){

					alert("新增聯絡人失敗!");
					
					

					}


			}

				}

		}

	function update(){

		var name=document.getElementById("name").value;
		var man=document.getElementById("man");
		var woman=document.getElementById("woman");
		var age=document.getElementById("age").value;
		var address=document.getElementById("address").value;
		var email=document.getElementById("email").value;
		var mobile=document.getElementById("mobile").value;
		var type=document.getElementById("type").value;
		var pid=document.getElementById("pid").value;
		var sex;
		alert(address);

		if(man.checked)
			sex=man.value;
		if(woman.checked)
			sex=woman.value;

		if(checkInput()){

			var path=document.getElementById("path").value;
			var url=path+"/PersonListServlet?action=update&typeid="+type+"&name="+name+"&age="+age+"&sex="+sex+"&mobile="+mobile+"&address="+address+"&email="+email+"&pid="+pid;
			//var param="action=update&typeid="+type+"&name="+name+"&age="+age+"&sex="+sex+"&mobile="+mobile+"&address="+address+"&email="+email+"&pid="+pid;
			request("post",url,true,callbackFuncUpdate);

			alert("update function end!!");
			window.close();	
			
			}
		
		}
	
	
	
	function callbackFuncUpdate(){

	alert("callback function called");
		
		alert(reg.readyState);
		alert(reg.status);
		
		if(reg.readyState==4){
			if(reg.status==200){
				
				var success=reg.responseXML.getElementsByTagName("success");
				var error=reg.responseXML.getElementsByTagName("error");
				
				if(typeof(success)!="undefined"&&success.length>0){

					alert("修改聯絡人成功!");
					
					window.opener.location.href = "personlist.jsp";					
            		
					}
				
				if(typeof(error)!="undefined"&&error.length>0){

					alert("修改聯絡人失敗!");
					
					

					}


			}

				}
		}
	

	
	function checkInput(){

		var name=document.getElementById("name").value;
		var man=document.getElementById("man");
		var woman=document.getElementById("woman");
		var age=document.getElementById("age").value;
		var address=document.getElementById("address").value;
		var email=document.getElementById("email").value;
		var mobile=document.getElementById("mobile").value;

		if(name==null||name==""){
			alert("請輸入姓名");
			document.getElementById("name").focus();
			return false;
			}
		if(!man.checked&&!woman.checked){
			alert("請選擇性別");
			return false;
			}

		if(age!=null||age!=""){

			if(isNaN(age)){
				alert("年齡應為數字");
				document.getElementById("age").focus();
				return false;
				}
			if(age<=0||age>150){
				alert("年齡範圍不正確");
				document.getElementById("age").focus();
				return false;
				}
			}
			return true;
		
		}

</script>


</head>
<body>


<form name="personform" id="personform" method="post">

	<input type="hidden" id="pid" value="<%=pid%>"/>
	<input type="hidden" id="path" value="<%=request.getContextPath() %>"/>
	
	<table width="556px" height="390px" border="0" align="center" cellpadding="0" cellspacing="0">
	
	<tr>
	<td height="34" style="background-image: url('images/tianjiatabletop.gif');">
	<img src="images/tianjialianxiren.gif">
	</td>
	</tr>
	
	<tr>
	<td height="337">
	
	<table width="463px" height="273px" border="0" cellpadding="0" cellspacing="0" >
	
		
		<tr>
		<td align="right" width="106px">姓名:
		</td>
		<td colspan="4" height="35px">
		<input type="text" name="name" id="name" value="<%if(flag!=null&&flag.equals("update")) out.println(personinfo.getpName());%>"/>
		</td>
		</tr>
		
		<tr>
		<td align="right">性別:
		</td>
		<td width="15px">
		<input type="radio" value="m" name="sex" id="man"
		<%if(flag!=null&&flag.equals("update")&&personinfo.getpSex().equals("m"))
			out.println("checked=true");
			%>
		<% 
		if(flag.equals("add")){
			out.println("checked=true");
		}
		%>
		/>
		
		</td>
		<td align="left" width="15px">
		男
		</td>
		<td width="15px">
		<input type="radio" value="f" name="sex" id="woman"
		<%if(flag!=null&&flag.equals("update")&&personinfo.getpSex().equals("f"))
			out.println("checked=true");
			%>
		
		/>
		</td>
		<td align="left" width="150px">
		女
		</td>
		</tr>
		
		<tr>
		<td align="right">年齡:
		</td>
		<td align="left" colspan="4">
		
		<input type="text" name="age" id="age" value="<%
		if(flag!=null&&flag.equals("update"))
		out.println(personinfo.getpAge());
		%>"/>
		
		</td>
		</tr>
		
		<tr>
		<td align="right">手機號碼:
		</td>
		<td align="left" colspan="4">
		
		<input type="text" name="mobile" id="mobile" value="<%
		if(flag!=null&&flag.equals("update"))
		out.println(personinfo.getpMobile());
		%>"/>
		
		</td>
		</tr>
		
		<tr>
		<td align="right">類別:
		</td>
		<td align="left" colspan="4">
		<select name="type" id="type">
		<%
		List<PersonType> list=PersontypeDao.getInstance().getAllType();
		
		for(int i=0;i<list.size();i++){
			
			PersonType type=(PersonType)list.get(i);%>
			<option value="<%=type.getTypeId()%>" <%if(flag!=null&&flag.equals("update")){if(type.getTypeId()==personinfo.getType().getTypeId()){out.println("selected='selected'");}} %>><%=type.getTypeName() %></option>
			
		
		<% }%>
	
		
		</select>
		</td>
		</tr>
		
		<tr>
		<td align="right">電子郵件:
		</td>
		<td align="left" colspan="4">
		<input type="text" name="email" id="email" value="<%
				if(flag!=null&&flag.equals("update"))
				out.println(personinfo.getpEmail());
		
		%>"/>
		</td>
		</tr>
		
		<tr>
		<td align="right">地址:
		</td>
		<td align="left" colspan="4">
		<input type="text" name="address" id="address" value="<%
				if(flag!=null&&flag.equals("update"))
					out.println(personinfo.getpAddress());
		%>"/>
		</td>
		</tr>
		
		<tr>
		<td colspan="5" align="center">
		<% 
		if(flag!=null&&flag.equals("update")){
		%>
		<img style="cursor:pointer" alt="" src="images/indexxiugai.gif" onclick="update()">
		<%}else{ %>
		<img style="cursor:pointer" alt="" src="images/tianjialianxirenbaochun.gif" onclick="add()">
		<%} %>
		<img style="cursor:pointer" alt="" src="images/tianjialianxirenguanbi.gif" onclick="window.close()">
		</td>
		</tr>
		
	
	
	</table>
	
	
	</td>
	</tr>
	
	<tr>
	<td bgcolor="b2b2b2">
	</td>
	</tr>
	
	</table>
	

</form>




</body>
</html>