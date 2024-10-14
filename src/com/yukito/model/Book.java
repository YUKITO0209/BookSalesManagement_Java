package com.yukito.model;

import java.math.BigDecimal;

/**
 * 书籍实体
 * @author wjw20
 *
 */
public class Book {
	private int id; // 序号
	private String isbn; // ISBN号
	private String bookName; // 书名
	private String author; // 作者
	private String publisher; // 出版社
	private BigDecimal price; // 单价
	private int inventory; // 库存
	private int salesVolume; // 销量
	private BigDecimal sales; // 销售额
	
	public Book() {
		super();
	}

	public Book(String isbn, String bookName) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
	}

	public Book(int id, int salesVolume) {
		super();
		this.id = id;
		this.salesVolume = salesVolume;
	}

	public Book(String isbn, String bookName, String author, String publisher) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
	}

	public Book(String isbn, String bookName, String author, String publisher, BigDecimal price, int inventory) {
		super();
		this.isbn = isbn;
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.inventory = inventory;
	}

	public Book(int id, String isbn, String bookName, String author, String publisher, BigDecimal price,
			int inventory) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.bookName = bookName;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.inventory = inventory;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public String getBookName() {
		return bookName;
	}
	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public int getInventory() {
		return inventory;
	}
	
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	
	public int getSalesVolume() {
		return salesVolume;
	}
	
	public void setSalesVolume(int salesVolume) {
		this.salesVolume = salesVolume;
	}
	
	public BigDecimal getSales() {
		return sales;
	}
	
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}
}
