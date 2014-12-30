package DAL;

import DataBase.DataBaseManager;
import Entities.House;
import Map.Bound;

public class HouseDAL {
	
	private DataBaseManager dm ;
	
	public HouseDAL(){
		this.dm = new DataBaseManager();
	}
	
	/**
	 * 添加新的house记录
	 * @param house
	 */
	public void addNewHouse(House house)
	{
		String sqlStr = String.format("NSERT INTO house(agent,address,lat,lon,name, the_geom) VALUES ('%s','%s',%f,%f,'%s',  ST_SetSRID(ST_Point(%f, %f),4326))",house.getAgent(),house.getAddress(),house.getLat(),house.getLon(),house.getName(),house.getLon(),house.getLat());
		dm.executeSQL(sqlStr);
		
	}
	/**
	 * 删除house记录根据ID
	 * @param house
	 */
	public void deleteHouseByID(String houseId){
		
		String sqlStr = String.format("delete from house where id = '%s'", houseId);
		dm.executeSQL(sqlStr);
	}
	/**
	 * 返回所有house记录，json格式
	 * @return
	 */
	
	public String getAllHouses(){
		String  result = null;
		String sqlStr = "select * from house";
		result = dm.getJSonResult(sqlStr);
		return result;
	}
	
	/**
	 * 根据访问范围house,结果格式为json
	 * @param bound
	 * @return
	 */
	public String getHouseByBounds(Bound bound){		
		
		String result = null;
		String sqlStr = "";
		result = dm.getJSonResult(sqlStr);
		return result;
	}
	
	

}
