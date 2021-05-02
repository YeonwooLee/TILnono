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
		//bookDTO 5�� ���� (���� books table�� insert �� ��)
		BookDTO book0 = new BookDTO(21424, "Java Basic", "���ϳ�", "Jaen.kr", "Java basic grammar", 15000);
		BookDTO book1 = new BookDTO(33455, "JDBC Pro", "��ö��", "Jaen.kr", "", 23000);
		BookDTO book2 = new BookDTO(55355, "Servlet/JSP", "���ڹ�", "Jaen.kr", "Based on Model2", 41000);
		BookDTO book3 = new BookDTO(35332, "Android App", "ȫ�浿", "Jaen.kr", "Lightweight Framework", 25000);
		BookDTO book4 = new BookDTO(35355, "OOAD�м�������", "�ҳ���", "Jaen.kr", "", 30000);
		
		//����� å�� books �迭�� �ֱ�
		BookDTO[] books = {book0,book1,book2,book3,book4};
		
		//StudentTest ��ü db ����
		StudentTest db = new StudentTest();

		//books���̺� books�迭�� �ִ� ��ü�� insert
		db.insertStudent(books);
		
		//books table�� �ִ� ��� row ���
		db.printAllBooks();
	}
	
	//BOOKS table�� row�� �־�ô�
	public void insertStudent(BookDTO[] booklist) {
		//����Ŭ ����̹� ������ ����̹���
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "yanoos";
		String password = "1234";

		//�˾Ƴ����� ������� localhost�� �ȵǸ� ��ó�� desktop���ñ� �ؾߵ� <!?!>
		//�� ���� :xe ���̴� �͵� ������ �� <!?!>
		String url = "jdbc:oracle:thin:@DESKTOP-E11BVG9:1521:xe";
		
		//Preparedstatement�� ����ϱ� ���� sql��. ?�� �� �κп� �ϳ��� ä������ %s���� �����
		String sql = "insert into Books (isbn,title,author,publisher,description,price) values (?,?,?,?,?,?)";
		
		//���⼭ �������� ����Ŭ db�� ���ῡ ���� �ִ� ��
		try {
			//driverManager�� ����ϱ����� �ʿ��� �ϴ� �𸣰� �Ѿ���� <!??>
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//���⼭ �������� ����Ŭ db�� �°� �����ߴµ� �� db�ּҳ� id�� pw�� ���� �ִ� ��
		try {
			//���Ḹ���
			Connection con= DriverManager.getConnection(url,user,password);
			//����� �� ����� sql���� db�� ���� �� �ִ�! <!??>
			PreparedStatement pst = con.prepareStatement(sql);
			
			for(int i=0;i<booklist.length;i++) {
				//bookDTO�� �����״� get method ���
				int isbn = booklist[i].getIsbn();
				String title = booklist[i].getTitle();
				String author = booklist[i].getAuthor();
				String publisher =booklist[i].getPublisher();
				String description= booklist[i].getDesc();
				int price = booklist[i].getPrice();
				
				//�ű��� PreparedStatement ��ü�� ���ڷ� �� sql���� ?�� ������� �巯��
				//���� ������ξ��ص� �� <!?!>
				pst.setInt(6, price);
				pst.setString(2, title);
				pst.setString(3, author);
				pst.setString(4, publisher);
				pst.setString(5, description);
				pst.setInt(1, isbn);
				
				//commit���� ����? �̰� ����� sql���� ����ǰ� db�� �ݿ��Ǵµ� Ȯ��ġ�� ����<!??>
				//�״ϱ� jdbc�� ������ commit���� sql�� ����� commit�� ���� �Ǵ� ���ΰ�<!??>
				int r = pst.executeUpdate();
				
				//executeUpdate�޼ҵ�� ��� ���� �ٲ������ �����Ѵ� �ٵ� ���� �ѹ��� �� �� �ٲٴ� �� �ۿ� ����
				//�׷��� ���� ���� exeuteUpdate�� ������ 1�� �����ϰԵ�. �Ʒ��Ʒ� ����� �ּ� Ǯ�� ���� 
				//executeUpdate �� ������ �������� sql�� �����ϴ� ����� �ִµ�?? <!??>
				//System.out.println(r);
			}
			//�̰� ���� ������ �� �� ����
			pst.close();
			//������ ������� �ٸ� ������ �� �� ����
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
			//db����غ����
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,password);
			Statement st = con.createStatement();
			//Before Of File(�ε����� -1����)���� �����Ͽ� End Of File(�ε����� �� +1)����
			ResultSet rs = st.executeQuery(sql);
			
			//��� ���� �𸦶��� ArrayList<�ڷ���>�� ����Ѵ�
			ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
			
			while(true) {
				//next�޼ҵ�� ó�� �����ϸ� 0���ε����� ����Ų��. booleanŸ���̰� EOF�� �����ϸ� false ����
				if(rs.next()) {
					//get<�ڷ���>(column��)�޼ҵ�� ���� �ε��� ���� �ڷ� ������
					BookDTO book = new BookDTO(rs.getInt("isbn"),rs.getString("title"),
							rs.getString("author"),rs.getString("publisher"),
							rs.getString("description"),rs.getInt("price"));
					//bookList�� �߰�
					bookList.add(book);
				}else {
					break;
				}
			}
			//booklist<BookDTO>���� BookDTO��ü �ϳ��� �̾Ƽ� toString�޼ҵ� ����
			//BookDTO�� toString�޼ҵ�� �������̵� �Ǿ�����
			System.out.println(String.format("|%-82s|", "******************************* ���� ��� *******************************"));
			for(int i=0;i<bookList.size();i++) {
				System.out.println(bookList.get(i).toString());		
			}
			
			//����ü�� ���� ��� ����ü�� ��
			rs.close();
			//��� ����ü�� ���� ������ ����
			st.close();
			//������ ������� ������ ��
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