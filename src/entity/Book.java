package entity;

import java.util.Date;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;

//@Entity
//@Table(name="books")
public class Book {
	
//	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bookId;
	private int bookInfoId;
	private String name;
	private int publisherId;
	private int languageId;
	private int pages;
	private String isbn;
	private double price;
	private int publicationYear;
	private int publicationMonth;
	private Date modifyTime;
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getBookInfoId() {
		return bookInfoId;
	}
	public void setBookInfoId(int bookInfoId) {
		this.bookInfoId = bookInfoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	public int getLanguageId() {
		return languageId;
	}
	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public int getPublicationMonth() {
		return publicationMonth;
	}
	public void setPublicationMonth(int publicationMonth) {
		this.publicationMonth = publicationMonth;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

}
