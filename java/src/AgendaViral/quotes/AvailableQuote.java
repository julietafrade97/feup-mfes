package AgendaViral.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class AvailableQuote {
  private static int hc = 0;
  private static AvailableQuote instance = null;

  public AvailableQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static AvailableQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new AvailableQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof AvailableQuote;
  }

  public String toString() {

    return "<Available>";
  }
}
