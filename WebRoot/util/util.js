/**
 * 判断是否晚于当前日期  年月日
 * @param dateStr
 * @returns
 */
function isAfterNow(dateStr){
    debugger
    if(dateStr!=null&&dateStr!=""){
        var now=new Date();
        var ny=now.getFullYear();
        var nm = now.getMonth()+1;
        var nd = now.getDate();

        var dataStr=dateStr.split("-");
        if(dataStr[0]<ny){
            return true;
        }
        if(dataStr[1]<nm){
            return true;
        }
        if(dataStr[2]<=nd){
            return true;
        }
        // if(date>nowDate){  //非ie8下可以使用该方法。
        //     return false;
        // }
    }else{
        return true;
    }
    return false;
}

/**
 * 判断是否晚于当前日期   2018-03-29 23:04
 */
function isAfterNow(dateStr) {
    if(dateStr!=null&&dateStr!=""){
        var date=new Date(  dateStr.replace(/-/g,"/") );
        var now=new Date();
        if(date>now){
            return true;
        }
    }
    return false;
}


/**
 * 动态创建form表单提交
 * 场景：
 * ajax不支持流的形式返回，所以只能通过form表单
 * 但是如果将参数拼接在url上，又容易造成url过长而产生的异常
 * @param option
 * 
 *  $.wt.formPost({
        url:BASE_PATH + DATA_PATH + "/di/survey/mediation/list/exportmediation",
        data:{
            rowIds:rowIds,
            array:[1,2,4],
            caze:{sueAmount:1.1,caseWords:3222}
        }
    })
    
 */
function formPost(option){  //data 只支持基本数据类型
    var form=$("<form></form>")
    form.attr("action",option.url);
    form.attr("method","post");
    form.attr("style","display:none");
    var data=option.data;
    if(data){
        var  inputStr="";
        for(key in data){
            inputStr=sss(inputStr,data[key],key);
        }
        form.append(inputStr);
    }
    form.appendTo("body");
    form.submit();
}

/**
 * 迭代对象或数组 --只支持一层对象
 */
function sss(inputStr,param,pName){
    if( isArray( param ) ){ //数组
        for( index in param){
            inputStr=sss(inputStr,param[index],pName);
        }
    }else if(typeof param=='object'){  //对象
        for( key in param){
            inputStr=sss(inputStr,param[key],key) ;
        }
    }else{
        inputStr+="<input type='hidden' name='"+pName+"' value='"+param+"'>";
    }
    return inputStr;
}

function isArray(obj){
    return Object.prototype.toString.call(obj) === '[object Array]';
}

/**
* 迭代对象或数组 -- 支持多个层（但需要服务端支持该形式）
*/
function sss2(inputStr,param,pName){
 if( Array.isArray( param ) ){ //数组
     for( index in param){
         inputStr=sss(inputStr,param[index],pName+"["+index+"]");
     }
 }else if(typeof param=='object'){  //对象
     for( key in param){
         inputStr=sss(inputStr,param[key],pName+"."+key) ;
     }
 }else{
     inputStr+="<input type='hidden' name='"+pName+"' value='"+param+"'>";
 }
 return inputStr;
}


/**
 * 根据 form 表单id获取对象
 */
function getFormData(formId){
	var fields=$("#"+formId).serializeArray();
	var o={};
	jQuery.each(fields, function(i, fields){   //数组转param对象.
	    if(o[this.name]){
		    /*
		    表单中可能有多个相同标签，比如有多个label，
		    那么你在json对象o中插入第一个label后，还要继续插入，
		    那么这时候o[label]在o中就已经存在，所以你要把o[label]做嵌套数组处理
		    */
		    //如果o[label]不是嵌套在数组中
		    if(!o[this.name].push){
		        o[this.name]=[o[this.name]];     // 将o[label]初始为嵌套数组，如o={a,[a,b,c]}
		    }
		    o[this.name].push(this.value || ''); // 将值插入o[label]
		}else{
		    o[this.name]=this.value || '';       // 第一次在o中插入o[label]
		}
	});
	return o;
}




