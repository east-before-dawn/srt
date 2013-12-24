package info;

import java.util.ArrayList;
import java.util.HashMap;

public class UserSample {
	private ArrayList<DataInfo> infolist = new ArrayList<DataInfo>();
	private HashMap<String,Double> bigfive = new HashMap<String,Double>();
	
	public void addInfo(int index, DataInfo info) {
		infolist.add(index, info);
	}
	
	public void setBigfive(double a, double c, double e, double o, double n) {
		bigfive.put("A", a);
		bigfive.put("C", c);
		bigfive.put("E", e);
		bigfive.put("O", o);
		bigfive.put("N", n);
	}
	
	public DataInfo getInfo(int index) {
		return infolist.get(index);
	}
	
	public HashMap<String,Double> getBigfive() {
		return bigfive;
	}
	
	public double getAScore() {
		return bigfive.get("A");
	}
	
	public double getEScore() {
		return bigfive.get("E");
	}
	
	public double getCScore() {
		return bigfive.get("C");
	}
	
	public double getNScore() {
		return bigfive.get("N");
	}
	
	public double getOScore() {
		return bigfive.get("O");
	}
	
	public ArrayList<DataInfo> getInfoList() {
		return infolist;
	}
}
