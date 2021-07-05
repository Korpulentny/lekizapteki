package pl.uw.mim.io.lekizapteki.excel.parser.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UnitConverter {

  private final int GRAMS_FACTOR = 1000;
  private final double MICROGRAMS_FACTOR = 0.001;
  private final double INTERNATIONAL_UNITS_FACTOR = 0.67; // TODO w sumie to nie wiadomo ile, różne są dane

  public String gramsToMilligrams(String grams) {
    BigDecimal decimal = new BigDecimal(grams.replace(",", "."));
    BigDecimal converted = decimal.multiply(BigDecimal.valueOf(GRAMS_FACTOR));

    return bigDecimalToString(converted);
  }

  public String microgramsToMilligrams(String micrograms) {
    BigDecimal decimal = new BigDecimal(micrograms.replace(",", "."));
    BigDecimal converted = decimal.multiply(BigDecimal.valueOf(MICROGRAMS_FACTOR));

    return bigDecimalToString(converted);
  }

  public String internationalUnitsToMilligrams(String internationalUnits) {
    BigDecimal decimal = new BigDecimal(internationalUnits.replace(",", "."));
    BigDecimal converted = decimal.multiply(BigDecimal.valueOf(INTERNATIONAL_UNITS_FACTOR));

    return bigDecimalToString(converted);
  }

  private String bigDecimalToTwoDecimalPlacesString(BigDecimal decimal) {
    return decimal.setScale(2, RoundingMode.HALF_EVEN).toString();
  }

  private String bigDecimalToString(BigDecimal decimal) {
    return decimal.toString();
  }
}
