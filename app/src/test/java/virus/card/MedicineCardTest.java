package virus.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MedicineCardTest {
    @Nested
    class CreateTest {
        @Test
        void 引数に指定した色の薬カードを生成できる() {
            // When:
            var card = MedicineCard.create(Color.BLUE);

            // Then:
            assertEquals(Color.BLUE, card.color());
        }
    }
}
