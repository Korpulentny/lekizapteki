package pl.uw.mim.io.lekizapteki.usecases;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.io.lekizapteki.mappers.dto.IdenticalMedicinesDtoMapper;
import pl.uw.mim.io.lekizapteki.mappers.dto.MedicineDetailsDtoMapper;
import pl.uw.mim.io.lekizapteki.models.medicine.IdenticalMedicinesDto;
import pl.uw.mim.io.lekizapteki.models.medicine.detailed.MedicineDetailsDto;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;
import pl.uw.mim.io.lekizapteki.services.MedicineService;
import pl.uw.mim.io.lekizapteki.profitability.MedicineValueSorter;

@Component
@AllArgsConstructor
public class GetIdenticalMedicines {

  private MedicineService medicineService;
  private MedicineValueSorter medicineValueSorter;

  public IdenticalMedicinesDto execute(String ean, Long diseaseId) {
    List<MedicineEntity> medicineEntityList = medicineService.getIdenticalMedicines(ean, diseaseId);

    return IdenticalMedicinesDtoMapper.map(
        mapMedicineDetailsDto(ean, diseaseId),
        mapMedicineDtoList(medicineEntityList));
  }

  private MedicineDetailsDto mapMedicineDetailsDto(String ean, Long diseaseId) {
    MedicineEntity medicineEntity = medicineService.getMedicineWithEanAndDiseaseIdOrThrow(ean, diseaseId);

    return MedicineDetailsDtoMapper.map(medicineEntity);
  }

  private List<MedicineDetailsDto> mapMedicineDtoList(List<MedicineEntity> medicineEntityList) {
    List<MedicineEntity> sortedMedicineEntityList = medicineValueSorter.sort(medicineEntityList);

    return sortedMedicineEntityList.stream()
        .map(MedicineDetailsDtoMapper::map)
        .collect(Collectors.toList());
  }

}
