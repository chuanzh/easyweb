package cn.chuanz.util;

import cn.chuanz.orm.DbConfBean;
import cn.chuanz.orm.dbadapter.MysqlDb;

public class DbEwebConnect extends MysqlDb {

	private static DbEwebConnect connect = null;
	private DbConfBean[] savleBeanArray = null;
	private DbConfBean masterBean = null;
	private DbEwebConnect (){
	}
	public static synchronized DbEwebConnect instance(){
		if(connect == null)
			connect = new DbEwebConnect();
		return connect;
	}
	
 

	@Override
	protected String getEncode() {
		return "utf-8";
	}
	@Override
	public boolean printSql() {
		return ConfigRead.readBooleanValue("showSql");
	}
	@Override
	protected DbConfBean getMasterDb() {
		if(masterBean == null){
			masterBean = new DbConfBean();
			masterBean.setDbName(ConfigRead.readValue("eweb_db_name"));
			masterBean.setIpAndPort(ConfigRead.readValue("eweb_db_ipandport"));
			masterBean.setUserName(ConfigRead.readValue("eweb_db_username"));
			masterBean.setPassword(ConfigRead.readValue("eweb_db_password"));
			masterBean.setPoolConfByStr(ConfigRead.readValue("eweb_db_poolconf"));
		}
		return masterBean;
	}
	@Override
	protected DbConfBean[] getSlaveDbArray() {
		String slaveIpStr = ConfigRead.readValue("eweb_slave_ipandport");
		if(FuncStatic.checkIsEmpty(slaveIpStr)){
			return null;
		}else{
			if(savleBeanArray == null){
				String[] slaveIpArray = FuncStatic.trim(slaveIpStr.trim(), ";").split(";");
				String[] slaveDbNameArray	= ConfigRead.readValue("eweb_slave_name").trim().split(";");
				String[] slaveUserNameArray	= ConfigRead.readValue("eweb_slave_username").trim().split(";");
				String[] slavePasswordArray	= ConfigRead.readValue("eweb_slave_password").trim().split(";");
				String  slavePoolconf = ConfigRead.readValue("eweb_slave_poolconf");
				savleBeanArray = new DbConfBean[slaveIpArray.length];
				for(int i=0; i<slaveIpArray.length; i++){
					DbConfBean bean = new DbConfBean();
					bean.setIpAndPort(slaveIpArray[i]);
					bean.setDbName(slaveDbNameArray[i]);
					bean.setUserName(slaveUserNameArray[i]);
					bean.setPassword(slavePasswordArray[i]);
					bean.setPoolConfByStr(slavePoolconf);
					savleBeanArray[i] = bean;
				}
			}
			return savleBeanArray;
		}
	}
	
}
