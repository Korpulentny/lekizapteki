package pl.uw.mim.io.lekizapteki.services;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.io.lekizapteki.mappers.entity.DiseaseEntityMapper;
import pl.uw.mim.io.lekizapteki.exceptions.NoSuchDiseaseException;
import pl.uw.mim.io.lekizapteki.repositories.DiseaseRepository;
import pl.uw.mim.io.lekizapteki.repositories.entities.DiseaseEntity;

@Service
@AllArgsConstructor
public class DiseaseService {

  private DiseaseRepository diseaseRepository;

  public List<DiseaseEntity> getAllDiseases() {
    return diseaseRepository.findAll();
  }

  public DiseaseEntity getDiseaseWithIdOrThrow(Long diseaseId) {
    return diseaseRepository
        .getDiseaseEntityById(diseaseId)
        .orElseThrow(NoSuchDiseaseException::new);
  }

  public DiseaseEntity getDiseaseWithNameOrCreateNew(String name) {
    return diseaseRepository
        .findFirstByName(name)
        .orElseGet(() -> createDiseaseWithName(name));
  }

  private DiseaseEntity createDiseaseWithName(String name) {
    DiseaseEntity diseaseEntity = DiseaseEntityMapper.map(name);

    return diseaseRepository
        .save(diseaseEntity);
  }
}
