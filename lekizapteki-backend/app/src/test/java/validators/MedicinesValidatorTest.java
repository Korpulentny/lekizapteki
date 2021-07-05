package validators;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import org.junit.jupiter.api.Test;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;
import pl.uw.mim.io.lekizapteki.excel.parser.validators.MedicinesValidator;

public class MedicinesValidatorTest {
  private static final Map<String, String> correctPackageMappings = Map.of(
      "30 szt.", "30 szt.",
      "30 szt. (2 blist.po 15 szt.)", "30 szt.",
      "90 szt. (6 blist.po 15 szt.)", "90 szt.",
      "28 szt.. (4 blist.po 7 szt.)", "28 szt.", // takie też są xd
      "100 szt. (1 słoik po 100 szt.)", "100 szt.",
      "1 but.po 28 szt.", "28 szt.",
      "100 szt. (1 poj.po 100 szt.)", "100 szt.",
      "1 but.po 30 szt.", "30 szt.",
      "6 tabl.", "6 szt."
  );

  @Test
  public void shouldChangePackages() {

    for (Map.Entry<String, String> entry : correctPackageMappings.entrySet()) {
      shouldChangePackagesCorrectly(entry.getKey(), entry.getValue());
    }
  }

  private void shouldChangePackagesCorrectly(String pack, String correctPack) {
    Medicine medicineMock = makeMedicineMock(pack);

    Medicine parsedMedicine = MedicinesValidator.parseMedicine(medicineMock);

    assertEquals(correctPack, parsedMedicine.getPack());
  }

  private Medicine makeMedicineMock(String pack) {
    Medicine medicine = new Medicine();

    medicine.setNameAndFormAndDose("123, tabl., 123 g");
    medicine.setPack(pack);

    return medicine;
  }
}
