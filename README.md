# easyweb
轻量级web框架，集成easyorm,freemarker
使用HttpDispatcher作为http任务调度。

#配置说明
主配置文件：src/main/resources/cfg.properties
```xml
#页面连接时长限制
run_time_limit=10000
#控制层类目录
control_path=cn.chuanz.control
#视图层类目录
view_path=cn.chuanz.view
```

#使用方法
1，编写控制类，如Index,使用freemarker传递参数
```Java
  private HtmlBuilder htmlBuilder = new FreemarkerBuilder();

	@IjHttpServletRequest
	private HttpServletRequest httpRequest;
	
	public String func() {
		htmlBuilder.setValue("name", "chuan.zhang");
		htmlBuilder.setTemplatePath("index.ftl");
		return htmlBuilder.toString();
	}
```
2，编写试图类，freemarker表达式使用：http://www.kerneler.com/freemarker2.3.23/dgui_template_exp.html
```Html
<html>
<head>
</head>
<body>
<div><h1>This is first page</h1></div>
<p>my name is ${name}</p>
</body>
</html>
```
