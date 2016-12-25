package cn.chuanz.db.query;

import java.util.List;

import cn.chuanz.orm.ConditionOperator;
import cn.chuanz.orm.MapTable;
import cn.chuanz.orm.BaseRowsSet;
import cn.chuanz.db.rowdata.UpGroupMenuRowData;
import cn.chuanz.db.maptable.UP_GROUP_MENU;
 
public class UpGroupMenuQuery extends BaseRowsSet{

	/**
	 * 
	 */
	public void ctnId(String value) {
		conditionTool.addCondition(UP_GROUP_MENU.f_id,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnId(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_GROUP_MENU.f_id,value,operator);
	}
	/**
	 * 组ID
	 */
	public void ctnGroupId(String value) {
		conditionTool.addCondition(UP_GROUP_MENU.f_group_id,value,ConditionOperator.EQ);
	}
	/**
	 * 组ID
	 */
	public void ctnGroupId(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_GROUP_MENU.f_group_id,value,operator);
	}
	/**
	 * 菜单ID
	 */
	public void ctnMenuId(String value) {
		conditionTool.addCondition(UP_GROUP_MENU.f_menu_id,value,ConditionOperator.EQ);
	}
	/**
	 * 菜单ID
	 */
	public void ctnMenuId(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_GROUP_MENU.f_menu_id,value,operator);
	}
	/**
	 * 
	 */
	public void ctnInsertTime(String value) {
		conditionTool.addCondition(UP_GROUP_MENU.f_insert_time,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnInsertTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_GROUP_MENU.f_insert_time,value,operator);
	}
	 
	public List<UpGroupMenuRowData> queryRows() throws Exception{
		return this.queryRows(UpGroupMenuRowData.class);
	} 
	
	@Override
	protected MapTable getMapTable() {
 		return UP_GROUP_MENU.instance();
	}
	 
}
