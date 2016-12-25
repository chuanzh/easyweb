package cn.chuanz.util;

import cn.chuanz.filter.HttpFilter;
import cn.chuanz.webframe.FreemarkerBuilder;

public class HttpDispatcher extends HttpFilter {

	@Override
	public int getRunTimeLimit() {
		// TODO Auto-generated method stub
		return ConfigRead.readIntValue("run_time_limit");
	}

	@Override
	public String projectEncode() {
		// TODO Auto-generated method stub
		return "UTF-8";
	}

	@Override
	public String controlFolder() {
		// TODO Auto-generated method stub
		return ConfigRead.readValue("control_path");
	}

	@Override
	protected String alias() {
		// TODO Auto-generated method stub
		return "/easyweb";
	}

	@Override
	protected void initHtmlBuilder() {
		FreemarkerBuilder.init(ConfigRead.readValue("view_path"), false, new String[]{"common/macro.ftl"});
	}

}
