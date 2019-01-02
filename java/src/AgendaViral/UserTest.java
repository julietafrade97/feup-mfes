package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UserTest extends Tests {
  public void cg_init_UserTest_1() {

    return;
  }

  public UserTest() {

    cg_init_UserTest_1();
  }

  private void testCreateAdmin() {

    User user = new Admin("julieta@gmail.com", "julieta12345");
    assertTrue(Utils.equals(user.getEmail(), "julieta@gmail.com"));
    assertTrue(Utils.equals(user.isAdmin(), true));
    assertTrue(Utils.equals(user.checkLogin("julieta@gmail.com", "julieta12345"), true));
  }

  private void testCreateRegular() {

    User user = new Regular("sofia@gmail.com", "sofia12345", "Sofia", "Silva", 500L, 30L);
    assertTrue(Utils.equals(user.getEmail(), "sofia@gmail.com"));
    assertTrue(Utils.equals(user.isAdmin(), false));
    assertTrue(Utils.equals(user.checkLogin("sofia@gmail.com", "sofia12345"), true));
    assertTrue(Utils.equals(user.getTicketsBought(), 30L));
    user.buyTickets(2L, 50L);
    assertTrue(Utils.equals(user.getBalance(), 400L));
    assertTrue(Utils.equals(user.getTicketsBought(), 32L));
  }

  public void test() {

    testCreateAdmin();
    testCreateRegular();
  }

  public String toString() {

    return "UserTest{}";
  }
}
