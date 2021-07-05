package pl.uw.mim.io.lekizapteki.mappers.dto;

import java.util.List;
import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.models.medicine.IdenticalMedicinesDto;
import pl.uw.mim.io.lekizapteki.models.medicine.detailed.MedicineDetailsDto;

@UtilityClass
public class IdenticalMedicinesDtoMapper {

  public IdenticalMedicinesDto map(MedicineDetailsDto medicineDetails, List<MedicineDetailsDto> identicalMedicinesDetails) {
    return IdenticalMedicinesDto.builder()
        .medicine(medicineDetails)
        .identicalMedicines(identicalMedicinesDetails)
        .build();
  }
}
