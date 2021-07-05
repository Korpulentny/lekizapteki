import {Component, EventEmitter, Input, OnChanges, Output, SimpleChanges} from '@angular/core';
import {MedicineSelectionProperties} from './medicine-selection.properties';
import {MedicineDto} from '../../services/webservices/models/medicine/medicine.dto';
import {WebService} from '../../services/webservices/web.service';
import {Observable, of} from 'rxjs';
import {map} from 'rxjs/operators';


@Component({
  selector: 'app-medicine-selection',
  templateUrl: './medicine-selection.component.html',
  styleUrls: ['./medicine-selection.component.css']
})
export class MedicineSelectionComponent implements OnChanges {

  @Input()
  selectedDiseaseId: number;

  @Output()
  confirmed = new EventEmitter<string>();

  selectedMedicineEan: string;

  private webService: WebService;
  private medicines: Observable<MedicineDto[]>;
  private medicinesWithNamesWithDose: MedicineWithNameAndDose[];

  private searchingByEan = false;

  private searchTypeButtonName: string = MedicineSelectionProperties.SEARCH_TYPE_EAN_BUTTON_NAME;
  private placeholder: string = MedicineSelectionProperties.LIST_PLACEHOLDER;

  constructor(webService: WebService) {
    this.webService = webService;
    this.medicines = of([]);
    this. medicinesWithNamesWithDose = [];
  }

  ngOnChanges(changes: SimpleChanges) {
    if (this.selectedDiseaseId >= 0) {
      this.medicines = this.webService
        .getMedicinesForDisease(this.selectedDiseaseId.toString());

      this.medicines
        .subscribe(medicinesDtoArray =>
          this.medicinesWithNamesWithDose = this.mapMedicineDtoArray(medicinesDtoArray));
    }
  }

  isSearchingByEan(): boolean {
    return this.searchingByEan;
  }

  getSearchTypeButtonName(): string {
    return this.searchTypeButtonName;
  }

  getMedicinesWithNameAndDose(): MedicineWithNameAndDose[] {
    return this.medicinesWithNamesWithDose;
  }

  getListPlaceholder(): string {
    return this.placeholder;
  }

  getInputPlaceholder(): string {
    return MedicineSelectionProperties.INPUT_PLACEHOLDER;
  }


  onClickSubmitButton() {
    if (this.selectedMedicineEan != null) {
      this.confirmed.emit(this.selectedMedicineEan);
    }
  }

  onClickSearchTypeInput() {
    this.searchingByEan = !this.searchingByEan;
  }

  openFloatingList() {
    this.placeholder = '';
  }

  private mapMedicineDtoArray(medicineDtoArray: MedicineDto[]): MedicineWithNameAndDose[] {
    const result: MedicineWithNameAndDose[] = [];

    medicineDtoArray.forEach(
      medicineDto => result
        .push(this.mapMedicineDto(medicineDto)));

    return result;
  }

  private mapMedicineDto(medicineDto: MedicineDto): MedicineWithNameAndDose {
    return {
      ean: medicineDto.ean,
      nameWithDose: `${medicineDto.name} - ${medicineDto.dose}`
    };
  }
}

interface MedicineWithNameAndDose {
  ean: string;
  nameWithDose: string;
}
