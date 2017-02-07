package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Book;
import entity.Publisher;
import util.JDBC;

public class PublisherDao {
	
	public Publisher findPublisherByBook(Book book,Connection conn) {
		Publisher publisher = new Publisher();
		
		int bookId = book.getBookId();
		
//		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = JDBC.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("b.publisherId,p.name ");
			sql.append("FROM ");
			sql.append("books AS b,publishers AS p ");
			sql.append("WHERE ");
			sql.append("b.bookId = ? ");
			sql.append("AND b.publisherId = p.publisherId ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, bookId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				publisher.setPublisherId(rs.getInt("publisherId"));
				publisher.setName(rs.getString("name"));
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
		return publisher;
	}

}
