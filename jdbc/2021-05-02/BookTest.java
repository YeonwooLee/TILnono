package assignment1;

public class BookTest {
	public static void main(String[] args) {
		BookDTO book0 = new BookDTO(21424, "Java Basic", "���ϳ�", "Jaen.kr", "Java basic grammar", 15000);
		BookDTO book1 = new BookDTO(33455, "JDBC Pro", "��ö��", "Jaen.kr", "", 23000);
		BookDTO book2 = new BookDTO(55355, "Servlet/JSP", "���ڹ�", "Jaen.kr", "Based on Model2", 41000);
		BookDTO book3 = new BookDTO(35332, "Android App", "ȫ�浿", "Jaen.kr", "Lightweight Framework", 25000);
		BookDTO book4 = new BookDTO(35355, "OOAD�м�������", "�ҳ���", "Jaen.kr", "", 30000);
		BookDTO[] books = {book0,book1,book2,book3,book4};
		
		printBookDTO(books);
		}
	public static void printBookDTO(BookDTO[] books) {
		System.out.println(String.format("|%-82s|", "******************************* ���� ��� *******************************"));
		for(int i=0;i<books.length;i++) {
			System.out.println(books[i].toString());
		}
	}
	
	public void insertStudent(BookDTO book) {
		
	}
	public void printALLBOOKS() {
		
	}
}
