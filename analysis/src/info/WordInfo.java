package info;

public class WordInfo extends DataInfo{

	double posWordNum;
	double negWordNum;
	double posStatusRatio;
	double negStatusRatio;

	public double getPosStatusRatio() {
		return posStatusRatio;
	}

	public void setPosStatusRatio(double posStatusRatio) {
		this.posStatusRatio = posStatusRatio;
	}

	public double getNegStatusRatio() {
		return negStatusRatio;
	}

	public void setNegStatusRatio(double negStatusRatio) {
		this.negStatusRatio = negStatusRatio;
	}

	public double getPosWordNum() {
		return posWordNum;
	}

	public void setPosWordNum(double posWordNum) {
		this.posWordNum = posWordNum;
	}

	public double getNegWordNum() {
		return negWordNum;
	}

	public void setNegWordNum(double negWordNum) {
		this.negWordNum = negWordNum;
	}

	@Override
	public String getAttribute() {
		StringBuilder ret = new StringBuilder();
		ret.append("@attribute PosWordNum numeric\n");
		ret.append("@attribute NegWordNum numeric\n");
		ret.append("@attribute PosStatusRatio numeric\n");
		ret.append("@attribute NegStatusRatio numeric\n");
		return ret.toString();
	}

	@Override
	public String getData() {
		StringBuilder ret = new StringBuilder();
		ret.append(posWordNum).append(",");
		ret.append(negWordNum).append(",");
		ret.append(posStatusRatio).append(",");
		ret.append(negStatusRatio);
		return ret.toString();
	}
	
	public String toString() {
		return "{posWordNum="+posWordNum+" negWordNum="+negWordNum+
				" posStatusRatio="+posStatusRatio+" negStatusRatio="
				+negStatusRatio+"}";
	}

}
