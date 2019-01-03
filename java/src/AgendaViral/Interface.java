package AgendaViral;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.overture.codegen.runtime.Maplet;
import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.Utils;
import org.overture.codegen.runtime.VDMSet;
import java.util.*;

import AgendaViral.Event.Date;

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
		Event event1 = new Event("Twenty One Pilots","Concertos", new Event.Date(17L, 3L, 2019L), new Event.Date(17L, 3L, 2019L), "Twenty One Pilots, o aclamado duo norte-americano constituido por Tyler Joseph e Josh Dun. The Bandito Tour sera a digressÃƒÂ£o mundial de apresentacao do album, com a estreia da banda ao vivo em Portugal, dia 17 de Marco, na Altice Arena.",42L,"Lisboa",20000L);
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
		
		agenda.login("julieta@gmail.com", "julieta12345");
		agenda.addEvent(event1);
		agenda.addEvent(event2);
		agenda.addEvent(event3);
		agenda.addEvent(event4);
		agenda.addEvent(event5);
		agenda.addEvent(event6);
		
		agenda.login("sofia@gmail.com", "sofia1235");
		agenda.proposeEvent(proposed1);
		agenda.proposeEvent(proposed2);
		
		loginMenu();
	}
	
	public void loginMenu(){
		System.out.println("---------------------------------------------");
		System.out.println("                    Login");
		System.out.println("---------------------------------------------");
		System.out.print("Email: ");
		String email = scanner.nextLine();	
		System.out.print("Password: ");
		String password = scanner.nextLine();
		
		if(agenda.login(email, password)){
			mainMenu();
		}
		else{
			System.out.println("Error!");
		}
		
		
	}
	
	public void mainMenu(){
		if(agenda.loggedInUser.isAdmin())
			mainMenuAdmin();
		else
			mainMenuRegular();
	}
	
	public void mainMenuAdmin(){
		System.out.println("---------------------------------------------");
		System.out.println("            Welcome to Agenda Viral!");
		System.out.println("---------------------------------------------");
		System.out.println("1.  Add Event                      0.  Logout");
		System.out.println("2.  Proposed Events");
		System.out.println("3.  Find by District");
		System.out.println("4.  Find by City");
		System.out.println("5.  Find by Category");
		System.out.println("6.  Find by Date");
		System.out.println("7.  Find by Multiple Filters");
		System.out.println("8.  View Most Popular");
		System.out.println("9.  View Most Profitable");
		System.out.println("10. View Most Active User");
		System.out.println("---------------------------------------------");
		System.out.print("Option: ");
		int option = scanner.nextInt();
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
			case 0:
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
		System.out.println("                Balance: " + agenda.loggedInUser.getBalance()+"€");
		System.out.println("---------------------------------------------");
		System.out.println("1.  Propose Event                  0.  Logout");
		System.out.println("2.  Find by District");
		System.out.println("3.  Find by City");
		System.out.println("4.  Find by Category");
		System.out.println("5.  Find by Date");
		System.out.println("6.  Find by Multiple Filters");
		System.out.println("---------------------------------------------");
		System.out.print("Option: ");
		int option = scanner.nextInt();
		scanner.nextLine();

		switch (option){
			case 1:
				proposeEventMenu();
				break;
			case 2:
				findByDistrictMenu();
				break;
			case 3:
				findByCityMenu();
				break;
			case 4:
				findByCategoryMenu();
				break;
			case 5:
				findByDateMenu();
				break;
			case 6:
				findByMultipleFiltersMenu();
				break;
			case 0:
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
		System.out.println("---------------------------------------------");
		System.out.println("              Find by District");
		System.out.println("---------------------------------------------");
		System.out.println("1.  Porto                         0.  Go Back");
		System.out.println("2.  Lisboa");
		System.out.println("3.  Faro");
		System.out.println("---------------------------------------------");
		System.out.print("District: ");
		int option = scanner.nextInt();
		scanner.nextLine();
		
		VDMSet events = SetUtil.set();
		
		switch (option){
		case 1:
			events = agenda.findByDistrict("Porto");
			break;
		case 2:
			events = agenda.findByDistrict("Lisboa");
			break;
		case 3:
			events = agenda.findByDistrict("Faro");
			break;
		case 0:
			mainMenu();
			break;
		default:
			mainMenu();
			break;
		}
		
		eventsMenu(events);
	}

	
	public void findByCityMenu(){
		System.out.println("---------------------------------------------");
		System.out.println("              Find by Category");
		System.out.println("---------------------------------------------");
		System.out.println("- Porto                           0.  Go Back");
		System.out.println("1.  Porto");
		System.out.println("2.  Matosinhos");
		System.out.println("3.  Maia");
		System.out.println("4.  Vila Nova de Gaia");
		System.out.println("- Lisboa");
		System.out.println("5.  Lisboa");
		System.out.println("6.  Amadora");
		System.out.println("7.  Cascais");
		System.out.println("8.  Sintra");
		System.out.println("- Faro");
		System.out.println("9.  Faro");
		System.out.println("10. Albufeira");
		System.out.println("11. Portimao");
		System.out.println("---------------------------------------------");
		System.out.print("Category: ");
		
		int option = scanner.nextInt();
		scanner.nextLine();
		
		VDMSet events = SetUtil.set();		
		switch (option){
		case 1:
			events = agenda.findByCity("Porto");
			break;
		case 2:
			events = agenda.findByCity("Matosinhos");
			break;
		case 3:
			events = agenda.findByCity("Maia");
			break;
		case 4:
			events = agenda.findByCity("Vila Nova de Gaia");
			break;
		case 5:
			events = agenda.findByCity("Lisboa");
			break;
		case 6:
			events = agenda.findByCity("Amadora");
			break;
		case 7:
			events = agenda.findByCity("Cascais");
			break;
		case 8:
			events = agenda.findByCity("Sintra");
			break;
		case 9:
			events = agenda.findByCity("Faro");
			break;
		case 10:
			events = agenda.findByCity("Albufeira");
			break;
		case 11:
			events = agenda.findByCity("Portimao");
			break;
		case 0:
			mainMenu();
			break;
		default:
			mainMenu();
			break;
		}
		
		eventsMenu(events);
	}
	
	public void findByCategoryMenu(){
		System.out.println("---------------------------------------------");
		System.out.println("              Find by Category");
		System.out.println("---------------------------------------------");
		System.out.println("1.  Concertos                     0.  Go Back");
		System.out.println("2.  Exposicoes");
		System.out.println("3.  Gastronomia");
		System.out.println("4.  Moda");
		System.out.println("5.  Desporto");
		System.out.println("6.  Natureza");
		System.out.println("---------------------------------------------");
		System.out.print("Category: ");
		
		int option = scanner.nextInt();
		scanner.nextLine();
		
		VDMSet events = SetUtil.set();
		
		switch (option){
		case 1:
			events = agenda.findByCategory("Concertos");
			break;
		case 2:
			events = agenda.findByCategory("Exposicoes");
			break;
		case 3:
			events = agenda.findByCategory("Gastronomia");
			break;
		case 4:
			events = agenda.findByCategory("Moda");
			break;
		case 5:
			events = agenda.findByCategory("Desporto");
			break;
		case 6:
			events = agenda.findByCategory("Natureza");
			break;
		case 0:
			mainMenu();
			break;
		default:
			mainMenu();
			break;
		}
		
		eventsMenu(events);
	}
	
	public void findByDateMenu(){
		System.out.println("---------------------------------------------");
		System.out.println("                Find by Date");
		System.out.println("---------------------------------------------");
		System.out.println("                                0.  Main Menu");
		System.out.print("Date[dd/mm/yy]: ");
		String date = scanner.nextLine();
		
		if(date.equals("0")){
			mainMenu();
		}
		
		String[] parts = date.split("/");
		
		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);
		
		VDMSet events = agenda.findByDate(new Event.Date(day, month, year));
		
		eventsMenu(events);
	}
	
	public void findByMultipleFiltersMenu(){ 
		System.out.println("---------------------------------------------");
		System.out.println("                Find by Date");
		System.out.println("---------------------------------------------");
		System.out.println("-Cities:");
		System.out.println("Porto, Matosinhos, Maia, Vila Nova de Gaia");
		System.out.println("Lisboa, Amadora, Cascais, Sintra");
		System.out.println("Faro, Albufeira, Portimao");
		System.out.println("");
		System.out.println("-Districts:");
		System.out.println("Porto, Lisboa, Faro");
		System.out.println("");
		System.out.println("-Categories: ");
		System.out.println("Concertos, Exposicoes, Gastronomia, Moda");
		System.out.println("Desporto, Natureza");
		System.out.println("---------------------------------------------");
		System.out.print("City: ");
		String city = scanner.nextLine();
		
		String district = "";
		if(city.equals("")){
			System.out.print("District: ");
			district = scanner.nextLine();
		}
		
		System.out.print("Category: ");
		String category = scanner.nextLine();
		
		System.out.print("Date[dd/mm/yy]: ");
		String date = scanner.nextLine();
		
		VDMSet events = null;
		
		if(date.equals("")){
			events = agenda.findEvents(city, district, category, null);
		}
		else{
			String[] parts = date.split("/");
			
			int day= Integer.parseInt(parts[0]);
			int month = Integer.parseInt(parts[1]);
			int year = Integer.parseInt(parts[2]);
			events = agenda.findEvents(city, district, category, new Event.Date(day, month, year));
		}

		eventsMenu(events);
	}

	public void viewMostPopularMenu(){
		
	}

	public void viewMostProfitableMenu(){
		
	}

	public void viewMostActiveMenu(){
		
	}
	
	public void eventsMenu(VDMSet events){
		System.out.println("---------------------------------------------");
		System.out.println("              	 Events");
		System.out.println("---------------------------------------------");  
		for (Iterator iter = events.iterator(); iter.hasNext(); ) {
	      Event event = (Event) iter.next();
	      System.out.println("ID: " + event.getID());
	      System.out.println("TITLE: " + event.getTitle());
	      System.out.println("CATEGOTY: " + event.getCategory());
	      System.out.println("CITY: " + event.getCity());
	      System.out.println("DATE START: " + event.getDateStart().day+"/"+ event.getDateStart().month+"/"+ event.getDateStart().year + " DATE END: " +event.getDateEnd().day+"/"+ event.getDateEnd().month+"/"+ event.getDateEnd().year);
	      System.out.println("PRICE: " + event.getPrice() +"€");
	      System.out.println("TOTAL TICKETS: " + event.getTotalTickets() + " SOLD TICKETS: " + event.getSoldTickets());
	      System.out.println("DESCRIPTION: " + event.getDescription());
	      System.out.println("");
	    }
		if(agenda.loggedInUser.isAdmin())
			System.out.println("                                0.  Main Menu");
		else
			System.out.println("1. Buy                          0.  Main Menu");
		
		System.out.println("---------------------------------------------");
		System.out.print("Option: ");
		
		int option = scanner.nextInt();
		scanner.nextLine();
		
		switch (option){
		case 1:
			System.out.print("Event ID: ");
			int eventID = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Number of Tickets: ");
			int nTickets = scanner.nextInt();
			scanner.nextLine();
			boolean res = agenda.buyTicket(eventID, nTickets);
			if(res)
				System.out.println("Tickets bought with success!");
			else
				System.out.println("Error buying tickets!");
			mainMenu();
			break;
		case 0:
			mainMenu();
			break;
		default:
			mainMenu();
			break;
		}
		
		eventsMenu(events);
	}

}
