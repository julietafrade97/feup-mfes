package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Regular extends User {
  private String firstName;
  private String lastName;
  private Number balance;
  private Number nTicketsBought;

  public void cg_init_Regular_1(
      final String m,
      final String p,
      final String fName,
      final String lName,
      final Number b,
      final Number nt) {

    UserInit(m, p);
    firstName = fName;
    lastName = lName;
    balance = b;
    nTicketsBought = nt;
    return;
  }

  public Regular(
      final String m,
      final String p,
      final String fName,
      final String lName,
      final Number b,
      final Number nt) {

    cg_init_Regular_1(m, p, fName, lName, b, nt);
  }

  public Boolean isAdmin() {

    return false;
  }

  public Number getTicketsBought() {

    return nTicketsBought;
  }

  public Number getBalance() {

    return balance;
  }

  public void buyTickets(final Number nTickets, final Number priceTicket) {

    balance = balance.doubleValue() - nTickets.longValue() * priceTicket.doubleValue();
    nTicketsBought = nTicketsBought.longValue() + nTickets.longValue();
  }

  public Regular() {}

  public String toString() {

    return "Regular{"
        + "firstName := "
        + Utils.toString(firstName)
        + ", lastName := "
        + Utils.toString(lastName)
        + ", balance := "
        + Utils.toString(balance)
        + ", nTicketsBought := "
        + Utils.toString(nTicketsBought)
        + "}";
  }
}
