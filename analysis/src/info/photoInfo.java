package info;

public class photoInfo extends DataInfo{
    private int photonum;
    private int albumnum;
    private int personnum;
    private int facenum;
    private double avgfacenum;
    private double avgphotonum;
    private double avgpersonnum;
    private double[] avgcolor;
    private double f1,f2,f3;
    
    public void setNum(int i) {photonum = i;}
    public int getNum() {return photonum;}
    
    public void setAlbumNum(int i) {albumnum = i;}
    public int getAlbumNum() {return albumnum;}
    
    public void setFaceNum(int i) {facenum = i;}
    public int getFaceNum() {return facenum;}
    
    public void setAvgPhotoNum(double i) {avgphotonum = i;}
    public double getAvgPhotoNum() {return avgphotonum;}
    
    public void setAvgFaceNum(double i) {avgfacenum = i;}
    public double getAvgFaceNum() {return avgfacenum;}
    
    public void setAvgColorInfo(double i[]) {avgcolor = i;}
    public double[] getAvgColorInfo() {return avgcolor;}
    
    public void setF1(double i) {f1 = i;}
    public double getF1() {return f1;}
    
    public void setF2(double i) {f2 = i;}
    public double getF2() {return f2;}
    
    public void setF3(double i) {f3 = i;}
    public double getF3() {return f3;}
    
    
    public String toString() {
        return "{photonum="+photonum+" albumnum="+albumnum+" avgPhotonum="+avgphotonum+
                " facenum="+facenum+" avgfacenum="+avgfacenum+" F1=" + f1 + " F2=" +f2 + " F3 =" + f3 +" colorinfo="
                +avgcolor[0]+"}";
    }
    
    @Override
    public String getAttribute() {
        StringBuilder ret = new StringBuilder();
        ret.append("@attribute Photo numeric\n");
        ret.append("@attribute Album numeric\n");
        ret.append("@attribute AvgPhoto numeric\n");
        ret.append("@attribute FaceNum numeric\n");
        ret.append("@attribute AvgFaceNum numeric\n");
        ret.append("@attribute F1 numeric\n");
        ret.append("@attribute F2 numeric\n");
        ret.append("@attribute F3 numeric\n");
        for (double i : avgcolor) {
          ret.append("@attribute AvgColorInfo numeric\n");
        }
        return ret.toString();
    }
    @Override
    public String getData() {
        StringBuilder ret = new StringBuilder();
        ret.append(photonum).append(",");
        ret.append(albumnum).append(",");
        ret.append(avgphotonum).append(",");
        ret.append(facenum).append(",");
        ret.append(avgfacenum).append(",");
        ret.append(f1).append(",");
        ret.append(f2).append(",");
        ret.append(f3);
        for (double i : avgcolor) {
          ret.append(",").append(i);
        }
        return ret.toString();
    }
}
