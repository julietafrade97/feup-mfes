package AgendaViral.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class SoldOutQuote {
  private static int hc = 0;
  private static SoldOutQuote instance = null;

  public SoldOutQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static SoldOutQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new SoldOutQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof SoldOutQuote;
  }

  public String toString() {

    return "<SoldOut>";
  }
}
