String.prototype.ReplaceAll=stringReplaceAll;

function stringReplaceAll(findText,replaceText){
	
	var reg=new RegExp(findText,"g");
	return this.replace(reg,replaceText);

}

function Trim(str){
	
		return str.replace(/(^\s*)|(\s*$)/g,"");
}


function IsNumber(str){
	var regExpression=/[^0-9]/;
	var flag;
	
	flag=str.search(regExpression);
	if(flag==-1){
		return false;
	}
	else{
		return true;
	}
}


function isDate(){
	

}

function StrChlength(str){
	return str.replace(/[^\x00-\xff]/g,"aa").length;
}


function MaxInputLimit(form,max){
	var inputstr=form.value;
	if(inputstr.length>max){
		form.value=inputstr.substring(0,max);
	}
	
}


function isNotNull(){
	

}

function StrangeCode(code,msg){
	var reg=/[\'\"\\<>;&=#]/;
	var flag;
	
	flag=code.search(reg);
	
	if(flag==-1)
		return false;
	else{
		alert(msg+"���s�b�D�k�r��,�Ч�");
		return true;
	} 
			
}


function StrangeCodeReplace(code){
	
	var reg=/[<>]/;
	var flag;
	
	flag=code.search(reg);
	
	if(flag!=-1){
		code.stringReplaceAll("<", "��");
		code.stringReplaceAll(">","��");
	}
	return code;
}


function StrangeCodeQuery(code,msg){
	
	var reg=/[~!@#$%^&*-+\\\'\"<>]/;
	var flag;
	
	flag=code.search(reg);
	
	if(flag==-1)
		return false;
	else{
		alert(msg+"���s�b�S��r��,�Ч�");
		return true;
	}	
}



function IsMobTel(str){
	
}


function IsTel(str){
	
}


function IsNullOrEmpty(){}


function checkEmail(form){
	form.value=Trim(form.value);
	var reg=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	var flag=form.value.search(reg);
	
	if(flag==-1){
		alert("E-mail�榡�����T,�Э��s��g!");
		form.focus();
		return false;
	}
	
	return true;
}


function CompareText(form1,form2,msg){
	
	if(form1.value!=form2.value){
		alert(msg);
		form1.focus();
		return false;
	}
	return true;
}


function checkText(form,msg){
	var reg=/^[a-zA-Z0-9]+$/;
	var flag=form.value.search(reg);
	
	if(flag==-1){
		alert("�u��Ѧr���M�Ʀr�զ�");
		form.focus();
		return false;
	}
	
	return true;
}


function checkLength(form,msg,min,max){
	
	if(form.value.length<min||form.value.length>max){
		alert("���׹L�u�ιL��");
		form.focus();
		return false;
	}
	
	return true;
}


function checkTxbLength(form,msg,max){
	
	if(form.value.length>max){
		alert("���׹L��");
		form.focus();
		return false;
	}
	
	return true;
}

function checkTime(){
	
}


function CompareDate(){}

function CDate(){}


function initPage(){
	
}
