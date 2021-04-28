package hedgehogShop;

public class HedgehogDTO {
	private String code, sex, purchaseDate, description;
	private int purchasePrice;
	
	/*
	//상품정보
	String code;
	String purchaseDate;
	String saleDate;
	int purchasePrice;
	int setPrice;
	int salePrice;
	
	//생물정보
	String name;
	String kind;
	String sex;
	String birth;
	String age;
	String description;
	
	//암놈특수
	String pregnantTimes;
	String lastDeleveryDate;
	*/
	
	public HedgehogDTO(String code,String sex,String purchaseDate,String description, int purchasePrice) {
		this.code=code;
		this.sex=sex;
		this.purchaseDate=purchaseDate;
		this.description=description;
		this.purchasePrice=purchasePrice;
	}
	
	
	public String getCode() {
		return this.code;
	}
	public String getSex() {
		return this.sex;
	}
	public String getPurchaseDate() {
		return this.purchaseDate;
	}
	public String getDescription() {
		return this.description;
	}
	public int getPurchasePrice() {
		return this.purchasePrice;
	}
	
	private void setCode(String code) {
		this.code=code;
	}
	private void setSex(String sex) {
		this.sex=sex;
	}
	private void setPurchaseDate(String purchaseDate) {
		this.purchaseDate=purchaseDate;
	}
	private void setDescription(String description) {
		this.description = description;
	}
	private void setPurchasePrice(int purchasePrice) {
		this.purchasePrice=purchasePrice;
	}
}
