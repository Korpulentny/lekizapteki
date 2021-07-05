package pl.uw.mim.io.lekizapteki.profitability;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.repositories.entities.DoseEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.IngredientEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;

@UtilityClass
public class MedicineProfitabilityRateResolver {

  private final static int DIVIDE_SCALE = 4;
  private final static BigDecimal MG_SCALE = new BigDecimal(1000);


  public BigDecimal getRate(MedicineEntity medicineEntity) {
    BigDecimal price = getRetailPrice(medicineEntity);
    BigDecimal ingredientQuantity = getIngredientQuantity(medicineEntity).divide(MG_SCALE, DIVIDE_SCALE * 2, RoundingMode.HALF_DOWN);

    return price.divide(ingredientQuantity, DIVIDE_SCALE, RoundingMode.HALF_DOWN);
  }

  private BigDecimal getRetailPrice(MedicineEntity medicineEntity) {
    return medicineEntity
        .getPricing()
        .getRetailPrice();
  }

  private BigDecimal getIngredientQuantity(MedicineEntity medicineEntity) {
    BigDecimal dose = getDoses(medicineEntity);
    BigDecimal quantity = getQuantity(medicineEntity);

    return dose.multiply(quantity);
  }

  private BigDecimal getDoses(MedicineEntity medicineEntity) {
    Long doses = getDosesLong(medicineEntity);

    return new BigDecimal(doses);
  }

  private Long getDosesLong(MedicineEntity medicineEntity) {
    return medicineEntity
        .getIngredients().stream()
        .map(IngredientEntity::getDose)
        .mapToLong(DoseEntity::getDose)
        .sum();
  }

  private BigDecimal getQuantity(MedicineEntity medicineEntity) {
    Long quantity = medicineEntity.getPack().getQuantity();

    return new BigDecimal(quantity);
  }

}
