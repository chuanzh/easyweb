package cn.chuanz.db.rowdata;

import java.util.Date;

import org.apache.log4j.Logger;

import cn.chuanz.util.FuncDate;
import cn.chuanz.util.FuncStatic;
import cn.chuanz.orm.ConditionTool;
import cn.chuanz.orm.BaseOneRow; 
import cn.chuanz.orm.MapTable;
import cn.chuanz.db.maptable.UP_USER_GROUP;

public class UpUserGroupRowData extends BaseOneRow{
	private static Logger logger = Logger.getLogger ( UpUserGroupRowData.class ) ;
	
	/**
	 * 
	 */
	public Integer getId() {
		try {
			return rowData.getValueInt(UP_USER_GROUP.f_id);
		} catch (Exception e) {
			logger.error(FuncStatic.errorTrace(e));
			return null;
		}
	}
	/**
	 * 
	 */
	public void setId(Integer value) {
		rowData.setValue(UP_USER_GROUP.f_id, value );
	}
	
	/**
	 * 组名
	 */
	public String getName() {
		return rowData.getValueString(UP_USER_GROUP.f_name);
	}
	/**
	 * 组名
	 */
	public void setName(String value) {
		rowData.setValue(UP_USER_GROUP.f_name, value );
	}
	
	/**
	 * 更新时间
	 */
	public Date getUpdateTime() {
		return rowData.getValueDate(UP_USER_GROUP.f_update_time);
	}
	/**
	 * 更新时间
	 */
	public void setUpdateTime(Date value) {
		rowData.setValue(UP_USER_GROUP.f_update_time, value );
	}
	
	/**
	 * 
	 */
	public Date getInsertTime() {
		return rowData.getValueDate(UP_USER_GROUP.f_insert_time);
	}
	/**
	 * 
	 */
	public void setInsertTime(Date value) {
		rowData.setValue(UP_USER_GROUP.f_insert_time, value );
	}
	
  
	@Override
	protected MapTable getMapTable() {
 		return UP_USER_GROUP.instance();
	}
 }
