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
	 * ����µ�house��¼
	 * @param agent ������
	 * @param name ��������
	 * @param address ��ַ
	 * @param tel �绰
	 * @param lat γ��
	 * @param lon ����
	 */
	public void addNewHouse(String agent,String name,String address,String tel,double lat,double lon){
		
		House  house= new House(agent,name,address,tel,lat,lon);
//		House house2 = new House();
		hDal.addNewHouse(house);
	}
	/**
	 * ���ݷ��ӵ�IDɾ����¼
	 * @param houseId
	 */
	public void deleteHouseByID(String houseId)
	{
		hDal.deleteHouseByID(houseId);
	}
	
	/**
	 * ��ȡ���з��Ӽ�¼��json��ʽ
	 * @return
	 */
	public String getAllHouses()
	{
		return hDal.getAllHouses();
	}
	/**
	 * ���ݷ�Χ��ѯhouse��¼������json��ʽ
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
