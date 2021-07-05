import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {animate, state, style, transition, trigger} from '@angular/animations';
import {MedicineDetailsDto} from '../../services/webservices/models/medicine/detailed/medicine-details.dto';
import {IdenticalMedicinesDetailsComponentProperties} from './identical-medicines-details.properties';
import {WebService} from '../../services/webservices/web.service';
import {Observable, of} from 'rxjs';
import {IdenticalMedicinesDto} from '../../services/webservices/models/medicine/identical-medicines.dto';

@Component({
  selector: 'app-identical-medicines-details',
  templateUrl: './identical-medicines-details.component.html',
  styleUrls: ['./identical-medicines-details.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class IdenticalMedicinesDetailsComponent implements OnChanges {

  private static MEDICINE_DETAILS_INIT: MedicineDetailsDto = {
    ean: '',
    name: '',
    activeIngredient: '',
    dose: '',
    form: '',
    pricing: {
      tradePrice: 0,
      salePrice: 0,
      retailPrice: 0,
      totalFunding: 0,
      chargeFactor: 0,
      refund: 0,
      isLumpSum: false,
      isFree: false,
      profitabilityRate: 0
    }
  };

  @Input()
  selectedDiseaseId: number;

  @Input()
  selectedMedicineEan: string;

  private identicalMedicines: Observable<IdenticalMedicinesDto>;
  private expandedElement: MedicineDetailsDto | null;

  private columnsToDisplay: string[] = [
    IdenticalMedicinesDetailsComponentProperties.MEDICINE_NAME_HEADER,
    IdenticalMedicinesDetailsComponentProperties.ACTIVE_INGREDIENT_HEADER,
    IdenticalMedicinesDetailsComponentProperties.PROFITABILITY_RATE_HEADER
  ];

  private selectedMedicine: MedicineDetailsDto;
  private identicalMedicinesArray: MedicineDetailsDto[];

  private webService: WebService;

  constructor(webService: WebService) {
    this.selectedMedicine = IdenticalMedicinesDetailsComponent.MEDICINE_DETAILS_INIT;
    this.identicalMedicinesArray = [];

    this.identicalMedicines = of();
    this.webService = webService;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (this.selectedDiseaseId >= 0 && this.selectedMedicineEan !== '') {
      this.identicalMedicines = this.webService
        .getMedicinesForDiseaseIdenticalToGiven(this.selectedMedicineEan, this.selectedDiseaseId.toString());

      this.identicalMedicines
        .subscribe(identicalMedicines => this.selectedMedicine = identicalMedicines.medicine);

      this.identicalMedicines
        .subscribe(identicalMedicines => this.identicalMedicinesArray = identicalMedicines.identicalMedicines);
    }

  }

  getSelectedMedicine(): MedicineDetailsDto {
    return this.selectedMedicine;
  }

  getIdenticalMedicinesDetails(): MedicineDetailsDto[] {
    return this.identicalMedicinesArray;
  }

  getColumnsToDisplay(): string[] {
    return this.columnsToDisplay;
  }

  getExpandedElement(): MedicineDetailsDto | null {
    return this.expandedElement;
  }

  getChargeFactorMessage(chargeFactor: number, isLumpSum: boolean, isFree: boolean): string {
    if (isLumpSum) {
      return this.getChargeFactorForLumpSum();
    } else if (isFree) {
      return this.getChargeFactorForFree();
    }

    return this.getChargeFactorDefault(chargeFactor);
  }

  private getChargeFactorDefault(chargeFactor: number): string {
    return `${chargeFactor} %`;
  }

  private getChargeFactorForLumpSum(): string {
    return IdenticalMedicinesDetailsComponentProperties.CHARGE_FACTOR_LUMP_SUM_MESSAGE;
  }

  private getChargeFactorForFree(): string {
    return IdenticalMedicinesDetailsComponentProperties.CHARGE_FACTOR_FREE_MESSAGE;
  }

  getColumnContentForHeader(header: string, element: MedicineDetailsDto): string | number {
    if (header === IdenticalMedicinesDetailsComponentProperties.MEDICINE_NAME_HEADER) {
      return element.name;
    }

    if (header === IdenticalMedicinesDetailsComponentProperties.ACTIVE_INGREDIENT_HEADER) {
      return element.activeIngredient;
    }

    if (header === IdenticalMedicinesDetailsComponentProperties.PROFITABILITY_RATE_HEADER) {
      return element.pricing.profitabilityRate;
    }
  }

  clickOnExpandedElement(element: MedicineDetailsDto) {
    this.expandedElement = this.expandedElement === element ? null : element;
  }

}
