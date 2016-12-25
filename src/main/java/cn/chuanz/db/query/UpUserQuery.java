package cn.chuanz.db.query;

import java.util.List;

import cn.chuanz.orm.ConditionOperator;
import cn.chuanz.orm.MapTable;
import cn.chuanz.orm.BaseRowsSet;
import cn.chuanz.db.rowdata.UpUserRowData;
import cn.chuanz.db.maptable.UP_USER;
 
public class UpUserQuery extends BaseRowsSet{

	/**
	 * 
	 */
	public void ctnId(String value) {
		conditionTool.addCondition(UP_USER.f_id,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnId(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER.f_id,value,operator);
	}
	/**
	 * 用户名
	 */
	public void ctnName(String value) {
		conditionTool.addCondition(UP_USER.f_name,value,ConditionOperator.EQ);
	}
	/**
	 * 用户名
	 */
	public void ctnName(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER.f_name,value,operator);
	}
	/**
	 * 密码
	 */
	public void ctnPwd(String value) {
		conditionTool.addCondition(UP_USER.f_pwd,value,ConditionOperator.EQ);
	}
	/**
	 * 密码
	 */
	public void ctnPwd(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER.f_pwd,value,operator);
	}
	/**
	 * 用户组
	 */
	public void ctnGroupId(String value) {
		conditionTool.addCondition(UP_USER.f_group_id,value,ConditionOperator.EQ);
	}
	/**
	 * 用户组
	 */
	public void ctnGroupId(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER.f_group_id,value,operator);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value) {
		conditionTool.addCondition(UP_USER.f_update_time,value,ConditionOperator.EQ);
	}
	/**
	 * 更新时间
	 */
	public void ctnUpdateTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER.f_update_time,value,operator);
	}
	/**
	 * 
	 */
	public void ctnInsertTime(String value) {
		conditionTool.addCondition(UP_USER.f_insert_time,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnInsertTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER.f_insert_time,value,operator);
	}
	/**
	 * 
	 */
	public void ctnLastLoginTime(String value) {
		conditionTool.addCondition(UP_USER.f_last_login_time,value,ConditionOperator.EQ);
	}
	/**
	 * 
	 */
	public void ctnLastLoginTime(String value,ConditionOperator operator) {
		conditionTool.addCondition(UP_USER.f_last_login_time,value,operator);
	}
	 
	public List<UpUserRowData> queryRows() throws Exception{
		return this.queryRows(UpUserRowData.class);
	} 
	
	@Override
	protected MapTable getMapTable() {
 		return UP_USER.instance();
	}
	 
}
