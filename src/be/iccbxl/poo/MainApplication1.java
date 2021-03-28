package be.iccbxl.poo;

import java.util.Scanner;

public class MainApplication1 {

	public static void mainMenu () {
		String[] menuP = {"1. Ajouter un membre","2. Ajouter un livre","3. Emprunter un livre","4. Afficher les statistiques","0. Quitter"};
		for(String a : menuP) {
			System.out.println(a);
		}
	}
	
	
	public static int onlineBookNb(String[][] livres) {
		int cpt = 0;
		for(int i=0; i<livres.length;i++) {
			if(livres[i][1].equals("OnlineBook")) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public static int graphicNovelNb(String[][] livres) {
		int cpt = 0;
		for(int i=0; i<livres.length;i++) {
			if(livres[i][1].equals("GraphicNovel")) {
				cpt++;
			}
		}
		return cpt;
	}
	
	public static int borrowersNb(String[][] membres) {
		int cpt = 0;
		for(int i=0; i<membres.length;i++) {
			if(Integer.parseInt(membres[i][1]) != 0) {
				cpt++;
			}
		}
		return cpt;
	}
	
	
	public static int borrowedBooksNb(String[][] membres) {
		int nb = 0;
		for(int i=0; i<membres.length;i++) {
			nb += Integer.parseInt(membres[i][1]);
		}
		return nb;
	}
	
//	public static int overdueBooksNb(String [][] livres) {
//		int nb;
//		return nb
//	}
//	
	public static void main(String[] args) {
		
		//name - borrowedBookNb -registrationDate
		String[][] membres = new String[3][3];
		membres[0][0] = "John";
		membres[0][1] = "2";
		membres[0][2] = "2021-01-05";
		membres[1][0] = "Jacob";
		membres[1][1] = "1";
		membres[1][2] = "2021-01-25";
		membres[2][0] = "Louise";
		membres[2][1] = "0";
		membres[2][2] = "2021-02-15";
		
		//title - bookType - loanPeriod - borrowingDate
		String[][] livres = new String[5][4];
		livres[0][0] = "Dracula";
		livres[0][1] = "OnlineBook";
		livres[0][2] = "";
		livres[0][3] = "2021-02-15";
		livres[1][0] = "Frankenstein";
		livres[1][1] = "OnlineBook";
		livres[1][2] = "";
		livres[1][3] = "2021-02-27";
		livres[2][0] = "Batman";
		livres[2][1] = "GraphicNovel";
		livres[2][2] = "";
		livres[2][3] = "2021-02-20";
		livres[3][0] = "Sin City";
		livres[3][1] = "GraphicNovel";
		livres[3][2] = "";
		livres[3][3] = "2021-03-01";
		livres[4][0] = "Watchmen";
		livres[4][1] = "GraphicNovel";
		livres[4][2] = "";
		livres[4][3] = "2021-02-03";
		
		
		int number;
		Scanner s = new Scanner(System.in);
		
		
		do {
			mainMenu();
			System.out.println("Faite votre choix : ");
			do {
				try {
					number = Integer.parseInt(s.nextLine());
					if(number<0 || number>4) {
						System.out.println("Veuiller faire un choix entre 0 et 4");
						number = -99;
					}
				} catch (NumberFormatException e){
					System.out.println("Veuillez entre un nombre entier !");
					number = -99;
				}
			
			} while (number == -99);
			
			switch(number) {
				case 0 :
					System.out.println("Application terminée");
					s.close();
					break;
				case 1 :
					break;
				case 2 :
					break;
				case 3 :
					break;
				case 4 :
					System.out.println("Nombre total de livres et membres : "+livres.length+" et "+membres.length);
					System.out.println("Nombre de livres électroniques et de romans graphiques : "+onlineBookNb(livres)+" et "+graphicNovelNb(livres));
					System.out.println("Nombres de membres qui ont un livre en emprunt : "+borrowersNb(membres));
					System.out.println("Nombre de livres empruntés : "+borrowedBooksNb(membres));
//					System.out.println("nombre de livres en retard : ");
					break;
				//default:
			}		

			
		} while (number !=0);
		
		
	}

}