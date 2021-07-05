package valueSorter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import pl.uw.mim.io.lekizapteki.repositories.entities.DoseEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.IngredientEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.PackageEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.PricingEntity;
import pl.uw.mim.io.lekizapteki.profitability.MedicineValueSorter;

public class ValueSorterTest {

  @Test
  void correctMedicineSortingTest() {
    MedicineValueSorter medicineValueSorter = new MedicineValueSorter();

    List<MedicineEntity> medicineEntityList = List
        .of(buildTestMedicineEntity(1L, "zero", 500L, "123", 10L),
            buildTestMedicineEntity(2L, "jeden", 25L, "124", 15L),
            buildTestMedicineEntity(3L, "dwa", 25L, "125", 15L));
    List<MedicineEntity> resultMedicineEntityList = medicineValueSorter.sort(medicineEntityList);

    assertEquals(1L, resultMedicineEntityList.get(0).getId());
    assertEquals(2L, resultMedicineEntityList.get(1).getId());
    assertEquals(3L, resultMedicineEntityList.get(2).getId());
  }

  private MedicineEntity buildTestMedicineEntity(Long id, String name, Long dose, String ean, Long quantity) {
    return MedicineEntity.builder()
        .id(id)
        .name(name)
        .ingredients(Set.of(buildTestIngredientEntity(id, "test", dose)))
        .ean(ean)
        .pack(buildTestPackageEntity(id, quantity))
        .pricing(buildTestPricingEntity(id))
        .build();
  }

  private IngredientEntity buildTestIngredientEntity(Long id, String ingredient, Long dose) {
    return IngredientEntity.builder()
        .id(id)
        .name(ingredient)
        .dose(buildTestDoseEntity(id, dose))
        .build();
  }

  private DoseEntity buildTestDoseEntity(Long id, Long dose) {
    return DoseEntity.builder()
        .id(id)
        .dose(dose)
        .build();
  }

  private PackageEntity buildTestPackageEntity(Long id, Long quantity) {
    return PackageEntity.builder()
        .id(id)
        .quantity(quantity)
        .build();
  }

  private PricingEntity buildTestPricingEntity(Long id) {
    return PricingEntity.builder()
        .id(id)
        .retailPrice(new BigDecimal("10.00"))
        .build();
  }

}
