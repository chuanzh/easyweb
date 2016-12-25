package cn.chuanz.db.query;

import java.util.List;

import cn.chuanz.orm.ConditionOperator;
import cn.chuanz.orm.MapTable;
import cn.chuanz.orm.BaseRowsSet;
import cn.chuanz.db.rowdata.UpMenuRowData;
import cn.chuanz.db.maptable.UP_MENU;
 
public class UpMenuQuery extends BaseRowsSet{

	/**
	 * 
	 */
	public void ctnId(String value) {
		conditionTool.addCondition(UP_MENU.f_id,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnId(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_MENU.f_id,value,operator);
	}
	/**
	 * 父ID
	 */
	public void ctnPid(String value) {
		conditionTool.addCondition(UP_MENU.f_pid,value,ConditionOperator.EQ);
	}
	/**
	 * 父ID
	 */
	public void ctnPid(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_MENU.f_pid,value,operator);
	}
	/**
	 * 名称
	 */
	public void ctnName(String value) {
		conditionTool.addCondition(UP_MENU.f_name,value,ConditionOperator.EQ);
	}
	/**
	 * 名称
	 */
	public void ctnName(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_MENU.f_name,value,operator);
	}
	/**
	 * 
	 */
	public void ctnUrl(String value) {
		conditionTool.addCondition(UP_MENU.f_url,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnUrl(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_MENU.f_url,value,operator);
	}
	/**
	 * 
	 */
	public void ctnIcon(String value) {
		conditionTool.addCondition(UP_MENU.f_icon,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnIcon(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_MENU.f_icon,value,operator);
	}
	/**
	 * 
	 */
	public void ctnUpdateTime(String value) {
		conditionTool.addCondition(UP_MENU.f_update_time,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnUpdateTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_MENU.f_update_time,value,operator);
	}
	/**
	 * 
	 */
	public void ctnInsertTime(String value) {
		conditionTool.addCondition(UP_MENU.f_insert_time,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnInsertTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_MENU.f_insert_time,value,operator);
	}
	 
	public List<UpMenuRowData> queryRows() throws Exception{
		return this.queryRows(UpMenuRowData.class);
	} 
	
	@Override
	protected MapTable getMapTable() {
 		return UP_MENU.instance();
	}
	 
}
