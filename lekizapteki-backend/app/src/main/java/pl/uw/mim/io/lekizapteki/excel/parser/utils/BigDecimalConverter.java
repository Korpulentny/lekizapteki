package pl.uw.mim.io.lekizapteki.excel.parser.utils;

import java.math.BigDecimal;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BigDecimalConverter {

  private final String LUMP_SUM = "ryczałt";
  private final String FREE_UP_TO_THE_LIMIT = "bezpłatny do limitu";
  private final int LUMP_SUM_VALUE = 0;
  private final int FREE_UP_TO_THE_LIMIT_VALUE = -1;

  public BigDecimal doseToBigDecimal(String dose) {
    return new BigDecimal(dose.replace(",", "."));
  }

  public BigDecimal priceToBigDecimal(String price) {
    return new BigDecimal(price.replace(",", "."));
  }

  public BigDecimal chargeFactorToBigDecimal(String chargeFactor) {
    BigDecimal equivalentDecimal;
    switch (chargeFactor) {
      case LUMP_SUM:
        equivalentDecimal = new BigDecimal(LUMP_SUM_VALUE);
        break;
      case FREE_UP_TO_THE_LIMIT:
        equivalentDecimal = new BigDecimal(FREE_UP_TO_THE_LIMIT_VALUE);
        break;
      default:
        equivalentDecimal = new BigDecimal(chargeFactor.replace("%", "")); // na razie 30% -> 30
    }
    return equivalentDecimal;
  }
}
