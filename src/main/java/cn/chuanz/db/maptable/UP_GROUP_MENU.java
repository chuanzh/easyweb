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
 
public class UP_GROUP_MENU extends MapTable{
	public static final String TABLE_NAME = "up_group_menu"; 
	
	/**
	 * 
	 */
	@FieldKey
	@FieldAuto
	@FieldNumber
	public static final String f_id = "id";
	
	/**
	 * 组ID
	 */
	@FieldNumber
	public static final String f_group_id = "group_id";
	
	/**
	 * 菜单ID
	 */
	@FieldNumber
	public static final String f_menu_id = "menu_id";
	
	/**
	 * 
	 */
	@FieldDate
	public static final String f_insert_time = "insert_time";
	
	 
	private UP_GROUP_MENU(){}
	private static UP_GROUP_MENU instanceObj = null;
	public static UP_GROUP_MENU instance(){
		if(instanceObj == null){
			instanceObj = new UP_GROUP_MENU();
		}
		return instanceObj;
	}
}
 