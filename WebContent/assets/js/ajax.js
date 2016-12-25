 
/**
 * sendAjax参数说明：
 * func: 提交到目标函数. 
 * params.formId ： 表示把form内的值提交回去。如果有多个form，写法为"form1,form2,form3..." 
 * params.type: 传回的值的类型：text,json （默认json）
 * params.wait: 是否显示等待提示"y,n"。默认"n"
 * params.url: 提交的目标url,默认本页url。 字符串类型
 * params.nowsend:  
 * callBack:回调函数
 * 其他参数可自定义。   
 *  
 */
$.ajaxSetup({timeout:900000}); 
function sendAjax(func,params,sendAjaxCallBack){
	if(params.wait == 'y')
		dialogWait(); 
	var _addAjaxParams = function(obj,key,value){ 
		if(obj[key] == null)
			obj[key] = value;
		else 
			obj[key] = obj[key]+","+value;
	} 
	var myCallBack = function(jo){ 
		if(params.wait == 'y')
			dialogWait(1);
		if(sendAjaxCallBack != null){
			if(jo.error != null && jo.error == 'no login'){
				dialogAlert("您离开电脑太久，为保证系统使用安全，请重新登录"); 
			}else{
				sendAjaxCallBack(jo);
			}
		}
			
	}
	var postData = {}; 
	postData['method'] = func;
	console.log("func: "+func);
	//初始化post数据
	for(var p in params)
	{
		if(p == "formId")
		{ 
			var forms = params[p].split(",")  ;
			for(var i=0; i<forms.length; i++){
				var temp = $("#"+forms[i]).formSerialize().replace(/\+/g, "%20").split("&");
				for(var x=0;x<temp.length; x++){
					var keyValue = temp[x].split("=");
					
					_addAjaxParams(postData,keyValue[0],decodeURIComponent(keyValue[1])); 
				}
			} 
		}
		else if( p == "url" || p == "wait" || p == "type"){
			 continue;
		}  
		else { 
			_addAjaxParams(postData,p,params[p]); 
		} 
	}
	
	
	var url;
	if(params.url != null)
		url = params.url;
	else
		url = window.location.href;
   
	if(url.indexOf("?")>-1)
		url = url+"&"+randomWord(6);
	else
		url = url+"?"+randomWord(6);
 
	var type = params.type; 
	if(type == null)
		 type="json";
	var afterSend = function(){
		$.ajax({
			 url: url,
			 type: 'POST',
			 timeout: 900000,//超时时间设定
			 data:postData,//参数设置
			 error: function(xmlHttpRequest, error){ },//错误处理，隐藏进度条
			 success: function(html){
				 if(type=="json")
					 myCallBack(JSON.parse(html));
				 else
					 myCallBack(html);
			 }
			});
	} 
	
	if(params.nowsend)
		afterSend();
	else
		setTimeout(afterSend,200);
 
}

 
function getErrorMsg(jo){
	if(jo.error != null)
		return jo.error;
	return "";
}
/**
获取当前时间字符串
*/
function getNowTimeStr(){
	var myDate=new Date()
	return myDate.getFullYear()+ "-" + myDate.getMonth()+ "-" + myDate.getDate()+ " "+myDate.getHours()+ ":"+myDate.getMinutes()+ ":"+myDate.getSeconds();
}

//生成N位随机数
function randomWord(n){
	if(n == null)
		n = 6;
    var baseStr = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var r ="";
    for (var i = 0; i < n; i++) {
    	r += ""+baseStr.charAt( Math.round(Math.random() * 62 )); 
    }
    return r;
}
/**
 * URL params To JSON
 */
function urlParamsToJSON(str){
	var json = {};
	
	var _addAjaxParams = function(obj,key,value){ 
		if(obj[key] == null)
			obj[key] = value;
		else 
			obj[key] = obj[key]+","+value;
	} 
	
	var temp = str.split("&");
	for(var x=0;x<temp.length; x++){
		var keyValue = temp[x].split("=");
		_addAjaxParams(json,keyValue[0],decodeURIComponent(keyValue[1])); 
	}
	return json;
}

/**checkbox gorup tool **/
/**
 * 返回已选择的数目
 */
function hasSelectCkCount(name){
	var cout = 0;
	var ckUserList = document.getElementsByName(name); 
	for(var i=0; i<ckUserList.length; i++){
		if(ckUserList[i].checked)
			 cout++;
	}
	return cout;
}

/**
 * 返回已选择的值，只返回一个
 */
function hasSelectCkValue(name){
	var ckUserList = document.getElementsByName(name); 
	for(var i=0; i<ckUserList.length; i++){
		if(ckUserList[i].checked)
			 return ckUserList[i].value;
	}
}

/**
 * 返回已选择的值，返回多个
 */
function hasSelectCkValues(name){
	var x = 0;
	var reobj = null;
	var ckUserList = document.getElementsByName(name); 
	for(var i=0; i<ckUserList.length; i++){
		if(ckUserList[i].checked)
		{
			if(reobj == null)
				reobj = {};
			reobj[x] = ckUserList[i].value;
			x++;
		}
	}
	return reobj;
}

/**
 * 返回已选择的值，返回一个字符串，逗号隔开
 */
function hasSelectCkValuesStr(name){
	var reobj = "";
	var ckUserList = document.getElementsByName(name); 
	for(var i=0; i<ckUserList.length; i++){
		if(ckUserList[i].checked)
		{
			reobj += ckUserList[i].value+","; 
		}
	}
	reobj = reobj.substring(0, reobj.length-1);
	return reobj;
}
/**checkbox gorup tool END**/

function initSelectData(domId, urlparam, selectedValue){
	var callback = function(jo){
 		for(var i=0; i<jo.length; i++){
			var   objOption   =   document.createElement( "OPTION"); 
			objOption.text=  jo[i].text; 
			objOption.value= jo[i].val; 
			document.getElementById(domId).options.add(objOption); 
		} 
 		document.getElementById(domId).value = selectedValue;
	}; 
	
	var url = "/"+globalHostName+"/SelectData.do?"+urlparam;
	sendAjax("func",{"url":url },callback);
	
}

function arrayToStr(obj){
	if(obj == null)
		return "";
	var str = ''; 
	for(var i=0; i<obj.length; i++){
		if(obj[i] != '' && obj[i] != null ){
			str += obj[i]+",";
		}
	}
	if(str.length > 0)
		str = str.substring(0, str.length-1);
	return str;
}

function strToArray(str){
	if(str == null)
		return [];
	var obj = []; 
	var arry = str.split(",");
	for(var i=0; i<arry.length; i++)
	{
		if(arry[i] != '' && arry[i] != null ){
			obj.push(arry[i]);
		}
	}
	return obj;
}
 
function removeArrayKey(array ,keyword){
	for(var i=0;i<array.length;i++){ 
		if(array[i]==keyword){ 
			array.splice(i, 1); 
		}
	} 
	return array;
}
