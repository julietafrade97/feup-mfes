package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Admin extends User {
  public void cg_init_Admin_1(final String mail, final String passw) {

    UserInit(mail, passw);
    return;
  }

  public Admin(final String mail, final String passw) {

    cg_init_Admin_1(mail, passw);
  }

  public Boolean isAdmin() {

    return true;
  }

  public Number getTicketsBought() {

    throw new UnsupportedOperationException();
  }

  public Number getBalance() {

    throw new UnsupportedOperationException();
  }

  public void buyTickets(final Number nTickets, final Number priceTicket) {

    throw new UnsupportedOperationException();
  }

  public Admin() {}

  public String toString() {

    return "Admin{}";
  }
}
