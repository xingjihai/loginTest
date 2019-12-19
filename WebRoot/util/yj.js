;(function(){
	$.extend($.wt, {
		formPost : function(options){
	        var sss=function (inputStr,param,pName){
	            if( $.wt.isArray( param ) ){
	                for( index in param){
	                    inputStr=sss(inputStr,param[index],pName);
	                }
	            }else if(typeof param=='object'){
	                for( key in param){
	                    inputStr=sss(inputStr,param[key],key) ;
	                }
	            }else{
	                inputStr+="<input type='hidden' name='"+pName+"' value='"+param+"'>";
	            }
	            return inputStr;
	        };
	        var form=$("<form></form>");
	        form.attr("action",options.url);
	        form.attr("method","post");
	        form.attr("style","display:none");
	        var data=options.data;
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
	 });
})(jQuery);

