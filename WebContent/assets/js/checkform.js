
function check(formId) {
	var rebool = true;
	$("#"+formId+" .notNull").each(function(){
		var temp = $(this).val();
		temp = temp.replace("/ /g", "");  
		if(($(this).val() == "" || temp=="")  && rebool )
		{ 
			  dialogAlert("请将信息填写完整,标红为必填项");
			  rebool = false;
		}	  
	 });
	return rebool;
}

function checkform(formId){
	var rebool = true;
	$("#"+formId+" .checkNull").each(function(){
		var temp = $(this).val();
		temp = temp.replace(/ /g, "");  
		if(($(this).val() == "" || temp=="")  && rebool )
		{ 
			  dialogAlert(findInputText(this)+" 为必填项,请输入完整");
			  rebool = false;
		}	  
	 });
	$("#"+formId+" .checkNullNoText").each(function(){
		var temp = $(this).val();
		temp = temp.replace(/ /g, "");  
		if(($(this).val() == "" || temp=="")  && rebool )
		{ 
			  dialogAlert(findInputText(this)+" 为必填项,请输入完整");
			  rebool = false;
		}	  
	 });
	$("#"+formId+" .checkNum").each(function(){
		if(!IsNonNegativeInteger($(this).val()) && rebool)
		{ 
			  dialogAlert(findInputText(this)+" 必需为数字,请检查后再输入");
			  rebool = false;
		}	  
	 });
	 
	$("#"+formId+" INPUT").each(function(){
		if($(this).attr("type") != "hidden" ){
			if($(this).attr("maxlength") > 20000){ //没有限定长度
				if($(this).val().length > 50){
					dialogAlert(findInputText(this)+" 输入内容过长，最多输入50字符");
					rebool = false;
				}
			}
		}
		 
	 });
	$("#"+formId+" textarea").each(function(){ 
	  
 
		if($(this).attr("maxlength") > 20000){ //没有限定长度 
			dialogAlert(findInputText(this)+" 输入内容过长，最多输入4000字符");
			rebool = false;
		}
		else if($(this).val().length > $(this).attr("maxlength") && !$(this).attr("readonly") && $(this).attr("maxlength")>0  ){
			 
				dialogAlert(findInputText(this)+" 输入内容过长，最多输入"+$(this).attr("maxlength")+"字符");
				rebool = false; 
		} 
	 }); 
	
	if(clearLockBtn != null && !rebool){ //关闭按钮锁定
		clearLockBtn();
	}
	return rebool;
}
 
function dateTimeChangeCheck(beforeId,afterId){
	if($("#"+beforeId).val() == '' || $("#"+afterId).val() == '')
		return true;
	var ret = compareDateTime($("#"+beforeId).val(),$("#"+afterId).val() );
	if(ret == '>'){
		dialogAlert("结束时间不能大于开始时间,否则无法查询到结果数据");
		return false;
	}
	return true;
}
function compareDateTime(date1, date2){
	date1Array=date1.substring(0,10).split('-');//以'-'分开放在数组里;
	date2Array=date2.substring(0,10).split('-');
	date1=date1Array[1]+'/'+ date1Array[2]+'/'+date1Array[0]+date1.substring(10,19);//date1.substring(10,19)取后面的时间
	date2=date2Array[1]+'/'+ date2Array[2]+'/'+date2Array[0]+date2.substring(10,19);
	var a=Date.parse(date1)-Date.parse(date2);
	if(a>0){
		return ">";
	}else if(a<0){
		return "<";
	}else{
		return "=";
	}
}
function findInputText(obj){
	if($("#"+$(obj).attr("name")+"_text").length > 0 )
		return filterHtmlTag($("#"+$(obj).attr("name")+"_text").html());
	return filterHtmlTag($(obj).parent().prev().html());
}
/*****************效验库********************/
//消除输入字符串前后的半角和全角空格 
function Trim(str){ 
	str=str.replace(/(^[\s\u3000]*)|([\s\u3000]*$)/g, ""); 
	return str; 
} 

//去左空格,只能去除半角 
function ltrim(s){ 
	return s.replace( /^\s*/, ""); 
} 

//去右空格,只能去除半角 
function rtrim(s){ 
	return s.replace( /\s*$/, ""); 
} 

//去左右空格,只能去除半角 
function trim(s){ 
	return rtrim(ltrim(s)); 
} 

//是否为空值; 
function IsEmpty(_str){ 
	var tmp_str = Trim(_str); 
	return tmp_str.length === 0; 
} 
//是否有效的正整数; 
function IsPositiveInteger(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^[0-9]*[1-9][0-9]*$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的非正整数
function IsNonPositiveInteger (_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^((-\d+)|(0+))$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的负整数
function IsNegativeInteger(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^((-\d+)|(0+))$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的非负整数
function IsNonNegativeInteger(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str);
	var pattern = /^\d+$/;  
	return pattern.test(tmp_str); 
} 

// 是否有效的整数
function IsInteger(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^-?\d+$/;  
	return pattern.test(tmp_str); 
} 

// 是否有效的正浮点数
function IsPositiveFloat(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的非正浮点数
function IsNonPositiveFloat(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^((-\d+(\.\d+)?)|(0+(\.0+)?))$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的负浮点数
function IsNegativeFloat(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^(-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*)))$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的非负浮点数
function IsNonNegativeFloat(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^\d+(\.\d+)?$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的浮点数
function IsFloat(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^(-?\d+)(\.\d+)?$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的大写字母组合
function IsCapitalLetter(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^[A-Z]+$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的小写字母组合
function IsLowercaseLetter(_str){
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^[a-z]+$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的小写字母组合
function IsLetter(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^[A-Za-z]+$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的大写字母和数字的组合
function IsCapitalLetterDigit(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^[A-Z0-9]+$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的小写字母和数字的组合
function IsLowercaseLetterDigit(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^[a-z0-9]+$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的字母和数字的组合
function IsLetterDigit(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^[A-Za-z0-9]+$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的字母和数字以及下划线的组合
function IsAccount(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^\w+$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的Email地址
function IsEmail(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/; 
	return pattern.test(tmp_str); 
} 

// 是否有效的Url地址
function IsUrl(url){ 
	if(_str == "")
		return true;
	var sTemp; 
	var b=true; 
	sTemp=url.substring(0,7); 
	sTemp=sTemp.toUpperCase(); 
	if ((sTemp!="XXXX://")||(url.length<10)){ // xxxx是HTTP
	b=false; 
	} 
	return b; 
} 

// 是否有效的颜色值;
function IsColor(color){ 
	if(_str == "")
		return true;
	var temp=color; 
	if (temp===""){ 
	return true; 
	} 
	if (temp.length!=7){ 
	return false; 
	} 
	return (temp.search(/\#[a-fA-F0-9]{6}/) != -1); 
} 

// 是否有效的手机号码;
function IsMobile(_str){ 
	if(_str == "")
		return true;
	var tmp_str = Trim(_str); 
	var pattern = /1\d{10}/; 
	return pattern.test(tmp_str); 
} 
function filterHtmlTag(str) {
	str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
	str.value = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
	//str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
	return str;
}