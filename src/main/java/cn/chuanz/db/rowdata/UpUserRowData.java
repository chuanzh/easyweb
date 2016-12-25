package cn.chuanz.db.rowdata;

import java.util.Date;

import org.apache.log4j.Logger;

import cn.chuanz.util.FuncDate;
import cn.chuanz.util.FuncStatic;
import cn.chuanz.orm.ConditionTool;
import cn.chuanz.orm.BaseOneRow; 
import cn.chuanz.orm.MapTable;
import cn.chuanz.db.maptable.UP_USER;

public class UpUserRowData extends BaseOneRow{
	private static Logger logger = Logger.getLogger ( UpUserRowData.class ) ;
	
	/**
	 * 
	 */
	public Integer getId() {
		try {
			return rowData.getValueInt(UP_USER.f_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 
	 */
	public void setId(Integer value) {
		rowData.setValue(UP_USER.f_id, value );
	}
	
	/**
	 * 用户名
	 */
	public String getName() {
		return rowData.getValueString(UP_USER.f_name);
	}
	/**
	 * 用户名
	 */
	public void setName(String value) {
		rowData.setValue(UP_USER.f_name, value );
	}
	
	/**
	 * 密码
	 */
	public String getPwd() {
		return rowData.getValueString(UP_USER.f_pwd);
	}
	/**
	 * 密码
	 */
	public void setPwd(String value) {
		rowData.setValue(UP_USER.f_pwd, value );
	}
	
	/**
	 * 用户组
	 */
	public Integer getGroupId() {
		try {
			return rowData.getValueInt(UP_USER.f_group_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 用户组
	 */
	public void setGroupId(Integer value) {
		rowData.setValue(UP_USER.f_group_id, value );
	}
	
	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return rowData.getValueDate(UP_USER.f_update_time);
	}
	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date value) {
		rowData.setValue(UP_USER.f_update_time, value );
	}
	
	/**
	 * 
	 */
	public Date getInsertTime() {
		return rowData.getValueDate(UP_USER.f_insert_time);
	}
	/**
	 * 
	 */
	public void setInsertTime(Date value) {
		rowData.setValue(UP_USER.f_insert_time, value );
	}
	
	/**
	 * 
	 */
	public Date getLastLoginTime() {
		return rowData.getValueDate(UP_USER.f_last_login_time);
	}
	/**
	 * 
	 */
	public void setLastLoginTime(Date value) {
		rowData.setValue(UP_USER.f_last_login_time, value );
	}
	
  
	@Override
	protected MapTable getMapTable() {
 		return UP_USER.instance();
	}
 }
