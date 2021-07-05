package pl.uw.mim.io.lekizapteki.mappers.entity;

import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.repositories.entities.IngredientEntity;

@UtilityClass
public class IngredientEntityMapper {

  public IngredientEntity map(String ingredient, Long dose) {
    return IngredientEntity.builder()
        .name(ingredient)
        .dose(DoseEntityMapper.map(dose))
        .build();
  }
}
