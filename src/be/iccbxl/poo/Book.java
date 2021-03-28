package be.iccbxl.poo;

import java.time.LocalDate;

public class Book {

	private String titre;
	private String author;
	private int totalPages;
	private int loanPeriod;
	private double rentalPrice;
	LocalDate borrowingDate;
	private String language;
	private Person borrower;
	// Necessaire de spécifier le type du livre ?
	private String bookType;
	
	
	public Book(String titre, String author, int totalPages, String language, String bookType) {
		
		this.titre = titre;
		this.author = author;
		this.totalPages = totalPages;
		// Periode fixe ? période communiquée par le membre ?
		this.loanPeriod = 30;
		// Dépend du type ? prix fixe  ? prix en foncton de la periode ?
		this.rentalPrice = 5;
		this.borrowingDate = null;
		this.language = language;
		this.borrower = null;
		this.bookType = bookType;
	}
	
	public LocalDate computeReturnDate(LocalDate borrowingDate, int loanPeriod) {
		LocalDate returnDate = null;
		returnDate = borrowingDate.plusDays(loanPeriod);
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

	public String getTitre() {
		return titre;
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
	
	
}
