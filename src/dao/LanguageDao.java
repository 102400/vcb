package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Book;
import entity.Language;
import util.JDBC;

public class LanguageDao {
	
	public Language findLanguageNameByLanguageId(Book book,Connection conn) {
		Language language = new Language();
		int bookId = book.getBookId();
		
//		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
//			conn = JDBC.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ");
			sql.append("b.languageId,l.languageName ");
			sql.append("FROM ");
			sql.append("books AS b,languages AS l ");
			sql.append("WHERE ");
			sql.append("b.bookId = ? ");
			sql.append("AND b.languageId = l.languageId ");
			stmt = conn.prepareStatement(sql.toString());
			stmt.setInt(1, bookId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				language.setLanguageId(rs.getInt("languageId"));
				language.setLanguageName(rs.getString("languageName"));
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
		return language;
	}

}
