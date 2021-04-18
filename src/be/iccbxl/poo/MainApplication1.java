package be.iccbxl.poo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApplication1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static MyLibrary myLibrary = new MyLibrary("test");
	
	public static void main(String[] args) {
		
		//load les 2 fichiers
		myLibrary.loadAll();

		int number;
		
		do {
			mainMenu();
			System.out.println("Faite votre choix : ");
			do {
				try {
					number = Integer.parseInt(br.readLine());
					if(number<0 || number>4) {
						System.out.println("Veuiller faire un choix entre 0 et 4");
						number = -99;
					}
				} catch (NumberFormatException e){
					System.out.println("Veuillez entre un nombre entier !");
					number = -99;
				} catch (IOException e) {
					number = -99;
				}
			
			} while (number == -99);
			
			switch(number) {
				case 0 :
					System.out.println("Application terminée");
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				// pour sauvegarder avant de quitter
//				myLibrary.saveBooks("data/books.csv");
//				myLibrary.saveMembers("data/members.csv");
					break;
				case 1 :
					addNewMember();
					break;
				case 2 :
					System.out.println(myLibrary.getPeople());
					break;
				case 3 :
					break;
				case 4 :
					System.out.println("Nombre total de livres et membres : "+myLibrary.getBooksNb()+" et "+myLibrary.getMembersNb());
					System.out.println("Nombre de livres électroniques et de romans graphiques : "+myLibrary.getOnlineBooksNb()+" et "+myLibrary.getGraphicNovelNb());
					System.out.println("Nombres de membres qui ont un livre en emprunt : "+myLibrary.getBorrowersNb());
					System.out.println("Nombre de livres empruntés : "+myLibrary.getBorrowedBooksNb());
					System.out.println("nombre de livres en retard : "+myLibrary.getOverdueBooksNb());
					break;
				//default:
			}		

			
		} while (number !=0);
		
		
	}
	
	//afficher le menu 
	public static void mainMenu () {
		String[] menuP = {"1. Ajouter un membre","2. Ajouter un livre","3. Emprunter un livre","4. Afficher les statistiques","0. Quitter"};
		for(String a : menuP) {
			System.out.println(a);
		}
	}
	
	// ajouter un nouveau membre
	 public static void addNewMember() {

		 String nom;
		 Byte maxBooksNb;
		 Byte choix;
		 
		 System.out.println("Veuillez entrer le nom du membre");
		 do {
			 try {
				nom = br.readLine();
				if(nom.isEmpty()) {
					 System.out.println("Le nom ne peut pas être vide");
					 nom = null;
				 } else if(nom.isBlank()) {
					 System.out.println("Le nom ne peut être uniquement constitué d'espace");
					 nom = null;
				 }
			} catch (IOException e) {
				System.out.println("Une erreur est survenue.");
				nom = null;
			}
			 
			 
		 } while (nom == null);
		 
		 System.out.println("Quel est le nombre de livres maximum que le membre peut emprunter ?");
		 do {
				try {
					maxBooksNb = Byte.parseByte(br.readLine());
					if(maxBooksNb<0) {
						System.out.println("le nombre de livres maximum ne peut pas être négatif");
						maxBooksNb = -1;
					}
				} catch (NumberFormatException e){
					System.out.println("Veuillez entre un nombre entier !");
					maxBooksNb = -1;
				} catch (IOException e) {
					System.out.println("Une erreur est survenue.");
					maxBooksNb = -1;
				}
			
			} while (maxBooksNb == -1);
		 
		 
		 Person p = new Person(java.util.UUID.randomUUID(),nom);
		 p.setMaxBooks(maxBooksNb);
		 
		 System.out.println("Veuillez confirmer la création du membre :");
		 System.out.println(p);
		 System.out.println("Entrez 1 pour confirmer ou 2 pour annuler ");
		 do {
				try {
					choix = Byte.parseByte(br.readLine());
					if(choix == 1) {
						myLibrary.addPerson(p);
						System.out.println("Le membre a bien été créé !");
					} else if(choix == 2) {
						System.out.println("Annulation de la création");
					} else {
						System.out.println("Seul 1 ou 2 est accepter");
						choix = -1;
					}
				} catch (NumberFormatException e){
					System.out.println("Veuillez entre un nombre entier !");
					choix = -1;
				} catch (IOException e) {
					System.out.println("Une erreur est survenue.");
					choix = -1;
				}
			
			} while (choix == -1);
		 	 
		
	 }

}