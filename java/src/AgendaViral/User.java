package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
abstract public class User {
  protected String email = "";
  protected String password = "";

  protected void UserInit(final String m, final String p) {

    email = m;
    password = p;
  }

  public String getEmail() {

    return email;
  }

  public abstract Boolean isAdmin();

  public Boolean checkLogin(final String m, final String p) {

    Boolean andResult_61 = false;

    if (Utils.equals(email, m)) {
      if (Utils.equals(password, p)) {
        andResult_61 = true;
      }
    }

    return andResult_61;
  }

  public abstract Number getTicketsBought();

  public abstract Number getBalance();

  public abstract void buyTickets(final Number nTickets, final Number priceTicket);

  public User() {}

  public String toString() {

    return "User{"
        + "email := "
        + Utils.toString(email)
        + ", password := "
        + Utils.toString(password)
        + "}";
  }
}
