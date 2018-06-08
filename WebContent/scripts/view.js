function upperValue(prop) {
	if (prop!=null) {
		prop.value = prop.value.toUpperCase();
	}
}
function doTopGoToUrl(url) {
	window.top.location = url;
}
function doGoToUrl(url) {
	window.location = url;
}
function doDispatch(form, dispatchCode) {
	form.dispatch.value = dispatchCode;
	window.alert(dispatchCode);
	form.submit();
}
function doPageChanged(form) {
	newPage = form.pageIndex.value;
	if (!isNaN(parseInt(newPage))) {
		form.pageIndex.value = parseInt(newPage);
		if (form.pageIndex.value<0) {
			form.pageIndex.value = 1;
		}
		if (form.pageIndex.value>totalPage) {
			form.pageIndex.value = totalPage;
		}
	} else {
		form.pageIndex.value = lastPage;
	}
}
function doFirstPage(form) {
	if (lastPage>1) {
		lastPage=1;
		form.pageIndex.value = lastPage;
		doGoPage(form);
	}
}
function doPrevPage(form) {
	if (lastPage>1) {
		lastPage=lastPage-1;
		form.pageIndex.value = lastPage;		
		doGoPage(form);
	}
}
function doNextPage(form) {
	if (lastPage<totalPage) {
		lastPage=lastPage+1;
		form.pageIndex.value = lastPage;
		doGoPage(form);
	}
}
function doLastPage(form) {
	if (lastPage<totalPage) {
		lastPage=totalPage;
		form.pageIndex.value = lastPage;
		doGoPage(form);
	}
}	
function doChangePage(form) {
	if (lastPage!=form.pageIndex.value) {
		doGoPage(form);
	}
}
function doGoPage(form) {
	form.dispatch.value= 'changePage';
	form.submit();
}

//elvino
function changeDispatch(form, _dispatch) {
	form.dispatch.value = _dispatch;
}

function changeDispatchMenu(form, _dispatch) {
	form.dispatch.value = _dispatch;
	if (form.searchValue.value != '') {
		if (form.pageIndex != null) { form.pageIndex.value = 1; }
		
	}
}

// Disable right mouse click Script
// By Maximus (maximus@nsimail.com) w/ mods by DynamicDrive
// For full source code, visit http://www.dynamicdrive.com
function clickIE4(){
	if (event.button==2) return false;
}

function clickNS4(e){
	if (document.layers || document.getElementById && !document.all) {
		if (e.which==2 || e.which==3) {
			return false;
		}
	}
}

function openHelp(path) {
	window.open(path,null,"height=400,width=600,dependent=yes,toolbar=no,location=no,resizable=yes,scrollbars=yes");
}

function back() {
	window.history.go(-1);
}
// Uncomment all code below to disable right click
//if (document.layers) {
//	document.captureEvents(Event.MOUSEDOWN);
//	document.onmousedown=clickNS4;
//} else if (document.all&&!document.getElementById){
//	document.onmousedown=clickIE4;
//}
//document.oncontextmenu=new Function("return false")