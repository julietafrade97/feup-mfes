package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Event {
  private static Number eventId = 1L;
  private Number id;
  private String title;
  private String category;
  private Object state;
  private Date dateStart;
  private Date dateEnd;
  private String description;
  private Number price;
  private String city;
  private Number totalTickets;
  private Number soldTickets;

  public void cg_init_Event_1(
      final String newTitle,
      final String newCategory,
      final Date newStart,
      final Date newEnd,
      final String newDescription,
      final Number newPrice,
      final String newCity,
      final Number newTotal) {

    id = Event.eventId;
    eventId = Event.eventId.longValue() + 1L;
    title = newTitle;
    category = newCategory;
    state = AgendaViral.quotes.AvailableQuote.getInstance();
    dateStart = Utils.copy(newStart);
    dateEnd = Utils.copy(newEnd);
    description = newDescription;
    price = newPrice;
    city = newCity;
    totalTickets = newTotal;
    soldTickets = 0L;
    return;
  }

  public Event(
      final String newTitle,
      final String newCategory,
      final Date newStart,
      final Date newEnd,
      final String newDescription,
      final Number newPrice,
      final String newCity,
      final Number newTotal) {

    cg_init_Event_1(
        newTitle,
        newCategory,
        Utils.copy(newStart),
        Utils.copy(newEnd),
        newDescription,
        newPrice,
        newCity,
        newTotal);
  }

  public Number getID() {

    return id;
  }

  public String getTitle() {

    return title;
  }

  public String getCategory() {

    return category;
  }

  public Object getState() {

    return state;
  }

  public Date getDateStart() {

    return Utils.copy(dateStart);
  }

  public Date getDateEnd() {

    return Utils.copy(dateEnd);
  }

  public String getDescription() {

    return description;
  }

  public Number getPrice() {

    return price;
  }

  public String getCity() {

    return city;
  }

  public Number getTotalTickets() {

    return totalTickets;
  }

  public Number getSoldTickets() {

    return soldTickets;
  }

  public Number getStats() {

    return Utils.divide((1.0 * soldTickets.longValue() * 100L), totalTickets.longValue());
  }

  public Number getProfit() {

    return soldTickets.longValue() * price.doubleValue();
  }

  public void buy(final Number nTickets) {

    soldTickets = soldTickets.longValue() + nTickets.longValue();
    if (Utils.equals(soldTickets, totalTickets)) {
      state = AgendaViral.quotes.SoldOutQuote.getInstance();
    }
  }

  public Boolean validateDates(final Date startDate, final Date endDate) {

    Number natDateStart =
        startDate.year.longValue() * 10000L
            + startDate.month.longValue() * 100L
            + startDate.day.longValue();
    Number natDateEnd =
        endDate.year.longValue() * 10000L
            + endDate.month.longValue() * 100L
            + endDate.day.longValue();
    return natDateStart.longValue() <= natDateEnd.longValue();
  }

  public Event() {}

  public String toString() {

    return "Event{"
        + "eventId := "
        + Utils.toString(eventId)
        + ", id := "
        + Utils.toString(id)
        + ", title := "
        + Utils.toString(title)
        + ", category := "
        + Utils.toString(category)
        + ", state := "
        + Utils.toString(state)
        + ", dateStart := "
        + Utils.toString(dateStart)
        + ", dateEnd := "
        + Utils.toString(dateEnd)
        + ", description := "
        + Utils.toString(description)
        + ", price := "
        + Utils.toString(price)
        + ", city := "
        + Utils.toString(city)
        + ", totalTickets := "
        + Utils.toString(totalTickets)
        + ", soldTickets := "
        + Utils.toString(soldTickets)
        + "}";
  }

  public static class Date implements Record {
    public Number day;
    public Number month;
    public Number year;

    public Date(final Number _day, final Number _month, final Number _year) {

      day = _day;
      month = _month;
      year = _year;
    }

    public boolean equals(final Object obj) {

      if (!(obj instanceof Date)) {
        return false;
      }

      Date other = ((Date) obj);

      return (Utils.equals(day, other.day))
          && (Utils.equals(month, other.month))
          && (Utils.equals(year, other.year));
    }

    public int hashCode() {

      return Utils.hashCode(day, month, year);
    }

    public Date copy() {

      return new Date(day, month, year);
    }

    public String toString() {

      return "mk_Event`Date" + Utils.formatFields(day, month, year);
    }
  }

  public static Boolean inv_Date(final Date date) {

    Boolean orResult_2 = false;

    if (Utils.equals(Utils.mod(date.year.longValue(), 400L), 0L)) {
      orResult_2 = true;
    } else {
      Boolean andResult_52 = false;

      if (!(Utils.equals(Utils.mod(date.year.longValue(), 100L), 0L))) {
        if (Utils.equals(Utils.mod(date.year.longValue(), 4L), 0L)) {
          andResult_52 = true;
        }
      }

      orResult_2 = andResult_52;
    }

    if (orResult_2) {
      Boolean andResult_53 = false;

      if (date.month.longValue() <= 12L) {
        if (date.day.longValue()
            <= ((Number)
                    Utils.get(
                        SeqUtil.seq(31L, 29L, 31L, 30L, 31L, 30L, 31L, 31L, 30L, 31L, 30L, 31L),
                        date.month))
                .longValue()) {
          andResult_53 = true;
        }
      }

      return andResult_53;

    } else {
      Boolean andResult_54 = false;

      if (date.month.longValue() <= 12L) {
        if (date.day.longValue()
            <= ((Number)
                    Utils.get(
                        SeqUtil.seq(31L, 28L, 31L, 30L, 31L, 30L, 31L, 31L, 30L, 31L, 30L, 31L),
                        date.month))
                .longValue()) {
          andResult_54 = true;
        }
      }

      return andResult_54;
    }
  }
}
