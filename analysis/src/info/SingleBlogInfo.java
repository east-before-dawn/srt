package info;

public class SingleBlogInfo extends DataInfo{
	private int share;
	private int cmt;
	private int view;
	private String timestamp;
	private int titlelength;
	private int textlength;
	private int ispublic;
	private int img;
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

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getTitlelength() {
		return titlelength;
	}

	public void setTitlelength(int titlelength) {
		this.titlelength = titlelength;
	}

	public int getTextlength() {
		return textlength;
	}

	public void setTextlength(int textlength) {
		this.textlength = textlength;
	}

	public int getIspublic() {
		return ispublic;
	}

	public void setIspublic(int ispublic) {
		this.ispublic = ispublic;
	}

	public int getImg() {
		return img;
	}

	public void setImg(int img) {
		this.img = img;
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
		ret.append("@attribute BlogTimestamp numeric\n");
		ret.append("@attribute BlogShare numeric\n");
		ret.append("@attribute BlogCmt numeric\n");
		ret.append("@attribute BlogView numeric\n");
		ret.append("@attribute BlogTitleLength numeric\n");
		ret.append("@attribute BlogTextLength numeric\n");
		ret.append("@attribute BlogIsPublic numeric\n");
		ret.append("@attribute BlogImg numeric\n");
		ret.append("@attribute BlogAplus numeric\n");
		ret.append("@attribute BlogAminus numeric\n");
		ret.append("@attribute BlogCplus numeric\n");
		ret.append("@attribute BlogCminus numeric\n");
		ret.append("@attribute BlogEplus numeric\n");
		ret.append("@attribute BlogEminus numeric\n");
		ret.append("@attribute BlogOplus numeric\n");
		ret.append("@attribute BlogOminus numeric\n");
		ret.append("@attribute BlogNplus numeric\n");
		ret.append("@attribute BlogNminus numeric\n");
		return ret.toString();
	}
	
	@Override
	public String getData() {
		StringBuilder ret = new StringBuilder();
		ret.append(timestamp).append(",");
		ret.append(share).append(",");
		ret.append(cmt).append(",");
		ret.append(view).append(",");
		ret.append(titlelength).append(",");
		ret.append(textlength).append(",");
		ret.append(ispublic).append(",");
		ret.append(img).append(",");
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
