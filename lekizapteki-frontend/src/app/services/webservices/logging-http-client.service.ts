import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {HttpLogger} from './http-logger.service';

export class LoggingHttpClient {

  public static get<T>(functionName: string, httpClient: HttpClient, url: string): Observable<T> {
    HttpLogger.logRequest(functionName, url);
    const response = httpClient.get<T>(url);
    HttpLogger.logResponse<T>(functionName, response);

    return response;
  }

}
