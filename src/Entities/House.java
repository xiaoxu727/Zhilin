package Entities;

public class House {

	private String Id;
	private String agent;
	private String name;
	private String address;
	private String Tel;
	private double lat;
	private double lon;

	public String getId() {
		return Id;
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return Tel;
	}

	public void setTel(String tel) {
		Tel = tel;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public House() {

	}

	public House (String agent, String name, String address, String tel,
			double lat, double lon) {
		this.agent = agent;
		this.name = name;
		this.address = address;
		this.Tel = tel;
		this.lat = lat;
		this.lon = lon;

	}

}
