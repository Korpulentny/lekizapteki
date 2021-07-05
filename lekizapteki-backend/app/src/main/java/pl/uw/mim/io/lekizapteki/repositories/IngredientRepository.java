package pl.uw.mim.io.lekizapteki.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.uw.mim.io.lekizapteki.repositories.entities.IngredientEntity;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Long> {

}
