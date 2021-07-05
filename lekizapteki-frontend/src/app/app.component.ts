import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.css' ]
})
export class AppComponent  {

  isMedicineSelectionHidden = true;
  areIdenticalMedicinesHidden = true;

  selectedDiseaseId: number;
  selectedMedicineEan: string;

  constructor() {
    this.selectedDiseaseId = -1;
    this.selectedMedicineEan = '';
  }

  confirmedDiseaseSelection($event) {
    this.isMedicineSelectionHidden = false;
    this.selectedDiseaseId = $event;
  }

  confirmedMedicineSelection($event) {
    this.areIdenticalMedicinesHidden = false;
    this.selectedMedicineEan = $event;
  }
}

