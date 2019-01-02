package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class AgendaTest extends Tests {
  private Agenda agenda = new Agenda();
  private Admin userAdmin = new Admin("julieta@gmail.com", "julieta12345");
  private Regular user1 = new Regular("sofia@gmail.com", "sofia12345", "Sofia", "Silva", 500L, 30L);
  private Regular user2 =
      new Regular("bibi@gmail.com", "bibi12345", "Beatriz", "Baldaia", 200L, 5L);
  private Regular user3 =
      new Regular("carlos@gmail.com", "carlos12345", "Carlos", "Freitas", 800L, 10L);
  private Regular user4 =
      new Regular("vicente@gmail.com", "vicente12345", "Vicente", "Espinha", 100L, 50L);
  private Event event1 =
      new Event(
          "Twenty One Pilots",
          "Concertos",
          new Event.Date(17L, 3L, 2019L),
          new Event.Date(17L, 3L, 2019L),
          "Twenty One Pilots, o aclamado duo norte-americano constituido por Tyler Joseph e Josh Dun. The Bandito Tour sera a digressÃ£o mundial de apresentacao do album, com a estreia da banda ao vivo em Portugal, dia 17 de Marco, na Altice Arena.",
          42L,
          "Lisboa",
          20000L);
  private Event event2 =
      new Event(
          "EXO",
          "Concertos",
          new Event.Date(20L, 5L, 2019L),
          new Event.Date(20L, 5L, 2019L),
          "EXO e um grupo masculino sino-coreano de Seul. Estreia em Portugal dia 20 de Maio, na Altice Arena.",
          30L,
          "Lisboa",
          20000L);
  private Event event3 =
      new Event(
          "Aberturas: Tom Emerson em conversa com o arquivo Alvaro Siza",
          "Exposicoes",
          new Event.Date(6L, 1L, 2019L),
          new Event.Date(6L, 2L, 2019L),
          "Visita orientada a exposicao por Matilde Seabra, educadora. Localizacao: Biblioteca de Serralves",
          2.5,
          "Porto",
          200L);
  private Event event4 =
      new Event(
          "Brunch Mercearia Bio",
          "Gastronomia",
          new Event.Date(5L, 1L, 2019L),
          new Event.Date(5L, 1L, 2019L),
          "Ir as compras e aproveitar para tomar um pequeno-almoco reforcado ou antecipar a hora do almoco e a proposta do nosso Brunch, servido entre as 11h e as 16h.",
          7.8,
          "Cascais",
          30L);
  private Event event5 =
      new Event(
          "Porto VS Belenenses",
          "Desporto",
          new Event.Date(30L, 1L, 2019L),
          new Event.Date(30L, 1L, 2019L),
          "Lorem ipsum.",
          35L,
          "Porto",
          50000L);
  private Event event6 =
      new Event(
          "Leixoes VS Famalicao",
          "Desporto",
          new Event.Date(7L, 4L, 2020L),
          new Event.Date(7L, 4L, 2020L),
          "Lorem ipsum.",
          12.5,
          "Matosinhos",
          2L);
  private Event proposed1 =
      new Event(
          "Workshop Comida Saudavel daTerra",
          "Gastronomia",
          new Event.Date(15L, 7L, 2019L),
          new Event.Date(15L, 7L, 2019L),
          "Workshop de comida saudavel, daTerra baixa, 15h.",
          5L,
          "Porto",
          20L);
  private Event proposed2 =
      new Event(
          "Cozinhar Nunca Foi Facil",
          "Gastronomia",
          new Event.Date(20L, 12L, 2019L),
          new Event.Date(20L, 12L, 2019L),
          "Lorem.",
          10L,
          "Lisboa",
          35L);

  public void cg_init_AgendaTest_1() {

    return;
  }

  public AgendaTest() {

    cg_init_AgendaTest_1();
  }

  private void testCreateAgenda() {

    Agenda a = new Agenda();
    assertTrue(
        Utils.equals(
            a.categories,
            SetUtil.set("Concertos", "Exposicoes", "Gastronomia", "Moda", "Desporto", "Natureza")));
    assertTrue(
        Utils.equals(
            a.locations,
            MapUtil.map(
                new Maplet(
                    "Porto", SetUtil.set("Porto", "Matosinhos", "Maia", "Vila Nova de Gaia")),
                new Maplet("Lisboa", SetUtil.set("Lisboa", "Amadora", "Cascais", "Sintra")),
                new Maplet("Faro", SetUtil.set("Faro", "Albufeira", "Portimao")))));
    assertTrue(Utils.equals(a.loggedInUser, null));
  }

  private void testAddUser() {

    agenda.addUser(userAdmin);
    agenda.addUser(user1);
    agenda.addUser(user2);
    agenda.addUser(user3);
    agenda.addUser(user4);
    assertTrue(!(Utils.empty(agenda.users)));
  }

  private void testLoginAdmin() {

    Boolean outcome = false;
    agenda.loggedInUser = null;
    outcome = agenda.login("julieta@gmail.com", "person12345");
    assertTrue(Utils.equals(outcome, false));
    assertTrue(Utils.equals(agenda.loggedInUser, null));
    outcome = agenda.login("julieta@gmail.com", "julieta12345");
    assertTrue(Utils.equals(outcome, true));
    assertTrue(!(Utils.equals(agenda.loggedInUser, null)));
    assertTrue(Utils.equals(agenda.loggedInUser, userAdmin));
  }

  private void testAddEvent() {

    agenda.addEvent(event1);
    agenda.addEvent(event2);
    agenda.addEvent(event3);
    agenda.addEvent(event4);
    agenda.addEvent(event5);
    agenda.addEvent(event6);
    assertTrue(!(Utils.empty(agenda.events)));
    assertTrue(
        Utils.equals(agenda.events, SetUtil.set(event1, event2, event3, event4, event5, event6)));
  }

  private void testFindByCity() {

    VDMSet cityEvents = SetUtil.set();
    cityEvents = agenda.findByCity("Faro");
    assertTrue(Utils.empty(cityEvents));
    cityEvents = agenda.findByCity("Porto");
    assertTrue(!(Utils.empty(cityEvents)));
  }

  private void testFindByDistrict() {

    VDMSet districtEvents = SetUtil.set();
    districtEvents = agenda.findByDistrict("Faro");
    assertTrue(Utils.empty(districtEvents));
    districtEvents = agenda.findByDistrict("Braganca");
    assertTrue(Utils.empty(districtEvents));
    districtEvents = agenda.findByDistrict("Lisboa");
    assertTrue(!(Utils.empty(districtEvents)));
  }

  private void testFindByCategory() {

    VDMSet categoryEvents = SetUtil.set();
    categoryEvents = agenda.findByCategory("Moda");
    assertTrue(Utils.empty(categoryEvents));
    categoryEvents = agenda.findByCategory("Desporto");
    assertTrue(!(Utils.empty(categoryEvents)));
  }

  private void testFindByDate() {

    VDMSet dateEvents = SetUtil.set();
    dateEvents = agenda.findByDate(new Event.Date(31L, 1L, 2018L));
    assertTrue(Utils.empty(dateEvents));
    dateEvents = agenda.findByDate(new Event.Date(5L, 1L, 2019L));
    assertTrue(!(Utils.empty(dateEvents)));
  }

  private void testFindEvents() {

    VDMSet eventsFound = SetUtil.set();
    eventsFound = agenda.findEvents("Matosinhos", "", "Moda", new Event.Date(7L, 4L, 2020L));
    assertTrue(Utils.empty(eventsFound));
    eventsFound = agenda.findEvents("Matosinhos", "", "", null);
    assertTrue(!(Utils.empty(eventsFound)));
    eventsFound = agenda.findEvents("", "Porto", "", null);
    assertTrue(!(Utils.empty(eventsFound)));
    eventsFound = agenda.findEvents("", "", "Desporto", null);
    assertTrue(!(Utils.empty(eventsFound)));
    eventsFound = agenda.findEvents("", "", "", new Event.Date(7L, 4L, 2020L));
    assertTrue(!(Utils.empty(eventsFound)));
    eventsFound = agenda.findEvents("Matosinhos", "", "Desporto", new Event.Date(7L, 4L, 2020L));
    assertTrue(!(Utils.empty(eventsFound)));
  }

  private void testLoginRegular() {

    Boolean outcome = false;
    agenda.loggedInUser = null;
    outcome = agenda.login("sofia@gmail.com", "person12345");
    assertTrue(Utils.equals(outcome, false));
    assertTrue(Utils.equals(agenda.loggedInUser, null));
    outcome = agenda.login("sofia@gmail.com", "sofia12345");
    assertTrue(Utils.equals(outcome, true));
    assertTrue(!(Utils.equals(agenda.loggedInUser, null)));
    assertTrue(Utils.equals(agenda.loggedInUser, user1));
  }

  private void testBuyTickets() {

    Boolean outcome = agenda.buyTicket(1L, 20001L);
    assertTrue(Utils.equals(outcome, false));
    assertTrue(Utils.equals(event1.getSoldTickets(), 0L));
    assertTrue(Utils.equals(agenda.loggedInUser.getTicketsBought(), 30L));
    outcome = agenda.buyTicket(6L, 2L);
    assertTrue(Utils.equals(outcome, true));
    assertTrue(Utils.equals(event6.getSoldTickets(), 2L));
    assertTrue(Utils.equals(agenda.loggedInUser.getTicketsBought(), 32L));
  }

  private void testExistsEvent() {

    Boolean outcome = agenda.existsEvent(event1);
    assertTrue(Utils.equals(outcome, true));
    outcome = agenda.existsEvent(proposed2);
    assertTrue(Utils.equals(outcome, false));
  }

  private void testProposeEvent() {

    agenda.proposeEvent(proposed1);
    agenda.proposeEvent(proposed2);
    assertTrue(!(Utils.empty(agenda.proposedEvents)));
    assertTrue(Utils.equals(agenda.proposedEvents, SetUtil.set(proposed1, proposed2)));
  }

  private void testRejectProposedEvent() {

    agenda.rejectProposedEvent(proposed2);
    assertTrue(Utils.equals(agenda.proposedEvents, SetUtil.set(proposed1)));
  }

  private void testAcceptProposedEvent() {

    agenda.acceptProposedEvent(proposed1);
    assertTrue(Utils.empty(agenda.proposedEvents));
    assertTrue(
        Utils.equals(
            agenda.events, SetUtil.set(event1, event2, event3, event4, event5, event6, proposed1)));
  }

  private void testMostPopularEvent() {

    Event outcome = agenda.mostPopularEvent();
    assertTrue(Utils.equals(outcome, event6));
  }

  private void testMostProfitableEvent() {

    Event outcome = agenda.mostProfitableEvent();
    assertTrue(Utils.equals(outcome, event6));
  }

  private void testMostActiveUser() {

    User outcome = agenda.mostActiveUser();
    assertTrue(Utils.equals(outcome, user4));
  }

  public void test() {

    testCreateAgenda();
    testAddUser();
    testLoginAdmin();
    testAddEvent();
    testFindByCity();
    testFindByDistrict();
    testFindByCategory();
    testFindByDate();
    testFindEvents();
    testLoginRegular();
    testBuyTickets();
    testExistsEvent();
    testProposeEvent();
    testLoginAdmin();
    testRejectProposedEvent();
    testAcceptProposedEvent();
    testMostPopularEvent();
    testMostProfitableEvent();
    testMostActiveUser();
  }

  public String toString() {

    return "AgendaTest{"
        + "agenda := "
        + Utils.toString(agenda)
        + ", userAdmin := "
        + Utils.toString(userAdmin)
        + ", user1 := "
        + Utils.toString(user1)
        + ", user2 := "
        + Utils.toString(user2)
        + ", user3 := "
        + Utils.toString(user3)
        + ", user4 := "
        + Utils.toString(user4)
        + ", event1 := "
        + Utils.toString(event1)
        + ", event2 := "
        + Utils.toString(event2)
        + ", event3 := "
        + Utils.toString(event3)
        + ", event4 := "
        + Utils.toString(event4)
        + ", event5 := "
        + Utils.toString(event5)
        + ", event6 := "
        + Utils.toString(event6)
        + ", proposed1 := "
        + Utils.toString(proposed1)
        + ", proposed2 := "
        + Utils.toString(proposed2)
        + "}";
  }
}
