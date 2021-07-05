package mappers.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pl.uw.mim.io.lekizapteki.mappers.dto.DiseaseDtoMapper;
import pl.uw.mim.io.lekizapteki.models.disease.DiseaseDto;
import pl.uw.mim.io.lekizapteki.repositories.entities.DiseaseEntity;

class DiseaseDtoMapperTest {

  private final static Long DISEASE_ID = 1L;
  private final static String DISEASE_NAME = "test name";

  @Test
  void shouldMapEntityDoDto() {
    DiseaseEntity diseaseEntity = buildTestDiseaseEntity();

    DiseaseDto diseaseDto = DiseaseDtoMapper.map(diseaseEntity);

    assertEquals(DISEASE_ID, diseaseDto.getId());
    assertEquals(DISEASE_NAME, diseaseDto.getName());
  }

  private DiseaseEntity buildTestDiseaseEntity() {
    return DiseaseEntity.builder()
        .id(DISEASE_ID)
        .name(DISEASE_NAME)
        .build();
  }

}
