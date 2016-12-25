<@html>

<script>
function gotoPage(nowPage){
	var sendAjaxCallBack = function(jo){
		$("#griddata").html(jo.content);
		$("#griddata").show();
	}
	sendAjax("doQuery", {"formId":"queryform","nowPage":nowPage}, sendAjaxCallBack);
}

function add () {
	$("#queryData").hide();
	$("#edit_id").val("");
	$("#edit_groupName").val("");
	$("#edit").show();
}

function editPermission (id) {
	var sendAjaxCallBack = function(jo){
		$("#queryData").hide();
		$("#ep_groupId").val(jo.groupId);
		$("#ep_groupName").html(jo.groupName);
		var checkboxs = document.getElementsByName("permission");
		for(var i=0;i<checkboxs.length;i++){$(checkboxs[i]).removeAttr("checked");}
		var permissions = jo.permissionList;
		for (var key in permissions)
		{ 
			$("#permission_"+permissions[key]).prop("checked",true);
		}
		$("#editPermission").show();
	}
	
	sendAjax("editPermission", {"groupId":id}, sendAjaxCallBack);
}

function del (ids) {
	var sendAjaxCallBack = function(jo){
		if (jo.result==1) {
			alert("删除成功！");
		} else {
			alert("删除失败！");
		}
		gotoPage(1);
	}
	if (ids == null) {
		if (hasSelectCkCount("chbox") == 0) {
			alert("请至少选择一条数据！");
		} else {
			var doDel = function () {
				ids = hasSelectCkValuesStr("chbox");
				sendAjax("del", {"ids":ids}, sendAjaxCallBack);
			}
			dialogConfirm('删除用户组后，用户组对应的菜单权限也会一起删除，是否继续？',doDel);
		}
	} else {
		var doDel = function () {
			sendAjax("del", {"ids":ids}, sendAjaxCallBack);
		}
		dialogConfirm('删除用户组后，用户组对应的菜单权限也会一起删除，是否继续？',doDel);
	}
	
}

function editCanal () {
	$("#edit").hide();
	$("#queryData").show();
}

function save () {
	var sendAjaxCallBack = function(jo){
		if (jo.result==1) {
			alert("保存成功！");
			$("#edit").hide();
			$("#queryData").show();
			gotoPage(1);
		} else {
			alert("保存失败！");
		}
	}
	
	sendAjax("save", {"formId":"editForm"}, sendAjaxCallBack);
}

function check_all(obj,ckname){
	  var checkboxs = document.getElementsByName(ckname);
	  for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;}
}

function doInsert(){
	var callBack = function(jo){
		if(jo.result == "0"){
			dialogAlert("操作失败!" + getErrorMsg(jo));
		}
		else{
			dialogAlert("操作完成！");
			$("#editPermission").hide();
			$("#queryData").show();
		}
	}
	sendAjax("savePermission",{"formId":"form1","url":"${host}/permission/upUserGroup.do"},callBack); 
}

function doCancel(){
	var callBack = function (){
		$("#editPermission").hide();
		$("#queryData").show();
	}
	dialogConfirm("是否确定不保存直接关闭本界面?",callBack);
	
}

</script>

	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">账号权限</a>
				</li>
				<li class="active">用户组管理</li>
			</ul><!-- /.breadcrumb -->

		</div>

		<div id="queryData" class="page-content">
			<form id="queryform">
			<div class="form-group">
				<div class="col-sm-9">
				<span>组名</span>
				<input type="text" id="groupName" name="groupName" style="height: 30px;">
				<a class="btn btn-primary btn-sm pull-right" onclick="gotoPage(1)">
					查询
				</a>
				</div>
				
				<div class="pull-right tableTools-container">
					<div class="dt-buttons btn-overlap btn-group">
					<a onclick="add()" class="dt-button buttons-collection buttons-colvis btn btn-white btn-primary btn-bold" tabindex="0" aria-controls="dynamic-table" data-original-title="" title=""><span><i class="fa fa-plus bigger-110 blue"></i> <span>新增</span></span></a>
					<a onclick="del()" class="dt-button buttons-copy buttons-html5 btn btn-white btn-primary btn-bold" tabindex="0" aria-controls="dynamic-table" data-original-title="" title=""><span><i class="fa fa-trash bigger-110 pink"></i> <span>删除</span></span></a>
					</div>
				</div>
			</div>
			</form>
			
			<div class="bsep"></div>
			
			<div class="row" id="griddata">
				<!--dataHtml start-->
				<div class="col-xs-12">
					<@pager pagerTool=pagerToolObj  func="gotoPage" />
					<table id="simple-table" class="table  table-bordered table-hover">
						<thead>
							<tr>
								<th class="center">
									<label class="pos-rel">
										<input type="checkbox" class="ace" onclick="check_all(this,'chbox')">
										<span class="lbl"></span>
									</label>
								</th>
								<th>序号</th>
								<th>组名</th>
								<th>权限管理</th>
							</tr>
						</thead>
	
						<tbody>
						<#list userGroupList! as dataItem>
							<tr>
								<td class="center">
									<label class="pos-rel">
										<input type="checkbox" class="ace" name="chbox" value="${dataItem["id"]!}">
										<span class="lbl"></span>
									</label>
								</td>
								<td class="" >${dataItem_index+1}</td>
								<td class="" >${dataItem["groupName"]!}</td>
								<td>
									<div class="hidden-sm hidden-xs btn-group">	
										<button class="btn btn-xs btn-info" onclick="editPermission('${dataItem["id"]!}')">
											<i class="ace-icon fa fa-pencil bigger-120"></i>
										</button>
	
										<button class="btn btn-xs btn-danger" onclick="del('${dataItem["id"]!}')">
											<i class="ace-icon fa fa-trash-o bigger-120"></i>
										</button>
	
									</div>
	
									<div class="hidden-md hidden-lg">
										<div class="inline pos-rel">
											<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
												<i class="ace-icon fa fa-cog icon-only bigger-110"></i>
											</button>
	
											<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
												<li>
													<a href="javascript:void(0)" onclick="edit('${dataItem["id"]!}')" class="tooltip-success" data-rel="tooltip" title="" data-original-title="Edit">
														<span class="green">
															<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
														</span>
													</a>
												</li>
	
												<li>
													<a href="javascript:void(0)" onclick="del('${dataItem["id"]!}')" class="tooltip-error" data-rel="tooltip" title="" data-original-title="Delete">
														<span class="red">
															<i class="ace-icon fa fa-trash-o bigger-120"></i>
														</span>
													</a>
												</li>
											</ul>
										</div>
									</div>
								</td>
							</tr> 
						</#list>
	
						</tbody>
					</table>
				</div><!-- /.span -->
				<!--dataHtml end-->
			</div>
		</div> <!-- /.page-content -->
		
		<!-- 编辑对话框开始 -->
		<div id="edit" class="page-content" style="display:none">
		<div class="row">
			<div class="col-xs-12">
			<form class="form-horizontal" role="form" id="editForm">
			<input class="col-xs-10 col-sm-5" type="hidden" id="edit_id" name="id" value="">
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1">组名</label>
				<div class="col-sm-9">
					<input type="text" id="edit_groupName" name="groupName" class="col-xs-10 col-sm-5">
				</div>
			</div>
			<div class="clearfix form-actions">
				<div class="col-md-offset-3 col-md-9">
					<button class="btn btn-info" type="button" onclick="save()">
						<i class="ace-icon fa fa-check bigger-110"></i>
						保存
					</button>

					&nbsp; &nbsp; &nbsp;
					<button class="btn" type="reset" onclick="editCanal()">
						<i class="ace-icon fa fa-undo bigger-110"></i>
						取消
					</button>
				</div>
			</div>
			</form>
			</div>
			</div>
		</div>
		<!-- 编辑对话框结束 -->
		
		
		<!-- 编辑权限开始 -->
		<div id="editPermission" class="page-content" style="display:none">
		<div class="row">
			<div class="col-xs-12">
				<form id="form1" >
				<input type="hidden" name="groupId" id="ep_groupId" value=""/>
				<div class="form-group">
					<h2 id="ep_groupName"></h2>
					<input class="ace" type="checkbox" name="permissionall" onclick="check_all(this,'permission')" /><span class="lbl" >全选/全不选</span>
				</div>
				
				<#list rootList as root>
				<div><span class="lbl" >${root.name}</span></div>
				<div class="form-group">
				<#list subList as sub>
		 		<#if sub.pid == root.id>
		 		<input class="ace" type="checkbox" name="permission" id="permission_${sub.id}" value="${sub.id}" /><span class="lbl" >${sub.name}</span>
		 		</#if>
		 		</#list>
				</div>
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
				</form>
			</div>
			</div>
		</div>
		<!-- 编辑权限结束 -->
		
	</div>
	

</@html>