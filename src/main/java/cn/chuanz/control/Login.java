package cn.chuanz.control;

import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.chuanz.db.query.UpUserQuery;
import cn.chuanz.db.rowdata.UpUserRowData;
import cn.chuanz.orm.DbBasicService;
import cn.chuanz.orm.DbFactory;
import cn.chuanz.util.DbEwebConnect;
import cn.chuanz.util.FuncStatic;
import cn.chuanz.webframe.FreemarkerBuilder;
import cn.chuanz.webframe.HtmlBuilder;
import cn.chuanz.webframe.annotation.IjDbService;
import cn.chuanz.webframe.annotation.IjHttpServletRequest;
import cn.chuanz.webframe.annotation.IjHttpServletResponse;
import net.sf.json.JSONObject;

public class Login {
	
	private HtmlBuilder htmlBuilder = new FreemarkerBuilder();
	
	@IjDbService(DbEwebConnect.class)
	private DbBasicService dbService ;
	
	@IjHttpServletRequest
	private HttpServletRequest httpRequest;
	
	@IjHttpServletResponse
	private HttpServletResponse httpResponse;

	public String func () {
		htmlBuilder.setTemplatePath("login.ftl");
		return htmlBuilder.toString();
	}
	
	public String doLogin () throws Exception {
		JSONObject jo = new JSONObject();
		String name = httpRequest.getParameter("name");
		String password = httpRequest.getParameter("password");
		UpUserQuery upUserQ = DbFactory.instance(dbService, UpUserQuery.class);
		if(!FuncStatic.checkIsEmpty(name) && !FuncStatic.checkIsEmpty(password)){
			upUserQ.ctnName(name);
			upUserQ.ctnPwd(password);
			if(upUserQ.queryCount() > 0){
				UpUserRowData UpUser = upUserQ.queryRows().get(0);
				UpUser.setLastLoginTime(new Date());
				UpUser.update();
				httpRequest.getSession().setAttribute("SESSION_LOGIN_USERID", name);
				httpRequest.getSession().setAttribute("SESSION_LOGIN_USERPWD", password);
				jo.put("flag", "1");
				jo.put("name", URLEncoder.encode(name, "UTF-8"));
				return jo.toString();
			}
		}
		
		jo.put("flag", "0");
		jo.put("error", "登录名或密码错误");
		return jo.toString();
	}
	
	public void loginOut () throws Exception {
		httpRequest.getSession().setAttribute("SESSION_LOGIN_USERID", null);
		httpRequest.getSession().setAttribute("SESSION_LOGIN_USERPWD", null);
		httpResponse.sendRedirect("login.do");
	}
	
}
