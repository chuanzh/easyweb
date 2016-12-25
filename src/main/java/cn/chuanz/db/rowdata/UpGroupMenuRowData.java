package cn.chuanz.db.rowdata;

import java.util.Date;

import org.apache.log4j.Logger;

import cn.chuanz.util.FuncDate;
import cn.chuanz.util.FuncStatic;
import cn.chuanz.orm.ConditionTool;
import cn.chuanz.orm.BaseOneRow; 
import cn.chuanz.orm.MapTable;
import cn.chuanz.db.maptable.UP_GROUP_MENU;

public class UpGroupMenuRowData extends BaseOneRow{
	private static Logger logger = Logger.getLogger ( UpGroupMenuRowData.class ) ;
	
	/**
	 * 
	 */
	public Integer getId() {
		try {
			return rowData.getValueInt(UP_GROUP_MENU.f_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 
	 */
	public void setId(Integer value) {
		rowData.setValue(UP_GROUP_MENU.f_id, value );
	}
	
	/**
	 * 组ID
	 */
	public Integer getGroupId() {
		try {
			return rowData.getValueInt(UP_GROUP_MENU.f_group_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 组ID
	 */
	public void setGroupId(Integer value) {
		rowData.setValue(UP_GROUP_MENU.f_group_id, value );
	}
	
	/**
	 * 菜单ID
	 */
	public Integer getMenuId() {
		try {
			return rowData.getValueInt(UP_GROUP_MENU.f_menu_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 菜单ID
	 */
	public void setMenuId(Integer value) {
		rowData.setValue(UP_GROUP_MENU.f_menu_id, value );
	}
	
	/**
	 * 
	 */
	public Date getInsertTime() {
		return rowData.getValueDate(UP_GROUP_MENU.f_insert_time);
	}
	/**
	 * 
	 */
	public void setInsertTime(Date value) {
		rowData.setValue(UP_GROUP_MENU.f_insert_time, value );
	}
	
  
	@Override
	protected MapTable getMapTable() {
 		return UP_GROUP_MENU.instance();
	}
 }
