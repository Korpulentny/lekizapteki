package pl.uw.mim.io.lekizapteki.models.medicine.detailed;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PricingDto {

  private BigDecimal tradePrice;
  private BigDecimal salePrice;
  private BigDecimal retailPrice;
  private BigDecimal totalFunding;
  private BigDecimal chargeFactor;
  private BigDecimal refund;
  private Boolean isLumpSum;
  private Boolean isFree;
  private BigDecimal profitabilityRate;

}
