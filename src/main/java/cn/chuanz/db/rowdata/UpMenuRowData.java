package cn.chuanz.db.rowdata;

import java.util.Date;

import org.apache.log4j.Logger;

import cn.chuanz.util.FuncDate;
import cn.chuanz.util.FuncStatic;
import cn.chuanz.orm.ConditionTool;
import cn.chuanz.orm.BaseOneRow; 
import cn.chuanz.orm.MapTable;
import cn.chuanz.db.maptable.UP_MENU;

public class UpMenuRowData extends BaseOneRow{
	private static Logger logger = Logger.getLogger ( UpMenuRowData.class ) ;
	
	/**
	 * 
	 */
	public Integer getId() {
		try {
			return rowData.getValueInt(UP_MENU.f_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 
	 */
	public void setId(Integer value) {
		rowData.setValue(UP_MENU.f_id, value );
	}
	
	/**
	 * 父ID
	 */
	public Integer getPid() {
		try {
			return rowData.getValueInt(UP_MENU.f_pid);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 父ID
	 */
	public void setPid(Integer value) {
		rowData.setValue(UP_MENU.f_pid, value );
	}
	
	/**
	 * 名称
	 */
	public String getName() {
		return rowData.getValueString(UP_MENU.f_name);
	}
	/**
	 * 名称
	 */
	public void setName(String value) {
		rowData.setValue(UP_MENU.f_name, value );
	}
	
	/**
	 * 
	 */
	public String getUrl() {
		return rowData.getValueString(UP_MENU.f_url);
	}
	/**
	 * 
	 */
	public void setUrl(String value) {
		rowData.setValue(UP_MENU.f_url, value );
	}
	
	/**
	 * 
	 */
	public String getIcon() {
		return rowData.getValueString(UP_MENU.f_icon);
	}
	/**
	 * 
	 */
	public void setIcon(String value) {
		rowData.setValue(UP_MENU.f_icon, value );
	}
	
	/**
	 * 
	 */
	public Date getUpdateTime() {
		return rowData.getValueDate(UP_MENU.f_update_time);
	}
	/**
	 * 
	 */
	public void setUpdateTime(Date value) {
		rowData.setValue(UP_MENU.f_update_time, value );
	}
	
	/**
	 * 
	 */
	public Date getInsertTime() {
		return rowData.getValueDate(UP_MENU.f_insert_time);
	}
	/**
	 * 
	 */
	public void setInsertTime(Date value) {
		rowData.setValue(UP_MENU.f_insert_time, value );
	}
	
  
	@Override
	protected MapTable getMapTable() {
 		return UP_MENU.instance();
	}
 }
