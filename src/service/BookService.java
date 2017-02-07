package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.BookAuthorDao;
import dao.BookDao;
import dao.BookInfoDao;
import dao.BookTranslatorDao;
import dao.LanguageDao;
import dao.PublisherDao;
import entity.Book;
import entity.BookAuthor;
import entity.BookInfo;
import entity.BookTranslator;
import entity.Celebrity;
import entity.Language;
import entity.Publisher;
import show.BookShow;
import util.JDBC;

public class BookService {
	
	public BookShow showBook (int bookId) {
		
		Connection connection = JDBC.getConnection();
//		try {
//			connection.setAutoCommit(false);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
		
		BookShow bookshow = new BookShow();
		
		BookDao bookdao = new BookDao();
		BookAuthorDao bookauthordao = new BookAuthorDao();
		BookTranslatorDao booktranslatordao = new BookTranslatorDao();
		LanguageDao languagedao = new LanguageDao();
		PublisherDao publisherdao = new PublisherDao();
		BookInfoDao bookinfodao = new BookInfoDao();
		
		Book book = new Book();
		book.setBookId(bookId);
		book = bookdao.findBookByBookId(book,connection);
		if(book==null) {
//			JDBC.rollback(connection);
			return null;
		}
		bookshow.setBook(book);
		
		BookAuthor bookauthor = new BookAuthor();
		bookauthor.setBookId(bookId);
		List<Celebrity> authorList = bookauthordao.findAuthorsNameByBookId(bookauthor,connection);
		if(authorList==null) {
//			JDBC.rollback(connection);
			return null;
		}
		bookshow.setAuthorList(authorList);
		
		//!!!
		BookTranslator booktranslator = new BookTranslator();
		booktranslator.setBookId(bookId);
		List<Celebrity> translatorList = booktranslatordao.findTranslatorsNameByBookId(booktranslator,connection);
		if(translatorList==null) {
//			JDBC.rollback(connection);
			return null;
		}
		bookshow.setTranslatorList(translatorList);
		
		
		Language language = languagedao.findLanguageNameByLanguageId(book,connection);
		if(language==null) {
//			JDBC.rollback(connection);
			return null;
		}
		bookshow.setLanguage(language);
		
		Publisher publisher = publisherdao.findPublisherByBook(book,connection);
		if(publisher==null) {
//			JDBC.rollback(connection);
			return null;
		}
		bookshow.setPublisher(publisher);
		
		BookInfo bookInfo = bookinfodao.findBookInfoByBook(book,connection);
		if(bookInfo==null) {
//			JDBC.rollback(connection);
			return null;
		}
		bookshow.setBookInfo(bookInfo);
		
//		try {
//			connection.commit();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
		JDBC.closeConnection(connection);
		
//		System.out.println(bookshow.getAuthorList().get(0).getNameZh());
//		System.out.println(bookshow.getAuthorList().get(1).getNameZh());
		return bookshow;
	}

}
