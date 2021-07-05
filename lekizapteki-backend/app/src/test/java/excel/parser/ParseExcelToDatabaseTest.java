package excel.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.uw.mim.io.lekizapteki.excel.parser.ExcelParser;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;
import pl.uw.mim.io.lekizapteki.excel.parser.validators.MedicinesValidator;


class ParseExcelToDatabaseTest {

  private final static String TEST_FILE_PATH = "src/main/resources/wykaz.xlsx";

  @Test
  void shouldParseAllLines() {
    ExcelParser excelParser = new ExcelParser();

    List<Medicine> medicines = excelParser.parseExcelFile(TEST_FILE_PATH);

    assertEquals(4312, medicines.size());

    medicines.forEach(Assertions::assertNotNull);
  }

  @Test
  void shouldFilterCorrectMedicines() {
    ExcelParser excelParser = new ExcelParser();
    List<Medicine> medicines = excelParser.parseExcelFile(TEST_FILE_PATH);
    List<Medicine> filteredMedicines = MedicinesValidator.filterAndParse(medicines);

    // to z dawkami zawierającymi znak '/'
//    assertEquals(3638, filteredMedicines.size());

    // to z normalnymi dawkami
    assertEquals(3584, filteredMedicines.size());

    filteredMedicines.forEach(Assertions::assertNotNull);
  }

}
