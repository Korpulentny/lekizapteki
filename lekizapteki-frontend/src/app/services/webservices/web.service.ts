import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {DiseaseDto} from './models/disease/disease.dto';
import {MedicineDto} from './models/medicine/medicine.dto';
import {UrlBuilder} from './url-builder.service';
import {LoggingHttpClient} from './logging-http-client.service';
import {environment} from '../../../environments/environment';
import {IdenticalMedicinesDto} from './models/medicine/identical-medicines.dto';

@Injectable({
  providedIn: 'root'
})
export class WebService {

  private static SERVER_URL = `${environment.SERVER_HOST}:${environment.SERVER_PORT}/${environment.SERVER_PATH_PREFIX}`;


  public constructor(private httpClient: HttpClient) {}

  public getDiseases(): Observable<DiseaseDto[]> {
    const url: string = UrlBuilder.builder(WebService.SERVER_URL)
      .addPath(environment.DISEASES_ENDPOINT_PATH)
      .buildUrl();

    return LoggingHttpClient
      .get<DiseaseDto[]>(
        this.getDiseases.name, this.httpClient, url);
  }

  public getMedicinesForDisease(diseaseId: string): Observable<MedicineDto[]> {
    const url: string = UrlBuilder.builder(WebService.SERVER_URL)
      .addPath(environment.MEDICINES_ENDPOINT_PATH)
      .addParam('diseaseId', diseaseId)
      .buildUrl();

    return LoggingHttpClient
      .get<MedicineDto[]>(this.getMedicinesForDisease.name, this.httpClient, url);
  }

  public getMedicinesForDiseaseIdenticalToGiven(ean: string, diseaseId: string): Observable<IdenticalMedicinesDto> {
    const url: string = UrlBuilder.builder(WebService.SERVER_URL)
      .addPath(environment.IDENTICAL_MEDICINES_ENDPOINT_PATH)
      .addParam('ean', ean)
      .addParam('diseaseId', diseaseId)
      .buildUrl();

    return LoggingHttpClient
      .get<IdenticalMedicinesDto>(this.getMedicinesForDiseaseIdenticalToGiven.name, this.httpClient, url);
  }

}
