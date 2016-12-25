package cn.chuanz.control.permission;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.chuanz.db.query.UpUserGroupQuery;
import cn.chuanz.db.query.UpUserQuery;
import cn.chuanz.db.rowdata.UpUserGroupRowData;
import cn.chuanz.db.rowdata.UpUserRowData;
import cn.chuanz.orm.ConditionOperator;
import cn.chuanz.orm.DbBasicService;
import cn.chuanz.orm.DbFactory;
import cn.chuanz.util.DbEwebConnect;
import cn.chuanz.util.FuncDate;
import cn.chuanz.util.FuncStatic;
import cn.chuanz.util.PagerTool;
import cn.chuanz.webframe.FreemarkerBuilder;
import cn.chuanz.webframe.HtmlBuilder;
import cn.chuanz.webframe.annotation.IjDbService;
import cn.chuanz.webframe.annotation.IjHttpServletRequest;
import cn.chuanz.webframe.annotation.IjParam;
import net.sf.json.JSONObject;

public class UpUser {

	private static Logger logger = Logger.getLogger(UpUser.class);
	@IjDbService(DbEwebConnect.class)
	private DbBasicService dbService;
	private HtmlBuilder hBuilder = new FreemarkerBuilder();

	@IjParam
	private Integer page = 1;
	@IjParam
	private Integer pageSize = 10;

	@IjHttpServletRequest
	private HttpServletRequest request;

	public String func() throws Exception {
		List<Map<String, String>> userGroupList = new ArrayList<Map<String, String>>();
		UpUserQuery query = DbFactory.instance(dbService, UpUserQuery.class);
		PagerTool pt = PagerTool.init(page, pageSize, (int) query.queryCount());
		query.setPagerDto(pt);
		List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
		Map<String, String> userMap = null;
		for (UpUserRowData upUser : query.queryRows()) {
			userMap = new HashMap<String, String>();
			userMap.put("id", FuncStatic.toStr(upUser.getId()));
			userMap.put("userName", FuncStatic.toStr(upUser.getName()));
			userMap.put("userPwd", FuncStatic.toStr(upUser.getPwd()));
			userMap.put("groupId", FuncStatic.toStr(upUser.getGroupId()));
			UpUserGroupRowData group = DbFactory.find(dbService,
					UpUserGroupRowData.class, upUser.getGroupId());
			userMap.put("groupName", FuncStatic.toStr(group.getName()));
			String lastLoginTime = "";
			if (!FuncStatic.checkIsEmpty(upUser.getLastLoginTime())) {
				lastLoginTime = FuncDate
						.dateToString(upUser.getLastLoginTime());
			}
			userMap.put("lastLoginTime", lastLoginTime);
			userList.add(userMap);
		}

		UpUserGroupQuery userGroupQ = DbFactory.instance(dbService,
				UpUserGroupQuery.class);
		Map<String, String> userGroupMap = null;
		for (UpUserGroupRowData data : userGroupQ.queryRows()) {
			userGroupMap = new HashMap<String, String>();
			userGroupMap.put("id", FuncStatic.toStr(data.getId()));
			userGroupMap.put("name", FuncStatic.toStr(data.getName()));
			userGroupList.add(userGroupMap);
		}

		hBuilder.setValue("upUserList", userList);
		hBuilder.setValue("upUserGroupList", userGroupList);
		hBuilder.setValue("pagerToolObj", pt);
		hBuilder.setTemplatePath("permission/upUser.ftl");
		return hBuilder.toString();
	}

	public String doQuery() throws Exception {
		page = Integer.parseInt(request.getParameter("nowPage"));
		UpUserQuery query = DbFactory.instance(dbService, UpUserQuery.class);
		if (!FuncStatic.checkIsEmpty(request.getParameter("userName"))) {
			query.ctnName("%"+request.getParameter("userName")+"%",
					ConditionOperator.LIKE);
		}
		if (!FuncStatic.checkIsEmpty(request.getParameter("userGroup"))) {
			query.ctnGroupId(request.getParameter("userGroup"));
		}
		PagerTool pt = PagerTool.init(page, pageSize, (int) query.queryCount());
		query.setPagerDto(pt);
		List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
		Map<String, String> userMap = null;
		for (UpUserRowData upUser : query.queryRows()) {
			userMap = new HashMap<String, String>();
			userMap.put("id", FuncStatic.toStr(upUser.getId()));
			userMap.put("userName", FuncStatic.toStr(upUser.getName()));
			userMap.put("userPwd", FuncStatic.toStr(upUser.getPwd()));
			userMap.put("groupId", FuncStatic.toStr(upUser.getGroupId()));
			UpUserGroupRowData group = DbFactory.find(dbService,
					UpUserGroupRowData.class, upUser.getGroupId());
			userMap.put("groupName", FuncStatic.toStr(group.getName()));
			String lastLoginTime = "";
			if (!FuncStatic.checkIsEmpty(upUser.getLastLoginTime())) {
				lastLoginTime = FuncDate
						.dateToString(upUser.getLastLoginTime());
			}
			userMap.put("lastLoginTime", lastLoginTime);
			userList.add(userMap);
		}

		List<Map<String, String>> userGroupList = new ArrayList<Map<String, String>>();
		UpUserGroupQuery userGroupQ = DbFactory.instance(dbService,
				UpUserGroupQuery.class);
		Map<String, String> userGroupMap = null;
		for (UpUserGroupRowData data : userGroupQ.queryRows()) {
			userGroupMap = new HashMap<String, String>();
			userGroupMap.put("id", FuncStatic.toStr(data.getId()));
			userGroupMap.put("name", FuncStatic.toStr(data.getName()));
			userGroupList.add(userGroupMap);
		}

		hBuilder.setValue("upUserList", userList);
		hBuilder.setValue("upUserGroupList", userGroupList);
		hBuilder.setValue("pagerToolObj", pt);
		hBuilder.setTemplatePath("permission/upUser.ftl");

		hBuilder.setNode("dataHtml");
		return createJson("content", hBuilder.toString()).toString();

	}

	public String findById() throws Exception {
		String id = request.getParameter("id");
		UpUserRowData upUser = DbFactory.find(dbService, UpUserRowData.class,
				id);
		JSONObject jo = new JSONObject();
		jo.put("id", upUser.getId());
		jo.put("userName", upUser.getName());
		jo.put("userPwd", upUser.getPwd());
		jo.put("groupId", upUser.getGroupId());
		return jo.toString();
	}

	public String save() throws Exception {
		JSONObject jo = new JSONObject();
		try {
			String id = request.getParameter("id");
			UpUserRowData upUser = DbFactory.instance(dbService,
					UpUserRowData.class);
			upUser.setName(request.getParameter("userName"));
			upUser.setPwd(request.getParameter("userPwd"));
			upUser.setGroupId(Integer.parseInt(request
					.getParameter("userGroup")));
			Date date = new Date();
			upUser.setUpdateTime(date);
			if (!FuncStatic.checkIsEmpty(id)) {
				upUser.setId(Integer.parseInt(id));
				upUser.update();
			} else {
				upUser.setInsertTime(date);
				upUser.insert();
			}
			jo.put("result", "1");
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			jo.put("result", "0");
		}
		return jo.toString();
	}

	public String resetPwd() {
		JSONObject jo = new JSONObject();
		try {
			String ids = request.getParameter("ids");
			for (String id : ids.split(",")) {
				UpUserRowData upUser = DbFactory.instance(dbService,
						UpUserRowData.class);
				upUser.setId(Integer.parseInt(id));
				upUser.setPwd("123456");
				upUser.setUpdateTime(new Date());
				upUser.update();
			}
			jo.put("result", "1");
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			jo.put("result", "0");
		}
		return jo.toString();
	}

	public String del() throws Exception {
		JSONObject jo = new JSONObject();
		try {
			String ids = request.getParameter("ids");
			for (String id : ids.split(",")) {
				UpUserRowData upUser = DbFactory.instance(dbService,
						UpUserRowData.class);
				upUser.setId(Integer.parseInt(id));
				upUser.delete();
			}
			jo.put("result", "1");
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			jo.put("result", "0");
		}
		return jo.toString();
	}

	private JSONObject createJson(String... args) {
		JSONObject jo = new JSONObject();
		for (int i = 0; i < args.length; i = i + 2) {
			jo.put(args[i], args[i + 1]);
		}
		return jo;
	}

	
}
