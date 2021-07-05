package pl.uw.mim.io.lekizapteki.models.medicine;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicineDto {

  private String ean;
  private String name;
  private String dose;

}
