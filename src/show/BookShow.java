package show;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import entity.Book;
import entity.BookInfo;
import entity.Celebrity;
import entity.Language;
import entity.Publisher;

public class BookShow {
	
	private Book book;
	private Language language;
	private Publisher publisher;
	private BookInfo bookInfo;
	
//	private int bookId;
//	private int bookInfoId;
//	private String bookInfoName;
//	private String bookName;
//	private int publisherId;
//	private String publisherName;
//	private int languageId;
//	private String languageName;
//	private int pages;
//	private String isbn;
//	private double price;
//	private int publicationYear;
//	private int publicationMonth;
	
	private List<Celebrity> authorList = new ArrayList<>();
	private List<Celebrity> translatorList = new ArrayList<>();
	
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	public List<Celebrity> getAuthorList() {
		return authorList;
	}
	public void setAuthorList(List<Celebrity> authorList) {
		this.authorList = authorList;
	}
	public List<Celebrity> getTranslatorList() {
		return translatorList;
	}
	public void setTranslatorList(List<Celebrity> translatorList) {
		this.translatorList = translatorList;
	}
	
	
	
//	public int getBookId() {
//		return bookId;
//	}
//	public void setBookId(int bookId) {
//		this.bookId = bookId;
//	}
//	public int getBookInfoId() {
//		return bookInfoId;
//	}
//	public void setBookInfoId(int bookInfoId) {
//		this.bookInfoId = bookInfoId;
//	}
//	public String getBookInfoName() {
//		return bookInfoName;
//	}
//	public void setBookInfoName(String bookInfoName) {
//		this.bookInfoName = bookInfoName;
//	}
//	public String getBookName() {
//		return bookName;
//	}
//	public void setBookName(String bookName) {
//		this.bookName = bookName;
//	}
//	public int getPublisherId() {
//		return publisherId;
//	}
//	public void setPublisherId(int publisherId) {
//		this.publisherId = publisherId;
//	}
//	public String getPublisherName() {
//		return publisherName;
//	}
//	public void setPublisherName(String publisherName) {
//		this.publisherName = publisherName;
//	}
//	public int getLanguageId() {
//		return languageId;
//	}
//	public void setLanguageId(int languageId) {
//		this.languageId = languageId;
//	}
//	public String getLanguageName() {
//		return languageName;
//	}
//	public void setLanguageName(String languageName) {
//		this.languageName = languageName;
//	}
//	public int getPages() {
//		return pages;
//	}
//	public void setPages(int pages) {
//		this.pages = pages;
//	}
//	public String getIsbn() {
//		return isbn;
//	}
//	public void setIsbn(String isbn) {
//		this.isbn = isbn;
//	}
//	public double getPrice() {
//		return price;
//	}
//	public void setPrice(double price) {
//		this.price = price;
//	}
//	public int getPublicationYear() {
//		return publicationYear;
//	}
//	public void setPublicationYear(int publicationYear) {
//		this.publicationYear = publicationYear;
//	}
//	public int getPublicationMonth() {
//		return publicationMonth;
//	}
//	public void setPublicationMonth(int publicationMonth) {
//		this.publicationMonth = publicationMonth;
//	}
//	
//	@Override
//	public String toString() {
//		return "BookShow [bookId=" + bookId + ", bookInfoId=" + bookInfoId + ", bookInfoName=" + bookInfoName
//				+ ", bookName=" + bookName + ", publisherId=" + publisherId + ", publisherName=" + publisherName
//				+ ", languageId=" + languageId + ", languageName=" + languageName + ", pages=" + pages + ", isbn="
//				+ isbn + ", price=" + price + ", publicationYear=" + publicationYear + ", publicationMonth="
//				+ publicationMonth + "]";
//	}
	
	

}
