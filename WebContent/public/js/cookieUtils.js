window.cookieUtils={}

/*
 * 在window中查找cookie
 * */
cookieUtils.findCookie = function(cookieName){
	var cookieStr = document.cookie;
	//切割cookie,得到key=value形式的字符串数组
	var cookieArr = cookieStr.split(";");
	//迭代cookie数组
	for(var i=0;i<cookieArr.length;i++){
		//再次对k=v形式的字符串切割,变成k，v的数组，第一个下标为键，第二个为值
		var cookieItemArr = cookieArr[i].split("=");
		if(cookieItemArr[0].trim() == cookieName)
			return cookieItemArr[1];
	}
	return null;
}