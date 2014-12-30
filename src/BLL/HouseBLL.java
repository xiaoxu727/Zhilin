package BLL;
import Entities.House;
import DAL.HouseDAL;
import Map.Bound;

public class HouseBLL {
	private HouseDAL hDal ;
	public HouseBLL()
	{
		this.hDal = new HouseDAL();
	}
	/**
	 * 添加新的house记录
	 * @param agent 代理人
	 * @param name 房子名字
	 * @param address 地址
	 * @param tel 电话
	 * @param lat 纬度
	 * @param lon 经度
	 */
	public void addNewHouse(String agent,String name,String address,String tel,double lat,double lon){
		
		House  house= new House(agent,name,address,tel,lat,lon);
//		House house2 = new House();
		hDal.addNewHouse(house);
	}
	/**
	 * 根据房子的ID删除记录
	 * @param houseId
	 */
	public void deleteHouseByID(String houseId)
	{
		hDal.deleteHouseByID(houseId);
	}
	
	/**
	 * 获取所有房子记录，json格式
	 * @return
	 */
	public String getAllHouses()
	{
		return hDal.getAllHouses();
	}
	/**
	 * 根据范围查询house记录，返回json格式
	 * @param latMin
	 * @param lonMin
	 * @param latMax
	 * @param lonMax
	 * @return
	 */
	public String getHousesByBound(double latMin,double lonMin,double latMax,double lonMax)
	{
		Bound  bound = new Bound(latMin,lonMin,latMax,lonMax);
		return hDal.getHouseByBounds(bound);
		
	}	
	
}
