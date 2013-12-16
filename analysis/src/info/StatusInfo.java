package info;

//define status info
public class StatusInfo extends DataInfo{
	private int statusnum;
	private double avgshare;
	private int maxshare;
	private double avgcmt;
	private int maxcmt;
	private double timediff;
	private int mintimediff;
	private double textlength;
	private double avgemoticon;
	
	public void setNum(int d) {statusnum = d;}
	public int getNum() {return statusnum;}
	
	public void setAvgshare(double d) {avgshare = d;}
	public double getAvgshare() {return avgshare;}
	
	public void setMaxshare(int i) {maxshare = i;}
	public double getMaxshare() {return maxshare;}
	
	public void setAvgcmt(double d) {avgcmt = d;}
	public double getAvgcmt() {return avgcmt;}
	
	public void setMaxcmt(int i) {maxcmt = i;}
	public double getMaxcmt() {return maxcmt;}
	
	public void setTimediff(double d) {timediff = d;}
	public double getTimediff() {return timediff;}
	
	public void setMintimediff(int i) {mintimediff = i;}
	public double getMintimediff() {return mintimediff;}
	
	public void setTextlength(double d) {textlength = d;}
	public double getTextlength() {return textlength;}
	
	public void setAvgemoticon(double d) {avgemoticon = d;}
	public double getAvgemoticon() {return avgemoticon;}
	
	public String toString() {
		return "{num="+statusnum+" maxshare="+maxshare+" maxcmt="+maxcmt+
				" mintimediff="+mintimediff+" textlength="+textlength+
				" avgemoticon="+avgemoticon+"}";
	}
	@Override
	public String getAttribute() {
		StringBuilder ret = new StringBuilder();
		ret.append("@attribute StatusNum numeric\n");
		ret.append("@attribute AvgStatusShare numeric\n");
		ret.append("@attribute MaxStatusShare numeric\n");
		ret.append("@attribute AvgStatusCmt numeric\n");
		ret.append("@attribute MaxStatusCmt numeric\n");
		ret.append("@attribute StatusTimeDiff numeric\n");
		ret.append("@attribute MinStatusTimeDiff numeric\n");
		ret.append("@attribute StatusTextLength numeric\n");
		ret.append("@attribute AvgStatusEmoticon numeric\n");
		return ret.toString();
	}
	@Override
	public String getData() {
		StringBuilder ret = new StringBuilder();
		ret.append(statusnum).append(",");
		ret.append(avgshare).append(",");
		ret.append(maxshare).append(",");
		ret.append(avgcmt).append(",");
		ret.append(maxcmt).append(",");
		ret.append(timediff).append(",");
		ret.append(mintimediff).append(",");
		ret.append(textlength).append(",");
		ret.append(avgemoticon);
		return ret.toString();
	}
}
