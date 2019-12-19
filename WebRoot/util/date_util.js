function formatDate(value,dateTime,harassing) {
	if(typeof(value)=="undefined"||value==""||value==null){
		return "";
	}
	if((value + "").length <= 10){
		value=value+"000";
	}
	value=Number(value);
    var date = new Date(value);
    var year = date.getFullYear().toString();
    var month = (date.getMonth() + 1);
    var day = date.getDate().toString();
    var hour = date.getHours().toString();
    var minutes = date.getMinutes().toString();
    var seconds = date.getSeconds().toString();
    if (month < 10) {
        month = "0" + month;
    }
    if (day < 10) {
        day = "0" + day;
    }
    if (hour < 10) {
        hour = "0" + hour;
    }
    if (minutes < 10) {
        minutes = "0" + minutes;
    }
    if (seconds < 10) {
        seconds = "0" + seconds;
    }
    if(dateTime){
    	return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
    }else if(harassing){
    	return year + "-" + month + "-" + day+ " " + hour + ":" + minutes;
    }else{
		return year + "-" + month + "-" + day;
	}
}