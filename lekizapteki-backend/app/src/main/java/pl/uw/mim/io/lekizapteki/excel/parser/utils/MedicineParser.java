package pl.uw.mim.io.lekizapteki.excel.parser.utils;

import lombok.Getter;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;

@Getter
public class MedicineParser {

  private String name;
  private String form;
  private String dose;

  public void parseMedicine(Medicine medicine) {
    String nameAndFormAndDose = medicine.getNameAndFormAndDose();

    String[] splitted = nameAndFormAndDose.split(", ");

    if (splitted.length < 4) {
      this.name = splitted[0];
      this.form = splitted[1];
      this.dose = splitted[2];
    } else if (splitted.length == 4) {
        this.name = splitted[0];
        this.form = splitted[1] + splitted[2];
        this.dose = splitted[3];
    } else if (splitted.length == 5) {
      this.name = splitted[0] + splitted[1];
      this.form = splitted[2] + splitted[3];
      this.dose = splitted[4];
    } else if (splitted.length == 6) {
      this.name = splitted[0] + splitted[1] + splitted[2];
      this.form = splitted[3] + splitted[4];
      this.dose = splitted[5];
    } else {
      assert(false);
    }

    dose = dose.replace(',', '.');
  }
}
