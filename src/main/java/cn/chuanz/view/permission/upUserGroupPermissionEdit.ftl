<@html>

<body>
<form id="form1" >
<div style="width:850px; margin:auto; border: 1px solid #98B8E7;background: #fff;">
	<div class="editTableDiv">
		<input type="hidden" name="groupId" value="${groupId}"/>
 		<h2>${groupName}</h2>
 		<input class="ace" type="checkbox" name="permissionall" onclick="check_all(this,'permission')" /><span class="lbl" >全选/全不选</span>
 		<#list rootList as root>
 		<div><span class="lbl" >${root.name}</span></div>
 		<#list subList as sub>
 		<#if sub.pid == root.id>
 		<input class="ace" type="checkbox" name="permission" id="permission_${sub.id}" value="${sub.id}" /><span class="lbl" >${sub.name}</span>
 		</#if>
 		</#list>
 		<hr>
 		</#list>
 		
 		<div class="clearfix form-actions">
				<div class="col-md-offset-3 col-md-9">
					<button class="btn btn-info" type="button" onclick="javascript:doInsert()">
						<i class="ace-icon fa fa-check bigger-110"></i>
						保存信息
					</button>

					&nbsp; &nbsp; &nbsp;
					<button class="btn" type="reset" onclick="doCancel()">
						<i class="ace-icon fa fa-undo bigger-110"></i>
						取消返回
					</button>
				</div>
		</div>
		<!-- <div class="Zoom"  style="margin-left: 160px;"> 
			<input type="button" value="取消返回" onclick="doCancel()" style="float: right; margin-top: 5px;"/> 
			<input type="button" value="保存信息" onclick="javascript:doInsert()" style="float: right; margin: 5px;"/>
		</div> -->
	</div>
	</div>
</form>

<script>
var permissions = ${permissionList};
init();
function init() {
	for (var key in permissions)
	{ 
		$("#permission_"+permissions[key]).attr("checked",true);
	} 
}
 
function doInsert(){
	var callBack = function(jo){
		if(jo.result == "0"){
			dialogAlert("操作失败!" + getErrorMsg(jo));
		}
		else{
			dialogAlert("操作完成！");
			window.opener.gotoPage(1);
			window.close();
		}
	}
	sendAjax("savePermission",{"formId":"form1","url":"${host}/permission/upUserGroup.do"},callBack); 
}

function doCancel(){
	var callBack = function (){
		window.close();
	}
	dialogConfirm("是否确定不保存直接关闭本界面?",callBack);
	
}

function check_all(obj,ckname){
	  var checkboxs = document.getElementsByName(ckname);
	  for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;}
}
</script> 
</body>
</@html>