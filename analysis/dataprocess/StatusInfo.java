import java.util.Collection;
import java.util.HashSet;

//define status info
public class StatusInfo {
	private int statusnum;
	private double avgshare;
	private double avgcmt;
	private double timediff;
	private double textlength;
	private double avgemoticon;
	
	public void setNum(int d) {statusnum = d;}
	public int getNum() {return statusnum;}
	
	public void setAvgshare(double d) {avgshare = d;}
	public double getAvgshare() {return avgshare;}
	
	public void setAvgcmt(double d) {avgcmt = d;}
	public double getAvgcmt() {return avgcmt;}
	
	public void setTimediff(double d) {timediff = d;}
	public double getTimediff() {return timediff;}
	
	public void setTextlength(double d) {textlength = d;}
	public double getTextlength() {return textlength;}
	
	public void setAvgemoticon(double d) {avgemoticon = d;}
	public double getAvgemoticon() {return avgemoticon;}
	
	public String toString() {
		return "{num="+statusnum+" avgshare="+avgshare+" avgcmt="+avgcmt+
				" timediff="+timediff+" textlength="+textlength+
				" avgemoticon="+avgemoticon+"}";
	}
}
