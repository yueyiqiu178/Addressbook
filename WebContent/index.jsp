<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>

<script type="text/javascript">

var req;

function request(reqType,url,sync){

	
	if(window.XMLHttpRequest)
		req=new XMLHttpRequest();
	else if(window.ActiveXObject)
		req=new ActiveXObject("Microsoft.XMLHTTP");


	req.open(reqType,url,true);
	req.onreadystatechange=callBackFunction;
	req.send(null);
	
} 

function callBackFunction(){
	
	alert("ready="+req.readyState);
	alert("status="+req.status);
	
	if(req.readyState==4){
		if(req.status==200){
		
			
			var hasUser=req.responseXML.getElementsByTagName("hasUser");
			var noUser=req.responseXML.getElementsByTagName("noUser");
			
			if(typeof(hasUser)!="undefined"&&hasUser.length>0){

				
				document.forms.enterform.action="<%=request.getContextPath()%>"+"/UserServlet?action=enter";
				document.forms.enterform.method="post";
				document.forms.enterform.submit();

				}
			
			if(typeof(noUser)!="undefined"&&noUser.length>0){

				alert("沒有這個ID");
				document.getElementById("userid").focus();
				


				}


		

			}
	
	}
	
}
	

function enter(){

	
	
	if(checkInput()){
		
	var name=document.getElementById("userid").value;
	
	var url="<%=request.getContextPath()%>"+"/UserServlet?action=checkEnter&name="+name;
	
	request("post",url,true);
	
	}
}


function checkInput(){

		var id=document.getElementById("userid").value;
		var pwd=document.getElementById("userpwd").value;

		if(id==null||id==""){

			alert("請輸入帳號");
			document.getElementById("userid").focus();
			return false;
			}
		
		if(pwd==null||pwd==""){

			alert("請輸入密碼");
			document.getElementById("userpwd").focus();
			return false;
			}
		return true;
	
}


function register(){

	window.open("register.jsp");
	
}



</script>

</head>
<body>

<form id="enterform">
<table width="800" align="center" border="0" cellpadding="0" cellspacing="0">

<tr>

<td colspan="3"><img alt="" src="images/Userlogin_01.gif"></td>


</tr>

<tr>

<td rowspan="2"><img alt="" src="images/Userlogin_02.gif"></td>
<td background="images/Userlogin_03.gif" height="182">

<table>


<tr>

<td>帳號:</td>
<td><input type="text" id="userid" name="userid" value="402881012ea399f6012ea399f82e0001"/></td>

</tr>

<tr>

<td>密碼:</td>
<td><input type="password" id="userpwd" name="userpwd" value="mrsoft"/></td>

</tr>

<tr>

<td><img style="cursor: pointer;" alt="" src="images/denglu.gif" onclick="enter()"></td>
<td><img style="cursor: pointer;" alt="" src="images/zhuce.gif" onclick="register()"></td>

</tr>

<tr>

<td></td>
<td></td>

</tr>

</table>


</td>
<td rowspan="2">

<img alt="" src="images/Userlogin_04.gif">
</td>

</tr>


<tr>

<td><img alt="" src="images/Userlogin_05.gif"></td>


</tr>


</table>
</form>

</body>
</html>