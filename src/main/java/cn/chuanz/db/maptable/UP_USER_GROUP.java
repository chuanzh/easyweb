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
 
public class UP_USER_GROUP extends MapTable{
	public static final String TABLE_NAME = "up_user_group"; 
	
	/**
	 * 
	 */
	@FieldKey
	@FieldAuto
	@FieldNumber
	public static final String f_id = "id";
	
	/**
	 * 组名
	 */
	@FieldString
	public static final String f_name = "name";
	
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
	
	 
	private UP_USER_GROUP(){}
	private static UP_USER_GROUP instanceObj = null;
	public static UP_USER_GROUP instance(){
		if(instanceObj == null){
			instanceObj = new UP_USER_GROUP();
		}
		return instanceObj;
	}
}
 