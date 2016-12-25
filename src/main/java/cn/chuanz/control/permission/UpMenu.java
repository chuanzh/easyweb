package cn.chuanz.control.permission;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import cn.chuanz.db.query.UpGroupMenuQuery;
import cn.chuanz.db.query.UpMenuQuery;
import cn.chuanz.db.rowdata.UpGroupMenuRowData;
import cn.chuanz.db.rowdata.UpMenuRowData;
import cn.chuanz.orm.ConditionOperator;
import cn.chuanz.orm.DbBasicService;
import cn.chuanz.orm.DbFactory;
import cn.chuanz.util.DbEwebConnect;
import cn.chuanz.util.FuncStatic;
import cn.chuanz.util.PagerTool;
import cn.chuanz.webframe.FreemarkerBuilder;
import cn.chuanz.webframe.HtmlBuilder;
import cn.chuanz.webframe.annotation.IjDbService;
import cn.chuanz.webframe.annotation.IjHttpServletRequest;
import cn.chuanz.webframe.annotation.IjParam;
import net.sf.json.JSONObject;

public class UpMenu {

	private static Logger logger = Logger.getLogger(UpMenu.class);
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
		UpMenuQuery query = DbFactory.instance(dbService, UpMenuQuery.class);
		//query.ctnPid("0", ConditionOperator.GT);
		PagerTool pt = PagerTool.init(page, pageSize, (int) query.queryCount());
		query.setPagerDto(pt);
		List<Map<String, String>> menuList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> rootMenuList = new ArrayList<Map<String, String>>();
		Map<String, String> menuMap = null;
		for (UpMenuRowData row : query.queryRows()) {
			menuMap = new HashMap<String, String>();
			menuMap.put("id", String.valueOf(row.getId()));
			menuMap.put("pid", String.valueOf(row.getPid()));
			menuMap.put("pname", "");
			if (row.getPid() != 0) {
				UpMenuRowData menuRow = DbFactory.find(dbService,
						UpMenuRowData.class, row.getPid());
				menuMap.put("pname", menuRow.getName());
			}
			menuMap.put("name", row.getName());
			menuMap.put("url", row.getUrl());
			menuMap.put("icon", row.getIcon());
			menuList.add(menuMap);
		}

		query = DbFactory.instance(dbService, UpMenuQuery.class);
		query.ctnPid("0");
		for (UpMenuRowData row : query.queryRows()) {
			menuMap = new HashMap<String, String>();
			menuMap.put("id", String.valueOf(row.getId()));
			menuMap.put("name", row.getName());
			menuMap.put("icon", row.getIcon());
			rootMenuList.add(menuMap);
		}

		hBuilder.setValue("menuList", menuList);
		hBuilder.setValue("rootMenuList", rootMenuList);
		hBuilder.setValue("pagerToolObj", pt);
		hBuilder.setTemplatePath("permission/upMenu.ftl");

		return hBuilder.toString();
	}

	public String doQuery() throws Exception {
		page = Integer.parseInt(request.getParameter("nowPage"));
		UpMenuQuery query = DbFactory.instance(dbService, UpMenuQuery.class);
		//query.ctnPid("0", ConditionOperator.GT);
		if (!FuncStatic.checkIsEmpty(request.getParameter("menuName"))) {
			query.ctnName("%" + request.getParameter("menuName") + "%",
					ConditionOperator.LIKE);
		}
		if (!FuncStatic.checkIsEmpty(request.getParameter("pid"))) {
			query.ctnPid(request.getParameter("pid"));
		}
		PagerTool pt = PagerTool.init(page, pageSize, (int) query.queryCount());
		query.setPagerDto(pt);
		List<Map<String, String>> menuList = new ArrayList<Map<String, String>>();
		List<Map<String, String>> rootMenuList = new ArrayList<Map<String, String>>();
		Map<String, String> menuMap = null;
		for (UpMenuRowData row : query.queryRows()) {
			menuMap = new HashMap<String, String>();
			menuMap.put("id", String.valueOf(row.getId()));
			menuMap.put("pid", String.valueOf(row.getPid()));
			menuMap.put("pname", "");
			if (row.getPid() != 0) {
				UpMenuRowData menuRow = DbFactory.find(dbService,
						UpMenuRowData.class, row.getPid());
				menuMap.put("pname", menuRow.getName());
			}
			menuMap.put("name", row.getName());
			menuMap.put("url", row.getUrl());
			menuMap.put("icon", row.getIcon());
			menuList.add(menuMap);
		}

		query = DbFactory.instance(dbService, UpMenuQuery.class);
		query.ctnPid("0");
		for (UpMenuRowData row : query.queryRows()) {
			menuMap = new HashMap<String, String>();
			menuMap.put("id", String.valueOf(row.getId()));
			menuMap.put("name", row.getName());
			menuMap.put("icon", row.getIcon());
			rootMenuList.add(menuMap);
		}

		hBuilder.setValue("menuList", menuList);
		hBuilder.setValue("rootMenuList", rootMenuList);
		hBuilder.setValue("pagerToolObj", pt);
		hBuilder.setTemplatePath("permission/upMenu.ftl");

		hBuilder.setNode("dataHtml");
		return FuncStatic.createJson("content", hBuilder.toString()).toString();
	}

	public String save() {
		JSONObject jo = new JSONObject();
		try {
			String id = request.getParameter("id");
			UpMenuRowData upMenu = DbFactory.instance(dbService,
					UpMenuRowData.class);
			upMenu.setPid(Integer.parseInt(request.getParameter("pid")));
			upMenu.setName(request.getParameter("menuName"));
			upMenu.setUrl(request.getParameter("menuUrl"));
			upMenu.setIcon(request.getParameter("icon"));
			Date date = new Date();
			upMenu.setUpdateTime(date);
			if (!FuncStatic.checkIsEmpty(id)) {
				upMenu.setId(Integer.parseInt(id));
				upMenu.update();
			} else {
				upMenu.setInsertTime(date);
				upMenu.insert();
			}
			jo.put("result", "1");
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			jo.put("result", "0");
		}
		return jo.toString();
	}

	public String findById() throws Exception {
		JSONObject jo = new JSONObject();
		String id = request.getParameter("id");
		UpMenuRowData upMenu = DbFactory.find(dbService, UpMenuRowData.class,
				id);
		jo.put("id", upMenu.getId());
		jo.put("pid", upMenu.getPid());
		jo.put("name", upMenu.getName());
		jo.put("url", upMenu.getUrl());
		jo.put("icon", upMenu.getIcon());
		return jo.toString();
	}

	public String del() {
		JSONObject jo = new JSONObject();
		try {
			String ids = request.getParameter("ids");
			for (String id : ids.split(",")) {
				UpMenuRowData upMenu = DbFactory.instance(dbService,
						UpMenuRowData.class);
				upMenu.setId(Integer.parseInt(id));
				upMenu.delete();

				// 删除菜单用户组对应菜单权限
				UpGroupMenuQuery query = DbFactory.instance(dbService,
						UpGroupMenuQuery.class);
				query.ctnMenuId(id);
				for (UpGroupMenuRowData row : query.queryRows()) {
					row.delete();
				}

				// 若是父节点，则删除其下的所有子节点 和子节点权限
				UpMenuQuery menuQ = DbFactory.instance(dbService,
						UpMenuQuery.class);
				menuQ.ctnPid(id);
				for (UpMenuRowData row : menuQ.queryRows()) {
					row.delete();

					query = DbFactory.instance(dbService,
							UpGroupMenuQuery.class);
					query.ctnMenuId(String.valueOf(row.getId()));
					for (UpGroupMenuRowData row1 : query.queryRows()) {
						row1.delete();
					}
				}

			}

			jo.put("result", "1");
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			jo.put("result", "0");
		}
		return jo.toString();
	}

	
}
