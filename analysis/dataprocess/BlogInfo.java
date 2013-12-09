//define blog info
public class BlogInfo {
	private int blognum;
	private double avgshare;
	private double avgcmt;
	private double avgview;
	private double timediff;
	private double titlelength;
	private double textlength;
	private double publicratio;
	private double avgimg;
	
	public void setNum(int i) {blognum = i;}
	public int getNum() {return blognum;}
	
	public void setAvgshare(double d) {avgshare = d;}
	public double getAvgshare() {return avgshare;}
	
	public void setAvgcmt(double d) {avgcmt = d;}
	public double getAvgcmt() {return avgcmt;}
	
	public void setAvgview(double d) {avgview = d;}
	public double getAvgview() {return avgview;}
	
	public void setTimediff(double d) {timediff = d;}
	public double getTimediff() {return timediff;}
	
	public void setTitlelength(double d) {titlelength = d;}
	public double geTitlelength() {return titlelength;}
	
	public void setTextlength(double d) {textlength = d;}
	public double geTextlength() {return textlength;}
	
	public void setPublicratio(double d) {publicratio = d;}
	public double getPublicratio() {return publicratio;}
	
	public void setAvgimg(double d) {avgimg = d;}
	public double getAvgimg() {return avgimg;}
	
	public String toString() {
		return "{blognum="+blognum+" avgshare="+avgshare+" avgcmt="+avgcmt+
				" avgview="+avgview+" timediff="+timediff+" titlelength="
				+titlelength+" textlength="+textlength+" publicratio="+
				publicratio+" avgimg="+avgimg+"}";
	}
}
