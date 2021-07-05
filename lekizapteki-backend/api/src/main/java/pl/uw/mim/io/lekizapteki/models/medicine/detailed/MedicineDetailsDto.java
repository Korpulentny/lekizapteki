package pl.uw.mim.io.lekizapteki.models.medicine.detailed;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicineDetailsDto {

  private String ean;
  private String name;
  private String activeIngredient;
  private String dose;
  private String form;
  private PricingDto pricing;

}
