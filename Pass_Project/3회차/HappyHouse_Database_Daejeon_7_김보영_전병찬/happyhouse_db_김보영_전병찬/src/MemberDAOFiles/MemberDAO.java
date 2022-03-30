package MemberDAOFiles;

import java.sql.*;


/*
create table member(
		id varchar2(30) primary key,
		password varchar2(30),
		email varchar2(40),
		name varchar2(20),
		age int
);
*/
public class MemberDAO implements DAO{
	public MemberDAO() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	private Connection getConnection() throws SQLException {// 2

		Connection con = DriverManager.getConnection(
				"jdbc:mysql://127.0.0.1:3306/happyhouse?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8", "ssafy",
				"ssafy");
		return con;
	}

	public void insertMember(Member m) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();// 2
			String q = "Insert into member values('" + m.getId() + "','" + m.getPassword() + "','" + m.getEmail() + "','" + m.getName() + "','" + m.getAge() + "');";
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

	public Member viewMember(String id) {
		// id를 이용한 조회
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Member m = null;
		try {
			con = getConnection();// 2
			String q = "Select * from member where id = '" + id + "';";// 3
			st = con.createStatement();// 3
			rs = st.executeQuery(q);// 4
			if (rs.next()) {// 5
				m = new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
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
		return m;
	}

	public void viewMember() {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = getConnection();// 2
			String q = "Select * from member;";// 3
			st = con.createStatement();// 3
			rs = st.executeQuery(q);// 4
			while (rs.next()) {// 5
				System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" + rs.getInt(5));
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

	public void deleteMember(String id) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();// 2
			String q = "Delete from member where id = '" + id + "';";// 3
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

	public void updateMember(Member m) {
		Connection con = null;
		Statement st = null;
		try {
			con = getConnection();
			String q = "Update member set password = '" + m.getPassword() + "', email = '" + m.getEmail() + "', name = '" + m.getName() + "',age = '" + m.getAge() + "'  where id = '"
					+ m.getId() + "';";
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
	
	public String login(String id) {
		// id를 이용한 조회
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String s = null;
		try {
			con = getConnection();// 2
			String q = "Select password from member where id = '" + id + "';";// 3
			st = con.createStatement();// 3
			rs = st.executeQuery(q);// 4
			if (rs.next()) {// 5
				s = rs.getString(1);
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
		return s;
	}
	
}// end class