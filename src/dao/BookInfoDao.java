package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Book;
import entity.BookInfo;
import util.JDBC;

public class BookInfoDao {
	
	public BookInfo findBookInfoByBook(Book book,Connection conn) {
		BookInfo bookInfo = new BookInfo();
		int bookId = book.getBookId();
		
//		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = JDBC.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("b.bookInfoId,bi.name ");
			sql.append("FROM ");
			sql.append("books AS b,bookinfo AS bi ");
			sql.append("WHERE ");
			sql.append("b.bookId = ? ");
			sql.append("AND b.bookInfoId = bi.bookInfoId; ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, bookId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				bookInfo.setBookInfoId(rs.getInt("bookInfoId"));
				bookInfo.setName(rs.getString("name"));
			}
			else {
				return null;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally {
//			JDBC.release(conn, stmt, rs);
			JDBC.setSRnull(stmt, rs);
		}
		return bookInfo;
	}

}
