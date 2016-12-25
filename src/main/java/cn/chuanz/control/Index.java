package cn.chuanz.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.chuanz.db.query.UpGroupMenuQuery;
import cn.chuanz.db.query.UpMenuQuery;
import cn.chuanz.db.query.UpUserQuery;
import cn.chuanz.db.rowdata.UpGroupMenuRowData;
import cn.chuanz.db.rowdata.UpMenuRowData;
import cn.chuanz.db.rowdata.UpUserRowData;
import cn.chuanz.orm.DbBasicService;
import cn.chuanz.orm.DbFactory;
import cn.chuanz.util.DbEwebConnect;
import cn.chuanz.webframe.FreemarkerBuilder;
import cn.chuanz.webframe.HtmlBuilder;
import cn.chuanz.webframe.annotation.IjDbService;
import cn.chuanz.webframe.annotation.IjHttpServletRequest;
import cn.chuanz.webframe.annotation.IjHttpServletResponse;

public class Index {

	private HtmlBuilder htmlBuilder = new FreemarkerBuilder();

	@IjDbService(DbEwebConnect.class)
	private DbBasicService dbService ;
	
	@IjHttpServletRequest
	private HttpServletRequest httpRequest;
	
	@IjHttpServletResponse
	private HttpServletResponse httpResponse;
	
	public String func() throws Exception {
		if (httpRequest.getSession().getAttribute("SESSION_LOGIN_USERID") == null) {
			httpResponse.sendRedirect("login.do");
		}
		
		UpUserQuery userQ = DbFactory.instance(dbService, UpUserQuery.class);
		userQ.ctnName(httpRequest.getSession().getAttribute("SESSION_LOGIN_USERID").toString());
		List<UpUserRowData> upUsers = userQ.queryRows();
		UpUserRowData upUser = null;
		List<UpGroupMenuRowData> datas = new ArrayList<UpGroupMenuRowData>();
		if (upUsers.size() > 0) {
			upUser = upUsers.get(0);
			UpGroupMenuQuery userGroup = DbFactory.instance(dbService, UpGroupMenuQuery.class);
			userGroup.ctnGroupId(String.valueOf(upUser.getGroupId()));
			datas = userGroup.queryRows();
		}
		
		List<String> idlist = new ArrayList<String>();
		String idStr = "";
		for (UpGroupMenuRowData data : datas) {
			idlist.add(data.getMenuId().toString());
			idStr += ","+data.getMenuId();
		}
		
		UpMenuQuery query = DbFactory.instance(dbService, UpMenuQuery.class);
		List<Map<String,String>> rootList = new ArrayList<Map<String,String>>();
		List<String> rootIds = new ArrayList<String>();
		List<Map<String,String>> subList = new ArrayList<Map<String,String>>();
		Map<String,String> menuMap = null;
		List<UpMenuRowData> menuDatas = query.queryRows();
		for(UpMenuRowData row : menuDatas) {
			if (idStr.contains(String.valueOf(row.getId()))) {
				menuMap = new HashMap<String,String>();
				menuMap.put("id", String.valueOf(row.getId()));
				menuMap.put("pid", String.valueOf(row.getPid()));
				menuMap.put("name", row.getName());
				menuMap.put("url", row.getUrl());
				menuMap.put("icon", row.getIcon());
				if (!rootIds.contains(String.valueOf(row.getPid()))) {
					rootIds.add(String.valueOf(row.getPid()));
				}
				subList.add(menuMap);
			}
		}
		
		for(UpMenuRowData row : menuDatas) {
			if (rootIds.contains(String.valueOf(row.getId()))) {
				menuMap = new HashMap<String,String>();
				menuMap.put("id", String.valueOf(row.getId()));
				menuMap.put("pid", String.valueOf(row.getPid()));
				menuMap.put("name", row.getName());
				menuMap.put("url", row.getUrl());
				menuMap.put("icon", row.getIcon());
				rootList.add(menuMap);
			}
		}
		
		htmlBuilder.setValue("rootList",rootList); 
		htmlBuilder.setValue("subList",subList); 
		htmlBuilder.setTemplatePath("index.ftl"); 
		return htmlBuilder.toString();
	}
	
	public String demo() {
		htmlBuilder.setTemplatePath("demo.ftl");
		return htmlBuilder.toString();
	}
	
}
