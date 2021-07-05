package pl.uw.mim.io.lekizapteki.excel;

import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.io.lekizapteki.excel.downloader.ExcelDownloader;
import pl.uw.mim.io.lekizapteki.excel.parser.ParseExcelToDatabase;

@Component
@AllArgsConstructor
public class ExcelProcessingInvoker {

  private ExcelDownloader excelDownloader;
  private ParseExcelToDatabase parseExcelToDatabase;

  @PostConstruct
  public void invoke() {
    excelDownloader.downloadIfUrlRequired();
    parseExcelToDatabase.parse();
  }
}
