package pl.uw.mim.io.lekizapteki.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.uw.mim.io.lekizapteki.mappers.entity.MedicineEntityMapper;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;
import pl.uw.mim.io.lekizapteki.exceptions.WrongMedicineForDiseaseException;
import pl.uw.mim.io.lekizapteki.repositories.DiseaseRepository;
import pl.uw.mim.io.lekizapteki.repositories.IngredientRepository;
import pl.uw.mim.io.lekizapteki.repositories.MedicineRepository;
import pl.uw.mim.io.lekizapteki.repositories.entities.DiseaseEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.IngredientEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;

@Service
@AllArgsConstructor
@Slf4j
public class MedicineService {

  private MedicineRepository medicineRepository;
  private DiseaseService diseaseService;

  private DiseaseRepository diseaseRepository;
  private IngredientRepository ingredientRepository;

  public List<MedicineEntity> getMedicinesForDiseaseId(Long diseaseId) {
    DiseaseEntity diseaseEntity = diseaseService.getDiseaseWithIdOrThrow(diseaseId);

    return medicineRepository
        .findAllByDisease(diseaseEntity);
  }

  public List<MedicineEntity> getIdenticalMedicines(String ean, Long diseaseId) {
    MedicineEntity medicineEntity = getMedicineWithEanAndDiseaseIdOrThrow(ean, diseaseId);
    Set<IngredientEntity> ingredientEntitySet = medicineEntity.getIngredients();

    List<DiseaseEntity> diseaseEntityListWithName = diseaseRepository.findAllByName(medicineEntity.getDisease().getName());

    return medicineRepository
        .findAllByDiseaseIn(diseaseEntityListWithName).stream()
        .filter(o -> compareIngredients(o.getIngredients(), ingredientEntitySet))
        .collect(Collectors.toList());
  }

  private boolean compareIngredients(Set<IngredientEntity> givenIngredientEntities, Set<IngredientEntity> requiredIngredientEntities) {
    return givenIngredientEntities.stream()
        .allMatch(o -> isInRequired(o, requiredIngredientEntities));
  }

  private boolean isInRequired(IngredientEntity ingredientEntity, Set<IngredientEntity> requiredIngredientEntities) {
    return requiredIngredientEntities.stream()
        .anyMatch(o -> compareIngredient(o, ingredientEntity));
  }

  private boolean compareIngredient(IngredientEntity ingredientEntity1, IngredientEntity ingredientEntity2) {
    return ingredientEntity1.getName().equals(ingredientEntity2.getName())
        && ingredientEntity1.getDose().getDose().equals(ingredientEntity2.getDose().getDose());
  }
  public MedicineEntity getMedicineWithEanAndDiseaseIdOrThrow(String ean, Long diseaseId) {
    DiseaseEntity diseaseEntity = diseaseService.getDiseaseWithIdOrThrow(diseaseId);

    return medicineRepository
        .findByEanAndDisease(ean, diseaseEntity)
        .orElseThrow(WrongMedicineForDiseaseException::new);
  }


  public void saveMedicinesToRepository(List<Medicine> medicines) {
    medicines.stream()
        .map(medicine -> MedicineEntityMapper.map(medicine, getDiseaseWithNameOrCreateNew(medicine)))
        .forEach(this::saveMedicineAndIngredients);
  }

  private DiseaseEntity getDiseaseWithNameOrCreateNew(Medicine medicine) {
    return diseaseService
        .getDiseaseWithNameOrCreateNew(medicine.getDisease());
  }

  private void saveMedicineAndIngredients(MedicineEntity medicineEntity) {
    medicineRepository.save(medicineEntity);

    medicineEntity.getIngredients().stream()
      .peek(o -> o.setMedicine(medicineEntity))
      .forEach(ingredientRepository::save);
  }
}
