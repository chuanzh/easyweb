
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>登陆页面-EWEB</title>
		<#include "common/basecssjs.ftl"/>
		
	</head>

	<body class="login-layout light-login">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-coffee green"></i>
									<span class="red">EWEB</span>
									<span class="grey" id="id-text2">管理平台</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; HNBC</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">

											<div class="space-6"></div>

											<form id="form1">
												<fieldset>
													<label class="block clearfix" id="nameDiv">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" name="name" id="name" placeholder="Username" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix" id="pwdDiv">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" name="password" id="password" placeholder="Password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input id="rememberMe" type="checkbox" class="ace" />
															<span class="lbl"> 记住我</span>
														</label>

														<button type="button" onclick="doLogin()" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">登陆</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

										</div><!-- /.widget-main -->

									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->

							</div><!-- /.position-relative -->

						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${host}/assets/js/jquery-2.1.4.min.js"></script>
		
		<script src="${host}/assets/js/ajax.js"></script>
    	<script src="${host}/assets/js/checkform.js"></script>
    	<script src="${host}/assets/js/jquery.cookie.js"></script>
    	<script type="text/javascript" src="${host}/assets/js/jquery.form.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${host}/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		
		
			$(document).ready(function() {  
		   	  	if ($.cookie("rememberMe") == "true") {  
			   	   	$("#rememberMe").attr("checked", true);  
			   	   	$("#name").val($.cookie("name"));  
			   	   	$("#password").val($.cookie("password"));  
		   	  	}  
	   	 	});
	    
		    function doLogin () {
		    	var sendAjaxCallBack = function(jo) {
		    		if (jo.flag == 0) {
			    		alert(jo.error);
		    		} else {
		    			if ($("#rememberMe").parent().hasClass("checked")) {
		    		        var name = $("#name").val();
		    		        var password = $("#password").val();
		    		        $.cookie("rememberMe", "true", { expires: 7 }); 
		    		        $.cookie("name", name, { expires: 7 }); 
		    		        $.cookie("password", password, { expires: 7 }); 
		    		    } else {
		    		    	$.cookie("rememberMe", "false", { expires: -1 }); 
		    		        $.cookie("name", "", { expires: -1 }); 
		    		        $.cookie("password", "", { expires: -1 }); 
		    		    }
		    			window.location.href="index.do?name="+jo.name;
		    		}
		    	}
		    	if (!checkForm()) return; 
		    	sendAjax("doLogin", {"formId":"form1"}, sendAjaxCallBack);
		    }
		    
		    function checkForm () {
		    	 var name = $("#name").val();
			     var password = $("#password").val();
		    	if (name == null || trim(name) == "") {
		    		alert("用户名不可为空！");
		    		$("#nameDiv").addClass("focused");
		    		$("#pwdDiv").removeClass("focused");
		    		return false;
		    	}
		    	
		    	if (password == null || trim(password) == "") {
		    		alert("密码不可为空！");
		    		$("#pwdDiv").addClass("focused");
		    		$("#nameDiv").removeClass("focused");
		    		return false;
		    	}
		    	return true;
		    }
		</script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
			
		</script>
	</body>
</html>
