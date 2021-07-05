package pl.uw.mim.io.lekizapteki.repositories;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.uw.mim.io.lekizapteki.repositories.entities.DiseaseEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.DoseEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.IngredientEntity;
import pl.uw.mim.io.lekizapteki.repositories.entities.MedicineEntity;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Long> {

  List<MedicineEntity> findAllByDisease(DiseaseEntity disease);

  Optional<MedicineEntity> findByEanAndDisease(String ean, DiseaseEntity disease);

  List<MedicineEntity> findAllByDiseaseIn(List<DiseaseEntity> diseases);
}
