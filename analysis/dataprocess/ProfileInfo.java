
public class ProfileInfo {
	double hanziratio;
	int star;
	double basicratio;
	double eduratio;
	int worknum;
	int likenum;
	int appcount;
	int visitorcount;
	int pagecount;
	int zhancount;
	int musiccount;
	int moviecount;
	int friendcount;
	double frienddensity;
	
	public void setHanziratio(double d) {hanziratio = d;}
	public double getHanziratio() {return hanziratio;}
	
	public void setStar(int i) {star = i;}
	public int getStar() {return star;}
	
	public void setBasicratio(double d) {basicratio = d;}
	public double getNBasicratio() {return basicratio;}
	
	public void setEduratio(double d) {eduratio = d;}
	public double getEduratio() {return eduratio;}
	
	public void setWorknum(int i) {worknum = i;}
	public int getWorknum() {return worknum;}
	
	public void setLikenum(int i) {likenum = i;}
	public int getLikenum() {return likenum;}
	
	public void setAppcount(int i) {appcount = i;}
	public int getAppcount() {return appcount;}
	
	public void setVisitorcount(int i) {visitorcount = i;}
	public int getVisitor() {return visitorcount;}
	
	public void setPagecount(int i) {pagecount = i;}
	public int getPagecount() {return pagecount;}
	
	public void setZhancount(int i) {zhancount = i;}
	public int getZhancount() {return zhancount;}
	
	public void setMusiccount(int i) {musiccount = i;}
	public int getMusiccount() {return musiccount;}
	
	public void setMoviecount(int i) {moviecount = i;}
	public int getMoviecount() {return moviecount;}
	
	public void setFriendcount(int i) {friendcount = i;}
	public int getFriendcount() {return friendcount;}
	
	public void setFriendDensity(double d) {frienddensity = d;}
	public double getFriendDensity() {return frienddensity;}
	
	public String toString() {
		return "{hanziratio="+hanziratio+"}";
	}
}
