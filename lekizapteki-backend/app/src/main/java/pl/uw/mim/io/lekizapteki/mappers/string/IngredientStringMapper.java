package pl.uw.mim.io.lekizapteki.mappers.string;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.repositories.entities.IngredientEntity;

@UtilityClass
public class IngredientStringMapper {

  private final String JOINING_DELIMITER = "-";

  public String mapDoses(Set<IngredientEntity> ingredientEntity) {
    return ingredientEntity.stream()
        .map(IngredientStringMapper::mapDose)
        .collect(Collectors.joining(JOINING_DELIMITER));
  }

  public String mapIngredients(Set<IngredientEntity> ingredientEntity) {
    return ingredientEntity.stream()
        .map(IngredientStringMapper::mapName)
        .collect(Collectors.joining(JOINING_DELIMITER));
  }

  private String mapName(IngredientEntity ingredientEntity) {
    return ingredientEntity.getName();
  }

  private String mapDose(IngredientEntity ingredientEntity) {
    return DoseStringMapper
        .map(ingredientEntity.getDose());
  }

}
