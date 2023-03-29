package virus.card;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BodyPartCardTest {

    @Nested
    class CreateTest {
        @Test
        void 引数に指定した色の健康なからだパーツカードを生成できる() {
            // When:
            var card = BodyPartCard.create(Color.BLUE);

            // Then:
            assertEquals(Color.BLUE, card.color);
            assertEquals(Status.HEALTHY, card.status);
        }
    }
}
