package pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine;

import static java.util.Map.entry;

import java.util.Map;
import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.MedicinePropertySetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.ChargeFactorSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.DiseaseSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.EanSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.IngredientSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.NameFormDoseSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.PackageSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.RefundSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.RetailPriceSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.SalePriceSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.TotalRefundingSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.editors.medicine.setter.implementations.TradePriceSetter;
import pl.uw.mim.io.lekizapteki.excel.parser.enums.ColumnType;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;

@UtilityClass
public class MedicinePropertySetterFactory {

  public MedicinePropertySetter forMedicineAndColumnType(Medicine medicine, ColumnType columnType) {
    final Map<ColumnType, MedicinePropertySetter> columnTypeToMedicinePropertySetter = Map.ofEntries(
        entry(ColumnType.INGREDIENT, new IngredientSetter(medicine)),
        entry(ColumnType.NAME_FORM_DOSE, new NameFormDoseSetter(medicine)),
        entry(ColumnType.PACKAGE, new PackageSetter(medicine)),
        entry(ColumnType.EAN, new EanSetter(medicine)),
        entry(ColumnType.SALE_PRICE, new SalePriceSetter(medicine)),
        entry(ColumnType.TRADE_PRICE, new TradePriceSetter(medicine)),
        entry(ColumnType.RETAIL_PRICE, new RetailPriceSetter(medicine)),
        entry(ColumnType.TOTAL_REFUNDING, new TotalRefundingSetter(medicine)),
        entry(ColumnType.DISEASE, new DiseaseSetter(medicine)),
        entry(ColumnType.CHARGE_FACTOR, new ChargeFactorSetter(medicine)),
        entry(ColumnType.REFUND, new RefundSetter(medicine))
    );

    return columnTypeToMedicinePropertySetter.get(columnType);
  }
}
