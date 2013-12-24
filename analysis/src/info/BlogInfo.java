package info;
//define blog info
public class BlogInfo extends DataInfo{
	private int blognum;
	private double avgshare;
	private int maxshare;
	private double avgcmt;
	private int maxcmt;
	private double avgview;
	private int maxview;
	private double timediff;
	private int mintimediff;
	private double titlelength;
	private double textlength;
	private double publicratio;
	private double avgimg;
	private int maximg;
	
	public void setNum(int i) {blognum = i;}
	public int getNum() {return blognum;}
	
	public void setAvgshare(double d) {avgshare = d;}
	public double getAvgshare() {return avgshare;}
	
	public void setMaxshare(int i) {maxshare = i;}
	public int getMaxshare() {return maxshare;}
	
	public void setAvgcmt(double d) {avgcmt = d;}
	public double getAvgcmt() {return avgcmt;}
	
	public void setMaxcmt(int i) {maxcmt = i;}
	public double getMaxcmt() {return maxcmt;}
	
	public void setAvgview(double d) {avgview = d;}
	public double getAvgview() {return avgview;}
	
	public void setMaxview(int i) {maxview = i;}
	public int getMaxview() {return maxview;}
	
	public void setTimediff(double d) {timediff = d;}
	public double getTimediff() {return timediff;}
	
	public void setMintimediff(int i) {mintimediff = i;}
	public int getMintimediff() {return mintimediff;}
	
	public void setTitlelength(double d) {titlelength = d;}
	public double geTitlelength() {return titlelength;}
	
	public void setTextlength(double d) {textlength = d;}
	public double geTextlength() {return textlength;}
	
	public void setPublicratio(double d) {publicratio = d;}
	public double getPublicratio() {return publicratio;}
	
	public void setAvgimg(double d) {avgimg = d;}
	public double getAvgimg() {return avgimg;}
	
	public void setMaximg(int i) {maximg = i;}
	public int getMaximg() {return maximg;}
	
	public String toString() {
		return "{blognum="+blognum+" maxshare="+maxshare+" maxcmt="+maxcmt+
				" maxview="+maxview+" mintimediff="+mintimediff+" titlelength="
				+titlelength+" textlength="+textlength+" publicratio="+
				publicratio+" maximg="+maximg+"}";
	}
	
	@Override
	public String getAttribute() {
		StringBuilder ret = new StringBuilder();
		ret.append("@attribute BlogNum numeric\n");
		ret.append("@attribute AvgBlogShare numeric\n");
		ret.append("@attribute MaxBlogShare numeric\n");
		ret.append("@attribute AvgBlogCmt numeric\n");
		ret.append("@attribute MaxBlogCmt numeric\n");
		ret.append("@attribute AvgBlogView numeric\n");
		ret.append("@attribute MaxBlogView numeric\n");
		ret.append("@attribute BlogTimeDiff numeric\n");
		ret.append("@attribute MinBlogTimeDiff numeric\n");
		ret.append("@attribute BlogTitleLength numeric\n");
		ret.append("@attribute BlogTextLength numeric\n");
		ret.append("@attribute PublicRatio numeric\n");
		ret.append("@attribute AvgImg numeric\n");
		ret.append("@attribute MaxImg numeric\n");
		return ret.toString();
	}
	@Override
	public String getData() {
		StringBuilder ret = new StringBuilder();
		ret.append(blognum).append(",");
		ret.append(avgshare).append(",");
		ret.append(maxshare).append(",");
		ret.append(avgcmt).append(",");
		ret.append(maxcmt).append(",");
		ret.append(avgview).append(",");
		ret.append(maxview).append(",");
		ret.append(timediff).append(",");
		ret.append(mintimediff).append(",");
		ret.append(titlelength).append(",");
		ret.append(textlength).append(",");
		ret.append(publicratio).append(",");
		ret.append(avgimg).append(",");
		ret.append(maximg);
		return ret.toString();
	}
}
