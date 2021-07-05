package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;
import pl.uw.mim.io.lekizapteki.excel.parser.utils.UnitConverter;

public class UnitConverterTest {

  private static final Map<String, String> gramsToMilligramsCorrectParsing = Map.of(
      "0.5", "500.00",
      "5", "5000.00",
      "12.4", "12400.00",
      "1", "1000.00",
      "0,5", "500.00",
      "0.11", "110.00",
      "0,69", "690.00",
      "0,01", "10.00"
  );

  private static final Map<String, String> gramsToMilligramsWithUnitsCorrectParsing = Map.of(
      "0.5 g", "500.00 mg",
      "5 g", "5000.00 mg",
      "12.4 g", "12400.00 mg",
      "1 g", "1000.00 mg",
      "0,5 g", "500.00 mg",
      "0.11 g", "110.00 mg",
      "0,69 g", "690.00 mg",
      "0,01 g", "10.00 mg"
  );

  private static final Map<String, String> microgramsToMilligramsCorrectParsing = Map.of(
      "100", "0.10",
      "50", "0.05",
      "75", "0.08"
  );

  @Test
  public void shouldParseGramsToMilligrams() {

    for (Map.Entry<String, String> entry : gramsToMilligramsCorrectParsing.entrySet()) {
      String grams = entry.getKey();
      String correctMilligrams = entry.getValue();

      String converted = UnitConverter.gramsToMilligrams(grams);

      assertEquals(correctMilligrams, converted);
    }
  }

  @Test
  public void shouldParseMicrogramsToMilligrams() {

    for (Map.Entry<String, String> entry : microgramsToMilligramsCorrectParsing.entrySet()) {
      String micrograms = entry.getKey();
      String correctMilligrams = entry.getValue();

      String converted = UnitConverter.microgramsToMilligrams(micrograms);

      assertEquals(correctMilligrams, converted);
    }
  }


}
