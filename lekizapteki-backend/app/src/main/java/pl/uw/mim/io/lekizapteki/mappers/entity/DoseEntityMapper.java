package pl.uw.mim.io.lekizapteki.mappers.entity;

import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.repositories.entities.DoseEntity;

@UtilityClass
public class DoseEntityMapper {

  public DoseEntity map(Long dose) {
    return DoseEntity.builder()
        .dose(dose)
        .build();
  }
}
