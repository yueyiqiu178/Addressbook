<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>會員註冊</title>

<script type="text/javascript">

	var reg;

	function close(){
		window.close(0);
		}

	
	function checkInput(){


			var id=document.getElementById("userid").value;
			var pwd=document.getElementById("userpwd").value;
			var pwdcon=document.getElementById("userpwdconfirm").value;
			var name=document.getElementById("username").value;
			var age=document.getElementById("userage").value;
			var man=document.getElementById("man");
			var woman=document.getElementById("woman");

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
			else{

				if(pwd.length<6||pwd.length>16){

					alert("密碼格式為6-16位");
					return false;

					}
				}

			if(pwdcon==null||pwdcon==""){
				alert("請輸入確認密碼");
				document.getElementById("userpwd").focus();
				return false;
				}

			if(pwd!=""&&pwdcon!=""){

				if(pwd!=pwdcon){

					alert("密碼輸入不一致");
					document.getElementById("userpwdconfirm").focus();
					return false;
					}				
				}

			if(name==null||name==""){
				alert("請輸入使用者姓名");
				document.getElementById("username").focus();
				return false;
				}
			
			if(!man.checked&&!woman.checked){

				alert("請選擇性別");
				return false;
				}

			if(age!=""){
				if(age>150||age<=0){

					alert("年齡須在0~150之間");
					document.getElementById("userage").focus();
					return false;

					}

				if(isNaN(age)){

					alert("年齡應為數字");
					document.getElementById("userage").focus();
					return false;

					
					}
				}
			alert("checkInput");
			return true;

		}

	function callBackFunction(){
		alert("ready="+reg.readyState);
		alert("status="+reg.status);
		if(reg.readyState==4){
			if(reg.status==200){
			alert("form");
			var hasUser=reg.responseXML.getElementsByTagName("hasUser");
			var noUser=reg.responseXML.getElementsByTagName("noUser");
			alert(hasUser);
			alert(noUser);
			if(typeof(hasUser)!="undefined"&&hasUser.length>0){

				
				alert("OK");
				document.forms.enterform.action="<%=request.getContextPath()%>"+"/UserServlet?action=register";
				document.forms.enterform.method="post";
				document.forms.enterform.submit();

				}
			
			if(typeof(noUser)!="undefined"&&noUser.length>0){

				alert("帳號已經有人使用");
				document.getElementById("userid").focus();
				


				}
			}
}
	}
	
	function request(reqType,url,sync,reFunction){

		

		if(window.XMLHttpRequest)
			reg=new XMLHttpRequest();
		else if(window.ActiveXObject)
			reg=new ActiveXObject("Microsoft.XMLHTTP");


		reg.open(reqType,url,true);
		reg.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		reg.onreadystatechange=reFunction;
		reg.send();
		alert("request");

		
	}

	
	function register(){

		if(checkInput()){

			var id=document.getElementById("userid").value;
			var url=document.getElementById("path").value+"/UserServlet?action=checkRegister&id="+id;
			//var param="action=checkRegister&id="+id;
			
			request("post",url,true,callBackFunction);

			}
		
		}


</script>


</head>
<body bgcolor="#FFFFFF">

	<form name="loginform" id="enterform" method="post">
	<input type="hidden" id="path" value=<%=request.getContextPath() %> />
	<table align="center" width="100px" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="4" width="1000px" height="124px"><img alt="" src="images/zhuce_01.gif"></td>
		</tr>
		<tr>
			<td colspan="4" width="1000px" height="61px"><img alt="" src="images/yonghuzhucexiaoren.gif"></td>
		</tr>
		<tr>
			<td valign="top" align="left" width="12px" height="372px" bgcolor="#FFFFFF" style="background-image: url('images/zuoshuxian.gif');"></td>
			<td width="596px" height="372px" align="center" valign="top">
			
			<table width="572px" height="357px" border="0" cellspacing="0">
				<tr>
				<td width="130px" align="right">用戶帳號:</td>
				<td colspan="4" width="150px"><input id="userid" name="userid" type="text" value="asdasd"/></td>
				<td width="250px">*</td>
				</tr>
				
				<tr>
				<td align="right">用戶密碼:</td>
				<td colspan="4"><input id="userpwd" name="userpwd" type="text" value="123456"/></td>
				<td>*</td>
				</tr>
				
				<tr>
				<td align="right">確認密碼:</td>
				<td colspan="4"><input id="userpwdconfirm" name="userpwdconfirm" type="text" value="123456"/></td>
				<td>*</td>
				</tr>
				
				<tr>
				<td align="right">用戶姓名:</td>
				<td colspan="4"><input id="username" name="username" type="text" value="Tom"/></td>
				<td>*</td>
				</tr>
				
				<tr>
				<td align="right">性別:</td>
				<td><input type="radio" name="sex" id="man" value="m" checked="checked"/></td>
				<td>男</td>
				<td><input type="radio" name="sex" id="woman" value="f" /></td>
				<td>女</td>
				<td></td>
				</tr>
				
				<tr>
				<td align="right">年齡:</td>
				<td colspan="4"><input id="userage" name="userage" type="text" value="30"/></td>
				<td>*</td>
				</tr>
				
				<tr>
				<td></td>
				<td colspan="3"><img alt="" src="images/tijiao.gif" style="cursor: pointer;" onclick="register();"></td>
				<td><img alt="" src="images/guanbi.gif" style="cursor: pointer;" onclick="javascript:window.close(0);"/></td>
				<td></td>
				</tr>
			
			</table>
			</td>
			<td width="382px" height="372px" bgcolor="#FFFFFF"/>
			<td width="10px" height="372px" background="images/youshuxian.gif" align="right"></td>
		</tr>
		<tr>
		
			<td background="images/zhuce_04.gif"><img alt="" src="images/zhuce_03.gif"></td>
			<td colspan="2" background="images/zhuce_04.gif"></td>
			<td background="images/zhuce_04.gif"><img alt="" src="images/zhuce_06.gif"></td>
		
		</tr>
	</table>
	</form>
</body>
</html>