 



/**
d * 函数清单: 
 * dialogWait(flg); 0 打开， 1 关闭
 * dialogAlert(text) 
 * dialogConfirm(text,callBack) 
 * dialogIframe(title,url,width,height) 
 * dialogIframeClose();
 * dialogMsg(text) 
 * dialogShowDiv(divid,title)  // 
 * dialogCloseDiv(id) 
 * dialogProgress(num) //num == -1关闭进度条对话框 
 */

var dialogWidthAdjust = 22;
function clearLockBtn(){
}
/**
 * 警告对话框函数 dialogAlert(text); text: 提示信息
 */
function dialogAlert(text) {
	
	alert(text);
	clearLockBtn();	
}


 
/**
 * 等待对话框函数 dialogWait(flag); flg:等待对话框打开还是关闭 0 打开， 1 关闭
 */
 
function dialogWait(flg) {
	var divid_mask= "mes-wait-div";
	if(flg){
		$("#" + divid_mask).hide();
 		lockWindow(1);  
	}
	else{ 
		var position = positionCompute($("#" + divid_mask).width(), $(
				"#" + divid_mask).height());
		$("#" + divid_mask).css("left", position.left).css("top", position.top);
		$("#" + divid_mask).show();
 		lockWindow();  
	}
}



/**
 * 确认对话框函数 dialogConfirm(text,callBack); text: 提示信息
 * callBack:确认回调函数. 
 */
function dialogConfirm(text, callBack) {
	if(confirm(text)){
		callBack();
	}else{
		clearLockBtn();	
	}
}
 

/**
 * iframe对话框函数 dialogIframe(title,url,width,height); url: iframe链接地址
 */
function dialogIframe(title, url, width, height) { 
	
	if(url == null)
		return ;
	
	//防止IE缓存
	if(url.indexOf("?") > -1){
		url = url +"&"+randomWord(8);
	}
	else{
		url = url +"?"+randomWord(8);
	}
 
	var divid_mask = "divIframeMask";
	$("#iframe-title").html(title);
	$("#mes-iframe").attr("src",url);
	if(width != null)
		$("#mes-iframe").width(width+" px");
	if(height != null)
		$("#mes-iframe").height(height+" px");
	
	$("#" + divid_mask).width($("#mes-iframe").width()+ dialogWidthAdjust +" px"); 
	
	var position = positionCompute($("#" + divid_mask).width(), $(
			"#" + divid_mask).height());
	$("#" + divid_mask).css("left", position.left).css("top", position.top);
	$("#" + divid_mask).show();
	$("#mes-iframe-close").click(function(){
		$("#mes-iframe").attr("src","about:blank");
		$("#" + divid_mask).hide();
		lockWindow(1);
	});
	lockWindow();
} 
function dialogIframeClose(){
	$("#mes-iframe-close").click();
}
/**
 * Msg 对话框函数 dialogMsg(text) ,显示在右下角 
 */
function dialogMsg(text){
	//$("#mes-msg-div .mes-conten span").html();
	
	var dd = document.documentElement; 
	var ddw = dd.clientWidth;//浏览器内容框宽度
	var ddh = dd.clientHeight;//浏览器内容框高度  
	
	$("#msg-msg-wrap").show();  
	$("#msg-msg-wrap").css("left", (ddw-$("#msg-msg-wrap").width()) ).css("top", (ddh - $("#msg-msg-wrap").height() )  );
	$("#mes-msg-div").css("top",  $("#mes-msg-div").height()  );
	$("#mes-msg-div .mes-conten span").html(text);
	$("#mes-msg-div").animate({top: '0'},500); 
	$("#mes-msg-close").click(function(){
		$("#msg-msg-wrap").hide();
	})
}
 
 
function dialogShowDiv(id,title){ 
	if(title != null){
		$("#" + id).attr("title",title);
	}
	showDialog(id);
	lockWindow();
}

function dialogCloseDiv(id){ 
	showDialogClose(id);
	lockWindow(1);
	clearLockBtn();	
}

/**
 * 进度条对话框。num显示当前进度值。num范围 0-- 100。 当num = -1 关闭进度条
 */
function dialogProgress(num){
	var pid = "spaceused1"; 
	var divid_mask = "mes-process-div";
	if(num == -1){ 
		$("#"+divid_mask).hide();  
		$("#"+pid).progressBar(0);
	}
	else{
		if(!$("#"+divid_mask).is(":visible")){ 
			var position = positionCompute($("#" + divid_mask).width(), $(
					"#" + divid_mask).height()); 
			$("#" + divid_mask).css("left", position.left).css("top", position.top); 
			$("#" + divid_mask).show();
		} 
		$("#"+pid).progressBar(num);
	}
}

function lockWindow(flg){
	if(flg == 1){ 
		$("#lockwindow").hide();
	}
	else{
 
		var dd = document.documentElement; 
		var bd = document.body; 
		
		var ddw = dd.clientWidth;//浏览器内容框宽度
		var ddh = dd.clientHeight;//浏览器内容框高度 
		
		var bdw = bd.clientWidth;//浏览器内容框宽度
		var bdh = bd.clientHeight;//浏览器内容框高度 
		
	 
		if(ddw > bdw){
			$("#lockwindow").width(ddw+" px"); 
		}else{
			$("#lockwindow").width(bdw+" px"); 
		}
		if(ddh > bdh){
			$("#lockwindow").height(ddh+" px"); 
		}else{
			$("#lockwindow").height(bdh+" px"); 
		} 
		
		$("#lockwindow").show();
	}
}

/************************以下为对话框内部函数，界面不调用*****************************/
/**
 * 计算对话框中间位置
 * ow ,对话框宽度
 * oh ,对话框高度
 */
function positionCompute(ow, oh) {

	var dd = document.documentElement;
	var db = document.body;

	var ddw = dd.clientWidth;//浏览器内容框宽度
	var ddh = dd.clientHeight;//浏览器内容框高度
	var dbw = db.clientWidth;//页面总宽度
	var dbh = db.clientHeight;//页面总长度
	var st = Math.max(dd.scrollTop, db.scrollTop);//被纵向滚动条卷去的长度
	var sl = Math.max(dd.scrollLeft, db.scrollLeft);//被横向滚动条卷去的长度

	var minX = sl;
	var maxX = ddw + minX - ow;
	var centerX = maxX / 2;
	var minY = st;
	var maxY = ddh + minY - oh;
	var centerY = (ddh * 0.382 - oh / 2 + minY > minY) ? ddh * 0.382 - oh
			/ 2 + minY : (maxY + minY) / 2;//黄金比例算法

	var reobj = {};
	reobj.top = centerY;
	reobj.left = centerX;
	return reobj;
}
function showDialog(divid) {
	$("#mes-close").click(); 
	var divid_mask = "divMask";
	$("#"+divid_mask+" .mes-title-content span").html($("#" + divid).attr("title"));
	if ($("#" + divid).attr("clone") == "false") { 
		if ($("#"+divid_mask+" .mes-conten").find("div[id='" + divid + "']").length == 0){
			$("#"+divid_mask+" .mes-conten")[0].appendChild(document.getElementById(divid)); 
			$("#" + divid_mask).width($("#" + divid).width()+ dialogWidthAdjust +" px");
		}
		$("#" + divid).show();
		
	} else {
		if ($("#"+divid_mask+" .mes-conten").find("div[id='" + divid + "_clone']").length == 0) {
			var clone = $("#" + divid).clone(true);
			$(clone).attr("id", divid + "_clone");
			$("#"+divid_mask+" .mes-conten").append($(clone));
			$("#" + divid_mask).width($(clone).width()+ dialogWidthAdjust+" px");
		}
		$("#" + divid + "_clone").show();
	}
	 
	var position = positionCompute($("#" + divid_mask).width(), $(
			"#" + divid_mask).height());
	$("#" + divid_mask).css("left", position.left).css("top", position.top);
	$("#" + divid_mask).show();
	$("#mes-close").click(function(){dialogCloseDiv(divid)});
	
}
function showDialogClose(divid) {
	if(divid == null){ 
			return;
	}
	var divid_mask = "divMask";

	if ($("#" + divid).attr("clone") == "false") {
		$("#" + divid).hide();
	} else {
		$("#" + divid + "_clone").remove();
	}
	$("#" + divid_mask).hide();

}

/**
 * 自动调整图片大小
 * @param ImgD
 * @param FitWidth
 * @param FitHeight
 */
function DrawImage(ImgD,FitWidth,FitHeight){
	var image=new Image();
	image.src=ImgD.src;
	if(image.width>0 && image.height>0){
		if(image.width/image.height>= FitWidth/FitHeight){
		if(image.width>FitWidth){
		ImgD.width=FitWidth;
		ImgD.height=(image.height*FitWidth)/image.width;
		}else{
		ImgD.width=image.width;
		ImgD.height=image.height;
		}
		} else{
		if(image.height>FitHeight){
		ImgD.height=FitHeight;
		ImgD.width=(image.width*FitHeight)/image.height;
		}else{
		ImgD.width=image.width;
		ImgD.height=image.height;
		}
		}
	}
}