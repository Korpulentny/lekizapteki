import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {DiseaseSelectionProperties} from './disease-selection.properties';
import {DiseaseDto} from '../../services/webservices/models/disease/disease.dto';
import {WebService} from '../../services/webservices/web.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-disease-selection',
  templateUrl: './disease-selection.component.html',
  styleUrls: ['./disease-selection.component.css']
})


export class DiseaseSelectionComponent implements OnInit {

  @Output()
  confirmed = new EventEmitter<number>();

  selectedDiseaseId: number;

  private readonly diseases: Observable<DiseaseDto[]>;
  private placeholder: string = DiseaseSelectionProperties.LIST_PLACEHOLDER;

  constructor(webService: WebService) {
    this.diseases = webService.getDiseases();
  }

  ngOnInit(): void { }

  onClickSubmitButton() {
    if (this.selectedDiseaseId != null) {
      this.confirmed.emit(this.selectedDiseaseId);
    }
  }

  openFloatingList() {
    this.selectedDiseaseId = null;
    this.hidePlaceholder();
  }

  getDiseases(): Observable<DiseaseDto[]> {
    return this.diseases;
  }

  getPlaceholder(): string {
    return this.placeholder;
  }

  private hidePlaceholder() {
    this.placeholder = DiseaseSelectionProperties.LIST_EMPTY_PLACEHOLDER;
  }

}

