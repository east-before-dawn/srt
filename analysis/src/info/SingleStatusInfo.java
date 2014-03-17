package info;

public class SingleStatusInfo extends DataInfo{
	private int share;
	private int cmt;
	private String timestamp;
	private int textlength;
	private int emoticon;
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
	
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
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
	public int getTextlength() {
		return textlength;
	}
	public void setTextlength(int textlength) {
		this.textlength = textlength;
	}
	public int getEmoticon() {
		return emoticon;
	}
	public void setEmoticon(int emoticon) {
		this.emoticon = emoticon;
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
		ret.append("@attribute StatusTimestamp numeric\n");
		ret.append("@attribute StatusShare numeric\n");
		ret.append("@attribute StatusCmt numeric\n");
		ret.append("@attribute StatusTextLength numeric\n");
		ret.append("@attribute StatusEmoticon numeric\n");
		ret.append("@attribute StatusAplus numeric\n");
		ret.append("@attribute StatusAminus numeric\n");
		ret.append("@attribute StatusCplus numeric\n");
		ret.append("@attribute StatusCminus numeric\n");
		ret.append("@attribute StatusEplus numeric\n");
		ret.append("@attribute StatusEminus numeric\n");
		ret.append("@attribute StatusOplus numeric\n");
		ret.append("@attribute StatusOminus numeric\n");
		ret.append("@attribute StatusNplus numeric\n");
		ret.append("@attribute StatusNminus numeric\n");
		return ret.toString();
	}
	@Override
	public String getData() {
		StringBuilder ret = new StringBuilder();
		ret.append(timestamp).append(",");
		ret.append(share).append(",");
		ret.append(cmt).append(",");
		ret.append(textlength).append(",");
		ret.append(emoticon).append(",");
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
