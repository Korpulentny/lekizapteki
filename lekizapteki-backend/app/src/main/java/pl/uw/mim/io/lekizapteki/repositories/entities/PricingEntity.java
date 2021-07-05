package pl.uw.mim.io.lekizapteki.repositories.entities;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "PRICING")
public class PricingEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private BigDecimal tradePrice;
  private BigDecimal salePrice;
  private BigDecimal retailPrice;
  private BigDecimal totalFunding;
  private BigDecimal chargeFactor;
  private BigDecimal refund;
  private Boolean isLumpSum;
  private Boolean isFree;

}
