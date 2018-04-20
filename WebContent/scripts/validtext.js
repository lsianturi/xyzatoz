function trimRight(totrim) {
	for (var i=(totrim.length-1); i>=0; i--) {
		if (totrim.charAt(i) == ' ') {
			totrim = totrim.substring(0, i+1);
		}
	}
	return totrim;
}
function trimLeft(totrim) {
	for (var i=0; i<totrim.length; i++) {
		if (totrim.charAt(i) == ' ') {
			totrim =  totrim.substring(i, totrim.length);
		}
	}
	return totrim;
}
function doTrim(totrim) {
	var value = '';
	value = trimRight(totrim);
	value = trimLeft(totrim);
	if(value == ' ') {
		value = '';
	}
	return value;
}
function checkNumber(theField) {
	var value = doTrim(theField.value);
	if(value.length == 0) {
		theField.value = '0';
	}
	if(isNaN(value)) {
		theField.value = '0';
	}
}