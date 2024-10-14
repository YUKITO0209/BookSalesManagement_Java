package com.yukito.model;

/**
 * 购物车实体
 * @author wjw20
 *
 */
public class Trolley {
	private String isbn; // ISBN号
	private int num; // 购买数量
	
	public Trolley() {
		super();
	}

	public Trolley(String isbn, int num) {
		super();
		this.isbn = isbn;
		this.num = num;
	}

	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
}
