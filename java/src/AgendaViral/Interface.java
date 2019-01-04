package AgendaViral;

import java.util.Iterator;
import java.util.Scanner;

import org.overture.codegen.runtime.SetUtil;
import org.overture.codegen.runtime.VDMSet;

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
		Event event1 = new Event("Twenty One Pilots", "Concertos", new Event.Date(17L, 3L, 2019L),
				new Event.Date(17L, 3L, 2019L), "The Bandito Tour, dia 17 de Marco, na Altice Arena.", 42L, "Lisboa",
				20000L);
		Event event2 = new Event("EXO", "Concertos", new Event.Date(20L, 5L, 2019L), new Event.Date(20L, 5L, 2019L),
				"Estreia em Portugal dia 20 de Maio, na Altice Arena.", 30L, "Lisboa", 20000L);
		Event event3 = new Event("Aberturas: Tom Emerson em conversa com o arquivo Alvaro Siza", "Exposicoes",
				new Event.Date(6L, 1L, 2019L), new Event.Date(6L, 2L, 2019L),
				"Visita orientada a exposicao por Matilde Seabra, educadora. Localizacao: Biblioteca de Serralves", 2.5,
				"Porto", 200L);
		Event event4 = new Event("Brunch Mercearia Bio", "Gastronomia", new Event.Date(5L, 1L, 2019L),
				new Event.Date(5L, 1L, 2019L),
				"Ir as compras e aproveitar para tomar um pequeno-almoco reforcado ou antecipar a hora do almoco e a proposta do nosso Brunch, servido entre as 11h e as 16h.",
				7.8, "Cascais", 30L);
		Event event5 = new Event("Porto VS Belenenses", "Desporto", new Event.Date(30L, 1L, 2019L),
				new Event.Date(30L, 1L, 2019L), "Lorem ipsum.", 35L, "Porto", 50000L);
		Event event6 = new Event("Leixoes VS Famalicao", "Desporto", new Event.Date(7L, 4L, 2020L),
				new Event.Date(7L, 4L, 2020L), "Lorem ipsum.", 12.5, "Matosinhos", 2000L);
		Event proposed1 = new Event("Workshop Comida Saudavel daTerra", "Gastronomia", new Event.Date(15L, 7L, 2019L),
				new Event.Date(15L, 7L, 2019L), "Workshop de comida saudavel, daTerra baixa, 15h.", 5L, "Porto", 20L);
		Event proposed2 = new Event("Cozinhar Nunca Foi Facil", "Gastronomia", new Event.Date(20L, 12L, 2019L),
				new Event.Date(20L, 12L, 2019L), "Lorem.", 10L, "Lisboa", 35L);

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

		agenda.login("carlos@gmail.com", "carlos12345");
		agenda.buyTicket(2, 5);

		agenda.login("sofia@gmail.com", "sofia12345");
		agenda.proposeEvent(proposed1);
		agenda.proposeEvent(proposed2);
		agenda.buyTicket(1, 5);

	}

	public void loginMenu() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|                   Login                   |");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > Email: ");
		String email = scanner.nextLine();

		System.out.print(" > Password: ");
		String password = scanner.nextLine();

		System.out.println("");

		if (agenda.login(email, password))
			mainMenu();
		else
			System.out.println(" > Error: login failed");
	}

	public void mainMenu() {
		if (agenda.loggedInUser.isAdmin())
			mainMenuAdmin();
		else
			mainMenuRegular();
	}

	/*
	 * ADMIN
	 */

	public void mainMenuAdmin() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|                AGENDA VIRAL               |");
		System.out.println(" ------------------------------------------- ");
		System.out.println(" 1.  Add Event                    0.  Logout");
		System.out.println(" 2.  Proposed Events");
		System.out.println(" 3.  Find by District");
		System.out.println(" 4.  Find by City");
		System.out.println(" 5.  Find by Category");
		System.out.println(" 6.  Find by Date");
		System.out.println(" 7.  Find by Multiple Filters");
		System.out.println(" 8.  Most Popular Event");
		System.out.println(" 9.  Most Profitable Event");
		System.out.println(" 10. Most Active User");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > Option: ");
		int option = scanner.nextInt();
		scanner.nextLine();

		System.out.println("");

		switch (option) {
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
			mostPopularMenu();
			break;
		case 9:
			mostProfitableMenu();
			break;
		case 10:
			mostActiveMenu();
			break;
		case 0:
			loginMenu();
			break;
		default:
			loginMenu();
			break;
		}
	}

	public void addEventMenu() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|                 Add Event                 |");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > Title: ");
		String title = scanner.nextLine();

		System.out.print(" > Category: ");
		String category = scanner.nextLine();

		System.out.print(" > Starting Date[dd/mm/yy]: ");
		String startDate = scanner.nextLine();
		String[] part1 = startDate.split("/");

		int day1 = Integer.parseInt(part1[0]);
		int month1 = Integer.parseInt(part1[1]);
		int year1 = Integer.parseInt(part1[2]);

		System.out.print(" > Ending Date[dd/mm/yy]: ");
		String endDate = scanner.nextLine();
		String[] part2 = endDate.split("/");

		int day2 = Integer.parseInt(part2[0]);
		int month2 = Integer.parseInt(part2[1]);
		int year2 = Integer.parseInt(part2[2]);

		System.out.print(" > Description: ");
		String description = scanner.nextLine();

		System.out.print(" > Price: ");
		int price = scanner.nextInt();
		scanner.nextLine();

		System.out.print(" > City: ");
		String city = scanner.nextLine();

		System.out.print(" > Total Tickets: ");
		int tickets = scanner.nextInt();
		scanner.nextLine();

		Event event = new Event(title, category, new Event.Date(day1, month1, year1),
				new Event.Date(day2, month2, year2), description, price, city, tickets);

		agenda.addEvent(event);

		mainMenuAdmin();
	}

	public void proposedEventsMenu() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|               Proposed Events             |");
		System.out.println(" ------------------------------------------- ");
		for (Iterator iter = agenda.proposedEvents.iterator(); iter.hasNext();) {
			Event event = (Event) iter.next();
			System.out.println(" Id: " + event.getID());
			System.out.println(" Title: " + event.getTitle());

			if (iter.hasNext())
				System.out.println("");
		}

		if (agenda.proposedEvents.isEmpty())
			System.out.println("              No proposed events             ");

		System.out.println(" ------------------------------------------- ");
		System.out.println("                                   0. Return ");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > Event Id: ");
		int option = scanner.nextInt();
		scanner.nextLine();

		System.out.println("");

		if (option == 0) {
			mainMenu();
		}

		for (Iterator iter = agenda.proposedEvents.iterator(); iter.hasNext();) {
			Event event = (Event) iter.next();

			if (option == event.getID().intValue()) {
				proposedEventMenu(event);
			}
		}

		mainMenuAdmin();
	}

	public void proposedEventMenu(Event event) {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|               Proposed Event             |");
		System.out.println(" ------------------------------------------- ");

		System.out.println(" Id: " + event.getID());
		System.out.println(" Title: " + event.getTitle());
		System.out.println(" Category: " + event.getCategory());
		System.out.println(" City: " + event.getCity());
		System.out.println(" Date: from " + event.getDateStart().day + "/" + event.getDateStart().month + "/"
				+ event.getDateStart().year + " to " + event.getDateEnd().day + "/" + event.getDateEnd().month + "/"
				+ event.getDateEnd().year);
		System.out.println(" Price: " + event.getPrice() + " euros");
		System.out.println(" Total Tickets: " + event.getTotalTickets() + " | Sold Tickets: " + event.getSoldTickets());
		System.out.println(" Descriprion: " + event.getDescription());

		System.out.println(" ------------------------------------------- ");

		System.out.print(" > Accept or Reject (A/R): ");
		String action = scanner.nextLine();

		System.out.println("");

		if (action.equals("A"))
			agenda.acceptProposedEvent(event);
		else if (action.equals("R"))
			agenda.rejectProposedEvent(event);

		mainMenuAdmin();
	}

	public void mostPopularMenu() {
		Event event = agenda.mostPopularEvent();

		System.out.println(" ------------------------------------------- ");
		System.out.println("|             Most Popular Event            |");
		System.out.println(" ------------------------------------------- ");

		System.out.println(" Id: " + event.getID());
		System.out.println(" Title: " + event.getTitle());
		System.out.println(" Category: " + event.getCategory());
		System.out.println(" City: " + event.getCity());
		System.out.println(" Date: from " + event.getDateStart().day + "/" + event.getDateStart().month + "/"
				+ event.getDateStart().year + " to " + event.getDateEnd().day + "/" + event.getDateEnd().month + "/"
				+ event.getDateEnd().year);
		System.out.println(" Price: " + event.getPrice() + " euros");
		System.out.println(" Total Tickets: " + event.getTotalTickets() + " | Sold Tickets: " + event.getSoldTickets());
		System.out.println(" Descriprion: " + event.getDescription());

		System.out.println(" ------------------------------------------- ");

		System.out.println(" > Press Enter to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
		mainMenuAdmin();
	}

	public void mostProfitableMenu() {
		Event event = agenda.mostProfitableEvent();

		System.out.println(" ------------------------------------------- ");
		System.out.println("|           Most Profitable Event           |");
		System.out.println(" ------------------------------------------- ");

		System.out.println(" Id: " + event.getID());
		System.out.println(" Title: " + event.getTitle());
		System.out.println(" Category: " + event.getCategory());
		System.out.println(" City: " + event.getCity());
		System.out.println(" Date: from " + event.getDateStart().day + "/" + event.getDateStart().month + "/"
				+ event.getDateStart().year + " to " + event.getDateEnd().day + "/" + event.getDateEnd().month + "/"
				+ event.getDateEnd().year);
		System.out.println(" Price: " + event.getPrice() + " euros");
		System.out.println(" Total Tickets: " + event.getTotalTickets() + " | Sold Tickets: " + event.getSoldTickets());
		System.out.println(" Descriprion: " + event.getDescription());

		System.out.println(" ------------------------------------------- ");

		System.out.println(" > Press Enter to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
		mainMenuAdmin();
	}

	public void mostActiveMenu() {
		User user = agenda.mostActiveUser();

		System.out.println(" ------------------------------------------- ");
		System.out.println("|               Most Active User            |");
		System.out.println(" ------------------------------------------- ");

		System.out.println(" Email: " + user.getEmail());
		System.out.println(" No. Bought Tickets: " + user.getTicketsBought());

		System.out.println(" ------------------------------------------- ");

		System.out.println(" > Press Enter to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
		}
		mainMenuAdmin();
	}

	/*
	 * REGULAR USER
	 */

	public void mainMenuRegular() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|                AGENDA VIRAL               |");
		System.out.println(" ------------------------------------------- ");
		System.out.println(" 1.  Propose Event                0.  Logout");
		System.out.println(" 2.  Find by District");
		System.out.println(" 3.  Find by City");
		System.out.println(" 4.  Find by Category");
		System.out.println(" 5.  Find by Date");
		System.out.println(" 6.  Find by Multiple Filters");
		System.out.println(" ------------------------------------------- ");
		System.out.println(" ! Balance: " + agenda.loggedInUser.getBalance() + " euros");

		System.out.print(" > Option: ");
		int option = scanner.nextInt();
		scanner.nextLine();

		System.out.println("");

		switch (option) {
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

	public void proposeEventMenu() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|               Propose Event               |");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > Title: ");
		String title = scanner.nextLine();

		System.out.print(" > Category: ");
		String category = scanner.nextLine();

		System.out.print(" > Starting Date[dd/mm/yy]: ");
		String startDate = scanner.nextLine();
		String[] part1 = startDate.split("/");

		int day1 = Integer.parseInt(part1[0]);
		int month1 = Integer.parseInt(part1[1]);
		int year1 = Integer.parseInt(part1[2]);

		System.out.print(" > Ending Date[dd/mm/yy]: ");
		String endDate = scanner.nextLine();
		String[] part2 = endDate.split("/");

		int day2 = Integer.parseInt(part2[0]);
		int month2 = Integer.parseInt(part2[1]);
		int year2 = Integer.parseInt(part2[2]);

		System.out.print(" > Description: ");
		String description = scanner.nextLine();

		System.out.print(" > Price: ");
		int price = scanner.nextInt();
		scanner.nextLine();

		System.out.print(" > City: ");
		String city = scanner.nextLine();

		System.out.print(" > Total Tickets: ");
		int tickets = scanner.nextInt();
		scanner.nextLine();

		Event event = new Event(title, category, new Event.Date(day1, month1, year1),
				new Event.Date(day2, month2, year2), description, price, city, tickets);

		agenda.proposeEvent(event);

		mainMenuRegular();
	}

	public void findByDistrictMenu() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|              Find By District             |");
		System.out.println(" ------------------------------------------- ");
		System.out.println(" 1.  Porto                         0. Return");
		System.out.println(" 2.  Lisboa");
		System.out.println(" 3.  Faro");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > District: ");
		int option = scanner.nextInt();
		scanner.nextLine();

		System.out.println("");

		VDMSet events = SetUtil.set();

		switch (option) {
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

		foundEventsMenu(events);
	}

	public void findByCityMenu() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|                Find By City               |");
		System.out.println(" ------------------------------------------- ");
		System.out.println(" - PORTO                           0. Return");
		System.out.println(" 1.  Porto");
		System.out.println(" 2.  Matosinhos");
		System.out.println(" 3.  Maia");
		System.out.println(" 4.  Vila Nova de Gaia");
		System.out.println(" - LISBOA");
		System.out.println(" 5.  Lisboa");
		System.out.println(" 6.  Amadora");
		System.out.println(" 7.  Cascais");
		System.out.println(" 8.  Sintra");
		System.out.println(" - FARO");
		System.out.println(" 9.  Faro");
		System.out.println(" 10. Albufeira");
		System.out.println(" 11. Portimao");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > City: ");
		int option = scanner.nextInt();
		scanner.nextLine();

		System.out.println("");

		VDMSet events = SetUtil.set();
		switch (option) {
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

		foundEventsMenu(events);
	}

	public void findByCategoryMenu() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|              Find By Category             |");
		System.out.println(" ------------------------------------------- ");
		System.out.println(" 1.  Concertos                     0. Return");
		System.out.println(" 2.  Exposicoes");
		System.out.println(" 3.  Gastronomia");
		System.out.println(" 4.  Moda");
		System.out.println(" 5.  Desporto");
		System.out.println(" 6.  Natureza");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > Category: ");
		int option = scanner.nextInt();
		scanner.nextLine();

		System.out.println("");

		VDMSet events = SetUtil.set();

		switch (option) {
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

		foundEventsMenu(events);
	}

	public void findByDateMenu() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|                Find By Date               |");
		System.out.println(" ------------------------------------------- ");
		System.out.println("                                0. Main Menu");

		System.out.print(" > Date[dd/mm/yy]: ");
		String date = scanner.nextLine();
		System.out.println("");

		if (date.equals("0")) {
			mainMenu();
		}

		String[] parts = date.split("/");

		int day = Integer.parseInt(parts[0]);
		int month = Integer.parseInt(parts[1]);
		int year = Integer.parseInt(parts[2]);

		VDMSet events = agenda.findByDate(new Event.Date(day, month, year));

		foundEventsMenu(events);
	}

	public void findByMultipleFiltersMenu() {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|                 Find Event                |");
		System.out.println(" ------------------------------------------- ");
		System.out.println(" - CITIES");
		System.out.println(" Porto, Matosinhos, Maia, Vila Nova de Gaia");
		System.out.println(" Lisboa, Amadora, Cascais, Sintra");
		System.out.println(" Faro, Albufeira, Portimao");
		System.out.println("");
		System.out.println(" - DISTRICTS");
		System.out.println(" Porto, Lisboa, Faro");
		System.out.println("");
		System.out.println(" - CATEGORIES");
		System.out.println(" Concertos, Exposicoes, Gastronomia, Moda");
		System.out.println(" Desporto, Natureza");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > City: ");
		String city = scanner.nextLine();

		String district = "";
		if (city.equals("")) {
			System.out.print(" > District: ");
			district = scanner.nextLine();
		}

		System.out.print(" > Category: ");
		String category = scanner.nextLine();

		System.out.print(" > Date[dd/mm/yy]: ");
		String date = scanner.nextLine();

		VDMSet events = null;

		if (date.equals("")) {
			events = agenda.findEvents(city, district, category, null);
		} else {
			String[] parts = date.split("/");

			int day = Integer.parseInt(parts[0]);
			int month = Integer.parseInt(parts[1]);
			int year = Integer.parseInt(parts[2]);
			events = agenda.findEvents(city, district, category, new Event.Date(day, month, year));
		}

		foundEventsMenu(events);
	}

	public void foundEventsMenu(VDMSet events) {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|                 Found Events              |");
		System.out.println(" ------------------------------------------- ");
		for (Iterator iter = events.iterator(); iter.hasNext();) {
			Event event = (Event) iter.next();
			System.out.println(" Id: " + event.getID());
			System.out.println(" Title: " + event.getTitle());

			if (iter.hasNext())
				System.out.println("");
		}

		if (events.isEmpty())
			System.out.println("                No events                ");

		System.out.println(" ------------------------------------------- ");
		System.out.println("                                   0. Return ");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > Event Id: ");
		int option = scanner.nextInt();
		scanner.nextLine();

		System.out.println("");

		if (option == 0) {
			mainMenu();
		}

		for (Iterator iter = events.iterator(); iter.hasNext();) {
			Event event = (Event) iter.next();

			if (option == event.getID().intValue()) {
				foundEventMenu(event, events);
			}
		}
	}

	public void foundEventMenu(Event event, VDMSet events) {
		System.out.println(" ------------------------------------------- ");
		System.out.println("|                   Event                   |");
		System.out.println(" ------------------------------------------- ");

		System.out.println(" Id: " + event.getID());
		System.out.println(" Title: " + event.getTitle());
		System.out.println(" Category: " + event.getCategory());
		System.out.println(" City: " + event.getCity());
		System.out.println(" Date: from " + event.getDateStart().day + "/" + event.getDateStart().month + "/"
				+ event.getDateStart().year + " to " + event.getDateEnd().day + "/" + event.getDateEnd().month + "/"
				+ event.getDateEnd().year);
		System.out.println(" Price: " + event.getPrice() + " euros");
		System.out.println(" Total Tickets: " + event.getTotalTickets() + " | Sold Tickets: " + event.getSoldTickets());
		System.out.println(" Descriprion: " + event.getDescription());

		System.out.println(" ------------------------------------------- ");
		if (!agenda.loggedInUser.isAdmin())
			System.out.println(" 1.  Buy Tickets                    0. Return");
		else
			System.out.println("                                   0. Return ");
		System.out.println(" ------------------------------------------- ");

		System.out.print(" > Option: ");
		int option = scanner.nextInt();
		scanner.nextLine();

		System.out.println("");

		if (option == 1) {
			System.out.print(" > Number of Tickets: ");
			int nTickets = scanner.nextInt();
			scanner.nextLine();
			boolean res = agenda.buyTicket(event.getID(), nTickets);
			if (res)
				System.out.println("Tickets bought with success!");
			else
				System.out.println("Error buying tickets!");
			mainMenu();
		} else {
			foundEventsMenu(events);
		}

	}

}
