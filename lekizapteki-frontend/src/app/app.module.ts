import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { FormControl, FormGroup, ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { TopBarComponent } from './components/top-bar/top-bar.component';

import { HttpClientModule } from '@angular/common/http';
import { DiseaseSelectionComponent } from './components/disease-selection/disease-selection.component';
import { NgSelectModule, NgOption } from '@ng-select/ng-select';
import { MedicineSelectionComponent } from './components/medicine-selection/medicine-selection.component';
import { IdenticalMedicinesDetailsComponent } from './components/identical-medicines-details/identical-medicines-details.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatTableModule} from '@angular/material/table';

@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    MatTableModule,
    RouterModule.forRoot([
      {path: '', component: TopBarComponent},
    ]),
    HttpClientModule,
    FormsModule,
    NgSelectModule,
    BrowserAnimationsModule,
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    DiseaseSelectionComponent,
    MedicineSelectionComponent,
    IdenticalMedicinesDetailsComponent,
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
