<#assign host = "/easyweb"> 
<!-- --------------html-------------- -->
<#macro html>
<!DOCTYPE html>
<html>
<head>
<#include "basecssjs.ftl"/>
<#include "footerjs.ftl"/>
</head>
<body>
	<#nested/> 
</body>
</html>
</#macro> 
<!-- --------------html end-------------- -->

<#macro pager pagerTool="" func="gotoPage">
<!-- 分页信息  -->
<#if pagerTool != "" >

<#if pagerTool.nowPage == 1>
  	<#assign t = 1 >
<#else>
  	<#assign t = pagerTool.nowPage - 1 >
</#if> 

<#if pagerTool.nowPage == pagerTool.pagerCount>
  	<#assign t1 = pagerTool.pagerCount >
<#else>
  	<#assign t1 = pagerTool.nowPage + 1  >
</#if> 

<#assign startPage = pagerTool.nowPage - 2  >
<#assign endPage = pagerTool.nowPage + 2  >
<#if startPage lt 1 >
	<#assign startPage = 1 >
</#if>
<#if endPage gt pagerTool.pagerCount >
	<#assign endPage = pagerTool.pagerCount >
</#if>


<div class="row">
	<div class="col-xs-6">
		<div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">
			结果总数：${pagerTool.totalCount!}，每页条数： ${pagerTool.pageSize!}， 页面总数：${pagerTool.pagerCount!}
		</div>
	</div>
	
	<div class="col-xs-6">
		<div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
			<ul class="pagination">
				<li class="paginate_button previous" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_previous">
					<a onclick="goto_BeforePager('1')" href="javascript:void(0);">首页</a></li>
				<#list startPage..endPage as indexPage>
				<#if pagerTool.nowPage == indexPage >
					<li class="paginate_button active" aria-controls="dynamic-table" tabindex="0">
					<a href="javascript:void(0);" >${indexPage}</a></li>
				<#else>
					<li class="paginate_button " aria-controls="dynamic-table" tabindex="0">
					<a href="javascript:void(0)" onclick="${func}(${indexPage});">${indexPage}</a></li>
				</#if>
				</#list>
				<li class="paginate_button next" aria-controls="dynamic-table" tabindex="0" id="dynamic-table_next">
					<a onclick="goto_NextPager('${pagerTool.pagerCount}')" href="javascript:void(0);">尾页→${pagerTool.pagerCount!}</a></li>
			</ul>
		</div>
	</div>
</div>

<script>
	var _glob_temp_nowpager = '${pagerTool.nowPage!}';
	function goto_BeforePager(pager){
		if(_glob_temp_nowpager == '1'){
			alert("当前已是第一页!");
		}else{
			${func}(pager);
		}  
	}
	function goto_NextPager(pager){
		if(_glob_temp_nowpager == '${pagerTool.pagerCount!}'){
			alert("当前已是最后一页!");
		}else{
			${func}(pager);
		} 
	}
	function goto_Pager(){
	 	if(document.getElementById("input__Pager").value == ''){
	 		alert('请输入要进入的页数');
			return ;
		}
	 	var strP=/^\d+$/; 
	 	if(!strP.test(document.getElementById("input__Pager").value)){
	 		alert('页数应该为正整数');
	 		return ;
	 	} 
	 	if( document.getElementById("input__Pager").value > ${pagerTool.pagerCount}){
	 		document.getElementById("input__Pager").value = ${pagerTool.pagerCount};
	 	}
	 	${func}(document.getElementById("input__Pager").value);
	}
</script>
</#if> 
<!-- 分页信息  end-->
</#macro>