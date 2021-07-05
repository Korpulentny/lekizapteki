package mappers.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import pl.uw.mim.io.lekizapteki.excel.parser.models.Medicine;
import pl.uw.mim.io.lekizapteki.excel.parser.utils.MedicineParser;

public class MedicineEntityMapperTest {

  private static final Map<String, List<String>> correctSplitsOfNameAndFormAndDose = Map.of(
      "Tritace 2,5 Comb, tabl., 2,5+12,5 mg", List.of("Tritace 2,5 Comb", "tabl.", "2,5+12,5 mg"),
      "Ampril HD, tabl., 5+25 mg", List.of("Ampril HD", "tabl.", "5+25 mg"),
      "Delmuno 2,5, tabl. powl., 2,5+2,5 mg", List.of("Delmuno 2,5", "tabl. powl.", "2,5+2,5 mg"),
      "Astrium, tabletki powlekane, 10 mg", List.of("Astrium", "tabletki powlekane", "10 mg"),
      "Oprymea, tabl. o przedł. uwalnianiu, 0,26+0,52+1,05 mg", List.of("Oprymea", "tabl. o przedł. uwalnianiu", "0,26+0,52+1,05 mg"),
      "Madopar 62,5 mg, tabl. do sporządzania zawiesiny doustnej, 50+12,5 mg", List.of("Madopar 62,5 mg", "tabl. do sporządzania zawiesiny doustnej", "50+12,5 mg"),
      "Co-Prenessa 4 mg/1,25 mg tabletki, tabl., 4+1,25 mg", List.of("Co-Prenessa 4 mg/1,25 mg tabletki", "tabl.", "4+1,25 mg")
  );

  @Test
  public void shouldSplitNameAndFormAndDose() {

    for (Map.Entry<String, List<String>> entry : correctSplitsOfNameAndFormAndDose.entrySet()) {
      shouldSplitCorrectly(entry.getKey(), entry.getValue());
    }
  }

  private void shouldSplitCorrectly(String nameAndFormAndDose, List<String> correctSplit) {
    List<String> split = new ArrayList<>();

    Medicine medicineMock = makeMedicineMock(nameAndFormAndDose);
    MedicineParser medicineParser = new MedicineParser();

    medicineParser.parseMedicine(medicineMock);

    split.add(medicineParser.getName());
    split.add(medicineParser.getForm());
    split.add(medicineParser.getDose());

//      System.out.println("split = " + split);
//      System.out.println("correct = " + correctSplit);

    assertEquals(correctSplit.size(), split.size());
    for (int i = 0; i < correctSplit.size(); i++) {
      assertEquals(split.get(i), correctSplit.get(i));
    }
  }

  private Medicine makeMedicineMock(String nameAndFormAndDose) {
    Medicine medicine = new Medicine();

    medicine.setNameAndFormAndDose(nameAndFormAndDose);

    return medicine;
  }
}
