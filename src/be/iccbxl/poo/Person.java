package be.iccbxl.poo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

/** 
 * Permet de représenter un membre.
 * Chaques membres possèdent les attributs suivant : id, name, maxBooks, registrationDate et books.
 * 
 * @author Gaëtan
 * @version 0.1
 * @see Book
 * */
public class Person {
	/**
	 * L'identifiant unique universelle du membre
	 */
	protected UUID id;
	/**
	 * Le nom du membre
	 */
	private String name;
	/**
	 * Le nombre maximum de livres que le membre peut emprunter
	 */
	private byte maxBooks;
	/**
	 * La date d'inscription du membre
	 */
	private LocalDate registrationDate;
	/**
	 * Liste des livres empruntés par le membre
	 */
	private ArrayList<Book> books;
	
	/**
	 * Création d'un membre en spécifiant son id et son nom
	 * Les attributs maxBooks, registrationDate et books sont définis dans le corps de la méthode.
	 * 
	 * @param id L'identifiant unique universelle du membre
	 * @param name Le nom du membre
	 */
	public Person(UUID id, String name) {
		this.id = id;
		this.name = name;
		this.maxBooks = 3;
		this.registrationDate = LocalDate.now();
		this.books = new ArrayList<Book>();
	}

	/**
	 * Renvoie le nom du membre
	 * @return Le nom du membre
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom du membre
	 * @param name Nouveau nom du membre
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Renvoie le nombre maximum de livres que le membre peut emprunter
	 * @return Le nombre maximum de livres que le membre peut emprunter
	 */
	public byte getMaxBooks() {
		return maxBooks;
	}

	/**
	 * Modifie le nombre maximum de livres que le membre peut emprunter
	 * @param maxBooks Nouveau nombre maximum de livres que le membre peut emprunter
	 */
	public void setMaxBooks(byte maxBooks) {
		this.maxBooks = maxBooks;
	}

	/**
	 * Renvoie l'identifiant unique universelle du membre
	 * @return L'identifiant unique universelle du membre
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * Renvoie la date d'inscription du membre
	 * @return La date d'inscription du membre
	 */
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	/**
	 * Renvoie la liste des livres empruntés par le membre
	 * @return La liste des livres empruntés par le membre
	 */
	public ArrayList<Book> getBooks() {
		return books;
	}

	/**
	 * Renvoie une chaine de caratère constituer de l'id, du nom, du nombre maximum de livres empruntable,
	 * la date d'inscription et le nombre de livres empruntés
	 */
	@Override
	public String toString() {
		final int maxLen = 3;
		return "Person [" + id + ": " + name + " (maxBooks=" + maxBooks + ", inscrit le "
				+ registrationDate + ")\n books="
				+ (books != null ? books.subList(0, Math.min(books.size(), maxLen)) : null) + "]";
	}
	
	/**
	 * Ajoute un livre à la liste de livres empruntés du membre
	 * Modifie les attributs borrower et borrowing date du livre emprunté
	 * @param book Le livre à emprunté
	 */
	public void borrows(Book book) {
		this.books.add(book);
		book.setBorrower(this);
		book.borrowingDate = LocalDate.now();
	}

	/**
	 * Supprime un livre de la liste de livres empruntés du membre
	 * Modifie les attributs borrower et borrowing date du livre restitué
	 * @param book Le livre à restitué
	 */
	public void returns(Book book) {
		this.books.remove(book);
		book.setBorrower(null);
		book.borrowingDate = null;
	}
	
}
	

