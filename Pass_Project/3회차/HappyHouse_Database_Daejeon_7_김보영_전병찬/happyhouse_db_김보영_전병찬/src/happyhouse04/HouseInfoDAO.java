package happyhouse04;

import java.sql.*;


public class HouseInfoDAO {
	static {
		try {
			//1. Driver 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2. Connection 연결
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}// 1
	}

	public HouseInfoDAO() {
	}

	private Connection getConnection() throws SQLException {// 2
		Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/happyhouse?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8","ssafy","ssafy");
		return con;
	}

	public void insertHouseInfo(int aptCode, String aptName, String dongCode, String dongName, int buildYear, String jibun, String lat, String lng, String img) {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = getConnection();// 2
			String q = "Insert into housedeal values(?,?,?,?,?,?,?,?,?) ";
			st = con.prepareStatement(q);
			st.setInt(1, aptCode);
			st.setString(2, aptName);
			st.setString(3, dongCode);
			st.setString(4, dongName);
			st.setInt(5, buildYear);
			st.setString(6, jibun);
			st.setString(7, lat);
			st.setString(8, lng);
			st.setString(9, img);
			
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println("insert SQL error:" + e);
		} finally {
			try {
				// if(rs != null) rs.close();//6
				if (st != null)		st.close();
				if (con != null)	con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	//아파트번호로 검색
	public void viewHouseInfoA(int aptCode) {
		// num�� �ش��ϴ� ������ ���
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();// 2
			String q = "Select * from houseinfo where aptCode=?";// 3
			st = con.prepareStatement(q);
			st.setInt(1, aptCode);
			rs = st.executeQuery();// 4
			if (rs.next()) {// 5
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t"
						+ rs.getString(3)+ "\t" + rs.getString(4)+"\t" + rs.getInt(5)+ "\t" + rs.getString(6)+ "\t" + rs.getString(7)+"\t" + rs.getString(8)+"\t" + rs.getString(9));
			}
		} catch (SQLException e) {
			System.out.println("select ����:" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();// 6
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	/* 모든 하웃므 정보 조회
	public void viewHouseInfo() {
		// ��ü ������ ���
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();// 2
			String q = "Select * from housedeal";// 3
			st = con.prepareStatement(q);
			rs = st.executeQuery();// 4
			while (rs.next()) {// 5
				System.out.println(rs.getInt(1) + "\t" + rs.getInt(2) + "\t"
						+ rs.getString(3)+"\t"+rs.getInt(4)+"\t" + rs.getInt(5)+"\t" + rs.getInt(6)+"\t" + rs.getString(7)+"\t" + rs.getString(8)+"\t" + rs.getString(9)+ "\t" +rs.getString(10));
			}
		} catch (SQLException e) {
			System.out.println("select ����:" + e);
		} finally {
			try {
				if (rs != null)
					rs.close();// 6
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	*/

}// end class
