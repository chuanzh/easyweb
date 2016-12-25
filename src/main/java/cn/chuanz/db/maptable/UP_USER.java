package cn.chuanz.db.maptable;

import cn.chuanz.orm.MapTable;
import cn.chuanz.orm.fieldannotation.FieldAuto;
import cn.chuanz.orm.fieldannotation.FieldChar;
import cn.chuanz.orm.fieldannotation.FieldClob;
import cn.chuanz.orm.fieldannotation.FieldDate;
import cn.chuanz.orm.fieldannotation.FieldKey;
import cn.chuanz.orm.fieldannotation.FieldNumber;
import cn.chuanz.orm.fieldannotation.FieldString;
import cn.chuanz.orm.fieldannotation.FieldBigNumber;
 
public class UP_USER extends MapTable{
	public static final String TABLE_NAME = "up_user"; 
	
	/**
	 * 
	 */
	@FieldKey
	@FieldAuto
	@FieldNumber
	public static final String f_id = "id";
	
	/**
	 * 用户名
	 */
	@FieldString
	public static final String f_name = "name";
	
	/**
	 * 密码
	 */
	@FieldString
	public static final String f_pwd = "pwd";
	
	/**
	 * 用户组
	 */
	@FieldNumber
	public static final String f_group_id = "group_id";
	
	/**
	 * 更新时间
	 */
	@FieldDate
	public static final String f_update_time = "update_time";
	
	/**
	 * 
	 */
	@FieldDate
	public static final String f_insert_time = "insert_time";
	
	/**
	 * 
	 */
	@FieldDate
	public static final String f_last_login_time = "last_login_time";
	
	 
	private UP_USER(){}
	private static UP_USER instanceObj = null;
	public static UP_USER instance(){
		if(instanceObj == null){
			instanceObj = new UP_USER();
		}
		return instanceObj;
	}
}
 