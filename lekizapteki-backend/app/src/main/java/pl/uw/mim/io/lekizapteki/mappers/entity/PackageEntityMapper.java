package pl.uw.mim.io.lekizapteki.mappers.entity;

import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.repositories.entities.PackageEntity;

@UtilityClass
public class PackageEntityMapper {

  public PackageEntity map(Long quantity) {
    return PackageEntity.builder()
        .quantity(quantity)
        .build();
  }
}
