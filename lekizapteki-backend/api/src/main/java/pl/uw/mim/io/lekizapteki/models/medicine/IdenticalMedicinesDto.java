package pl.uw.mim.io.lekizapteki.models.medicine;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import pl.uw.mim.io.lekizapteki.models.medicine.detailed.MedicineDetailsDto;

@Data
@Builder
public class IdenticalMedicinesDto {

  private MedicineDetailsDto medicine;
  private List<MedicineDetailsDto> identicalMedicines;

}
