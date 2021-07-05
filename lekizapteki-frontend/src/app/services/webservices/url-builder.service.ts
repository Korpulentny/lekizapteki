export class UrlBuilder {

  private static URL_AND_PARAMETERS_DELIMITER = '?';
  private static PARAMETERS_DELIMITER = '&';

  private url: string;
  private params: string[];

  private constructor(origin: string) {
    this.url = origin;
    this.params = [];
  }

  public static builder(origin: string): UrlBuilder {
    return new UrlBuilder(origin);
  }

  public addPath(path: string): UrlBuilder {
    this.url = this.url.concat(`/${path}`);

    return this;
  }

  public addParam(name: string, value: string): UrlBuilder {
    this.params.push(`${name}=${value}`);

    return this;
  }

  public buildUrl(): string {
    if (this.params.length > 0) {
      this.concatParamsToUrl();
    }

    return this.url;
  }

  private concatParamsToUrl() {
    this.url = this.url
      .concat(UrlBuilder.URL_AND_PARAMETERS_DELIMITER)
      .concat(this.concatParamsToString());
  }

  private concatParamsToString(): string {
    return this.params.join(UrlBuilder.PARAMETERS_DELIMITER);
  }

}
