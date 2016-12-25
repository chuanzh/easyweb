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
	$('#edit_pid')[0].selectedIndex = 0;
	$("#edit_menuName").val("");
	$("#edit_menuUrl").val("");
	$("#edit_icon").val("");
	$("#edit").show();
}

function edit (id) {
	var sendAjaxCallBack = function(jo){
		$("#queryData").hide();
		$("#edit_id").val(jo.id);
		$("#edit_pid ").val(jo.pid);
		$("#edit_menuName").val(jo.name);
		$("#edit_menuUrl").val(jo.url);
		$("#edit_icon").val(jo.icon);
		checkSelectChange();
		$("#edit").show();
	}
	sendAjax("findById", {"id":id}, sendAjaxCallBack);
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
			dialogConfirm('确认要删除？',doDel);
		}
	} else {
		var doDel = function () {
			sendAjax("del", {"ids":ids}, sendAjaxCallBack);
		}
		dialogConfirm('确认要删除？',doDel);
	}
}

function delParents () {
	var sendAjaxCallBack = function(jo){
		if (jo.result==1) {
			alert("删除成功！");
		} else {
			alert("删除失败！");
		}
		window.location.reload()
	}
	var pid = document.getElementById("pid").value;
	if (pid != null && pid != "") {
		var delCheck = function () {
			var doDel = function () {
				var ids = hasSelectCkValuesStr("chbox");
				sendAjax("del", {"ids":pid}, sendAjaxCallBack);
			}
			dialogConfirm('删除菜单后，用户组对应的菜单权限也会一起删除，是否继续？',doDel);
		}
		dialogConfirm('删除父节点，其子节点也会一起删除，是否继续？',delCheck);
	} else {
		alert("请选择一个父节点！");
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
			window.location.reload()
		} else {
			alert("保存失败！");
		}
	}
	
	sendAjax("save", {"formId":"editForm"}, sendAjaxCallBack);
}

function checkSelectChange() {
	var selectVal = $("#edit_pid").val();
	var msg = "父节点为空链接地址不需填写";
	if (selectVal == 0) {
		$("#edit_menuUrl").removeClass("focused");
		$("#edit_menuUrl").addClass("disabled");
		$("#edit_menuUrl").attr("disabled","disabled");
		$("#edit_menuUrl").val(msg);
	} else {
		$("#edit_menuUrl").removeClass("disabled");
		$("#edit_menuUrl").addClass("focused");
		$("#edit_menuUrl").removeAttr("disabled");
		var url = $("#edit_menuUrl").val();
		if (url == msg) {
			$("#edit_menuUrl").val("");
		}	
	}
}

function grid_CheckAll(obj){
	  var checkboxs = document.getElementsByName("chbox");
	  for(var i=0;i<checkboxs.length;i++){checkboxs[i].checked = obj.checked;}
}
</script>

	<div class="main-content-inner">
		<div class="breadcrumbs ace-save-state" id="breadcrumbs">
			<ul class="breadcrumb">
				<li>
					<i class="ace-icon fa fa-home home-icon"></i>
					<a href="#">账号权限</a>
				</li>
				<li class="active">菜单管理</li>
			</ul><!-- /.breadcrumb -->

		</div>
	    
	    <div id="queryData" class="page-content">
			<form id="queryform">
			<div class="form-group">
				<div class="col-sm-9">
				<span>父节点</span>
				<select id="pid" name="pid">
					<option value="">--全部--</option>
					<#list rootMenuList! as dataItem>
                    <option value="${dataItem["id"]!}">${dataItem["name"]!}</option>
                    </#list>
				</select>
				<span class="lsep"></span>
				<span>菜单名</span>
				<input type="text" id="menuName" name="menuName" style="height: 30px;">
				<a class="btn btn-primary btn-sm pull-right" onclick="gotoPage(1)">
					查询
				</a>
				</div>
				
				<div class="pull-right tableTools-container">
					<div class="dt-buttons btn-overlap btn-group">
					<a onclick="add()" class="dt-button buttons-collection buttons-colvis btn btn-white btn-primary btn-bold" tabindex="0" aria-controls="dynamic-table" data-original-title="" title=""><span><i class="fa fa-plus bigger-110 blue"></i> <span>新增</span></span></a>
					<a onclick="del()" class="dt-button buttons-copy buttons-html5 btn btn-white btn-primary btn-bold" tabindex="0" aria-controls="dynamic-table" data-original-title="" title=""><span><i class="fa fa-trash bigger-110 pink"></i> <span>删除</span></span></a>
					<a onclick="delParent()" class="dt-button buttons-copy buttons-html5 btn btn-white btn-primary btn-bold" tabindex="0" aria-controls="dynamic-table" data-original-title="" title=""><span><i class="fa fa-trash bigger-110 pink"></i> <span>删除父节点</span></span></a>
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
										<input type="checkbox" class="ace" onclick="grid_CheckAll(this)">
										<span class="lbl"></span>
									</label>
								</th>
								<th>序号</th>
								<th>父节点</th>
								<th>名称</th>
								<th>地址</th>
								<th>图标</th>
								<th></th>
							</tr>
						</thead>
	
						<tbody>
						<#list menuList! as dataItem>
							<tr>
								<td class="center">
									<label class="pos-rel">
										<input type="checkbox" class="ace" name="chbox" value="${dataItem["id"]!}">
										<span class="lbl"></span>
									</label>
								</td>
								<td class="" >${dataItem_index+1}</td>
								<td class="" >${dataItem["pname"]!}</td>
								<td class="">${dataItem["name"]!}</td>
								<td class="">${dataItem["url"]!}</td>
								<td class="">${dataItem["icon"]!}</td>
								<td>
									<div class="hidden-sm hidden-xs btn-group">	
										<button class="btn btn-xs btn-info" onclick="edit('${dataItem["id"]!}')">
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
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1">父节点</label>
				<div class="col-sm-9">
	            <select id="edit_pid" name="pid" style="width: 150px;" onchange="checkSelectChange()">
	               	<option value="0"></option>
					<#list rootMenuList! as dataItem>
	                <option value="${dataItem["id"]!}">${dataItem["name"]!}</option>
	                </#list>
	            </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1">名称</label>
				<div class="col-sm-9">
					<input type="text" id="edit_menuName" name="menuName" class="col-xs-10 col-sm-5">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1">地址</label>
				<div class="col-sm-9">
					<input type="text" id="edit_menuUrl" name="menuUrl" class="col-xs-10 col-sm-5">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label no-padding-right" for="form-field-1">图标</label>
				<div class="col-sm-9">
					<input type="text" id="edit_icon" name="icon" class="col-xs-10 col-sm-5">
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
		
		
	</div>
		
</@html>