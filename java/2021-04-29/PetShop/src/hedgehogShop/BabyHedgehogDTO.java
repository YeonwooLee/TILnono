package hedgehogShop;

public class BabyHedgehogDTO {
	private String birth, kind;
	private int setPrice, salePrice;
	
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getSetPrice() {
		return setPrice;
	}
	public void setSetPrice(int setPrice) {
		this.setPrice = setPrice;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

}
