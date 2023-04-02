package virus.field;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import virus.card.Color;
import virus.card.body.BodyPartCard;

class DiscardPileTest {

    @Nested
    class CreateTest {
        @Test
        void 捨て札のカードが0枚で生成される() {
            // When:
            var discardPile = DiscardPile.create();

            // Then:
            assertEquals(0, discardPile.count());
        }
    }

    @Nested
    class PlaceTest {
        @Test
        void 捨て札にカードを置くことができる() {
            // Given:
            var card1 = BodyPartCard.create(Color.BLUE);
            var card2 = BodyPartCard.create(Color.RED);
            var card3 = BodyPartCard.create(Color.GREEN);
            var card4 = BodyPartCard.create(Color.YELLOW);
            var discardPile = DiscardPile.create();

            // When:
            discardPile.place(card1);
            discardPile.place(card2);
            discardPile.place(card3);
            discardPile.place(card4);

            // Then:
            assertEquals(4, discardPile.count());
            assertEquals(Color.BLUE, discardPile.evilCards().get(0).color());
            assertEquals(Color.RED, discardPile.evilCards().get(1).color());
            assertEquals(Color.GREEN, discardPile.evilCards().get(2).color());
            assertEquals(Color.YELLOW, discardPile.evilCards().get(3).color());
        }
    }
}
