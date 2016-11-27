package cn.chuanz.control;

import javax.servlet.http.HttpServletRequest;

import cn.chuanz.webframe.FreemarkerBuilder;
import cn.chuanz.webframe.HtmlBuilder;
import cn.chuanz.webframe.annotation.IjHttpServletRequest;

public class Index {

	private HtmlBuilder htmlBuilder = new FreemarkerBuilder();

	@IjHttpServletRequest
	private HttpServletRequest httpRequest;
	
	public String func() {
		htmlBuilder.setValue("name", "chuan.zhang");
		htmlBuilder.setTemplatePath("index.ftl");
		return htmlBuilder.toString();
	}
	
}
