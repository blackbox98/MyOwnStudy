package InterestDAOFiles;

import java.sql.*;


/*
create table interest (
	no int,
    id varchar2(30),
    dongCode varchar2(10),
    primary key(no, id),
    foreign key(id) references member(id) on delete cascade
);
*/
public class InterestDAO implements DAO{
	public InterestDAO() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	private Connection getConnection() throws SQLException {// 2

		Connection con = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/happyhouse?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8", "ssafy",
				"ssafy");
		return con;
	}

	public void insertInterest(Interest i) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();// 2
			String q = "Insert into interest values('" + i.getNo() + "','" + i.getId() + "','" + i.getDongCode() + "');";
			st = con.createStatement();// 3
			st.executeUpdate(q);
		} catch (SQLException e) {
			System.out.println("insert 에러:" + e);
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
		}
	}

	public void viewInterest(String id, String dongCode) {
		// id와 dongCode를 이용한 조회
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();// 2
			String q = "Select * from baseaddress b where b.dongCode = (select i.dongCode from interest i where i.id = '" + id + "' and i.dongCode = " + dongCode + ");";// 3
			st = con.createStatement();// 3
			rs = st.executeQuery(q);// 4
			if (rs.next()) {// 5
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5) + "\t" + rs.getDouble(6) + "\t" + rs.getDouble(7));
			}
		} catch (SQLException e) {
			System.out.println("select SQL error:" + e);
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

	public void viewInterest() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();// 2
			String q = "Select * from interest;";// 3
			st = con.createStatement();// 3
			rs = st.executeQuery(q);// 4
			while (rs.next()) {// 5
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
			}
		} catch (SQLException e) {
			System.out.println("select error:" + e);
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

	public void deleteInterest(String id, String dongCode) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();// 2
			String q = "Delete from interest where id = '" + id + "' and dongCode = '" + dongCode + "';";// 3
			st = con.createStatement();// 3
			st.executeUpdate(q);// 4
		} catch (SQLException e) {
			System.out.println("delete error:" + e);
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
		} // end finally
	}// end deleteCustomer()

	public void updateInterest(Interest i) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			String q = "Update interest set no = '" + i.getNo() + "', id = '" + i.getId() + "', dongCode = '" + i.getDongCode() + "'  where id = '" + i.getId() + "' and dongCode = '" + i.getDongCode() + "';";
			st = con.createStatement();
			st.executeUpdate(q);
		} catch (SQLException e) {
			System.out.println("update error:" + e);
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
		} // end finally
	}// end updateMember()
}// end class