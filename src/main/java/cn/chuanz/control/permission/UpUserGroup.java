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
import cn.chuanz.db.query.UpUserGroupQuery;
import cn.chuanz.db.rowdata.UpGroupMenuRowData;
import cn.chuanz.db.rowdata.UpMenuRowData;
import cn.chuanz.db.rowdata.UpUserGroupRowData;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * eweb平台用户组管理
 * @author chuan.zhang
 *
 */
public class UpUserGroup {

	private static Logger logger = Logger.getLogger(UpUserGroup.class);
	@IjDbService(DbEwebConnect.class)
	private DbBasicService dbService ; 
	private HtmlBuilder hBuilder = new FreemarkerBuilder();
	
	@IjParam
	private Integer page = 1;
	@IjParam
	private Integer pageSize = 10;

	@IjHttpServletRequest
	private HttpServletRequest request;
	
	public String func() throws Exception{
		UpUserGroupQuery query = DbFactory.instance(dbService, UpUserGroupQuery.class);
		PagerTool pt = PagerTool.init(page, pageSize, (int) query.queryCount());
		query.setPagerDto(pt);
		List<Map<String,String>> userGroupList = new ArrayList<Map<String,String>>();
		Map<String,String> userMap = null;
		for (UpUserGroupRowData upUser : query.queryRows()) {
			userMap = new HashMap<String,String>();
			userMap.put("id", FuncStatic.toStr(upUser.getId()));
			userMap.put("groupName", FuncStatic.toStr(upUser.getName()));
			userGroupList.add(userMap);
		}
		hBuilder.setValue("userGroupList", userGroupList);
		hBuilder.setValue("pagerToolObj", pt);
		
		/*菜单列表*/
		List<Map<String,String>> rootList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> subList = new ArrayList<Map<String,String>>();
		Map<String,String> rootMap = new HashMap<String, String>();
		Map<String,String> subMap = new HashMap<String, String>();
		UpMenuQuery menuQ = DbFactory.instance(dbService, UpMenuQuery.class);
		for (UpMenuRowData row : menuQ.queryRows()) {
			if (row.getPid() == 0) {
				rootMap = new HashMap<String, String>();
				rootMap.put("id", String.valueOf(row.getId()));
				rootMap.put("name", row.getName());
				rootList.add(rootMap);
			} else {
				subMap = new HashMap<String, String>();
				subMap.put("id", String.valueOf(row.getId()));
				subMap.put("name", row.getName());
				subMap.put("pid", String.valueOf(row.getPid()));
				subList.add(subMap);
			}
		}
		hBuilder.setValue("rootList",rootList);
		hBuilder.setValue("subList",subList);
		
		hBuilder.setTemplatePath("permission/upUserGroup.ftl");
		return hBuilder.toString();
	}
	
	
	public String doQuery() throws Exception
	{		
		page = Integer.parseInt(request.getParameter("nowPage")); 
		UpUserGroupQuery query = DbFactory.instance(dbService, UpUserGroupQuery.class);
		if (!FuncStatic.checkIsEmpty(request.getParameter("groupName"))) {
			query.ctnName("%"+request.getParameter("groupName")+"%",ConditionOperator.LIKE);
		}
		PagerTool pt = PagerTool.init(page, pageSize, (int) query.queryCount());
		query.setPagerDto(pt);
		List<Map<String,String>> userGroupList = new ArrayList<Map<String,String>>();
		Map<String,String> userMap = null;
		for (UpUserGroupRowData upUser : query.queryRows()) {
			userMap = new HashMap<String,String>();
			userMap.put("id", FuncStatic.toStr(upUser.getId()));
			userMap.put("groupName", FuncStatic.toStr(upUser.getName()));
			userGroupList.add(userMap);
		}
		hBuilder.setValue("userGroupList", userGroupList);
		hBuilder.setValue("pagerToolObj", pt);
		
		/*菜单列表*/
		List<Map<String,String>> rootList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> subList = new ArrayList<Map<String,String>>();
		Map<String,String> rootMap = new HashMap<String, String>();
		Map<String,String> subMap = new HashMap<String, String>();
		UpMenuQuery menuQ = DbFactory.instance(dbService, UpMenuQuery.class);
		for (UpMenuRowData row : menuQ.queryRows()) {
			if (row.getPid() == 0) {
				rootMap = new HashMap<String, String>();
				rootMap.put("id", String.valueOf(row.getId()));
				rootMap.put("name", row.getName());
				rootList.add(rootMap);
			} else {
				subMap = new HashMap<String, String>();
				subMap.put("id", String.valueOf(row.getId()));
				subMap.put("name", row.getName());
				subMap.put("pid", String.valueOf(row.getPid()));
				subList.add(subMap);
			}
		}
		hBuilder.setValue("rootList",rootList);
		hBuilder.setValue("subList",subList);
		
		hBuilder.setTemplatePath("permission/upUserGroup.ftl");
		hBuilder.setNode("dataHtml");
		return FuncStatic.createJson("content", hBuilder.toString())
				.toString();
		
	}
	
	public String findById() throws Exception {
		String id = request.getParameter("id");
		UpUserGroupRowData upUser = DbFactory.find(dbService, UpUserGroupRowData.class, id);
		JSONObject jo = new JSONObject();
		jo.put("id", upUser.getId());
		jo.put("groupName", upUser.getName());
		return jo.toString();
	}
	
	public String save() throws Exception {
		JSONObject jo = new JSONObject();
		try {
			String id = request.getParameter("id");
			UpUserGroupRowData upUser = DbFactory.instance(dbService, UpUserGroupRowData.class);
			upUser.setName(request.getParameter("groupName"));
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
	
	public String del() throws Exception {
		JSONObject jo = new JSONObject();
		try {
			String ids = request.getParameter("ids");
			for (String id : ids.split(",")) {
				UpUserGroupRowData upUser = DbFactory.instance(dbService, UpUserGroupRowData.class);
				upUser.setId(Integer.parseInt(id));
				upUser.delete();
				
				UpGroupMenuQuery permissionQ = DbFactory.instance(dbService, UpGroupMenuQuery.class);
				permissionQ.ctnGroupId(id);
				for (UpGroupMenuRowData row : permissionQ.queryRows()) {
					row.delete(); //删除此用户组的所有权限
				}
				
			}
			jo.put("result", "1");
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			jo.put("result", "0");
		}
		return jo.toString();
	}
	
	/**
	 * 编辑用户组权限
	 * @return
	 * @throws Exception
	 */
	public String editPermission() throws Exception{
		String groupId = request.getParameter("groupId");
		
		UpGroupMenuQuery groupPerQ = DbFactory.instance(dbService, UpGroupMenuQuery.class);
		groupPerQ.ctnGroupId(groupId);
		List<String> permissionList = new ArrayList<String>();
		for (UpGroupMenuRowData row : groupPerQ.queryRows()) {
			permissionList.add(row.getMenuId().toString());
		}
		
		UpUserGroupRowData group = DbFactory.find(dbService, UpUserGroupRowData.class, groupId);
		
		JSONObject jo = new JSONObject();
		jo.put("groupId", group.getId());
		jo.put("groupName", group.getName());
		jo.put("permissionList", JSONArray.fromObject(permissionList).toString());
		return jo.toString();
	}
	
	/**
	 * 保存用户组权限
	 * @return
	 * @throws Exception
	 */
	public String savePermission() throws Exception {
		JSONObject jo = new JSONObject();
		try {
			String permissions = request.getParameter("permission");
			int groupId = Integer.parseInt(request.getParameter("groupId"));
			String[] permissionArr = permissions.split(",");
			Date date = new Date();
			UpGroupMenuQuery query = DbFactory.instance(dbService, UpGroupMenuQuery.class);
			query.ctnGroupId(String.valueOf(groupId));
			for (UpGroupMenuRowData row : query.queryRows()) {
				row.delete();
			}
			for (String permission : permissionArr) {
				UpGroupMenuRowData groupPermission = DbFactory.instance(dbService, UpGroupMenuRowData.class);
				groupPermission.setGroupId(groupId);
				groupPermission.setMenuId(Integer.parseInt(permission));
				groupPermission.setInsertTime(date);
				groupPermission.insert();
			}
			jo.put("result", "1");
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			jo.put("result", "0");
		}
		return jo.toString();
	}
	
}
