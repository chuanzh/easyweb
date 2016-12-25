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
 
public class UP_MENU extends MapTable{
	public static final String TABLE_NAME = "up_menu"; 
	
	/**
	 * 
	 */
	@FieldKey
	@FieldAuto
	@FieldNumber
	public static final String f_id = "id";
	
	/**
	 * 父ID
	 */
	@FieldNumber
	public static final String f_pid = "pid";
	
	/**
	 * 名称
	 */
	@FieldString
	public static final String f_name = "name";
	
	/**
	 * 
	 */
	@FieldString
	public static final String f_url = "url";
	
	/**
	 * 
	 */
	@FieldString
	public static final String f_icon = "icon";
	
	/**
	 * 
	 */
	@FieldDate
	public static final String f_update_time = "update_time";
	
	/**
	 * 
	 */
	@FieldDate
	public static final String f_insert_time = "insert_time";
	
	 
	private UP_MENU(){}
	private static UP_MENU instanceObj = null;
	public static UP_MENU instance(){
		if(instanceObj == null){
			instanceObj = new UP_MENU();
		}
		return instanceObj;
	}
}
 