package hedgehogShop;

import java.sql.*;

public class Temp {

	public static void main(String[] args){
		//드라이버 로드
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@DESKTOP-E11BVG9:1521:xe";
			String id = "yanoos";
			String pw = "1234";
			System.out.println("DB정상연결");
			try {
				con = DriverManager.getConnection(url,id,pw);
				System.out.println("DB계정일치");
			} catch (SQLException e) {
				System.out.println("DB계정불일치");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println("DB연결실패");
			e.printStackTrace();
		}
}
}