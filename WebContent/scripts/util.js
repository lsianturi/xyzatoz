function openWindow(fileName,popUpName,style) 
{
	var popup = window.open(fileName,popUpName,style);
	return popup;		
}

function SetInputValue(formName,id,value) {
	eval('var form = document.' + formName + ';');
	form.elements[id].value = value;
	
}

function WriteInputValue(formName,id,value) {
	eval('var form = document.' + formName + ';');
	form.elements[id].Text = value;	
}

function isEmptyField(formName,id) {
	eval('var form = document.' + formName + ';');
	return (form.elements[id].value.length > 0 ? false : true);
}

function trim(str){
	if(str.length<1) return "";
	str=ltrim(str);
	str=rtrim(str);
	return str;
}

function rtrim(str){
	var space=String.fromCharCode(32);
	var length=str.length;
	var strTemp="";
	if(length<0)return "";
	for(var i=length-1;i>=0;i--){
		if(str.charAt(i)!=space){
			strTemp = str.substring(0,i+1);
			break;
		}
	}
	return strTemp;
}

function ltrim(str){
	var space=String.fromCharCode(32);
	var length=str.length;
	var strTemp="";
	if(length<0)return "";
	for(var i=0;i<length;i++){
		if(str.charAt(i)!=space){
			strTemp = str.substring(i,length);
			break;
		}
	}
	return strTemp;
}