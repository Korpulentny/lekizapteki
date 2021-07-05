package pl.uw.mim.io.lekizapteki.mappers.entity;

import lombok.Builder;
import pl.uw.mim.io.lekizapteki.excel.parser.utils.BigDecimalConverter;
import pl.uw.mim.io.lekizapteki.repositories.entities.PricingEntity;

@Builder
public class PricingEntityMapper {

  private String salePrice;
  private String tradePrice;
  private String retailPrice;
  private String totalFunding;
  private String chargeFactor;
  private String refund;
  private Boolean isLumpSum;
  private Boolean isFree;

  public PricingEntity map() {

    return PricingEntity.builder()
        .salePrice(BigDecimalConverter.priceToBigDecimal(salePrice))
        .tradePrice(BigDecimalConverter.priceToBigDecimal(tradePrice))
        .retailPrice(BigDecimalConverter.priceToBigDecimal(retailPrice))
        .totalFunding(BigDecimalConverter.priceToBigDecimal(totalFunding))
        .chargeFactor(BigDecimalConverter.chargeFactorToBigDecimal(chargeFactor))
        .refund(BigDecimalConverter.priceToBigDecimal(refund))
        .isLumpSum(isLumpSum)
        .isFree(isFree)
        .build();
  }
}
