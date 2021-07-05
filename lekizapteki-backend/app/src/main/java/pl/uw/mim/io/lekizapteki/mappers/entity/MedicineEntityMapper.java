package pl.uw.mim.io.lekizapteki.mappers.entity;

import java.math.BigDecimal;
import java.util.Set;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;
import pl.uw.mim.io.lekizapteki.repositories.entities.DiseaseEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.FormEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.IngredientEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.PackageEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.PricingEntity;

@UtilityClass
@Slf4j
public class MedicineEntityMapper {

  private String name;
  private String form;
  private Long fstDose;
  private Long sndDose;

  public MedicineEntity map(Medicine medicine, DiseaseEntity diseaseEntity) {
    moveNameAndFormAndDoseToSeparateVariables(medicine);

    Set<IngredientEntity> setOfIngredients;
    IngredientEntity fstIngredientEntity = IngredientEntityMapper.map(medicine.getIngredient(), fstDose);
    if (sndDose != 0) {
      IngredientEntity sndIngredientEntity = IngredientEntityMapper.map(medicine.getIngredient(), sndDose);
      setOfIngredients = Set.of(fstIngredientEntity, sndIngredientEntity);
    } else {
      setOfIngredients = Set.of(fstIngredientEntity);
    }

    FormEntity formEntity = FormEntityMapper.map(form);

    PackageEntity packageEntity = PackageEntityMapper.map(Long.parseLong(medicine.getPack().split(" ")[0]));

    PricingEntity pricingEntity = buildPricingEntityMapper(medicine).map();

    return MedicineEntity.builder()
        .ean(medicine.getEan())
        .name(name)
        .ingredients(setOfIngredients)
        .form(formEntity)
        .disease(diseaseEntity)
        .pack(packageEntity)
        .pricing(pricingEntity)
        .build();
  }

  private void moveNameAndFormAndDoseToSeparateVariables(Medicine medicine) {
    String[] splitNameAndFormAndDose = medicine.getNameAndFormAndDose().split(";");

    name = splitNameAndFormAndDose[0];
    form = splitNameAndFormAndDose[1];
    String dose = splitNameAndFormAndDose[2].split(" ")[0];

    fstDose = 0L;
    sndDose = 0L;
    if (dose.contains("+")) {
      String[] dosages = dose.split("\\+");
      fstDose = new BigDecimal(dosages[0]).multiply(BigDecimal.valueOf(1000)).longValue();
      sndDose = new BigDecimal(dosages[1]).multiply(BigDecimal.valueOf(1000)).longValue();
    } else {
      fstDose = new BigDecimal(dose).multiply(BigDecimal.valueOf(1000)).longValue();
    }
  }

  private PricingEntityMapper buildPricingEntityMapper(Medicine medicine) {
    return PricingEntityMapper.builder()
        .salePrice(medicine.getSalePrice())
        .tradePrice(medicine.getTradePrice())
        .retailPrice(medicine.getRetailPrice())
        .totalFunding(medicine.getTotalFunding())
        .chargeFactor(medicine.getChargeFactor())
        .refund(medicine.getRefund())
        .isLumpSum(medicine.getChargeFactor().equals("ryczałt"))
        .isFree(medicine.getChargeFactor().equals("bezpłatny do limitu"))
        .build();
  }
}
