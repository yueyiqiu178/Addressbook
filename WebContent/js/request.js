var ptr;

function request(reqType,url,sync,reFunction){

	if(window.XMLHttpRequest)
		ptr=new XMLHttpRequest();
	else if(window.ActiveXObject)
		ptr=new ActiveXObject("Microsoft.XMLHTTP");

	ptr.open(reqType,url,true);
	//ptr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	//ptr.setRequestHeader("Cache-Control","no-cache"); 
	//ptr.setRequestHeader("If-Modified-Since","0"); 
	ptr.onreadystatechange=reFunction;
	//ptr.send(parameters);
	
	alert("request");
	
	
	
} 