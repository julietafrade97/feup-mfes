package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class EventTest extends Tests {
  public void cg_init_EventTest_1() {

    return;
  }

  public EventTest() {

    cg_init_EventTest_1();
  }

  private void testCreateEvent() {

    Event event =
        new Event(
            "Twenty One Pilots",
            "Concertos",
            new Event.Date(17L, 3L, 2019L),
            new Event.Date(17L, 3L, 2019L),
            "Lorem ipsum dolor sit amet.",
            42L,
            "Lisboa",
            100L);
    assertTrue(Utils.equals(event.getTitle(), "Twenty One Pilots"));
    assertTrue(Utils.equals(event.getCategory(), "Concertos"));
    assertTrue(Utils.equals(event.getState(), AgendaViral.quotes.AvailableQuote.getInstance()));
    assertTrue(Utils.equals(event.getDateStart(), new Event.Date(17L, 3L, 2019L)));
    assertTrue(Utils.equals(event.getDateEnd(), new Event.Date(17L, 3L, 2019L)));
    assertTrue(Utils.equals(event.getDescription(), "Lorem ipsum dolor sit amet."));
    assertTrue(Utils.equals(event.getPrice(), 42L));
    assertTrue(Utils.equals(event.getCity(), "Lisboa"));
    assertTrue(Utils.equals(event.getTotalTickets(), 100L));
    assertTrue(Utils.equals(event.getSoldTickets(), 0L));
    event.buy(100L);
    assertTrue(Utils.equals(event.getSoldTickets(), 100L));
    assertTrue(Utils.equals(event.getState(), AgendaViral.quotes.SoldOutQuote.getInstance()));
    assertTrue(Utils.equals(event.getStats(), 100L));
    assertTrue(Utils.equals(event.getProfit(), 4200L));
  }

  private void testCreateEventLeap() {

    Event event =
        new Event(
            "Twenty One Pilots",
            "Concertos",
            new Event.Date(29L, 2L, 2020L),
            new Event.Date(29L, 2L, 2020L),
            "Lorem ipsum dolor sit amet.",
            42L,
            "Lisboa",
            100L);
    assertTrue(Utils.equals(event.getDateStart(), new Event.Date(29L, 2L, 2020L)));
    assertTrue(Utils.equals(event.getDateEnd(), new Event.Date(29L, 2L, 2020L)));
  }

  public void test() {

    testCreateEvent();
    testCreateEventLeap();
  }

  public String toString() {

    return "EventTest{}";
  }
}
