package pl.uw.mim.io.lekizapteki.usecases;

import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.io.lekizapteki.mappers.dto.MedicineDtoMapper;
import pl.uw.mim.io.lekizapteki.models.medicine.MedicineDto;
import pl.uw.mim.io.lekizapteki.services.MedicineService;

@Component
@AllArgsConstructor
public class GetMedicines {

  private MedicineService medicineService;

  public List<MedicineDto> execute(Long diseaseId) {
    return medicineService
        .getMedicinesForDiseaseId(diseaseId).stream()
        .map(MedicineDtoMapper::map)
        .collect(Collectors.toList());
  }

}
