package pl.uw.mim.io.lekizapteki.mappers.dto;

import java.math.BigDecimal;
import lombok.experimental.UtilityClass;
import pl.uw.mim.io.lekizapteki.models.medicine.detailed.PricingDto;
import pl.uw.mim.io.lekizapteki.profitability.MedicineProfitabilityRateResolver;
import pl.uw.mim.io.lekizapteki.repositories.entities.PricingEntity;

@UtilityClass
public class PricingDtoMapper {

  public PricingDto map(PricingEntity pricingEntity, BigDecimal profitabilityRate) {
    return PricingDto.builder()
        .tradePrice(pricingEntity.getTradePrice())
        .salePrice(pricingEntity.getSalePrice())
        .retailPrice(pricingEntity.getRetailPrice())
        .totalFunding(pricingEntity.getTotalFunding())
        .chargeFactor(pricingEntity.getChargeFactor())
        .refund(pricingEntity.getRefund())
        .isLumpSum(pricingEntity.getIsLumpSum())
        .isFree(pricingEntity.getIsFree())
        .profitabilityRate(profitabilityRate)
        .build();
  }
}
