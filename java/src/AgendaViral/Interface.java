package AgendaViral;
import java.util.Scanner;

public class Interface {
    private Agenda agenda;
    Scanner scanner = new Scanner(System.in);
    
	public Interface(Agenda agenda) {
		this.agenda = agenda;
		
		Admin userAdmin = new Admin("julieta@gmail.com", "julieta12345");
		Regular user1 = new Regular("sofia@gmail.com", "sofia12345", "Sofia", "Silva", 500L, 30L);
		Regular user2 = new Regular("bibi@gmail.com", "bibi12345", "Beatriz", "Baldaia", 200L, 5L);
		Regular user3 = new Regular("carlos@gmail.com", "carlos12345", "Carlos", "Freitas", 800L, 10L);
		Regular user4 = new Regular("vicente@gmail.com", "vicente12345", "Vicente", "Espinha", 100L, 50L);
		Event event1 = new Event("Twenty One Pilots","Concertos", new Event.Date(17L, 3L, 2019L), new Event.Date(17L, 3L, 2019L), "Twenty One Pilots, o aclamado duo norte-americano constituido por Tyler Joseph e Josh Dun. The Bandito Tour sera a digressÃ£o mundial de apresentacao do album, com a estreia da banda ao vivo em Portugal, dia 17 de Marco, na Altice Arena.",42L,"Lisboa",20000L);
		Event event2 = new Event("EXO","Concertos",new Event.Date(20L, 5L, 2019L),new Event.Date(20L, 5L, 2019L),"EXO e um grupo masculino sino-coreano de Seul. Estreia em Portugal dia 20 de Maio, na Altice Arena.",30L,"Lisboa",20000L);
		Event event3 = new Event("Aberturas: Tom Emerson em conversa com o arquivo Alvaro Siza","Exposicoes", new Event.Date(6L, 1L, 2019L),new Event.Date(6L, 2L, 2019L), "Visita orientada a exposicao por Matilde Seabra, educadora. Localizacao: Biblioteca de Serralves", 2.5, "Porto",200L);
		Event event4 = new Event("Brunch Mercearia Bio","Gastronomia",new Event.Date(5L, 1L, 2019L),new Event.Date(5L, 1L, 2019L),"Ir as compras e aproveitar para tomar um pequeno-almoco reforcado ou antecipar a hora do almoco e a proposta do nosso Brunch, servido entre as 11h e as 16h.", 7.8,"Cascais",30L);
		Event event5 = new Event("Porto VS Belenenses","Desporto",new Event.Date(30L, 1L, 2019L),new Event.Date(30L, 1L, 2019L),"Lorem ipsum.",35L,"Porto", 50000L);
		Event event6 = new Event("Leixoes VS Famalicao", "Desporto", new Event.Date(7L, 4L, 2020L), new Event.Date(7L, 4L, 2020L),"Lorem ipsum.", 12.5, "Matosinhos",2L);
		Event proposed1 = new Event("Workshop Comida Saudavel daTerra","Gastronomia", new Event.Date(15L, 7L, 2019L), new Event.Date(15L, 7L, 2019L), "Workshop de comida saudavel, daTerra baixa, 15h.", 5L,"Porto",20L);
		Event proposed2 = new Event("Cozinhar Nunca Foi Facil","Gastronomia",new Event.Date(20L, 12L, 2019L),new Event.Date(20L, 12L, 2019L),"Lorem.", 10L, "Lisboa", 35L);

		agenda.addUser(userAdmin);
		agenda.addUser(user1);
		agenda.addUser(user2);
		agenda.addUser(user3);
		agenda.addUser(user4);
		
		loginMenu();
	}
	
	public void loginMenu(){
		System.out.println("---------------------------------------------");
		System.out.println("                    Login");
		System.out.println("---------------------------------------------");
		System.out.println("Email: ");
		
		String email = scanner.nextLine();	
		System.out.println("Password: ");
		String password = scanner.nextLine();
		
		if(agenda.login(email, password)){
			if(agenda.loggedInUser.isAdmin())
				mainMenuAdmin();
			else
				mainMenuRegular();
		}
		else{
			System.out.println("Error!");
		}
		
		
	}
	
	public void mainMenuAdmin(){
		System.out.println("---------------------------------------------");
		System.out.println("            Welcome to Agenda Viral!");
		System.out.println("---------------------------------------------");
		System.out.println("1.  Add Event");
		System.out.println("2.  Proposed Events");
		System.out.println("3.  Find by District");
		System.out.println("4.  Find by City");
		System.out.println("5.  Find by Category");
		System.out.println("6.  Find by Date");
		System.out.println("7.  Find by Multiple Filters");
		System.out.println("8.  View Most Popular");
		System.out.println("9.  View Most Profitable");
		System.out.println("10. View Most Active User");
		System.out.println("11. Logout");
		System.out.println("");
		
		int option = scanner.nextInt();
		// Skip the newline
		scanner.nextLine();

		switch (option){
			case 1:
				addEventMenu();
				break;
			case 2:
				proposedEventsMenu();
				break;
			case 3:
				findByDistrictMenu();
				break;
			case 4:
				findByCityMenu();
				break;
			case 5:
				findByCategoryMenu();
				break;
			case 6:
				findByDateMenu();
				break;
			case 7:
				findByMultipleFiltersMenu();
				break;
			case 8:
				viewMostPopularMenu();
				break;
			case 9:
				viewMostProfitableMenu();
				break;
			case 10:
				viewMostActiveMenu();
				break;
			case 11:
				loginMenu();
				break;
			default:
				loginMenu();
				break;
		}
	}
	
	public void mainMenuRegular(){
		System.out.println("---------------------------------------------");
		System.out.println("            Welcome to Agenda Viral!");
		System.out.println("---------------------------------------------");
		System.out.println("1.  Propose Event");
		System.out.println("2.  Find by District");
		System.out.println("3.  Find by City");
		System.out.println("4.  Find by Category");
		System.out.println("5.  Find by Date");
		System.out.println("6.  Find by Multiple Filters");
		System.out.println("7.  Logout");
		System.out.println("");
		
		int option = scanner.nextInt();
		// Skip the newline
		scanner.nextLine();

		switch (option){
			case 1:
				proposeEventMenu();
				break;
			case 3:
				findByDistrictMenu();
				break;
			case 4:
				findByCityMenu();
				break;
			case 5:
				findByCategoryMenu();
				break;
			case 6:
				findByDateMenu();
				break;
			case 7:
				findByMultipleFiltersMenu();
				break;
			case 11:
				loginMenu();
				break;
			default:
				loginMenu();
				break;
		}
	}
	
	public void addEventMenu(){
		
	}
	
	public void proposedEventsMenu(){
		
	}
	
	public void proposeEventMenu(){
		
	}
	
	public void findByDistrictMenu(){
		
	}
	
	public void findByCityMenu(){
		
	}
	
	public void findByCategoryMenu(){
		
	}
	
	public void findByDateMenu(){
	
	}
	
	public void findByMultipleFiltersMenu(){
		
	}

	public void viewMostPopularMenu(){
		
	}

	public void viewMostProfitableMenu(){
		
	}

	public void viewMostActiveMenu(){
		
	}
	
	
	
	

}
