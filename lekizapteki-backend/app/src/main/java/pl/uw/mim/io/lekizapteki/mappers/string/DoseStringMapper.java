package pl.uw.mim.io.lekizapteki.mappers.string;

import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.repositories.entities.DoseEntity;

@UtilityClass
public class DoseStringMapper {

  public String map(DoseEntity doseEntity) {
    return doseEntity
        .getDose()
        .toString();
  }
}
