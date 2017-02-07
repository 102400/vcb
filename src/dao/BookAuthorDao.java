package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.BookAuthor;
import entity.Celebrity;
import show.BookShow;
import util.JDBC;

public class BookAuthorDao {
	
	public List<Celebrity> findAuthorsNameByBookId(BookAuthor bookauthor,Connection conn) {
		
		List<Celebrity> celebrityList = new ArrayList<>();
		int bookId = bookauthor.getBookId();
		
//		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = JDBC.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("c.celebrityId,c.nameZh,c.nameEn ");
			sql.append("FROM ");
			sql.append("bookauthors as ba,celebrites AS c ");
			sql.append("WHERE ");
			sql.append("ba.bookId = ? ");
			sql.append("AND ba.bookAuthorId = c.celebrityId; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, bookauthor.getBookId());
			rs = stmt.executeQuery();
			while(rs.next()) {
				Celebrity celebrity = new Celebrity();
				celebrity.setCelebrityId(rs.getInt("celebrityId"));
				celebrity.setNameZh(rs.getString("nameZh"));
				celebrity.setNameEn(rs.getString("nameEn"));
				celebrityList.add(celebrity);
			}
//			else {
//				return null;
//			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
//			JDBC.release(conn, stmt, rs);
			JDBC.setSRnull(stmt, rs);
		}
//		System.out.println(bookshow);
		return celebrityList;
	}

}
