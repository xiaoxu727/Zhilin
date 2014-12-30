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
	 * ����µ�house��¼
	 * @param house
	 */
	public void addNewHouse(House house)
	{
		String sqlStr = String.format("NSERT INTO house(agent,address,lat,lon,name, the_geom) VALUES ('%s','%s',%f,%f,'%s',  ST_SetSRID(ST_Point(%f, %f),4326))",house.getAgent(),house.getAddress(),house.getLat(),house.getLon(),house.getName(),house.getLon(),house.getLat());
		dm.executeSQL(sqlStr);
		
	}
	/**
	 * ɾ��house��¼����ID
	 * @param house
	 */
	public void deleteHouseByID(String houseId){
		
		String sqlStr = String.format("delete from house where id = '%s'", houseId);
		dm.executeSQL(sqlStr);
	}
	/**
	 * ��������house��¼��json��ʽ
	 * @return
	 */
	
	public String getAllHouses(){
		String  result = null;
		String sqlStr = "select * from house";
		result = dm.getJSonResult(sqlStr);
		return result;
	}
	
	/**
	 * ���ݷ��ʷ�Χhouse,�����ʽΪjson
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
