package pl.uw.mim.io.lekizapteki.excel.parser.models;

import lombok.Data;

@Data
public class Medicine {

  private String ingredient;
  private String nameAndFormAndDose;
  private String pack;
  private String ean;
  private String salePrice;
  private String tradePrice;
  private String retailPrice;
  private String totalFunding;
  private String disease;
  private String chargeFactor;
  private String refund;
}
