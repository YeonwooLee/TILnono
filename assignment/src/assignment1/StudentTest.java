package assignment1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StudentTest {
	public static void main(String[] args) {
		//bookDTO 5개 생성 (이후 books table에 insert 할 것)
		BookDTO book0 = new BookDTO(21424, "Java Basic", "김하나", "Jaen.kr", "Java basic grammar", 15000);
		BookDTO book1 = new BookDTO(33455, "JDBC Pro", "김철수", "Jaen.kr", "", 23000);
		BookDTO book2 = new BookDTO(55355, "Servlet/JSP", "박자바", "Jaen.kr", "Based on Model2", 41000);
		BookDTO book3 = new BookDTO(35332, "Android App", "홍길동", "Jaen.kr", "Lightweight Framework", 25000);
		BookDTO book4 = new BookDTO(35355, "OOAD분석과설계", "소나무", "Jaen.kr", "", 30000);
		
		//저장된 책들 books 배열에 넣기
		BookDTO[] books = {book0,book1,book2,book3,book4};
		
		//StudentTest 객체 db 생성
		StudentTest db = new StudentTest();

		//books테이블에 books배열에 있는 객체들 insert
		db.insertStudent(books);
		
		//books table에 있는 모든 row 출력
		db.printAllBooks();
	}
	
	//BOOKS table에 row를 넣어봅시다
	public void insertStudent(BookDTO[] booklist) {
		//오라클 드라이버 생성용 드라이버명
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "yanoos";
		String password = "1234";

		//알아내느라 고생했음 localhost로 안되면 나처럼 desktop뭐시기 해야됨 <!?!>
		//맨 끝에 :xe 붙이는 것도 주의할 것 <!?!>
		String url = "jdbc:oracle:thin:@DESKTOP-E11BVG9:1521:xe";
		
		//Preparedstatement를 사용하기 위한 sql문. ?가 들어간 부분에 하나씩 채워진다 %s문과 비슷함
		String sql = "insert into Books (isbn,title,author,publisher,description,price) values (?,?,?,?,?,?)";
		
		//여기서 오류나면 오라클 db랑 연결에 문제 있는 것
		try {
			//driverManager를 사용하기위해 필요함 일단 모르고 넘어가겠음 <!??>
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//여기서 오류나면 오라클 db는 맞게 연결했는데 내 db주소나 id나 pw에 문제 있는 것
		try {
			//연결만들기
			Connection con= DriverManager.getConnection(url,user,password);
			//만들어 둔 연결로 sql문을 db에 보낼 수 있다! <!??>
			PreparedStatement pst = con.prepareStatement(sql);
			
			for(int i=0;i<booklist.length;i++) {
				//bookDTO에 만들어뒀던 get method 사용
				int isbn = booklist[i].getIsbn();
				String title = booklist[i].getTitle();
				String author = booklist[i].getAuthor();
				String publisher =booklist[i].getPublisher();
				String description= booklist[i].getDesc();
				int price = booklist[i].getPrice();
				
				//신기함 PreparedStatement 객체에 인자로 들어간 sql문의 ?에 순서대로 드러감
				//숫자 순서대로안해도 됨 <!?!>
				pst.setInt(6, price);
				pst.setString(2, title);
				pst.setString(3, author);
				pst.setString(4, publisher);
				pst.setString(5, description);
				pst.setInt(1, isbn);
				
				//commit같은 느낌? 이걸 해줘야 sql문이 실행되고 db에 반영되는듯 확실치는 않음<!??>
				//그니까 jdbc는 별도의 commit없이 sql문 실행과 commit이 같이 되는 것인가<!??>
				int r = pst.executeUpdate();
				
				//executeUpdate메소드는 몇개의 행이 바뀌었는지 리턴한다 근데 나는 한번에 한 행 바꾸는 것 밖에 못함
				//그래서 내가 쓰는 exeuteUpdate는 무조건 1만 리턴하게됨. 아래아래 출력행 주석 풀면 보임 
				//executeUpdate 한 번으로 여러개의 sql문 실행하는 방법이 있는듯?? <!??>
				//System.out.println(r);
			}
			//이걸 꺼야 연결을 끌 수 있음
			pst.close();
			//연결을 끊어줘야 다른 곳에서 쓸 수 있음
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void printAllBooks() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "yanoos";
		String password = "1234";
		String url = "jdbc:oracle:thin:@DESKTOP-E11BVG9:1521:xe";
		String sql = "SELECT * FROM BOOKS";
		try {
			//db사용준비과정
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,password);
			Statement st = con.createStatement();
			//Before Of File(인덱스상 -1느낌)에서 시작하여 End Of File(인덱스상 끝 +1)까지
			ResultSet rs = st.executeQuery(sql);
			
			//몇개가 될지 모를때는 ArrayList<자료형>를 사용한다
			ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
			
			while(true) {
				//next메소드는 처음 실행하면 0번인덱스를 가리킨다. boolean타입이고 EOF에 도달하면 false 리턴
				if(rs.next()) {
					//get<자료형>(column명)메소드로 현재 인덱스 행의 자료 가져옴
					BookDTO book = new BookDTO(rs.getInt("isbn"),rs.getString("title"),
							rs.getString("author"),rs.getString("publisher"),
							rs.getString("description"),rs.getInt("price"));
					//bookList에 추가
					bookList.add(book);
				}else {
					break;
				}
			}
			//booklist<BookDTO>에서 BookDTO객체 하나씩 뽑아서 toString메소드 실행
			//BookDTO의 toString메소드는 오버라이드 되어있음
			System.out.println(String.format("|%-82s|", "******************************* 도서 목록 *******************************"));
			for(int i=0;i<bookList.size();i++) {
				System.out.println(bookList.get(i).toString());		
			}
			
			//실행체를 꺼야 명령 전달체를 끔
			rs.close();
			//명령 전달체를 꺼야 연결을 끊음
			st.close();
			//연결을 끊어줘야 딴데서 씀
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}