package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Agenda {
  public VDMSet categories = SetUtil.set();
  public VDMMap locations = MapUtil.map();
  public VDMMap users = MapUtil.map();
  public VDMSet events = SetUtil.set();
  public User loggedInUser = null;
  public VDMSet proposedEvents = SetUtil.set();

  public void cg_init_Agenda_1() {

    categories =
        SetUtil.set("Concertos", "Exposicoes", "Gastronomia", "Moda", "Desporto", "Natureza");
    locations =
        MapUtil.map(
            new Maplet("Porto", SetUtil.set("Porto", "Matosinhos", "Maia", "Vila Nova de Gaia")),
            new Maplet("Lisboa", SetUtil.set("Lisboa", "Amadora", "Cascais", "Sintra")),
            new Maplet("Faro", SetUtil.set("Faro", "Albufeira", "Portimao")));
    return;
  }

  public Agenda() {

    cg_init_Agenda_1();
  }

  public void addUser(final User user) {

    users = MapUtil.override(Utils.copy(users), MapUtil.map(new Maplet(user.getEmail(), user)));
  }

  public Boolean login(final String email, final String password) {

    User user = ((User) Utils.get(users, email));
    if (user.checkLogin(email, password)) {
      loggedInUser = user;
      return true;
    }

    return false;
  }

  public void addEvent(final Event event) {

    events = SetUtil.union(Utils.copy(events), SetUtil.set(event));
  }

  public void rejectProposedEvent(final Event event) {

    proposedEvents = SetUtil.diff(Utils.copy(proposedEvents), SetUtil.set(event));
  }

  public void acceptProposedEvent(final Event event) {

    events = SetUtil.union(Utils.copy(events), SetUtil.set(event));
    proposedEvents = SetUtil.diff(Utils.copy(proposedEvents), SetUtil.set(event));
  }

  public Event mostPopularEvent() {

    Event popularEvent = null;
    Number value = 0L;
    for (Iterator iterator_9 = events.iterator(); iterator_9.hasNext(); ) {
      Event event = (Event) iterator_9.next();
      if (event.getStats().doubleValue() >= value.doubleValue()) {
        value = event.getStats();
        popularEvent = event;
      }
    }
    return popularEvent;
  }

  public Event mostProfitableEvent() {

    Event profitableEvent = null;
    Number value = 0L;
    for (Iterator iterator_10 = events.iterator(); iterator_10.hasNext(); ) {
      Event event = (Event) iterator_10.next();
      if (event.getProfit().doubleValue() >= value.doubleValue()) {
        value = event.getProfit();
        profitableEvent = event;
      }
    }
    return profitableEvent;
  }

  public User mostActiveUser() {

    VDMSet allUsers = MapUtil.rng(Utils.copy(users));
    User activeUser = null;
    Number value = 0L;
    for (Iterator iterator_11 = allUsers.iterator(); iterator_11.hasNext(); ) {
      User user = (User) iterator_11.next();
      if (user instanceof Regular) {
        if (user.getTicketsBought().longValue() >= value.longValue()) {
          value = user.getTicketsBought();
          activeUser = user;
        }
      }
    }
    return activeUser;
  }

  public void proposeEvent(final Event event) {

    proposedEvents = SetUtil.union(Utils.copy(proposedEvents), SetUtil.set(event));
  }

  public Boolean buyTicket(final Number eventID, final Number nTickets) {

    Event eventBought = null;
    for (Iterator iterator_12 = events.iterator(); iterator_12.hasNext(); ) {
      Event event = (Event) iterator_12.next();
      if (Utils.equals(event.getID(), eventID)) {
        eventBought = event;
      }
    }
    Boolean andResult_24 = false;

    if (eventBought.getTotalTickets().longValue()
        >= eventBought.getSoldTickets().longValue() + nTickets.longValue()) {
      if (loggedInUser.getBalance().longValue()
          >= nTickets.longValue() * eventBought.getPrice().doubleValue()) {
        andResult_24 = true;
      }
    }

    if (andResult_24) {
      loggedInUser.buyTickets(nTickets, eventBought.getPrice());
      eventBought.buy(nTickets);
      return true;

    } else {
      return false;
    }
  }

  public VDMSet findEvents(
      final String city, final String district, final String category, final Event.Date date) {

    VDMSet foundEvents = SetUtil.set();
    if (!(Utils.equals(city, ""))) {
      foundEvents = findByCity(city);
    } else if (!(Utils.equals(district, ""))) {
      foundEvents = findByDistrict(district);
    }

    if (!(Utils.equals(category, ""))) {
      Boolean andResult_35 = false;

      if (Utils.equals(city, "")) {
        if (Utils.equals(district, "")) {
          andResult_35 = true;
        }
      }

      if (andResult_35) {
        foundEvents = findByCategory(category);
      } else {
        foundEvents = SetUtil.intersect(Utils.copy(foundEvents), findByCategory(category));
      }
    }

    if (!(Utils.equals(date, null))) {
      Boolean andResult_36 = false;

      if (Utils.equals(city, "")) {
        Boolean andResult_37 = false;

        if (Utils.equals(district, "")) {
          if (Utils.equals(category, "")) {
            andResult_37 = true;
          }
        }

        if (andResult_37) {
          andResult_36 = true;
        }
      }

      if (andResult_36) {
        foundEvents = findByDate(Utils.copy(date));
      } else {
        foundEvents = SetUtil.intersect(Utils.copy(foundEvents), findByDate(Utils.copy(date)));
      }
    }

    return Utils.copy(foundEvents);
  }

  public VDMSet findByCity(final String city) {

    VDMSet cityEvents = SetUtil.set();
    for (Iterator iterator_13 = events.iterator(); iterator_13.hasNext(); ) {
      Event event = (Event) iterator_13.next();
      if (Utils.equals(event.getCity(), city)) {
        cityEvents = SetUtil.union(Utils.copy(cityEvents), SetUtil.set(event));
      }
    }
    return Utils.copy(cityEvents);
  }

  public VDMSet findByDistrict(final String district) {

    VDMSet districtEvents = SetUtil.set();
    if (existsDistrict(district)) {
      for (Iterator iterator_14 = events.iterator(); iterator_14.hasNext(); ) {
        Event event = (Event) iterator_14.next();
        if (existsCityInDistrict(event.getCity(), district)) {
          districtEvents = SetUtil.union(Utils.copy(districtEvents), SetUtil.set(event));
        }
      }
      return Utils.copy(districtEvents);

    } else {
      return SetUtil.set();
    }
  }

  public VDMSet findByCategory(final String category) {

    VDMSet categoryEvents = SetUtil.set();
    for (Iterator iterator_15 = events.iterator(); iterator_15.hasNext(); ) {
      Event event = (Event) iterator_15.next();
      if (Utils.equals(category, event.getCategory())) {
        categoryEvents = SetUtil.union(Utils.copy(categoryEvents), SetUtil.set(event));
      }
    }
    return Utils.copy(categoryEvents);
  }

  public VDMSet findByDate(final Event.Date date) {

    VDMSet dateEvents = SetUtil.set();
    for (Iterator iterator_16 = events.iterator(); iterator_16.hasNext(); ) {
      Event event = (Event) iterator_16.next();
      if (wantedDate(Utils.copy(date), event.getDateStart(), event.getDateEnd())) {
        dateEvents = SetUtil.union(Utils.copy(dateEvents), SetUtil.set(event));
      }
    }
    return Utils.copy(dateEvents);
  }

  public Boolean existsEvent(final Event event) {

    for (Iterator iterator_17 = events.iterator(); iterator_17.hasNext(); ) {
      Event e = (Event) iterator_17.next();
      Boolean andResult_44 = false;

      if (Utils.equals(event.getTitle(), e.getTitle())) {
        Boolean andResult_45 = false;

        if (Utils.equals(event.getCategory(), e.getCategory())) {
          Boolean andResult_46 = false;

          if (Utils.equals(event.getDateStart(), e.getDateStart())) {
            Boolean andResult_47 = false;

            if (Utils.equals(event.getDateEnd(), e.getDateEnd())) {
              if (Utils.equals(event.getCity(), e.getCity())) {
                andResult_47 = true;
              }
            }

            if (andResult_47) {
              andResult_46 = true;
            }
          }

          if (andResult_46) {
            andResult_45 = true;
          }
        }

        if (andResult_45) {
          andResult_44 = true;
        }
      }

      if (andResult_44) {
        return true;
      }
    }
    return false;
  }

  public Boolean existsCity(final String city) {

    VDMSet citiesSet = MapUtil.rng(Utils.copy(locations));
    VDMSet cities = SetUtil.set();
    for (Iterator iterator_18 = citiesSet.iterator(); iterator_18.hasNext(); ) {
      VDMSet cs = (VDMSet) iterator_18.next();
      cities = SetUtil.union(Utils.copy(cities), Utils.copy(cs));
    }
    return SetUtil.inSet(city, cities);
  }

  public Boolean existsDistrict(final String district) {

    VDMSet districts = MapUtil.dom(Utils.copy(locations));
    return SetUtil.inSet(district, districts);
  }

  public Boolean existsCityInDistrict(final String city, final String district) {

    VDMSet districtCities = Utils.copy(((VDMSet) Utils.get(locations, district)));
    return SetUtil.inSet(city, districtCities);
  }

  public Boolean existsCategory(final String category) {

    return SetUtil.inSet(category, categories);
  }

  public Boolean wantedDate(
      final Event.Date date, final Event.Date dateStart, final Event.Date dateEnd) {

    Number natDateStart =
        dateStart.year.longValue() * 10000L
            + dateStart.month.longValue() * 100L
            + dateStart.day.longValue();
    Number natDateEnd =
        dateEnd.year.longValue() * 10000L
            + dateEnd.month.longValue() * 100L
            + dateEnd.day.longValue();
    Number natDate =
        date.year.longValue() * 10000L + date.month.longValue() * 100L + date.day.longValue();
    Boolean andResult_49 = false;

    if (natDateStart.longValue() <= natDate.longValue()) {
      if (natDate.longValue() <= natDateEnd.longValue()) {
        andResult_49 = true;
      }
    }

    return andResult_49;
  }

  public String toString() {

    return "Agenda{"
        + "categories := "
        + Utils.toString(categories)
        + ", locations := "
        + Utils.toString(locations)
        + ", users := "
        + Utils.toString(users)
        + ", events := "
        + Utils.toString(events)
        + ", loggedInUser := "
        + Utils.toString(loggedInUser)
        + ", proposedEvents := "
        + Utils.toString(proposedEvents)
        + "}";
  }
}
