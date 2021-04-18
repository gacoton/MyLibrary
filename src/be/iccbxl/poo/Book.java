package be.iccbxl.poo;

import java.time.LocalDate;
import java.time.Period;

public class Book {

	private String title;
	private String author;
	private int totalPages;
	private int loanPeriod;
	private double rentalPrice;
	private LocalDate borrowingDate;
	private String language;
	private Person borrower;
	private String bookType;
	
	
	public Book(String title, String author, int totalPages, String language, String bookType) {
		
		this.title = title;
		this.author = author;
		this.totalPages = totalPages;
		this.loanPeriod = 30;
		this.rentalPrice = 5;
		this.borrowingDate = null;
		this.language = language;
		this.borrower = null;
		this.bookType = bookType;
	}
	
	public int computeRemainingDays(Book book) {
		Period nbOfDays;
		nbOfDays = LocalDate.now().until(book.getBorrowingDate().plusDays(book.getLoanPeriod()));
		return nbOfDays.getDays();
	}
	
	public LocalDate computeReturnDate(Book book) {
		LocalDate returnDate = null;
		returnDate = book.getBorrowingDate().plusDays(book.getLoanPeriod());
		return returnDate;
	}

	public int getLoanPeriod() {
		return loanPeriod;
	}

	public void setLoanPeriod(int loanPeriod) {
		this.loanPeriod = loanPeriod;
	}

	public double getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(double rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	public LocalDate getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(LocalDate borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public Person getBorrower() {
		return borrower;
	}

	public void setBorrower(Person borrower) {
		this.borrower = borrower;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public String getLanguage() {
		return language;
	}

	public String getBookType() {
		return bookType;
	}
	
	@Override
	public String toString() {
		
		return "Book [" + title + ": " + author +" "+ totalPages+ " pages, langue "+language+", type "+bookType+" "
		+rentalPrice+" "+borrowingDate+" "+borrower+" "+loanPeriod+"]";
	}
	
}
