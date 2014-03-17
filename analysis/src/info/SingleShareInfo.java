package info;

public class SingleShareInfo extends DataInfo{
	private int isphoto;
	private int isblog;
	private int isvideo;
	private int isother;
	private int cmt;
	private String timestamp;
	private int Aplus;
	private int Aminus;
	private int Cplus;
	private int Cminus;
	private int Eplus;
	private int Eminus;
	private int Oplus;
	private int Ominus;
	private int Nplus;
	private int Nminus;
	
	public int getIsphoto() {
		return isphoto;
	}
	public void setIsphoto(int isphoto) {
		this.isphoto = isphoto;
	}
	public int getIsblog() {
		return isblog;
	}
	public void setIsblog(int isblog) {
		this.isblog = isblog;
	}
	public int getIsvideo() {
		return isvideo;
	}
	public void setIsvideo(int isvideo) {
		this.isvideo = isvideo;
	}
	public int getIsother() {
		return isother;
	}
	public void setIsother(int isother) {
		this.isother = isother;
	}
	public int getCmt() {
		return cmt;
	}
	public void setCmt(int cmt) {
		this.cmt = cmt;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public int getAplus() {
		return Aplus;
	}
	public void setAplus(int aplus) {
		Aplus = aplus;
	}
	public int getAminus() {
		return Aminus;
	}
	public void setAminus(int aminus) {
		Aminus = aminus;
	}
	public int getCplus() {
		return Cplus;
	}
	public void setCplus(int cplus) {
		Cplus = cplus;
	}
	public int getCminus() {
		return Cminus;
	}
	public void setCminus(int cminus) {
		Cminus = cminus;
	}
	public int getEplus() {
		return Eplus;
	}
	public void setEplus(int eplus) {
		Eplus = eplus;
	}
	public int getEminus() {
		return Eminus;
	}
	public void setEminus(int eminus) {
		Eminus = eminus;
	}
	public int getOplus() {
		return Oplus;
	}
	public void setOplus(int oplus) {
		Oplus = oplus;
	}
	public int getOminus() {
		return Ominus;
	}
	public void setOminus(int ominus) {
		Ominus = ominus;
	}
	public int getNplus() {
		return Nplus;
	}
	public void setNplus(int nplus) {
		Nplus = nplus;
	}
	public int getNminus() {
		return Nminus;
	}
	public void setNminus(int nminus) {
		Nminus = nminus;
	}

	public String toString() {
		return this.getData();
	}
	
	@Override
	public String getAttribute() {
		StringBuilder ret = new StringBuilder();
		ret.append("@attribute ShareTimestamp numeric\n");
		ret.append("@attribute isPhoto numeric\n");
		ret.append("@attribute isBlog numeric\n");
		ret.append("@attribute isVideo numeric\n");
		ret.append("@attribute isOther numeric\n");
		ret.append("@attribute ShareCmt numeric\n");
		ret.append("@attribute ShareAplus numeric\n");
		ret.append("@attribute ShareAminus numeric\n");
		ret.append("@attribute ShareCplus numeric\n");
		ret.append("@attribute ShareCminus numeric\n");
		ret.append("@attribute ShareEplus numeric\n");
		ret.append("@attribute ShareEminus numeric\n");
		ret.append("@attribute ShareOplus numeric\n");
		ret.append("@attribute ShareOminus numeric\n");
		ret.append("@attribute ShareNplus numeric\n");
		ret.append("@attribute ShareNminus numeric\n");
		return ret.toString();
	}
	
	@Override
	public String getData() {
		StringBuilder ret = new StringBuilder();
		ret.append(timestamp).append(",");
		ret.append(isphoto).append(",");
		ret.append(isblog).append(",");
		ret.append(isvideo).append(",");
		ret.append(isother).append(",");
		ret.append(cmt).append(",");
		ret.append(Aplus).append(",");
		ret.append(Aminus).append(",");
		ret.append(Cplus).append(",");
		ret.append(Cminus).append(",");
		ret.append(Eplus).append(",");
		ret.append(Eminus).append(",");
		ret.append(Oplus).append(",");
		ret.append(Ominus).append(",");
		ret.append(Nplus).append(",");
		ret.append(Nminus).append("\n");
		return ret.toString();
	}
}
