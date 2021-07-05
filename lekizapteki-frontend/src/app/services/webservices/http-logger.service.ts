import {Observable} from 'rxjs';
import {HttpErrorResponse} from '@angular/common/http';

export class HttpLogger {

  private static REQUEST_LOG_PREFIX = 'REQUEST';
  private static RESPONSE_LOG_PREFIX = 'RESPONSE';

  public static logRequest(functionName: string, request: string) {
    this.buildMessageAndLog(this.REQUEST_LOG_PREFIX, functionName, request);
  }

  public static logResponse<T>(functionName: string, response: Observable<T>) {
    response.subscribe(
      res =>
          this.buildMessageAndLog<T>(this.RESPONSE_LOG_PREFIX, functionName, res),
      (err: HttpErrorResponse) =>
          this.buildMessageAndLog<HttpErrorResponse>(this.RESPONSE_LOG_PREFIX, functionName, err)
    );
  }

  private static buildMessageAndLog<T>(logPrefix: string, functionName: string, message: T) {
    const logMessage = `[${logPrefix}] ${functionName}: `;

    console.log(logMessage);
    console.log(message);
  }

}
