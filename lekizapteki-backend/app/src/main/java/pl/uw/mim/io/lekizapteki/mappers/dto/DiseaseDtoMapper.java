package pl.uw.mim.io.lekizapteki.mappers.dto;

import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.models.disease.DiseaseDto;
import pl.uw.mim.io.lekizapteki.repositories.entities.DiseaseEntity;

@UtilityClass
public class DiseaseDtoMapper {

  public DiseaseDto map(DiseaseEntity diseaseEntity) {
    return DiseaseDto.builder()
        .id(diseaseEntity.getId())
        .name(diseaseEntity.getName())
        .build();
  }

}
