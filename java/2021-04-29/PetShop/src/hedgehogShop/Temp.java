package hedgehogShop;

import java.sql.*;

public class Temp {

	public static void main(String[] args){
		//����̹� �ε�
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@DESKTOP-E11BVG9:1521:xe";
			String id = "yanoos";
			String pw = "1234";
			System.out.println("DB���󿬰�");
			try {
				con = DriverManager.getConnection(url,id,pw);
				System.out.println("DB������ġ");
			} catch (SQLException e) {
				System.out.println("DB��������ġ");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("DB�������");
			e.printStackTrace();
		}
}
}