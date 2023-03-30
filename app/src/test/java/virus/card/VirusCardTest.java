package virus.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class VirusCardTest {
    @Nested
    class CreateTest {
        @Test
        void 引数に指定した色のウィルスカードを生成できる() {
            // When:
            var card = VirusCard.create(Color.BLUE);

            // Then:
            assertEquals(Color.BLUE, card.color());
        }
    }
}
