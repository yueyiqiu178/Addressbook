<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<script type="text/javascript">

function save(){
	if(checkinput()){

		alert("保存成功");
		document.forms.actionform.action="<%=request.getContextPath()%>"+"/PersonTypeServlet?action=add";
		document.forms.actionform.method="post";
		document.forms.actionform.submit();
		window.close();
		}
}


function checkinput(){

		var type=document.getElementById("type").value;
		if(type==null||type==""){
			alert("請輸入類別名稱!");
			document.getElementById("type").focus();
			return false;
			}
		return true;
}

</script>
</head>
<body>
<form name="actionform">
<table>
<tr>
<td>
<img alt="" src="images/addtype.gif">
</td>
</tr>
<tr>
<td>
<table>
<tr>
<td>
輸入類別名稱:
</td>
<td>
<label><input type="text" name="type" id="type"/></label>
</td>
<td>
<img alt="" src="images/tianjialianxirenbaochun.gif" style="cursor: pointer;" onclick="save()"/>
</td>
</tr>
</table>
</td>
</tr>
<tr>
<td>

</td>
</tr>
</table>
</form>
</body>
</html>