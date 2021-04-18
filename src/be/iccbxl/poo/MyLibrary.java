package be.iccbxl.poo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;

/**
 * Représente la bibliothèque
 * Définie par son nom, la liste des membres et la liste des livres.
 *  
 * @author Gaêtan
 * @version 0.1
 * @see Book
 * @see Person
 */
public class MyLibrary {

	/**
	 * Nom de la bibliothèque
	 */
	private String name;
	
	/**
	 * Liste des livres
	 */
	private ArrayList<Book> books;
	
	/**
	 * Liste des membres
	 */
	private ArrayList<Person> people;
	
	
	
	/**
	 * Crée une bibliothèque en spécifiant son nom
	 * 
	 * @param name Nom de la bibliothèque
	 */
	public MyLibrary(String name) {
		this.name = name;
		this.books = new ArrayList<Book>();
		this.people = new ArrayList<Person>();
	}

	/**
	 * Renvoie le nom de la bibliothèque
	 * 
	 * @return Le nom de la bibliothèque
	 */
	public String getName() {
		return name;
	}

	/**
	 * Modifie le nom de la bibliothèque
	 * 
	 * @param name Nouveau nom de la bibliothèque
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Renvoie la liste des livres
	 * 
	 * @return la liste des livres
	 */
	public ArrayList<Book> getBooks() {
		return books;
	}

	/**
	 * Renvoie la liste des membres
	 * 
	 * @return la liste des membres
	 */
	public ArrayList<Person> getPeople() {
		return people;
	}


	@Override
	public String toString() {
		final int maxLen = 3;
		return "MyLibrary " + name + ": books="
				+ (books != null ? books.subList(0, Math.min(books.size(), maxLen)) : null) + ", people="
				+ (people != null ? people.subList(0, Math.min(people.size(), maxLen)) : null);
	}
	
	// la liste des livres en retard
	public ArrayList<Book> getAllLateBooks(){
		ArrayList<Book> lateBook = new ArrayList<Book>();
		for(int i=0; i<books.size();i++) {
			//si la date de retour est avant la date actuelle
			if ((books.get(i).getBorrowingDate().plusDays(books.get(i).getLoanPeriod()).isBefore(LocalDate.now()))){
				lateBook.add(books.get(i));
			}
		}
		return lateBook;
	}
	
	// trouver un membre avec son nom
	public Person findMemberByName(String name) {
		Person member = null;
		for(int i=0; i<people.size();i++) {
			if(people.get(i).getName() == name) {
				member = people.get(i);
			}
		}
		return member;
	
	}
	
	// ajouter un livre
	public void addBook(Book book) {
		this.books.add(book);
	}

	// ajouter un membre
	public void addPerson(Person person) {
		this.people.add(person);
	}
	
	// afficher les livres
	public int printBooks() {
		int cpt = 0;

		Iterator<Book> it = this.getBooks().iterator();
		
		while(it.hasNext()) {
			Book b = it.next();
								
			System.out.println(++cpt + ": " 
					+b.getTitle() + " - "
					+b.getAuthor());
		}
		return cpt;
	}

	// afficher les membres
	public int printMembers() {
		int cpt = 0;

		Iterator<Person> itp = this.getPeople().iterator();
		
		while(itp.hasNext()) {
			Person p = itp.next();
			
			System.out.println(++cpt + ": " + p.getName());
		}
		return cpt;
	}
	
	// problème : obliger de loadBooks en 1er pour add les books aux membres avec loadMembers
	public void loadAll() {
		loadBooks("data/books.csv");
		loadMembers("data/members.csv");
	}
	
	// Problème : pour récupérer la liste de books des membres
	public int loadMembers(String filename) {
		int cpt = 0;
		
		File f = new File(filename);
		
		if(f.exists()) {
			FileReader fr = null;
			BufferedReader br = null;
			String[] data = null;
			String[] dataBooks = null;
			
			try {
				try {
					fr = new FileReader(f);
					br = new BufferedReader(fr);

					String ligne;

					//Lire une ligne du fichier					
					while ((ligne = br.readLine()) != null) {
						//"a7aa0ae7-9ce3-44bc-a72a-894edb9a4653;Bob Smith;2;01-03-20"
						data = ligne.split(",");

						Person p = new Person(UUID.fromString(data[0]), data[1]);
						// modifier les valeurs maxBooks registrationDate books pour chaque Person p
						p.setMaxBooks(Byte.parseByte(data[2]));
						//This method throws DateTimeParseException if the text cannot be parsed
						p.setRegistrationDate(LocalDate.parse(data[3]));

						// parcourir books et si un objet a le même nom que la string
						// à l'index j du tableau dataBooks alors p.getBooks.add(books.get(i)) 
						if(data.length == 5) {
							dataBooks = data[4].split("//");
							
							for(int i = 0; i<books.size();i++) {
								for(int j=0; j<dataBooks.length;j++) {
									if(books.get(i).getTitle().matches(dataBooks[j])) {
										p.getBooks().add(books.get(i));
									}
								}
							}
						}
						
						//ajouter cette Person dans people
						this.people.add(p);
						cpt++;
					}
				} finally {
					br.close();
				}
			} catch (IOException e) {
				
			}
			
		} 
		
		return cpt;
	}
	
	//problème : pour récupérer les borrowers
	public int loadBooks(String filename) {
		int cpt = 0;
		
		File f = new File(filename);
		
		if(f.exists()) {
			FileReader fr = null;
			BufferedReader br = null;
			String[] data = null;
			
			try {
				try {
					fr = new FileReader(f);
					br = new BufferedReader(fr);

					String ligne;
					
					//Lire une ligne du fichier					
					while ((ligne = br.readLine()) != null) {
						data = ligne.split(",");

						Book b = new Book(data[0],data[1],Integer.parseInt(data[2]),data[3],data[4]);
						b.setRentalPrice(Double.parseDouble(data[5]));
						if(data[6].matches("null")) {
							
						} else {
							b.setBorrowingDate(LocalDate.parse(data[6]));
						}
						b.setLoanPeriod(Integer.parseInt(data[8]));
						//ajouter le book dans books
						this.books.add(b);
						cpt++;
						
					}
				} finally {
					br.close();
				}
			} catch (IOException e) {
				
			}
			
		} 
		
		return cpt;
	}
	
	// sauvegarde les membres dans un fichier
	public void saveMembers(String filename) {
		
		File f = new File(filename);
		if(f.exists()) {
			FileWriter fw = null;
			BufferedWriter bw = null;
			StringBuilder sb = null;
			String line = null;
			
			try {
				try {
					fw = new FileWriter(f);
					bw = new BufferedWriter(fw);
					sb = new StringBuilder();
					for(int i = 0; i<people.size();i++) {
						sb.append(people.get(i).getId());
						sb.append(",");
						sb.append(people.get(i).getName());
						sb.append(",");
						sb.append(people.get(i).getMaxBooks());
						sb.append(",");
						sb.append(people.get(i).getRegistrationDate());
						sb.append(",");
						for(int j=0; j<people.get(i).getBooks().size();j++) {
							sb.append(people.get(i).getBooks().get(j).getTitle());
							if(j<(people.get(i).getBooks().size()) -1)
							sb.append("//");
						}
						line = sb.toString();
						sb.setLength(0);
						bw.write(line);
						bw.newLine();
					}
					
					
				}finally {
					bw.close();
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
		
	}
	
	// sauvegarde les livres dans un fichier
	public void saveBooks(String filename) {
		File f = new File(filename);
		if(f.exists()) {
			FileWriter fw = null;
			BufferedWriter bw = null;
			StringBuilder sb = null;
			String line = null;
			
			try {
				try {
					fw = new FileWriter(f);
					bw = new BufferedWriter(fw);
					sb = new StringBuilder();
					for(int i = 0; i<books.size();i++) {
						sb.append(books.get(i).getTitle());
						sb.append(",");
						sb.append(books.get(i).getAuthor());
						sb.append(",");
						sb.append(books.get(i).getTotalPages());
						sb.append(",");
						sb.append(books.get(i).getLanguage());
						sb.append(",");
						sb.append(books.get(i).getBookType());
						sb.append(",");
						sb.append(books.get(i).getRentalPrice());
						sb.append(",");
						sb.append(books.get(i).getBorrowingDate());
						sb.append(",");
						if(books.get(i).getBorrower() != null) {
							sb.append(books.get(i).getBorrower().getName());
						} else {
							sb.append(books.get(i).getBorrower());
						}
						sb.append(",");
						sb.append(books.get(i).getLoanPeriod());
						line = sb.toString();
						sb.setLength(0);
						bw.write(line);
						bw.newLine();
					}
					
					
				}finally {
					bw.close();
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}
	}
	
	
	
	// Methods d'affichage des stats
	public int getBooksNb() {
		int cpt = 0;
		for(int i=0;i<this.getBooks().size();i++) {
			cpt++;
		}
		return cpt;
	}
	
	public int getMembersNb() {
		int cpt = 0;
		for(int i=0;i<this.getPeople().size();i++) {
			cpt++;
		}
		return cpt;
	}
	
	public int getOnlineBooksNb() {
		int cpt = 0;
		for(int i=0;i<this.getBooks().size();i++) {
			if(this.getBooks().get(i).getBookType().matches("OnlineBook")) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public int getGraphicNovelNb() {
		int cpt = 0;
		Iterator<Book> iterator = books.iterator();
		while(iterator.hasNext()){
			Book book = (Book) iterator.next();
			if(book.getBookType().matches("GraphicNovel")) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public int getBorrowersNb() {
		int cpt = 0;
		for(int i=0;i<this.getPeople().size();i++) {
			if(this.getPeople().get(i).getBooks().size() != 0) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public int getBorrowedBooksNb() {
		int cpt = 0;
		for(int i=0;i<people.size();i++) {
			cpt = cpt + people.get(i).getBooks().size();
		}
		return cpt;
	}
	
	public int getOverdueBooksNb() {
		int cpt = 0;
//		Book book = new Book(name, name, cpt, name, name);
		for(int i=0;i<books.size();i++) {
			if(books.get(i).getBorrowingDate() == null) {
				
			}else {
				//books.get(i) ou autre objet Book  pour lancer computeReturnDate
				if(books.get(i).computeReturnDate(books.get(i)).isBefore(LocalDate.now())) {
					cpt++;
				}
			}
		}
		return cpt;
	}
	
	
}
	

