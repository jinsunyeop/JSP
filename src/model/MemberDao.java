package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
	
	private static String JDBC_URL = "jdbc:mysql://localhost:3306/testdb";
	private static String ID = "tester";
	private static String PW ="1234";
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(JDBC_URL,ID,PW);
	}
	
	
	
	public MemberDto getMember(String id,String pw) { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto member = null;
		String sql = "select * from MEMBER where M_ID=? and M_PW=?";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					member = new MemberDto();
					member.setId(rs.getString("M_ID"));
					member.setPw(rs.getString("M_PW"));
					member.setEmail(rs.getString("M_EMAIL"));
					member.setDate(rs.getTimestamp("M_DATE"));
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
	
		}return member;
	}
	
	

}
