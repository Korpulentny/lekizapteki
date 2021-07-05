package pl.uw.mim.io.lekizapteki.excel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "excel.processing")
public class ExcelProcessingProperties {

  private Boolean download;
  private String url;
  private String path;

}
