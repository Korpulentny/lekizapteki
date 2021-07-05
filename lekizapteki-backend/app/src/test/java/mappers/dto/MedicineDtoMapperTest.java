package mappers.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;
import org.junit.jupiter.api.Test;
import pl.uw.mim.io.lekizapteki.mappers.dto.MedicineDtoMapper;
import pl.uw.mim.io.lekizapteki.models.medicine.MedicineDto;
import pl.uw.mim.io.lekizapteki.repositories.entities.DoseEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;

class MedicineDtoMapperTest {

  private final static Long MEDICINE_ID = 2L;
  private final static String MEDICINE_NAME = "test medicine";
  private final static String MEDICINE_EAN = "test ean";

  @Test
  void shouldMapEntityToDto() {
    MedicineEntity medicineEntity = buildTestMedicineEntity();

    MedicineDto medicineDto = MedicineDtoMapper.map(medicineEntity);

    assertEquals(MEDICINE_NAME, medicineDto.getName());
    assertEquals(MEDICINE_EAN, medicineDto.getEan());
  }

  private MedicineEntity buildTestMedicineEntity() {
    return MedicineEntity.builder()
        .id(MEDICINE_ID)
        .name(MEDICINE_NAME)
        .ingredients(Set.of())
        .ean(MEDICINE_EAN)
        .build();
  }

}
