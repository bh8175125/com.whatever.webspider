package com.whatever.webspider.module;

public class Book {

	private String	author;
	private String	publisher;
	private String	date;
	private int			price;
	private double	score;
	private long		scorers;

	public Book() {
		super();
	}

	public Book(String author, String publisher, String date, int price,
			double score, long scorers) {
		super();
		this.author = author;
		this.publisher = publisher;
		this.date = date;
		this.price = price;
		this.score = score;
		this.scorers = scorers;

	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getScore() {
		return this.score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public long getScorers() {
		return this.scorers;
	}

	public void setScorers(long scorers) {
		this.scorers = scorers;
	}
}
