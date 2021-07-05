package pl.uw.mim.io.lekizapteki.models.disease;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DiseaseDto {

  private Long id;
  private String name;

}
