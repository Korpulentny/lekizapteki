import {MedicineDetailsDto} from './detailed/medicine-details.dto';

export interface IdenticalMedicinesDto {

  medicine: MedicineDetailsDto;
  identicalMedicines: MedicineDetailsDto[];

}
