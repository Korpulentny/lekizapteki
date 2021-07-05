package pl.uw.mim.io.lekizapteki.mappers.dto;

import java.math.BigDecimal;
import java.util.Set;
import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.mappers.string.FormStringMapper;
import pl.uw.mim.io.lekizapteki.mappers.string.IngredientStringMapper;
import pl.uw.mim.io.lekizapteki.models.medicine.detailed.MedicineDetailsDto;
import pl.uw.mim.io.lekizapteki.models.medicine.detailed.PricingDto;
import pl.uw.mim.io.lekizapteki.profitability.MedicineProfitabilityRateResolver;
import pl.uw.mim.io.lekizapteki.repositories.entities.FormEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.IngredientEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.PricingEntity;

@UtilityClass
public class MedicineDetailsDtoMapper {


  public MedicineDetailsDto map(MedicineEntity medicineEntity) {
    BigDecimal profitabilityRate = MedicineProfitabilityRateResolver.getRate(medicineEntity);

    return MedicineDetailsDto.builder()
        .ean(medicineEntity.getEan())
        .dose(mapDoseDto(medicineEntity.getIngredients()))
        .name(medicineEntity.getName())
        .activeIngredient(mapIngredientDto(medicineEntity.getIngredients()))
        .pricing(mapPricingDto(medicineEntity.getPricing(), profitabilityRate))
        .form(mapFormDto(medicineEntity.getForm()))
        .build();
  }

  private PricingDto mapPricingDto(PricingEntity pricingEntity, BigDecimal profitabilityRate) {
    return PricingDtoMapper.map(pricingEntity, profitabilityRate);
  }

  private String mapFormDto(FormEntity formEntity) {
    return FormStringMapper.map(formEntity);
  }

  private String mapDoseDto(Set<IngredientEntity> ingredientEntity) {
    return IngredientStringMapper.mapDoses(ingredientEntity);
  }

  private String mapIngredientDto(Set<IngredientEntity> ingredientEntity) {
    return IngredientStringMapper.mapIngredients(ingredientEntity);
  }
}
