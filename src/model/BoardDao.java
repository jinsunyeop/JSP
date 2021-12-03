package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

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
		
		
		public int getArticleCount() { //총 게시물 수 (list.do에 사용)
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int count = 0;
			String sql = "select count(*) from LIBRARY";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);	
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();}catch(SQLException e) {}
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
		
			}return count;
		}
		
		public List<BoardDto> getArticles(int start,int end) { //게시물 목록(list.do)에 사용
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<BoardDto> articleList = null;
			String sql = "SELECT * FROM (SELECT @rownum:=@rownum+1  rnum, l.*  FROM library l, (SELECT @ROWNUM := 0) R WHERE 1=1) list\n" + 
					"WHERE rnum >= ? AND rnum <= ? ";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					articleList = new ArrayList<BoardDto>(5); //5개의 ArrayList 사이즈
					do {
						BoardDto article = new BoardDto();
						article.setNum(rs.getInt("NUM"));
						article.setId(rs.getString("ID"));
						article.setPw(rs.getInt("PW"));
						article.setTitle(rs.getString("title"));
						article.setContent(rs.getString("CONTENT"));
						article.setCount(rs.getInt("COUNT"));
						article.setFname(rs.getString("FNAME"));
						article.setDate(rs.getTimestamp("DATE"));
						articleList.add(article);
					}while(rs.next());
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();}catch(SQLException e) {}
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
		
			}return articleList;
		}
		
		public BoardDto getArticle(int num) { //각 게시물 조회 
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql1 = "update LIBRARY set count=count+1 where num = ? ";
			String sql2 = "select * from LIBRARY where num = ?";
			BoardDto article = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				pstmt.close();
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					article = new BoardDto();
					article.setNum(rs.getInt("NUM"));
					article.setId(rs.getString("ID"));
					article.setPw(rs.getInt("PW"));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("CONTENT"));
					article.setCount(rs.getInt("COUNT"));
					article.setFname(rs.getString("FNAME"));
					article.setDate(rs.getTimestamp("DATE"));
				}else {
					System.out.println("조회 실패");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();}catch(SQLException e) {}
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
		
			}return article;
		}
		
	
		
		
		
		public void insertArticle(BoardDto article) { //게시물 쓰기(writePro.do)에사용
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String id = article.getId();
			int pw = article.getPw();
			String title = article.getTitle();
			String content = article.getContent();
			String fname = article.getFname();
			String sql = "insert into LIBRARY(ID,PW,TITLE,content,FNAME) values(?,?,?,?,?)";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2,pw);
				pstmt.setString(3, title);
				pstmt.setString(4, content);
				pstmt.setString(5, fname);
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();}catch(SQLException e) {}
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
			}
			
		}
		
		
		public BoardDto updateGetArticle(int num) { //수정시 게시물 조회 
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql1 = "select * from LIBRARY where num = ?";
			BoardDto article = null;
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql1);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					article = new BoardDto();
					article.setNum(rs.getInt("NUM"));
					article.setId(rs.getString("ID"));
					article.setPw(rs.getInt("PW"));
					article.setTitle(rs.getString("title"));
					article.setContent(rs.getString("CONTENT"));
					article.setCount(rs.getInt("COUNT"));
					article.setFname(rs.getString("FNAME"));
					article.setDate(rs.getTimestamp("DATE"));
				}else {
					System.out.println("조회 실패");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();}catch(SQLException e) {}
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
		
			}return article;
		}
		
		public BoardDto updateArticle(BoardDto article) { //수정 구현
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "update LIBRARY set pw = ?,title=?,content=?,fname=? where num = ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, article.getPw());
				pstmt.setString(2, article.getTitle());
				pstmt.setString(3, article.getContent());
				pstmt.setString(4, article.getFname());
				pstmt.setInt(5, article.getNum());
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();}catch(SQLException e) {}
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
		
			}return article;
		}
		
		public void deleteArticle(int num) { //수정 구현
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "delete from LIBRARY where num = ?";
			try {
				conn = getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try {rs.close();}catch(SQLException e) {}
				if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null)try {conn.close();}catch(SQLException e) {}
		
			}
		}
		
		
		
		
	

}
