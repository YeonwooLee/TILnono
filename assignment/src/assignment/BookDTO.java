package assignment;

public class BookDTO {
	 private String isbn, title, author, publisher, desc; 
	 private int price;

	public BookDTO(String isbn, String title, String author, String publisher, String desc, int price) {
		this.isbn=isbn;
		this.title=title;
		this.author=author;
		this.publisher=publisher;
		this.desc=desc;
		this.price=price;
	}
	private void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getIsbn() {
		return this.isbn;
	}
	
	private void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	private void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthor() {
		return this.author;
	}
	private void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublisher() {
		return this.publisher;
	}
	private void setDesc(String desc) {
		this.desc = desc;
	}
	public String getDesc() {
		return this.desc;
	}
	private void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		String price = String.valueOf(this.price);
		return String.format("|%-12s|", this.isbn)+String.format("%-12s|", this.title)+String.format("%-12s|", this.author)+String.format("%-12s|", this.publisher)+String.format("%-12s|", price)+String.format("%21s|", this.desc);
	}
}