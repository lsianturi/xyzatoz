function chkResult (val, mandatory, data) {
	var temp = false;
	switch (val) {
		case 'isInteger'          : { temp = isInteger(data); break}	
		case 'isIntegerNotZero'   : { temp = isIntegerNotZero(data); break}	
		case 'isAlphanumeric'     : { temp = isAlphanumeric(data); break }
		case 'isAlphabetic'       : { temp = isAlphabetic(data); break }
		case 'isAlphanumericplus' : { temp = isAlphanumericplus(data); break }
		case 'isTime' 		  	  : { temp = isTime(data); break }
		case 'isTime12' 		  : { temp = isTime12(data); break }
		case 'isDateType' 		  : { temp = isDateType(data); break }
		case 'isFloat'			  : { temp = isFloat(data); break }
		case 'isFloat2'			  : { temp = isFloat2(data); break }
		case 'isFloatplus'		  : { temp = isFloatplus(data); break }
		case 'isIntegerplus'      : { temp = isIntegerplus(data); break }
		case 'isCGRNumber'        : { temp = isCGRNumber(data); break }
		case ''                   : { temp = true; break}
	}
	if (mandatory != '*') {
		if (isWhitespace(data)) {
			temp = true;	
		}
	} else if (mandatory == '%' ) {
			temp = true;			
	} else {
		if (isWhitespace(data))
			temp = false;
	}
	
	return temp;
}	

function isAlphanumericplus (data){
	var exceptChars = /[''&%<>]/;
	var res = data.search(exceptChars);
	if ( res < 0)
		return true;
	else 
	  return false;
}

function isFloatplus(data){
 	var temp = 0, begin = 0, dot = 0;		
	var exceptChars = /[^0-9-.]/;
	var res = data.search(exceptChars);	
	if ( res >= 0)		
		return false;						
	for(begin;begin<data.length;begin++){
		if(data.substring(begin,begin+1)=='.')
			dot++;		
	}
	if (data.substring(0,1)=='-') 
		temp = parseFloat(data.substring(1));		
	else 
		temp = parseFloat(data);		
	if(isFloat(temp))
		return true;
	else
		return false;	
}

function isFloat2(data){
	var postdigit=2;
	if (data=="") return false;
	if (data=="0") return true;
	var delimiter = '.';
	var delimiterfound = false;
	if (data.substring(0,1)=='.'||data.substring(data.length-1,data.length)=='.') return false;
	firstdigit=data.charAt(0);
	seconddigit=data.charAt(1);
	if (firstdigit=='0'&&seconddigit!=delimiter) return false;
	for (var i=0;i<data.length;i++) {
		checkchar = data.charAt(i);
		if ((checkchar==delimiter)&&!delimiterfound) 
			delimiterfound = true;
		else
			if (!isDigit(checkchar)) return false;
	}
	if (!delimiterfound) {
		return true;
	}
	var digits = data.split(delimiter);
	if ((digits[1].length > postdigit)&&(postdigit!=0)) return false;
	return true;
}

function isIntegerplus (data){
	var exceptChars = /[^0-9-]/;
	var pos = data.indexOf('-');
	var res = data.search(exceptChars);
	if ( res >= 0)
		return false;
	else{	  	
		if(pos>=0){
			if(pos==0)
				if(data.length>1)
					return true;
				else
					return false;
			else
				return false;
		}
		else{
			return true;				
		}
	}
}

function isIntegerNotZero(data) {
  if (isInteger(data)) {
    if (parseInt(data)==0)
	  return false;
	else
	  return true;
  } else
    return false;
}

function isZero(data){
	if(data.value=='0')
		return true;
	return false;
}

function isTime(data) {
var hour, minute;
  if (data.substring(2,3)=='.') {
    result = data.split('.');
	hour = result[0];
	minute = result[1];
	if ((hour.length!=2)||(minute.length!=2)) return false;
	if (!isIntegerInRange(parseInt(hour,10),0,23)) return false;
	if (!isIntegerInRange(parseInt(minute,10),0,59)) return false;
	return true;
  }
  return false;
}

function isTime12(data) {
var hour, minute;
  if (data.substring(2,3)=='.') {
    result = data.split('.');
	hour = result[0];
	minute = result[1];
	if ((hour.length!=2)||(minute.length!=2)) return false;
	if (!isIntegerInRange(parseInt(hour,10),0,11)) return false;
	if (!isIntegerInRange(parseInt(minute,10),0,59)) return false;
	return true;
  }
  return false;
}

function isDateType(data) {
	if (data.substring(2,3)=='-' && data.substring(5,6)=='-') {
		result = data.split('-');
		if (result[0].length != 2) return false;
		if (result[1].length != 2) return false;
		if (result[2].length != 4) return false;
		return isDate(result[2],parseInt(result[1],10),parseInt(result[0],10));
	}		
	return false;
}

function dateCompare(date1,date2) {
  if (date1==date2)
    return 0;
  else
  if (date1.substring(6,10)>date2.substring(6,10))
    return 1;
  else
  if (date1.substring(6,10)<date2.substring(6,10))
    return -1;
  else
  if (date1.substring(3,5)>date2.substring(3,5))
    return 1;
  else
  if (date1.substring(3,5)<date2.substring(3,5))
    return -1;
  else
  if (date1.substring(0,2)>date2.substring(0,2))
    return 1;
  else
    return -1;
}

function isCGRNumber(data) {
    if (data=="") return false;
    //if (data=="0") return true;
    var delimiter = '-';
    var checkchar = '';
    var delimiterfound = false;
    //if (data.substring(0,1) =='-' || data.substring(data.length-1, data.length)=='-') return false;
    for (var i=0;i<data.length;i++) {
        checkchar = data.charAt(i);
        if ((checkchar==delimiter)&&!delimiterfound)
            delimiterfound = true;
        else
            if (!isDigit(checkchar)) return false;
    }
    if (!delimiterfound) {
        if (data.length>3)
            return false;
        else
            return true;
    }
    var digits = data.split(delimiter);
    if (digits[0].length > 3) return false;
    if (digits[1].length > 3)return false;
    return true;
}
