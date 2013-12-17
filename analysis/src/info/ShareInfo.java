package info;
//define share info
public class ShareInfo extends DataInfo{
	private int sharenum;
	private double photoratio;
	private double blogratio;
	private double videoratio;
	private double otherratio;
	private double avgcmt;
	private int maxcmt;
	private double timediff;
	private int mintimediff;
	
	public void setNum(int i) {sharenum = i;}
	public int getNum() {return sharenum;}
	
	public void setPhotoratio(double d) {photoratio = d;}
	public double getPhotoratio() {return photoratio;}
	
	public void setBlogratio(double d) {blogratio = d;}
	public double getBLogratio() {return blogratio;}
	
	public void setVideoratio(double d) {videoratio = d;}
	public double getVideoratio() {return videoratio;}
	
	public void setOtherratio(double d) {otherratio = d;}
	public double getOtherratio() {return otherratio;}
	
	public void setAvgcmt(double d) {avgcmt = d;}
	public double getAvgcmt() {return avgcmt;}
	
	public void setMaxcmt(int i) {maxcmt = i;}
	public int getMaxcmt() {return maxcmt;}
	
	public void setTimediff(double d) {timediff = d;}
	public double getTimediff() {return timediff;}
	
	public void setMintimediff(int i) {mintimediff = i;}
	public double getMintimediff() {return mintimediff;}
	
	public String toString() {
		return "{sharenum="+sharenum+" photoratio="+photoratio+" blogratio="
				+blogratio+" videoratio="+videoratio+" otherratio="+otherratio+
				" maxcmt="+maxcmt+" mintimediff="+mintimediff+"}";
	}
	@Override
	public String getAttribute() {
		StringBuilder ret = new StringBuilder();
		ret.append("@attribute ShareNum numeric\n");
		ret.append("@attribute PhotoRatio numeric\n");
		ret.append("@attribute BlogRatio numeric\n");
		ret.append("@attribute VideoRatio numeric\n");
		ret.append("@attribute OtherRatio numeric\n");
		ret.append("@attribute AvgShareCmt numeric\n");
		ret.append("@attribute MaxShareCmt numeric\n");
		ret.append("@attribute ShareTimeDiff numeric\n");
		ret.append("@attribute MinShareTimeDiff numeric\n");
		return ret.toString();
	}
	@Override
	public String getData() {
		StringBuilder ret = new StringBuilder();
		ret.append(sharenum).append(",");
		ret.append(photoratio).append(",");
		ret.append(blogratio).append(",");
		ret.append(videoratio).append(",");
		ret.append(otherratio).append(",");
		ret.append(avgcmt).append(",");
		ret.append(maxcmt).append(",");
		ret.append(timediff).append(",");
		ret.append(mintimediff);
		return ret.toString();
	}
}
