package pl.uw.mim.io.lekizapteki.repositories.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "MEDICINE")
public class MedicineEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String ean;
  private String name;

  @OneToMany(mappedBy = "medicine")
  private Set<IngredientEntity> ingredients = new HashSet<>();

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "form_id", nullable = false)
  private FormEntity form;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @JoinColumn(name = "disease_id", nullable = false)
  private DiseaseEntity disease;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "package_id", nullable = false)
  private PackageEntity pack;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "pricing_id", nullable = false)
  private PricingEntity pricing;
}
