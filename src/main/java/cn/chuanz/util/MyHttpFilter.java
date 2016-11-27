package cn.chuanz.util;

import cn.chuanz.filter.HttpFilter;
import cn.chuanz.webframe.FreemarkerBuilder;

public class MyHttpFilter extends HttpFilter {

	@Override
	public int getRunTimeLimit() {
		// TODO Auto-generated method stub
		return 10000;
	}

	@Override
	public String projectEncode() {
		// TODO Auto-generated method stub
		return "UTF-8";
	}

	@Override
	public String controlFolder() {
		// TODO Auto-generated method stub
		return "cn.chuanz.control";
	}

	@Override
	protected String alias() {
		// TODO Auto-generated method stub
		return "/easyweb";
	}

	@Override
	protected void initHtmlBuilder() {
		FreemarkerBuilder.init("cn.chuanz.view", false, new String[]{"macro.ftl"});
	}

}
