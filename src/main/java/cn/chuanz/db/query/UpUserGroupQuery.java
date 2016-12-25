package cn.chuanz.db.query;

import java.util.List;

import cn.chuanz.orm.ConditionOperator;
import cn.chuanz.orm.MapTable;
import cn.chuanz.orm.BaseRowsSet;
import cn.chuanz.db.rowdata.UpUserGroupRowData;
import cn.chuanz.db.maptable.UP_USER_GROUP;
 
public class UpUserGroupQuery extends BaseRowsSet{

	/**
	 * 
	 */
	public void ctnId(String value) {
		conditionTool.addCondition(UP_USER_GROUP.f_id,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnId(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER_GROUP.f_id,value,operator);
	}
	/**
	 * 组名
	 */
	public void ctnName(String value) {
		conditionTool.addCondition(UP_USER_GROUP.f_name,value,ConditionOperator.EQ);
	}
	/**
	 * 组名
	 */
	public void ctnName(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER_GROUP.f_name,value,operator);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value) {
		conditionTool.addCondition(UP_USER_GROUP.f_update_time,value,ConditionOperator.EQ);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER_GROUP.f_update_time,value,operator);
	}
	/**
	 * 
	 */
	public void ctnInsertTime(String value) {
		conditionTool.addCondition(UP_USER_GROUP.f_insert_time,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnInsertTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER_GROUP.f_insert_time,value,operator);
	}
	 
	public List<UpUserGroupRowData> queryRows() throws Exception{
		return this.queryRows(UpUserGroupRowData.class);
	} 
	
	@Override
	protected MapTable getMapTable() {
 		return UP_USER_GROUP.instance();
	}
	 
}
