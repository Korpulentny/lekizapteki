package pl.uw.mim.io.lekizapteki.mappers.dto;

import java.util.Set;
import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.mappers.string.IngredientStringMapper;
import pl.uw.mim.io.lekizapteki.models.medicine.MedicineDto;
import pl.uw.mim.io.lekizapteki.repositories.entities.IngredientEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;

@UtilityClass
public class MedicineDtoMapper {

  public MedicineDto map(MedicineEntity medicineEntity) {
    return MedicineDto.builder()
        .ean(medicineEntity.getEan())
        .dose(mapDose(medicineEntity.getIngredients()))
        .name(medicineEntity.getName())
        .build();
  }

  private String mapDose(Set<IngredientEntity> ingredientEntity) {
    return IngredientStringMapper.mapDoses(ingredientEntity);
  }

}
