package AgendaViral;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Tests {
  protected void assertTrue(final Boolean arg) {

    return;
  }

  public void test() {

    AgendaTest agendaTest = new AgendaTest();
    EventTest eventTest = new EventTest();
    UserTest userTest = new UserTest();
    agendaTest.test();
    eventTest.test();
    userTest.test();
  }

  public Tests() {}

  public String toString() {

    return "Tests{}";
  }
}
