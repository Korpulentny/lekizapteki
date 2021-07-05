package pl.uw.mim.io.lekizapteki.excel.downloader;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.uw.mim.io.lekizapteki.excel.config.ExcelProcessingProperties;

@Component
@AllArgsConstructor
@Slf4j
public class ExcelDownloader {

  private ExcelProcessingProperties excelProcessingProperties;

  @SneakyThrows
  public void downloadIfUrlRequired() {
    if (excelProcessingProperties.getDownload()) {
      URL url = new URL(excelProcessingProperties.getUrl());

      downloadFromUrl(url);
    }
  }

  @SneakyThrows
  private void downloadFromUrl(URL url) {
    Path filePath = Paths.get(excelProcessingProperties.getPath());

    InputStream inputStream = url.openStream();
    Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
  }
}
