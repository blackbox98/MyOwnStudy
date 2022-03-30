package happyhouse04;

import java.sql.*;

/*
 create table customer(
 num  number(4)  primary key,
 name  varchar2(20),
 address  varchar2(200));
 */
public class HouseDealDAO {
	static {
		try {
			//1. Driver 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2. Connection 연결
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}// 1
	}

	public HouseDealDAO() {
	}

	private Connection getConnection() throws SQLException {// 2
		Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/happyhouse?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8","ssafy","ssafy");
		return con;
	}

	public void insertHouseDeal(int no, int aptCode, String dealAmount, int dealMonth, int dealDay, int dealYear, String area, String floor, String type, String rentMoney) {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = getConnection();// 2
			String q = "Insert into housedeal values(?,?,?,?,?,?,?,?,?,?) ";
			st = con.prepareStatement(q);
			st.setInt(1, no);
			st.setInt(2, aptCode);
			st.setString(3, dealAmount);
			st.setInt(4, dealYear);
			st.setInt(5, dealMonth);
			st.setInt(6, dealDay);
			st.setString(7, area);
			st.setString(8, floor);
			st.setString(9, type);
			st.setString(10, rentMoney);
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
	
	//no로 검색
	public void viewHouseDealInfo(int no) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();// 2
			String q = "Select * from housedeal where no=?";// 3
			st = con.prepareStatement(q);
			st.setInt(1, no);
			rs = st.executeQuery();// 4
			if (rs.next()) {// 5
				System.out.println(rs.getInt(1) + "\t" + rs.getInt(2) + "\t"
						+ rs.getString(3)+ "\t" + rs.getInt(4)+"\t" + rs.getInt(5)+ "\t" + rs.getInt(6)+ "\t" + rs.getString(7)+"\t" + rs.getString(8)+"\t" + rs.getString(9)+"\t" + rs.getString(10));
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

	public void viewHouseDeal() {
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
	
	//아파트로 검색
	public void viewHouseDealApart(int aptCode) {
		// num�� �ش��ϴ� ������ ���
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();// 2
			String q = "select houseinfo.aptName, houseinfo.dongName, housedeal.dealAmount, housedeal.dealYear, housedeal.dealMonth, housedeal.dealDay, houseinfo.img\n" + 
					"from housedeal join houseinfo on housedeal.aptCode=houseinfo.aptCode\n" + 
					"where houseinfo.aptCode=?;";// 3
			st = con.prepareStatement(q);
			st.setInt(1, aptCode);
			rs = st.executeQuery();// 4
			while (rs.next()) {// 5
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t"
						+ rs.getString(3)+"\t"+rs.getInt(4)+"\t" + rs.getInt(5)+"\t" + rs.getInt(6)+"\t" + rs.getString(7));
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
	
	//동으로 검색
	public void viewHouseDealDong(String dongCode) {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();// 2
			String q = "select houseinfo.aptName, houseinfo.dongName, housedeal.dealAmount, housedeal.dealYear, housedeal.dealMonth, housedeal.dealDay, houseinfo.img from housedeal join houseinfo on housedeal.aptCode=houseinfo.aptCode where houseinfo.dongCode=?;";// 3
			st = con.prepareStatement(q);
			st.setString(1, dongCode);
			rs = st.executeQuery();// 4
			while (rs.next()) {// 5
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t"
						+ rs.getString(3)+"\t"+rs.getInt(4)+"\t" + rs.getInt(5)+"\t" + rs.getInt(6)+"\t" + rs.getString(7)+"\t");
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

	public void deleteHouseDeal(int no) {
		// num�� �ش��ϴ� �� ����
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = getConnection();// 2
			String q = "Delete from housedeal  where no=" +no ;// 3
			st = con.prepareStatement(q);// 3
			st.executeUpdate();// 4
		} catch (SQLException e) {
			System.out.println("delete ����:" + e);
		} finally {
			try {
				// if(rs != null) rs.close();//6
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}// end finally

	}// end deleteCustomer()
}// end class
