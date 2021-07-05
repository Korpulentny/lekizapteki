package pl.uw.mim.io.lekizapteki;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.uw.mim.io.lekizapteki.models.medicine.IdenticalMedicinesDto;
import pl.uw.mim.io.lekizapteki.models.medicine.MedicineDto;

@RequestMapping("/lekizapteki")
public interface MedicineApi {

  @CrossOrigin(origins = "*")
  @GetMapping("/medicines")
  List<MedicineDto> getMedicines(
      @RequestParam(required = false) Long diseaseId);

  @CrossOrigin(origins = "*")
  @GetMapping("/medicines/identical")
  IdenticalMedicinesDto getIdenticalMedicines(
      @RequestParam(required = true) String ean,
      @RequestParam(required = false) Long diseaseId);

}
