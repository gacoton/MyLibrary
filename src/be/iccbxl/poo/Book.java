package be.iccbxl.poo;

import java.time.LocalDate;

public class Book {

	private String titre;
	private String author;
	private int totalPages;
	private int loanPeriod;
	private double rentalPrice;
	private LocalDate borrowingDate;
	private String language;
	private String borrower;
	// necessaire de spécifier le type du livre ?
	private String bookType;
	
	
	public Book(String titre, String author, int totalPages, String language, String bookType) {
		
		this.titre = titre;
		this.author = author;
		this.totalPages = totalPages;
		// Periode fixe ?
		this.loanPeriod = 30;
		// Dépend du type ?
		this.rentalPrice = 0;
		this.borrowingDate = null;
		this.language = language;
		this.borrower = null;
		this.bookType = bookType;
	}
	
	public LocalDate computeReturnDate(LocalDate borrowingDate, int loanPeriod) {
		LocalDate returnDate = null;
	//TODO ajouter une periode à une date
		return returnDate;
	}
	
	
}
