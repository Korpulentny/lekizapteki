package pl.uw.mim.io.lekizapteki.controlers;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.uw.mim.io.lekizapteki.DiseaseApi;
import pl.uw.mim.io.lekizapteki.models.disease.DiseaseDto;
import pl.uw.mim.io.lekizapteki.usecases.GetDiseases;

@RestController
@AllArgsConstructor
public class DiseaseController implements DiseaseApi {

  private GetDiseases getDiseases;

  @Override
  public List<DiseaseDto> getDiseases() {
    return getDiseases.execute();
  }

}
