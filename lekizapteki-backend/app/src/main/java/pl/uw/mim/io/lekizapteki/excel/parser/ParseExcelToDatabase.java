package pl.uw.mim.io.lekizapteki.excel.parser;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.uw.mim.io.lekizapteki.excel.config.ExcelProcessingProperties;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;
import pl.uw.mim.io.lekizapteki.excel.parser.validators.MedicinesValidator;
import pl.uw.mim.io.lekizapteki.services.MedicineService;

@Component
@AllArgsConstructor
@Slf4j
public class ParseExcelToDatabase {

  private final ExcelProcessingProperties excelProcessingProperties;
  private final ExcelParser excelParser;
  private final MedicineService medicineService;

  public void parse() {
    List<Medicine> medicines = excelParser.parseExcelFile(excelProcessingProperties.getPath());
    List<Medicine> validatedMedicines = MedicinesValidator.filterAndParse(medicines);

    medicineService.saveMedicinesToRepository(validatedMedicines);
  }
}
