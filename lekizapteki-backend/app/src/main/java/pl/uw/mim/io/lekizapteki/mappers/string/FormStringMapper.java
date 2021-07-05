package pl.uw.mim.io.lekizapteki.mappers.string;

import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.repositories.entities.FormEntity;

@UtilityClass
public class FormStringMapper {

  public String map(FormEntity formEntity) {
    return formEntity.getName();
  }
}
