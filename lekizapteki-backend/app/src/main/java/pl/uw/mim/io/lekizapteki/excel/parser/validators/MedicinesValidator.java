package pl.uw.mim.io.lekizapteki.excel.parser.validators;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;
import pl.uw.mim.io.lekizapteki.excel.parser.utils.MedicineParser;
import pl.uw.mim.io.lekizapteki.excel.parser.utils.UnitConverter;

@UtilityClass
public class MedicinesValidator {

  private final String MULTIPLE_INGREDIENT_DELIMITER = "+";
  private final String PILL_FORM_SUBSTRING = "tabl";
  private final String SPECIAL_MEDICINE_PACK_PREFIX = "1 but.po";
  private final String WEIRD_DOSE = "160+12.5 mg mg";

  private final List<String> UNSUPPORTED_NAME_FORM_DOSE = List.of(
      "Aprepitant Sandoz, kaps. twarde, 125 mg, 80 mg",
      "Aprepitant Teva, kaps. twarde, 125 mg, 80 mg",
      "Emend, kaps. twarde, 125 mg, 80 mg",
      "Divina, tabl., 2 mg, 2+10 mg",
      "Seebri Breezhaler, proszek do inhalacji w kaps. twardej, 44 µg"
  );


  public List<Medicine> filterAndParse(List<Medicine> medicines) {

    List<Medicine> filteredMedicines = filterInvalidMedicines(medicines);

    return parseMedicines(filteredMedicines);
  }

  private List<Medicine> filterInvalidMedicines(List<Medicine> medicinesToValidate) {
    List<Medicine> validatedMedicines = new ArrayList<>();

    for (Medicine medicineToValidate : medicinesToValidate) {
      if (isValidMedicine(medicineToValidate)) {
        validatedMedicines.add(medicineToValidate);
      }
    }

    return validatedMedicines;
  }

  private boolean isValidMedicine(Medicine medicine) {
    if (UNSUPPORTED_NAME_FORM_DOSE.contains(medicine.getNameAndFormAndDose())) {
      return false;
    }
    return hasSupportedDose(medicine) || (hasSingleIngredient(medicine) && hasPillForm(medicine));
  }

  private boolean hasSupportedDose(Medicine medicine) {
    return medicine.getNameAndFormAndDose().endsWith("g")
        && !medicine.getNameAndFormAndDose().contains("/")
        && !medicine.getNameAndFormAndDose().contains(";");
  }

  private boolean hasSingleIngredient(Medicine medicine) {
    return !medicine.getIngredient().contains(MULTIPLE_INGREDIENT_DELIMITER);
  }

  private boolean hasPillForm(Medicine medicine) {
    return medicine.getNameAndFormAndDose().contains(PILL_FORM_SUBSTRING);
  }

  private List<Medicine> parseMedicines(List<Medicine> medicines) {

    List<Medicine> parsedMedicines = new ArrayList<>();

    for (Medicine medicine : medicines) {
      parsedMedicines.add(parseMedicine(medicine));
    }

    return parsedMedicines;
  }

  public Medicine parseMedicine(Medicine medicine) {
    MedicineParser medicineParser = new MedicineParser();

    medicineParser.parseMedicine(medicine);
    String name = medicineParser.getName();
    String form = medicineParser.getForm();

    String dose = medicineParser.getDose();
    String pack = medicine.getPack();

    String parsedDose = parseMedicineDose(dose);
    String parsedPack = parseMedicinePack(pack);

    medicine.setNameAndFormAndDose(putNameAndFormAndDoseTogether(name, form, parsedDose));
    medicine.setPack(parsedPack);

    return medicine;
  }

  private String parseMedicineDose(String dose) {
    String[] split = dose.split(" ");

    // czyli te z plusem i w j.m.
    if (dose.contains("+") || dose.contains("j.m.")) {
      return parseSpecialMedicineDose(dose);
    }

    assert (split.length == 2);

    String value = split[0];
    String units = split[1];

    value = convertToMg(value, units);

    return putDoseBackTogether(value, "mg");
  }

  private String convertToMg(String value, String units) {
    switch (units) {
      case "g":
        return UnitConverter.gramsToMilligrams(value);
      case "µg": // Those are two different characters
      case "μg":
        return UnitConverter.microgramsToMilligrams(value);
      case "j.m.":
        return UnitConverter.internationalUnitsToMilligrams(value);
      default:
        return value;
    }
  }

  // są to dawki postaci 1.5 mln j.m. i 3 mln j.m. lub te z plusem
  private String parseSpecialMedicineDose(String specialDose) {

    if (specialDose.equals(WEIRD_DOSE)) {
      return "160+12.5 mg";
    }

    if (specialDose.contains("+")) {
      String[] splitted = specialDose.split(" ");
      if (splitted.length == 2) {
        String[] dosages = splitted[0].split("\\+");
        String units = splitted[1];
        dosages[0] = convertToMg(dosages[0], units);
        dosages[1] = convertToMg(dosages[1], units);
        return dosages[0] + "+" + dosages[1] + " mg";
      } else if (splitted.length == 4) {
        return splitted[0] + '+' + splitted[2] + " mg";
      } else if (splitted.length == 5) {
        return splitted[0] + '+' + splitted[3] + " mg";
      } else {
        assert (false);
      }
      return specialDose;
    }

    final int MLN = 1000000000;

    String[] split = specialDose.split(" ");

    String value = split[0];

    BigDecimal decimalValue = new BigDecimal(value);

    if (specialDose.contains("mln")) {
      decimalValue = decimalValue.multiply(BigDecimal.valueOf(MLN));
    }

    value = UnitConverter.internationalUnitsToMilligrams(decimalValue.toString());

    return putDoseBackTogether(value, "mg");
  }

  private String parseMedicinePack(String pack) {
    String[] split = pack.split(" ");

    if (pack.startsWith(SPECIAL_MEDICINE_PACK_PREFIX)) {
      return putPackageBackTogether(split[2]);
    }

    return putPackageBackTogether(split[0]);
  }

  private String putNameAndFormAndDoseTogether(String name, String form, String dose) {
    return name + ";" + form + ";" + dose;
  }

  private String putDoseBackTogether(String value, String units) {
    return value + " " + units;
  }

  private String putPackageBackTogether(String value) {
    return value + " szt.";
  }
}
