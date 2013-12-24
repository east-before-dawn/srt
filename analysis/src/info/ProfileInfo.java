package info;

public class ProfileInfo extends DataInfo{
	double hanziratio;
	int star;
	int basicinfo;
	int education;
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
	
	public void setBasicinfo(int i) {basicinfo = i;}
	public double getBasicinfo() {return basicinfo;}
	
	public void setEducation(int i) {education = i;}
	public double getEducation() {return education;}
	
	public void setWorknum(int i) {worknum = i;}
	public int getWorknum() {return worknum;}
	
	public void setLikenum(int i) {likenum = i;}
	public int getLikenum() {return likenum;}
	
	public void setAppcount(int i) {appcount = i;}
	public int getAppcount() {return appcount;}
	
	public void setVisitorcount(int i) {visitorcount = i;}
	public int getVisitorcount() {return visitorcount;}
	
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
	@Override
	public String getAttribute() {
		StringBuilder ret = new StringBuilder();
		ret.append("@attribute HanziRatio numeric\n");
		ret.append("@attribute StarNum numeric\n");
		ret.append("@attribute BasicInfo numeric\n");
		ret.append("@attribute Education numeric\n");
		ret.append("@attribute WorkNum numeric\n");
		ret.append("@attribute LikeNum numeric\n");
		ret.append("@attribute AppCount numeric\n");
		ret.append("@attribute VisitorCount numeric\n");
		ret.append("@attribute PageCount numeric\n");
		ret.append("@attribute ZhanCount numeric\n");
		ret.append("@attribute MusicCount numeric\n");
		ret.append("@attribute MovieCount numeric\n");
		ret.append("@attribute FriendCount numeric\n");
		ret.append("@attribute FriendDensity numeric\n");
		return ret.toString();
	}
	@Override
	public String getData() {
		StringBuilder ret = new StringBuilder();
		ret.append(hanziratio).append(",");
		ret.append(star).append(",");
		ret.append(basicinfo).append(",");
		ret.append(education).append(",");
		ret.append(worknum).append(",");
		ret.append(likenum).append(",");
		ret.append(appcount).append(",");
		ret.append(visitorcount).append(",");
		ret.append(pagecount).append(",");
		ret.append(zhancount).append(",");
		ret.append(musiccount).append(",");
		ret.append(moviecount).append(",");
		ret.append(friendcount).append(",");
		ret.append(frienddensity);
		return ret.toString();
	}
}
