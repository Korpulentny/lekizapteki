package pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations;

import lombok.AllArgsConstructor;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.MedicinePropertySetter;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;

@AllArgsConstructor
public class SalePriceSetter implements MedicinePropertySetter {

  private final Medicine medicine;

  public void setMedicineProperty(String value) {
    medicine.setSalePrice(value);
  }
}
